let table = document.getElementById("pt1")
let table1 = document.getElementById("pt2")
let table2 = document.getElementById("pt3")
let arr = [
	{
		city: "Алматы",
		building: 142,
		done: 325,
		sum: 487
	},
	{
		city: "Нур-Султан",
		building: 242,
		done: 234,
		sum: 486
	},
	{
		city: "Шымкент",
		building: 14,
		done: 230,
		sum:244
	},
	{
		city: "Алматы",
		building: 142,
		done: 325,
		sum: 487
	},
	{
		city: "Нур-Султан",
		building: 242,
		done: 234,
		sum: 486
	},
	{
		city: "Шымкент",
		building: 14,
		done: 230,
		sum:244
	}

]


function progressDataLoading(){
	let str  = '';
	for(let i = 0; i<arr.length; i++){
		str+= '<div class="progress-line">' +
                    '<p>'+arr[i].city+'</p>'+
                    '<div class="progress-wrap1">'+
                    	'<p>'+arr[i].building+'</p>'+
                    	'<div class="progress">'+
                      	  '<div class="progress-bar" role="progressbar" style="width:'+(arr[i].building/arr[i].sum)*100+'%;" aria-valuenow="'+arr[i].building+'" aria-valuemin="0" aria-valuemax="'+arr[i].sum+'"></div>'+
                   		'</div>'+
                    '</div>'+
                    '<div class="progress-wrap2">'+
                    	'<p>'+arr[i].done+'</p>'+
                    	'<div class="progress">'+
                      	  '<div class="progress-bar" role="progressbar" style="width:'+(arr[i].done/arr[i].sum)*100+'%;" aria-valuenow="'+arr[i].done+'" aria-valuemin="0" aria-valuemax="'+arr[i].sum+'"></div>'+
                   		 '</div>'+
                   	'</div>'+
                    '<p class="progress-sum">'+arr[i].sum+'</p>'+
                '</div>'
	}
	table.innerHTML = str
}

function progressDataLoading2(){
	let str  = '';
	for(let i = 0; i<arr.length; i++){
		str+= '<div class="progress-line">' +
                    '<p>'+arr[i].city+'</p>'+
                    '<div class="progress-wrap3">'+
                    	'<p>'+arr[i].building+'</p>'+
                    	'<div class="progress">'+
                      	  '<div class="progress-bar" role="progressbar" style="width:'+(arr[i].building/arr[i].sum)*100+'%;" aria-valuenow="'+arr[i].building+'" aria-valuemin="0" aria-valuemax="'+arr[i].sum+'"></div>'+
                   		'</div>'+
                    '</div>'+
                    '<p class="progress-sum">'+arr[i].sum+'</p>'+
                '</div>'
	}
	table1.innerHTML = str
}
function progressDataLoading3(){
	let str  = '';
	for(let i = 0; i<arr.length; i++){
		str+= '<div class="progress-line">' +
                    '<p>'+arr[i].city+'</p>'+
                    '<div class="progress-wrap3">'+
                    	'<p>'+arr[i].building+'</p>'+
                    	'<div class="progress">'+
                      	  '<div class="progress-bar" role="progressbar" style="width:'+(arr[i].building/arr[i].sum)*100+'%;" aria-valuenow="'+arr[i].building+'" aria-valuemin="0" aria-valuemax="'+arr[i].sum+'"></div>'+
                   		'</div>'+
                    '</div>'+
                    '<p class="progress-sum">'+arr[i].sum+'</p>'+
                '</div>'
	}
	table2.innerHTML = str
}

progressDataLoading();
progressDataLoading2();
progressDataLoading3();