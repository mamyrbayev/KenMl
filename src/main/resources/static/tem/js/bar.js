new Chart(document.getElementById("bar-chart"), {
    type: 'bar',
    data: {
        labels: ["", ""],
        datasets: [
            {
                label: "Population (millions)",
                backgroundColor: ["#9A9B9C", "#9A9B9C",],
                data: overallForYears.filter(e => e.year != 2020).map(e => e.overall),

            }
        ]
    },
    options: {
        animation: {
            duration: 0,
            onComplete: function() {
                var chartInstance = this.chart,
                    ctx = chartInstance.ctx;

                Chart.defaults.global.defaultFontSize = 25;
                Chart.defaults.global.defaultFontStyle = 'Bold'
                ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                ctx.textAlign = 'center';
                ctx.textBaseline = 'bottom';

                this.data.datasets.forEach(function(dataset, i) {
                    var meta = chartInstance.controller.getDatasetMeta(i);
                    meta.data.forEach(function(bar, index) {
                        var data = dataset.data[index];
                        ctx.fillText(data, bar._model.x, bar._model.y - 5);
                    });
                });
            }
        },
        legend: {display: false},
        title: {
            display: true,
        },
        responsive: false,
        scales: {
            xAxes: [{
                scaleLabel: {
                    display: true,
                    labelString: '',
                },
                gridLines: {
                    display: false,
                    drawBorder: false //<- set this
                },
                ticks: {
                    fontColor: "#152731",
                    fontSize: '26',
                    fontWeight: 700
                },
            }],
            yAxes: [{
                gridLines: {
                    display: true,
                    drawBorder: true //<- set this
                },
                ticks: {
                    display: false
                },
            }]
        }
    },


});
