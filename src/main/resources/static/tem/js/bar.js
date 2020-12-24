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
            duration: 0
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
