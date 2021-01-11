let barItemsElement = document.getElementById("bar-items")

let arrTop = topTenList;


function topLoad() {
    let str = '';
    for (let i = 0; i < arrTop.length; i++) {
        let overallVolumeAndPrice = arrTop[i].overallVolumeAndPrice;
        if (!overallVolumeAndPrice) {
            overallVolumeAndPrice = {};
        }
        const volume = overallVolumeAndPrice.volumeStr;
        const measurer = overallVolumeAndPrice.measurer ? overallVolumeAndPrice.measurer : '';
        const price = overallVolumeAndPrice.priceStr;

        if (i === arrTop.length - 1) {
            str += '<div class="bar-item">' +
                '<div class="bar-item--text">' +
                '<div class="bar-item-text--inner">' +
                '<div class="bar-item-text-left">' +
                '<p class="bar-item-top"><span class="bar-number number-3">' + Number(i + 1) + '</span></p>' +
                `<p>Объем: ${volume} ${measurer}<br>Цена: ${price}₸</p>` +
                '</div>' +
                '<div class="bar-item-text-right">'
                + arrTop[i].name +
                '</div>' +
                '</div>' +
                '<footer class="footer footer-top"> <div class="line"></div><p style="text-align: right" class = "page-num">2 из <span class="overall-pages">100</span></p></footer>' +
                '</div>' +
                '<div class="bar-line bar-' + Number(i + 1) + '"></div>' +

                '</div>'
            break;
        }
        str += '<div class="bar-item">' +
            '<div class="bar-item--text">' +
            '<div class="bar-item-text--inner">' +
            '<div class="bar-item-text-left">' +
            '<p class="bar-item-top"><span class="bar-number number-3">' + Number(i + 1) + '</span></p>' +
            `<p>Объем: ${volume} ${measurer}<br>Цена: ${price}₸ </p>` +
            '</div>' +
            '<div class="bar-item-text-right">'
            + arrTop[i].name +
            '</div>' +
            '</div>' +

            '</div>' +
            '<div class="bar-line bar-' + Number(i + 1) + '"></div>' +

            '</div>'

    }
    barItemsElement.innerHTML = str
}

topLoad()
