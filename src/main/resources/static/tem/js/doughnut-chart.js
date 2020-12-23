var randomScalingFactor = function() {
    return Math.round(Math.random() * 100);
};
console.log(window.chartColors)
var config = {
    type: 'doughnut',
    data: {
        datasets: [{                  
            data: [
                randomScalingFactor(),
                randomScalingFactor(),
                randomScalingFactor(),
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
        labels: [
            
        ]
    },
    options: {
        animation: {
            duration: 0
        },
        responsive: false,
        cutoutPercentage: 80
    }
};

window.onload = function() {
    var ctx = document.getElementById('myChart').getContext('2d');
    window.myPie = new Chart(ctx, config);
};
