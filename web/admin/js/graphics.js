
var ventas = {
    labels: ["Enero", "Febrero", "Marzo"],
    datasets: [
        {
            label: "Pedidos",
            fillColor: "rgba(244, 67, 54, 0.40)",
            strokeColor: "#f44336",
            pointColor: "#f44336",
            pointStrokeColor: "#f4433",
            pointHighlightFill: "transparent",
            pointHighlightStroke: "#f44336",
            data: [10, 15, 12]
        },
    ]
};

var algo = document.getElementById("grafico").getContext("2d");
var myLineChart = new Chart(algo).Line(ventas, {
    scaleFontColor: "#fff",
    scaleShowVerticalLines: false,
    responsive: true,
    scaleGridLineColor: "rgba(255,255,255,0.4)"
});


var productos = {
    labels: ["Pegante", "Vinilo", "Anticorrosivo", "PVA", "Esmalte", "Estuco"],
    datasets: [
        {
            label: "My First dataset",
            fillColor: "rgba(220,220,220,0.2)",
            strokeColor: "rgba(220,220,220,1)",
            pointColor: "rgba(220,220,220,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(220,220,220,1)",
            data: [10, 15, 12, 19, 3, 25]
        },
    ]
};
var ctx2 = document.getElementById("grafico2").getContext("2d");
var myRadarChart2 = new Chart(ctx2).Radar(productos, {
    pointLabelFontColor: "#fff",
    pointDot: true,
    angleLineColor: "#fff"
});

var barras = [
    {
        value: 10,
        color: "#f44336",
        highlight: "#c62828",
        label: "Pegante"
    },
    {
        value: 15,
        color: "#3f51b5 ",
        highlight: "#303f9f ",
        label: "Vinilo"
    },
    {
        value: 12,
        color: "#00e676  ",
        highlight: "#00c853 ",
        label: "Anticorrosivo"
    },
    {
        value: 19,
        color: "#ffcc80",
        highlight: "#ffa726 ",
        label: "PVA"
    },
    {
        value: 3,
        color: "#fff ",
        highlight: "#eceff1",
        label: "Esmalte"
    },
    {
        value: 25,
        color: "#90a4ae",
        highlight: "#607d8b",
        label: "Estuco"
    }
]
var ctx3 = document.getElementById("grafico3").getContext("2d");
var myDoughnutChart = new Chart(ctx3).Doughnut(barras, {
    segmentStrokeColor: "transparent"

});