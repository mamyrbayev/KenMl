let asd = document.getElementById("bar-items")

let arrTop = [
	{
		percentage: "25%",
        text: 'Щебень из плотных горных пород для строительных работ М600, фракция 20-40 мм СТ РК 1284-2004',
	},
	{
		percentage: "25%",
		text: 'Щебень из плотных горных пород для строительных работ М600, фракция 20-40 мм СТ РК 1284-2004',
    },
    {
		percentage: "25%",
		text: 'Щебень из плотных горных пород для строительных работ М600, фракция 20-40 мм СТ РК 1284-2004',
    },
    {
		percentage: "25%",
		text: 'Щебень из плотных горных пород для строительных работ М600, фракция 20-40 мм СТ РК 1284-2004',
    },
    {
		percentage: "25%",
		text: 'Щебень из плотных горных пород для строительных работ М600, фракция 20-40 мм СТ РК 1284-2004',
    },
    {
		percentage: "25%",
		text: 'Щебень из плотных горных пород для строительных работ М600, фракция 20-40 мм СТ РК 1284-2004',
    },
    {
		percentage: "25%",
		text: 'Щебень из плотных горных пород для строительных работ М600, фракция 20-40 мм СТ РК 1284-2004',
    },
    {
		percentage: "25%",
		text: 'Щебень из плотных горных пород для строительных работ М600, фракция 20-40 мм СТ РК 1284-2004',
    },
    {
		percentage: "25%",
		text: 'Черепица гибкая двухслойная стандарт-класса на основе стеклохолста, пропитанного окисленным битумом, с цветовой посыпкой из базальта, для кровель с уклоном от 12° и до отрицательных углов, толщиной 6 мм ГОСТ 32806-2014',
    },
    {
		percentage: "25%",
		text: 'Щебень из плотных горных пород для строительных работ М600, фракция 20-40 мм СТ РК 1284-2004',
	}
]


function topLoad(){
	let str  = '';
	for(let i = 0; i<arrTop.length; i++){
        if (i === arrTop.length -1) {
            str += '<div class="bar-item">'+
            '<div class="bar-item--text">'+
            '<div class="bar-item-text--inner">'+
                '<div class="bar-item-text-left">' +
                    '<p class="bar-item-top">Топ<span class="bar-number number-'+ Number(i+1)+'">'+ Number(i+1)+'</span></p>'+
                    '<p>'+arrTop[i].percentage+"от рынка"+ '</p>'+
                    '</div>'+
                    '<div class="bar-item-text-right">'
             +arrTop[i].text+
         '</div>'+
             '</div>'+
             '<footer class="footer footer-top"> <span>Потребность строительного рынка на 2020-2021гг.</span><div class="line"></div><p>Разработано на основании автоматического анализа KENML смет системой GENRO (АО Институт цифровой техники и технологий)</p></footer>'+
         '</div>'+
            '<div class="bar-line bar-'+ Number(i+1)+ '"></div>'+

        '</div>'
        break;
        }
		str+= '<div class="bar-item">'+
                '<div class="bar-item--text">'+
                '<div class="bar-item-text--inner">'+
                    '<div class="bar-item-text-left">' +
                        '<p class="bar-item-top">Топ<span class="bar-number number-'+Number(i+1)+'">'+Number(i+1)+'</span></p>'+
                        '<p>'+arrTop[i].percentage+"от рынка"+ '</p>'+
                        '</div>'+
                        '<div class="bar-item-text-right">'
                 +arrTop[i].text+
             '</div>'+
                 '</div>'+

             '</div>'+
                '<div class="bar-line bar-'+ Number(i+1)+ '"></div>'+

            '</div>'

	}
	asd.innerHTML = str
}

topLoad()
