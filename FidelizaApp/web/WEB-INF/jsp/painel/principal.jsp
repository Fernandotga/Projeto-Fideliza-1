<head>
    <title><fmt:message key="app.title"/> | <fmt:message key="app.painel" /> </title>
</head>
<body>
    <fieldset>
        <div id="legend">
            <legend class=""> 
                <fmt:message key="painel.painel"/> 
                <small> <fmt:message key="painel.home"/> </small>
            </legend>  
        </div>  

        <h4>
            <div class="controls">
                <span class="badge badge-warning">
                    <label class="control-label"> <h4> ${qnt} </h4> </label>
                </span>
                Clientes/Fidelizados
            </div>
        </h4>

        <div class="control-group">
            <div class="controls">
                <label class="control-label"><b> Leitura x Dias </b></label>
                <div id="visualization" style="width: 940px; height: 300px; border: 1px solid #ccc; float: right;"></div>
            </div>
        </div>
    </fieldset>

    <script type="text/javascript">
        google.load('visualization', '1', {packages: ['corechart']});
    </script>
    <script type="text/javascript">
        function drawVisualization() {
            // Create and populate the data table.
//            var data = google.visualization.arrayToDataTable([
//                ['x', 'QTD'],
//                ['0', 1],
//                ['1', 2],
//                ['2', 4],
//                ['3', 8],
//                ['4', 7],
//                ['5', 7],
//                ['6', 8]
//            ]);
            var data = google.visualization.arrayToDataTable();
            data.addColumn('string', 'x');
            data.addColumn('number', 'Quantidade');

            var rows = [];

            <c:forEach items="${dadosGrafico}" var="i">
                rows.push(['${i[0]}', ${i[1]}]);
            </c:forEach> 

            data.addRows(rows);

            // Create and draw the visualization.
            new google.visualization.LineChart(document.getElementById('visualization')).
                    draw(data, {curveType: "function",
                width: 940, height: 300,
                vAxis: {maxValue: 75}}
            );
        }
        google.setOnLoadCallback(drawVisualization);
    </script>
</body>
