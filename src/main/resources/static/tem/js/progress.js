let table = document.getElementById("pt1")
let table1 = document.getElementById("pt2")
let table2 = document.getElementById("pt3")

let overallForYearByRegions2020 = [];
let overallForYearByRegions2021 = [];
let overallForYearByRegions2022 = [];

overallForYearByRegions.forEach(e => {
    if (e.year == 2020) {
        overallForYearByRegions2020 = e.overallByRegions;
    } else if (e.year == 2021) {
        overallForYearByRegions2021 = e.overallByRegions;
    } else if (e.year == 2022) {
        overallForYearByRegions2022 = e.overallByRegions;
    }
})

function progressDataLoading(arr) {
    let str = '';
    for (let i = 0; i < arr.length; i++) {
        str += '<div class="progress-line">' +
            '<p>' + arr[i].regionName + '</p>' +
            '<div class="progress-wrap1">' +
            '<p>' + arr[i].underConstruction + '</p>' +
            '<div class="progress">' +
            '<div class="progress-bar" role="progressbar" style="width:' + (arr[i].underConstruction / arr[i].overall) * 100 + '%;" aria-valuenow="' + arr[i].underConstruction + '" aria-valuemin="0" aria-valuemax="' + arr[i].overall + '"></div>' +
            '</div>' +
            '</div>' +
            '<div class="progress-wrap2">' +
            '<p>' + arr[i].completed + '</p>' +
            '<div class="progress">' +
            '<div class="progress-bar" role="progressbar" style="width:' + (arr[i].completed / arr[i].overall) * 100 + '%;" aria-valuenow="' + arr[i].completed + '" aria-valuemin="0" aria-valuemax="' + arr[i].overall + '"></div>' +
            '</div>' +
            '</div>' +
            '<p class="progress-sum">' + arr[i].overall + '</p>' +
            '</div>'
    }
    table.innerHTML = str
}

function progressDataLoading2(arr, table) {
    let str = '';
    for (let i = 0; i < arr.length; i++) {
        str += '<div class="progress-line">' +
            '<p>' + arr[i].regionName + '</p>' +
            '<div class="progress-wrap3">' +
            '<p>' + arr[i].underConstruction + '</p>' +
            '<div class="progress">' +
            '<div class="progress-bar" role="progressbar" style="width:' + (arr[i].underConstruction / arr[i].overall) * 100 + '%;" aria-valuenow="' + arr[i].underConstruction + '" aria-valuemin="0" aria-valuemax="' + arr[i].overall + '"></div>' +
            '</div>' +
            '</div>' +
            '<p class="progress-sum">' + arr[i].overall + '</p>' +
            '</div>'
    }
    table.innerHTML = str
}


progressDataLoading(overallForYearByRegions2020);
progressDataLoading2(overallForYearByRegions2021, table1);
progressDataLoading2(overallForYearByRegions2022, table2);