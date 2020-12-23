new Chart(document.getElementById("chart-bar"), {
    type: 'bar',
    data: {
        labels: ["Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"],
        datasets: [
            {
                label: "",
                backgroundColor: "#FFB877",
                data: [3500, 500, 0, 3600, 3100, 600, 1700, 2200, 0, 1000, 1600, 3200],

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
                barThickness: 30,
            }]
        }
    },


});

new Chart(document.getElementById("chart-bar2"), {
    type: 'bar',
    data: {
        labels: ["Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"],
        datasets: [
            {
                label: "",
                backgroundColor: "#FFB877",
                data: [3500, 500, 0, 3600, 3100, 600, 1700, 2200, 0, 1000, 1600, 3200],

            }
        ]
    },
    options: {
        legend: {display: false},
        title: {
            display: true,
        },
        responsive: false,
        scales: {
            xAxes: [{
                barThickness: 30,
            }]
        }
    },


});

new Chart(document.getElementById("chart-bar3"), {
    type: 'bar',
    data: {
        labels: ["Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"],
        datasets: [
            {
                label: "Population (millions)",
                backgroundColor: "#FFB877",
                data: [3500, 500, 0, 3600, 3100, 600, 1700, 2200, 0, 1000, 1600, 3200],

            }
        ]
    },
    options: {
        legend: {display: false},
        title: {
            display: true,
        },
        responsive: false,
        scales: {
            xAxes: [{
                barThickness: 30,
            }]
        }
    },


});
new Chart(document.getElementById("chart-bar4"), {
    type: 'bar',
    data: {
        labels: ["Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"],
        datasets: [
            {
                label: "Population (millions)",
                backgroundColor: "#FFB877",
                data: [3500, 500, 0, 3600, 3100, 600, 1700, 2200, 0, 1000, 1600, 3200],

            }
        ]
    },
    options: {
        legend: {display: false},
        title: {
            display: true,
        },
        responsive: false,
        scales: {
            xAxes: [{
                barThickness: 30,
            }]
        }
    },


});

let val = document.getElementById("chart-values")
let val2 = document.getElementById("chart-values2")
let val3 = document.getElementById("chart-values3")
let val4 = document.getElementById("chart-values4")

function putVal() {
    let str = ''
    for (let i = 0; i < 12; i++) {
        str += '<div class="chart-val">' +
            '<p>3500 m3</p>' +
            '<p>3 500 000 тг</p>' +
            '</div>'
    }

    val.innerHTML = str
}

function putVal2() {
    let str = ''
    for (let i = 0; i < 12; i++) {
        str += '<div class="chart-val">' +
            '<p>3500 m3</p>' +
            '<p>3 500 000 тг</p>' +
            '</div>'
    }

    val2.innerHTML = str
}

function putVal3() {
    let str = ''
    for (let i = 0; i < 12; i++) {
        str += '<div class="chart-val">' +
            '<p>3500 m3</p>' +
            '<p>3 500 000 тг</p>' +
            '</div>'
    }

    val3.innerHTML = str
}

function putVal4() {
    let str = ''
    for (let i = 0; i < 12; i++) {
        str += '<div class="chart-val">' +
            '<p>3500 m3</p>' +
            '<p>3 500 000 тг</p>' +
            '</div>'
    }

    val4.innerHTML = str
}

putVal()
putVal2()
putVal3()
putVal4()
