const potrebnostiElement = document.getElementById("potrebnosti");

function generatePotrebnosiByCity(id, potrebnost, index) {
    let html = `
     <div class="page-4">
        <section class="count">
            <div class="header-chart-inner">
                <header class="header">
                    <p>StroyMart.<span>kz</span></p>
                </header>
                <div class="header-block">
                    <div class="logo-title-chart">
                        <h3>Потребность</h3>
                        <h4>в стройметериалах
                            в разрезе регионов </h4>
                    </div>
                </div>
            </div>

            <div class="chart-page-title">
                <div class="chart-round">
                    <h3>${index}</h3>
                    <p>${potrebnost.material.mtName}</p>
                </div>
                <div class="chart-img">
                    <img src="/tem/assets/orange-lines.png">
                </div>
            </div>`;
    const localities = potrebnost.localitiesByMatrials;

    localities.forEach((e) => {
        html += generateLocality(e);
    });

    html += `
                <footer class="footer footer-count" style="position: absolute; bottom: 0">
                    <p>Потребность строительного рынка на 2020-2021гг.</p>
                    <div class="line"></div>
                    <p>Разработано на основании автоматического анализа KENML смет системой GENRO (АО Институт цифровой
                        техники и технологий)</p>
                </footer>
            </div>
        </section>
    </div>
    <div class="page-4">
        <section class="count">
            <div class="header-chart-inner" style="margin-bottom: 100px">
                <header class="header">
                    <p>StroyMart.<span>kz</span></p>
                </header>
                <div class="header-block">
                    <div class="logo-title-chart">
                        <div class="chart-page-title2">
                            <div class="chart-round2">
                                <h3>1</h3>
                                <p>Трубы, рукава и шланги и их фитинги из пластмасс</p>
                            </div>
                            <div class="chart-img2">
                                <img src="/tem/assets/chart-l-w.svg">
                            </div>
                        </div>
                    </div>
                    <div class="chart-city-num3" style="margin: 130px 0 50px 0; padding-right: 5%">
                        <h1>Алматы</h1>
                        <div class="chart-vol-price">
                            <div class="chart-vol">
                                <span>Общий объем</span>
                                <p>944 354 м3</p>
                            </div>
                            <div class="chart-price ">
                                <span>Общая стоимость</span>
                                <p>944 354 000 тг</p>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
            <div class="chart-grey-bg">
                <div class="width-90">
                    <div class="chart-city-num">
                        <div class="chart-str">
                            <span>Застройщик</span>
                            <p>BI Group</p>
                        </div>
                        <div class="chart-vol-price">
                            <div class="chart-vol">
                                <span>Общий объем</span>
                                <p>944 354 м3</p>
                            </div>
                            <div class="chart-price ">
                                <span>Общая стоимость</span>
                                <p>944 354 000 тг</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="width-90">
                <div class="chart-wrap">
                    <div class="chart-obj">
                        <p>Объект <span>1</span></p>
                        <p>ID 9128389</p>
                    </div>
                    <div class="chart-p">
                        <p>Строительство многоквартирных жилых домов в микрорайоне "Мадениет" в Алатауском районе города
                            Алматы, участок №834/2 (без благоустройства и внутриплощадочных инженерных сетей)</p>
                    </div>
                </div>

                <div class="chart-bars-block">
                    <div class="chart-bar-title">
                        <h1>2020</h1>
                        <div class="chart-vol-price">
                            <div class="chart-vol">
                                <p>944 354 м3</p>
                            </div>
                            <div class="chart-price ">
                                <p>944 354 000 тг</p>
                            </div>
                        </div>
                    </div>
                    <div class="chart-bar">
                        <canvas id="chart-bar3" width="1300" height="215"></canvas>
                        <div class="chart-values" id="chart-values3">
                            <!-- <div class="chart-val">
                                <p>3500 m3</p>
                                <p>3 500 000 тг</p>
                            </div> -->
                        </div>
                    </div>
                </div>
            </div>

            <div class="chart-grey-bg">
                <div class="width-90">
                    <div class="chart-city-num" style="border-bottom: 1px solid white">
                        <div class="chart-str">
                            <span>Застройщик</span>
                            <p>BI Group</p>
                        </div>
                        <div class="chart-vol-price">
                            <div class="chart-vol">
                                <span>Общий объем</span>
                                <p>944 354 м3</p>
                            </div>
                            <div class="chart-price ">
                                <span>Общая стоимость</span>
                                <p>944 354 000 тг</p>
                            </div>
                        </div>
                    </div>
                    <div class="chart-city-num2">
                        <div class="chart-str2 str1">
                            <span>Телефон</span>
                            <p>8 777 170 70 70</p>
                        </div>
                        <div class="chart-str2 str2">
                            <span>E-mail</span>
                            <p>Contact@Bi.group</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="width-90">

                <div class="chart-bars-block">
                    <div class="chart-bar-title">
                        <h1>2021</h1>
                        <div class="chart-vol-price">
                            <div class="chart-vol">
                                <p>944 354 м3</p>
                            </div>
                            <div class="chart-price ">
                                <p>944 354 000 тг</p>
                            </div>
                        </div>
                    </div>
                    <div class="chart-bar">
                        <canvas id="chart-bar4" width="1300" height="215"></canvas>
                        <div class="chart-values" id="chart-values4">
                            <!-- <div class="chart-val">
                                <p>3500 m3</p>
                                <p>3 500 000 тг</p>
                            </div> -->
                        </div>
                    </div>

                </div>

                <footer class="footer footer-count" style="position: absolute; bottom: 0">
                    <p>Потребность строительного рынка на 2020-2021гг.</p>
                    <div class="line"></div>
                    <p>Разработано на основании автоматического анализа KENML смет системой GENRO (АО Институт цифровой
                        техники и технологий)</p>
                </footer>
            </div>
        </section>
    </div>
    `;
    return html;
}

function generateLocality(locality) {
    let html = `
    <div class="width-90">
                <div class="chart-city-num">
                    <h1>${locality.localities.name}</h1>
                    <div class="chart-vol-price">
                        <div class="chart-vol">
                            <span>Общий объем</span>
                            <p>${locality.overallVolume} м3</p>
                        </div>
                        <div class="chart-price ">
                            <span>Общая стоимость</span>
                            <p>${locality.overallPrice} тг</p>
                        </div>
                    </div>
                </div>
            </div>
    `;
    locality.companiesList.forEach((e) => {
        html += generateCompany(e);
    });
    return html;
}

function generateCompany(company) {
    let html = `<div class="chart-grey-bg">
                <div class="width-90">
                    <div class="chart-city-num" style="border-bottom: 1px solid white">
                        <div class="chart-str">
                            <span>Застройщик</span>
                            <p>${company.title}</p>
                        </div>
                        <div class="chart-vol-price">
                            <div class="chart-vol">
                                <span>Общий объем</span>
                                <p>${company.overallVolume} м3</p>
                            </div>
                            <div class="chart-price ">
                                <span>Общая стоимость</span>
                                <p>${company.overallPrice} тг</p>
                            </div>
                        </div>
                    </div>
                    <div class="chart-city-num2">
                        <div class="chart-str2 str1">
                            <span>Телефон</span>
                            <p>${
        company.directorPhone
            ? company.directorPhone
            : "Нет телефона"
    }</p>
                        </div>
                        <div class="chart-str2 str2">
                            <span>E-mail</span>
                            <p>${
        company.emailAddress
            ? company.emailAddress
            : "Нет email"
    }</p>
                        </div>
                    </div>
                </div>
            </div>
            `;
    company.objectsDto.forEach((e) => {
        html += generateObject(e);
    });
    return html;
}

function generateObject(object) {
    let html = `<div class="width-90">
    
                <div class="chart-wrap">
                    <div class="chart-obj">
                        <p>Объект <span>1</span></p>
                        <p>ID ${object.id}</p>
                    </div>
                    <div class="chart-p">
                        <p>${object.name}</p>
                    </div>
                </div>
               
    `;
    object.objectInYearDtos.forEach((e) => {
        html += generateObjectInYear(e);
    });
    html += "</div>";
    return html;
}

function generateObjectInYear(objectInYear) {
    let html = `<div class="chart-bars-block">
                    <div class="chart-bar-title">
                        <h1>${objectInYear.year}</h1>
                        <div class="chart-vol-price">
                            <div class="chart-vol">
                                <p>${objectInYear.overallVolume} м3</p>
                            </div>
                            <div class="chart-price ">
                                <p>${objectInYear.overallPrice} тг</p>
                            </div>
                        </div>
                    </div>
                    <div class="chart-bar">
                        <canvas id="chart-bar" width="1300" height="215"></canvas>
                        <div class="chart-values" id="chart-values">
                            `;

    html += `</div></div></div>`;
    return html;
}

window.onload = function () {
    /* Add your logic here */
    for (let i = 0; i < potrebnostis.length; i++) {
        potrebnostiElement.innerHTML += generatePotrebnosiByCity(
            i,
            potrebnostis[i],
            i + 1
        );
    }
};

new Chart(document.getElementById("chart-bar"), {
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
        animation: {
            duration: 0,
        },
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

let val = document.getElementById("chart-values");
let val2 = document.getElementById("chart-values2");
let val3 = document.getElementById("chart-values3");
let val4 = document.getElementById("chart-values4");

function putVal() {
    let str = "";
    for (let i = 0; i < 12; i++) {
        str +=
            '<div class="chart-val">' +
            "<p>3500 m3</p>" +
            "<p>3 500 000 тг</p>" +
            "</div>";
    }

    val.innerHTML = str;
}

function putVal2() {
    let str = "";
    for (let i = 0; i < 12; i++) {
        str +=
            '<div class="chart-val">' +
            "<p>3500 m3</p>" +
            "<p>3 500 000 тг</p>" +
            "</div>";
    }

    val2.innerHTML = str;
}

function putVal3() {
    let str = "";
    for (let i = 0; i < 12; i++) {
        str +=
            '<div class="chart-val">' +
            "<p>3500 m3</p>" +
            "<p>3 500 000 тг</p>" +
            "</div>";
    }

    val3.innerHTML = str;
}

function putVal4() {
    let str = "";
    for (let i = 0; i < 12; i++) {
        str +=
            '<div class="chart-val">' +
            "<p>3500 m3</p>" +
            "<p>3 500 000 тг</p>" +
            "</div>";
    }

    val4.innerHTML = str;
}

//
// putVal()
// putVal2()
// putVal3()
// putVal4()
