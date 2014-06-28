<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="chartCateogies" type="java.util.List" required="true"%>

<script type="text/javascript"
  src='https://www.google.com/jsapi?autoload={"modules":[{"name":"visualization","version":"1","packages":["corechart","table"]}]}'>
</script>

<script type="text/javascript">
	$(function() {
		$("#tabchart").tabs({
			collapsible : true,
			activate: function( event, ui ) {
				var firstChartid = $("#hiddenfirstchart_" + ui.newTab.attr("id")).attr("chartid");
				initChart(firstChartid, ui.newTab.attr("id"));
				getChartTableData(firstChartid);
			}
		});
		
		var firstTabId = $('#tabchart').tabs('option','active');
		var selector = '#tabchart > ul > li';
		var chartcategoryid = $(selector).eq(firstTabId).attr('id');
		var firstChartid = $("#hiddenfirstchart_" + chartcategoryid).attr("chartid");
		initChart(firstChartid, chartcategoryid);		
		getChartTableData(firstChartid);
		
		$('#tabchart').tabs('paging', {
			cycle : true,
			follow : true
		});
	});
</script>

<div id="tabchart">
	<ul>
		<c:forEach var="item" items="${ chartCateogies }">
			<li id="${item.chartcategoryid}"><a href="#tab-${item.chartcategoryid}">${ item.categoryname }</a></li>
		</c:forEach>
	</ul>

	<c:forEach var="item" items="${ chartCategories }">
		<div id="tab-${ item.chartcategoryid }" name="${ item.chartcategoryid }">
			<c:if test="${ fn:length(item.listOfChart) > 0 }">
				<input type="hidden" id="hiddenfirstchart_${ item.chartcategoryid }" chartid="${ item.listOfChart[0].chartid }" />			
			</c:if>
			<div class="chart-content">
				<div class="chart-detail">
					<tags:chart chartcategoryid="${ item.chartcategoryid }"><jsp:text /></tags:chart>
				</div>
				
				<tags:accoridionchart chartcategoryid="${ item.chartcategoryid }" listChart="${ item.listOfChart }"><jsp:text /></tags:accoridionchart>
			</div>
		</div>
	</c:forEach>
</div>
<script type="text/javascript">

  var wrapper = new google.visualization.ChartWrapper();

  function initWrapperChart( option, charttype, idchart)
  {
	  wrapper.setContainerId(idchart);
	  wrapper.setChartType(charttype);
	  
	  wrapper.setOptions(option);
  }
  function drawChart(data) {
	  wrapper.setDataTable(data);
	  
      wrapper.draw();
  }
</script>

