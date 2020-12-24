const valueFor2020 = overallForYears[0];
const pieNumOverall = document.getElementById('pieNumOverall');
const pieCompleted = document.getElementById('pieCompleted');
const pieNumeUnderConstruction = document.getElementById('pieNumeUnderConstruction');

pieNumOverall.innerHTML = valueFor2020.overall;
pieCompleted.innerHTML = valueFor2020.completed;
pieNumeUnderConstruction.innerHTML = valueFor2020.underConstruction;

var config = {
    type: 'doughnut',
    data: {
        datasets: [{
            data: [
                valueFor2020.overall,
                valueFor2020.completed,
                valueFor2020.underConstruction,
            ],
            backgroundColor: [

                '#000000',
                '#05C43C',
                '#9A9B9C',
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

var ctx = document.getElementById('myChart').getContext('2d');
window.myPie = new Chart(ctx, config);

const chartNumberElement = document.getElementById('chartNumbers')

overallForYears.forEach(e => {
    if (e.year != 2020) {
        chartNumberElement.innerHTML += `
            <div class="bar-img">
                <img src="/tem/assets/lines.svg">
            </div>
            <div class="chart-number">
                <p>${e.year}</p>
                <span>год</span>
            </div>
    `;
    }
});