            // prepare the data
            var data = generatedata(30);

            var source =
            {
                localdata: data,
                datatype: "array",
                datafields:
                [
                    {name:'id',index:'id', width:55},
					{ name: 'maSP', type: 'string' },
                    { name: 'tenSP', type: 'string' },
                    { name: 'quantity', type: 'number' },
                    { name: 'price', type: 'number' },
					{ name: 'giamgia', type: 'number' },
					{ name: 'total', type: 'number' }
                ],
                updaterow: function (rowid, rowdata) {
                    // synchronize with the server - send update command
					
                }
            };

            var dataAdapter = new $.jqx.dataAdapter(source);

            // initialize jqxGrid
            $("#jqxgrid").jqxGrid(
            {
                width: 1090,
                source: dataAdapter,     
                showstatusbar: true,
                statusbarheight: 30,
                editable: true,
                showaggregates: true,
            //    selectionmode: 'singlecell',
				showtoolbar: true,
				autoheight: false,
				height: 250,
                rendertoolbar: function (toolbar) {
                    var me = this;
                    var container = $("<div style='margin: 5px;'></div>");
                    toolbar.append(container);
                    container.append('<input style="margin-left: 5px;" id="deleterowbutton" type="button" value="  Xoa  " />');
                    $("#deleterowbutton").jqxButton();
                    
                    // delete row.
                    $("#deleterowbutton").on('click', function () {
                        var selectedrowindex = $("#jqxgrid").jqxGrid('getselectedrowindex');
                        var rowscount = $("#jqxgrid").jqxGrid('getdatainformation').rowscount;
                        if (selectedrowindex >= 0 && selectedrowindex < rowscount) {
                            var id = $("#jqxgrid").jqxGrid('getrowid', selectedrowindex);
                            var commit = $("#jqxgrid").jqxGrid('deleterow', id);
                        }
                    });
                },
				
                columns: [
				  { text: 'STT', columntype: 'textbox', datafield: 'id', width: 50, editable: false, align: 'center' },
				  { text: 'Ma SP', columntype: 'textbox', datafield: 'maSP', width: 170, editable: false, align: 'center' },
                  { text: 'Ten SP', datafield: 'tenSP', columntype: 'textbox', width: 300, editable: false, align: 'center' },
                  { text: 'So Luong', datafield: 'quantity', width: 70, cellsalign: 'right', cellsformat: 'n2', editable: true, align: 'center'
                  },
                  { text: 'Gia', datafield: 'price', cellsalign: 'right', cellsformat: 'c', editable: false, width:100, align: 'center' },
				  { text: 'Giam Gia', datafield: 'giamgia', cellsalign: 'right', cellsformat: 'c', editable: false, width:100, align: 'center' },
				  { text: 'Thanh Tien', datafield: 'total', cellsalign: 'right', cellsformat: 'c', aggregates: ['sum'], align: 'center', editable:false }
                ]
            });
			
			
function generatedata(rowscount, hasNullValues) {
    // prepare the data
    var data = new Array();
    if (rowscount == undefined) rowscount = 100;
    var firstNames =
    [
        "Andrew", "Nancy", 
    ];

    var lastNames =
    [
        "Fuller", "Davolio"
    ];

    var productNames =
    [
        "Black Tea", "Green Tea"
    ];

    var priceValues =
    [
         "15000", "100000"
    ];

    for (var i = 0; i < rowscount; i++) {
        var row = {};
        var productindex = Math.floor(Math.random() * productNames.length);
        var price = parseFloat(priceValues[productindex]);
        var quantity = 1 + Math.round(Math.random() * 10);
		var giamgia = price - 1;
        row["id"] = i + 1;// + 1 
        if (hasNullValues == true) {
            if (productindex % 2 != 0) {
                var random = Math.floor(Math.random() * rowscount);
                row["available"] = i % random == 0 ? null : false;
            }
        }
        row["maSP"] = firstNames[Math.floor(Math.random() * firstNames.length)];
        row["tenSP"] = lastNames[Math.floor(Math.random() * lastNames.length)];
        row["price"] = price;
        row["quantity"] = quantity;
        row["total"] = price * quantity - giamgia;
		row["giamgia"] = giamgia;

        data[i] = row;
    }

    return data;
}
