const potrebnostiElement = document.getElementById("potrebnosti");



new Chart(document.getElementById("chart-bar2"), {
    type: "bar",
    data: {
        labels: [
            "Янв",
            "Фев",
            "Мар",
            "Апр",
            "Май",
            "Июн",
            "Июл",
            "Авг",
            "Сен",
            "Окт",
            "Ноя",
            "Дек",
        ],
        datasets: [
            {
                label: "",
                backgroundColor: "#FFB877",
                data: [3500, 500, 0, 3600, 3100, 600, 1700, 2200, 0, 1000, 1600, 3200],
            },
        ],
    },
    options: {
        legend: {display: false},
        title: {
            display: true,
        },
        responsive: false,
        scales: {
            xAxes: [
                {
                    barThickness: 30,
                },
            ],
        },
    },
});

new Chart(document.getElementById("chart-bar3"), {
    type: "bar",
    data: {
        labels: [
            "Янв",
            "Фев",
            "Мар",
            "Апр",
            "Май",
            "Июн",
            "Июл",
            "Авг",
            "Сен",
            "Окт",
            "Ноя",
            "Дек",
        ],
        datasets: [
            {
                label: "Population (millions)",
                backgroundColor: "#FFB877",
                data: [3500, 500, 0, 3600, 3100, 600, 1700, 2200, 0, 1000, 1600, 3200],
            },
        ],
    },
    options: {
        legend: {display: false},
        title: {
            display: true,
        },
        responsive: false,
        scales: {
            xAxes: [
                {
                    barThickness: 30,
                },
            ],
        },
    },
});
new Chart(document.getElementById("chart-bar4"), {
    type: "bar",
    data: {
        labels: [
            "Янв",
            "Фев",
            "Мар",
            "Апр",
            "Май",
            "Июн",
            "Июл",
            "Авг",
            "Сен",
            "Окт",
            "Ноя",
            "Дек",
        ],
        datasets: [
            {
                label: "Population (millions)",
                backgroundColor: "#FFB877",
                data: [3500, 500, 0, 3600, 3100, 600, 1700, 2200, 0, 1000, 1600, 3200],
            },
        ],
    },
    options: {
        legend: {display: false},
        title: {
            display: true,
        },
        responsive: false,
        scales: {
            xAxes: [
                {
                    barThickness: 30,
                },
            ],
        },
    },
});