const potrebnostiElement = document.getElementById("potrebnosti");

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


// class PotrebnostiManager {
//
//     constructor(potrebnost) {
//         this.potrebnost = potrebnost;
//     }
//
//     pageWrap(html) {
//         return `
//         <div class="page-4">
//             <section class="count">
//             ${html}
//             </section>
//         </div>
//         `;
//     }
//
//     getMaterial() {
//         return this.potrebnost.material;
//     }
//
//     generateFirstHeader() {
//         var material = this.getMaterial();
//         return `
//             <div class="header-chart-inner">
//                 <header class="header">
//                     <p>StroyMart.<span>kz</span></p>
//                 </header>
//                 <div class="header-block">
//                     <div class="logo-title-chart">
//                         <h3>Потребность</h3>
//                         <h4>в стройметериалах
//                             в разрезе регионов </h4>
//                     </div>
//                 </div>
//             </div>
//
//             <div class="chart-page-title">
//                 <div class="chart-round">
//                     <h3>1</h3>
//                     <p>{{material.mtName}}</p>
//                 </div>
//                 <div class="chart-img">
//                     <img src="/tem/assets/orange-lines.png">
//                 </div>
//             </div>
//         `;
//     }
//
//     generateByLocality(locality) {
//         return `
//                 <div class="width-90 chart-city-num">
//             <h1>${locality.localities.name}</h1>
//             <div class="chart-vol-price">
//                 <div class="chart-vol">
//                     <span>Общий объем</span>
//                     <p>${locality.overallVolume} м3</p>
//                 </div>
//                 <div class="chart-price ">
//                     <span>Общая стоимость</span>
//                     <p>${locality.overallPrice} тг</p>
//                 </div>
//             </div>
//         </div>
//         `;
//     }
//
//     generateBuilder(company) {
//         return `
//         <div class="chart-grey-bg">
//             <div class="width-90">
//                 <div class="chart-city-num" style="border-bottom: 1px solid white">
//                     <div class="chart-str">
//                         <span>Застройщик</span>
//                         <p>${company.title}</p>
//                     </div>
//                     <div class="chart-vol-price">
//                         <div class="chart-vol">
//                             <span>Общий объем</span>
//                             <p>${company.overallVolume} м3</p>
//                         </div>
//                         <div class="chart-price ">
//                             <span>Общая стоимость</span>
//                             <p>${company.overallPrice} тг</p>
//                         </div>
//                     </div>
//                 </div>
//                 <div class="chart-city-num2">
//                     <div class="chart-str2 str1">
//                         <span>Телефон</span>
//                         <p>${company.directorPhone ? company.directorPhone : 'Нет номера телефона'}</p>
//                     </div>
//                     <div class="chart-str2 str2">
//                         <span>E-mail</span>
//                         <p>${company.emailAddress ? company.emailAddress : 'Нет почтового адреса'}</p>
//                     </div>
//                 </div>
//             </div>
//         </div>
//         `;
//     }
//
//     generateObject(object) {
//         return `
//         <div class="chart-wrap">
//             <div class="chart-obj">
//                 <p>Объект <span>1</span></p>
//                 <p>ID ${object.id}</p>
//             </div>
//             <div class="chart-p">
//                 <p>${object.objectName}</p>
//             </div>
//         </div>
//         `;
//     }
//
//     generateCardStatInYear(objectInYear) {
//         const year = objectInYear.year ? objectInYear.year : '    ';
//         return `
//         <div class="chart-bars-block">
//             <div class="chart-bar-title">
//                 <h1>${year.substr(0, 2)}<span class="text-orange">${year.substr(2, 2)}</span></h1>
//                 <div class="chart-vol-price">
//                     <div class="chart-vol">
//                         <p>${objectInYear.overallVolume} м3</p>
//                     </div>
//                     <div class="chart-price ">
//                         <p>${objectInYear.overallPrice} тг</p>
//                     </div>
//                 </div>
//             </div>
//             <div class="chart-bar">
//                 <canvas id="chart-bar" width="1300" height="215"></canvas>
//                 <div class="chart-values" id="chart-values">
//                     <div class="chart-val">
//                         <p>3500 m3</p>
//                         <p>3 500 000 тг</p>
//                     </div>
//                 </div>
//             </div>
//         </div>
//         `;
//     }
//
//     generateFooter() {
//         return `
//         <footer class="footer footer-count" style="position: absolute; bottom: 0; left: 80px;">
//             <p>Потребность строительного рынка на 2020-2021гг.</p>
//             <div class="line"></div>
//             <p>Разработано на основании автоматического анализа KENML смет системой GENRO (АО Институт цифровой
//                 техники и технологий)</p>
//         </footer>
//         `;
//     }
//
//
//     generateSecondHeader(locality) {
//         return `
//         <div class="header-chart-inner" style="margin-bottom: 100px">
//             <header class="header">
//                 <p>StroyMart.<span>kz</span></p>
//             </header>
//             <div class="header-block">
//                 <div class="logo-title-chart">
//                     <div class="chart-page-title2">
//                         <div class="chart-round2">
//                             <h3>1</h3>
//                             <p>${this.getMaterial().mtName}</p>
//                         </div>
//                         <div class="chart-img2">
//                             <img th:src="/tem/assets/chart-l-w.svg">
//                         </div>
//                     </div>
//                 </div>
//                 <div class="chart-city-num3" style="margin: 130px 0 50px 0; padding-right: 5%">
//                     <h1>${locality.localities.name}</h1>
//                     <div class="chart-vol-price">
//                         <div class="chart-vol">
//                             <span>Общий объем</span>
//                             <p>${locality.overallVolume} м3</p>
//                         </div>
//                         <div class="chart-price ">
//                             <span>Общая стоимость</span>
//                             <p>${locality.overallPrice} тг</p>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         </div>
//         `;
//     }
//
//     generateSecondBuilder(company) {
//         return `
//         <div class="chart-grey-bg">
//             <div class="width-90 chart-city-num">
//                 <div class="chart-str">
//                     <span>Застройщик</span>
//                     <p>${company.title}</p>
//                 </div>
//                 <div class="chart-vol-price">
//                     <div class="chart-vol">
//                         <span>Общий объем</span>
//                         <p>${company.overallVolume} м3</p>
//                     </div>
//                     <div class="chart-price ">
//                         <span>Общая стоимость</span>
//                         <p>${company.overallPrice} тг</p>
//                     </div>
//                 </div>
//             </div>
//         </div>
//         `;
//     }
//
//     generateSecondBuilderGrey(company) {
//         return `
//         <div class="chart-grey-bg">
//             <div class="width-90">
//                 <div class="chart-city-num" style="border-bottom: 1px solid white">
//                     <div class="chart-str">
//                         <span>Застройщик</span>
//                         <p>${company.title}</p>
//                     </div>
//                     <div class="chart-vol-price">
//                         <div class="chart-vol">
//                             <span>Общий объем</span>
//                             <p>${company.overallVolume} м3</p>
//                         </div>
//                         <div class="chart-price ">
//                             <span>Общая стоимость</span>
//                             <p>${company.overallPrice} тг</p>
//                         </div>
//                     </div>
//                 </div>
//                 <div class="chart-city-num2">
//                      <div class="chart-str2 str1">
//                         <span>Телефон</span>
//                         <p>${company.directorPhone ? company.directorPhone : 'Нет номера телефона'}</p>
//                     </div>
//                     <div class="chart-str2 str2">
//                         <span>E-mail</span>
//                         <p>${company.emailAddress ? company.emailAddress : 'Нет почтового адреса'}</p>
//                     </div>
//                 </div>
//             </div>
//         </div>
//         `;
//     }
//
//     getResultHtml() {
//         var html = '';
//
//         for (var i = 0; i < this.potrebnost.localitiesByMatrials.length; i++) {
//             var innerHtml = '';
//             var locality = this.potrebnost.localitiesByMatrials[i];
//             innerHtml += this.generateFirstHeader();
//             innerHtml += this.generateSecondHeader(locality)
//             innerHtml += this.generateByLocality(locality);
//
//             for (var j = 0; j < locality.companiesList.length; j++) {
//                 var company = locality.companiesList[j];
//                 innerHtml += this.generateBuilder(company);
//                 for (var z = 0; z < company.objectsDto.length; z++) {
//                     var object = company.objectsDto[z];
//                     innerHtml += this.generateObject(object);
//                 }
//             }
//
//             html = this.pageWrap(innerHtml);
//         }
//         return html;
//     }
// }
//
// var html = '';
// potrebnostis.forEach(e => {
//     var potrManager = new PotrebnostiManager(e);
//     html += potrManager.getResultHtml();
// })
// potrebnostiElement.innerHTML = html;