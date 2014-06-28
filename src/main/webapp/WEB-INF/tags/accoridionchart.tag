<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="chartcategoryid" required="true"%>
<%@attribute name="listChart" type="java.util.List" required="true"%>

<c:choose>
	<c:when test="${ fn:length(listChart) == 1 }">
		<div id="charttable-${ listChart[0].chartid }" class="control-chart-block">
			<jsp:text />
		</div>
	</c:when>
	<c:otherwise>
		<div id="accrodionchart-${ chartcategoryid }" class="control-chart-block">
			<c:forEach var="item" items="${ listChart }">
				<h3 id="${ item.chartid }">${ item.chartname }</h3>
				<div id="charttable-${ item.chartid }" style="display:none;height:20px;" class="control-chart" >
					<jsp:text />
				</div>
			</c:forEach>
		</div>
	</c:otherwise>
</c:choose>

<script>
	function initChart(chartid, divid) {
		
		var chartInfo = $.ajax({
			url : "charts/chartoption?chartid=" + chartid,
			dataType : "json",
			async : false
		}).responseText;

		var jchartInfo = JSON.parse(chartInfo);
		
		var options = {
			ChartType : "ColumnChart",
			hAxis : {
				title : 'Cool',
				titleTextStyle : {
					color : 'red'
				}
			},
			vAxis : {
				title : 'Cool',
				titleTextStyle : {
					color : 'red'
				},
				minValue:0,
				maxValue:10000
			},
			animation : {
				duration : 1000,
				easing : 'out',
			},
			colors : ['black','#004411','red']
		};

		var div =  "interactive_chart_" + divid;
		
		initWrapperChart(jchartInfo, jchartInfo.ChartType, div);
		
		//var chartid = $(this).find("h3").attr('id');
		var jsonZeroData = $.ajax({
			url : "charts/chartdata?chartid=" + chartid + "&charttype=toan&isFirstLoad=true",
			dataType : "json",
			async : false
		}).responseText;
		
		var zerodata = new google.visualization.DataTable(jsonZeroData);
		
		drawChart(zerodata);
		
		var jsonData = $.ajax({
			url : "charts/chartdata?chartid=" + chartid + "&charttype=toan",
			dataType : "json",
			async : false
		}).responseText;
		
		var data = new google.visualization.DataTable(jsonData);
		
		drawChart(data);
	}
	$("#accrodionchart-${ chartcategoryid }").accordion({
		activate : function(event, ui) {
			var div = "${ chartcategoryid }";
			initChart(ui.newHeader.attr("id"), div);
			getChartTableData(ui.newHeader.attr("id"));
		}
	});
	
	
	function XMLToString(oXML) {
		//code for IE
		if (window.ActiveXObject) {
			var oString = oXML.xml;
			return oString;
		}
		// code for Chrome, Safari, Firefox, Opera, etc.
		else {
			return (new XMLSerializer()).serializeToString(oXML);
		}
	}

	function getChartTableData(chartid)
	{
		$.ajax({
			url : 'charts/gettabledata?chartid=' + chartid,
			type : 'GET',
			async : false,
			success : function(data) {
				
				$('#charttable-' + chartid).html(data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR + " : " + textStatus + " : " + errorThrown);
			}
		});
		
		$(".control-chart").css("height", '');
	}
</script>
