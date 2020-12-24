var valueFor2020 = overallForYears.find(e => e.year == 2020);

var pieNumOverall = document.getElementById('pieNumOverall');
var pieCompleted = document.getElementById('pieCompleted');
var pieNumeUnderConstruction = document.getElementById('pieNumeUnderConstruction');

pieNumOverall.innerHTML = valueFor2020.overall;
pieCompleted.innerHTML = valueFor2020.completed;
pieNumeUnderConstruction.innerHTML = valueFor2020.underConstruction;

var config = {
    type: 'doughnut',
    data: {
        datasets: [{
            data: [
                valueFor2020.completed,
                valueFor2020.underConstruction,
                valueFor2020.overall,
            ],
            backgroundColor: [

                '#9A9B9C',
                '#000000',
                '#05C43C'
                // window.chartColors.green,
                // window.chartColors.blue,
            ],
            label: 'Dataset 1'
        }],
        labels: []
    },
    options: {
        animation: {
            duration: 0
        },
        responsive: false,
        cutoutPercentage: 80
    }
};

window.onload = function () {
    var ctx = document.getElementById('myChart').getContext('2d');
    window.myPie = new Chart(ctx, config);
};
