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
                    <label class="control-label"> <h3>150</h3> </label>
                </span>
                Clientes/Fidelizados
            </div>
        </h4>

        <div class="control-group">
            <div class="controls">
                <label class="control-label"><b> Leitura x Dias </b></label>
                <div id="visualization" style="width: 940px; height: 200px; border: 1px solid #ccc; float: right;"></div>
            </div>
            <div class="controls">
                <label class="control-label"><b> Idade X Sexo </b></label>
                <div id="visualization1" style="width: 940px; height: 300px; border: 1px solid #ccc; float: right;"></div>
            </div>
        </div>
    </fieldset>

    <script type="text/javascript">
        google.load('visualization', '1', {packages: ['corechart']});
    </script>
    <script type="text/javascript">
        function drawVisualization() {
            // Create and populate the data table.
            var data = google.visualization.arrayToDataTable([
                ['x', 'QTD'],
                ['0', 1],
                ['1', 2],
                ['2', 4],
                ['3', 8],
                ['4', 7],
                ['5', 7],
                ['6', 8]
            ]);

            // Create and draw the visualization.
            new google.visualization.LineChart(document.getElementById('visualization')).
                    draw(data, {curveType: "function",
                width: 940, height: 200,
                vAxis: {maxValue: 75}}
            );
        }
        google.setOnLoadCallback(drawVisualization);
    </script>

    <script type="text/javascript">
        function drawVisualization() {
            // Create and populate the data table.
            var data = google.visualization.arrayToDataTable([
                ['Year', 'Homens', 'Mulheres'],
                ['2003', 1336060, 400361],
                ['2004', 1538156, 366849],
                ['2005', 1576579, 440514],
                ['2006', 1600652, 434552],
                ['2007', 1968113, 393032],
                ['2008', 1901067, 517206]
            ]);

            // Create and draw the visualization.
            new google.visualization.BarChart(document.getElementById('visualization1')).
                    draw(data,
                    {
                        width: 940, height: 300}
            );
        }


        google.setOnLoadCallback(drawVisualization);
    </script>
</body>
