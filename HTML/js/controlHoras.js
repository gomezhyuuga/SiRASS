$(document).ready(function() {
	init();
});

function init() {
	$.datepicker.setDefaults(
	{
		showAnim: "blind",
		dateFormat: "dd/mm/yy",
		changeMonth: true,
		changeYear: true
	});
	$.datepicker.regional['es'];
	setDatePickers();
	
	$('#addHour').click(function() {
		addHour();
	});
	
	$('button:reset').click(function() {
		$('#rows-horas').html('');
	});
}

function setDatePickers() {
	// Datos del reporte
	var fInicio = $('#fInicio');
	var fTermino = $('#fTermino');
	
	// Registro de hroas
	var hEntrada1 = $('#hEntrada1');
	var hSalida1 = $('#hSalida1');
	var fecha1 = $('#fecha1');
	
	fInicio.datepicker();
	fTermino.datepicker();
	fecha1.datepicker();
	hEntrada1.timepicker({
		onSelect: function(dateText, inst) {
			calcularHoras(dateText, inst);
		}
	});
	hSalida1.timepicker({
		onSelect: function(dateText, inst) {
			calcularHoras(dateText, inst);
		}
	});
}

function calcularHoras(dateText, inst) {
	console.log("Calculando horas....");
	console.log(dateText);
	console.log("hour: " + inst.hour);
	console.log("min: " + inst.minute);
	// 1) Obtener fila del elemento cambiado
	var index = inst.$input.attr('data-reg');
	var hEntrada = $('#hEntrada' + index);
	var hSalida = $('#hSalida' + index);
	var spanSuma = $('span#suma' + index);
	var inputAcum = $('input#acum' + index);
	var inputSumaHrs = $('input#sumaHrs' + index);
	var inputSumaMins = $('input#sumaMins' + index);
	// 2)) Validación de que no estén vacíos
	if (hEntrada.val() != "" && hSalida.val() != "") {
		// 4) Obtener Dates
		var dSalida = hSalida.datepicker('getDate');
		var dEntrada = hEntrada.datepicker('getDate');
		// 3) Validación de que hsalida > hentrada
		if (dSalida.getTime() > dEntrada.getTime()) {
			// 4) Calcular diferencia de horas
			console.log('Calculando diferencia');
			var oDiff = get_time_difference(dEntrada, dSalida);
			console.log(oDiff);
			var acum = oDiff.hours + ':' + oDiff.minutes;
			spanSuma.text(acum);
			inputAcum.val(acum);
			inputSumaHrs.val(oDiff.hours);
			inputSumaMins.val(oDiff.minutes);
		} else {
			spanSuma.text('');
			inputAcum.val('');
			inputSumaHrs.val('');
			inputSumaMins.val('');
		}
	}
}

function calcular(text, inst) {
	var time1 = $('#date1');
	var time2 = $('#date2');
	var difEl = $('#dif');
	if (time1.val() != "" && time2.val() != "") {
		time1Date = time1.datepicker('getDate');
		time2Date = time2.datepicker('getDate');
		console.log(time1Date);
		console.log(time2Date);
		
		if (time1Date.getTime() < time2Date.getTime()) {
			console.log("hora2 es mayor a hora1. Obteniendo diferencia...");
			var dif = get_time_difference(time1Date, time2Date);
			console.log(dif);
			difEl.text(dif.hours + ":" + dif.minutes);
		} else {
			difEl.text('');
		}
	} else {
		difEl.text('');
	}
}

function addHour() {
	var rows = $('#rows-horas');
	var index;
	if (rows.contents('tr:last') != null && rows.contents('tr:last') != undefined
		&& rows.contents('tr:last').length != 0 ) {
		index = rows.contents('tr:last').attr('id').substring(3);
	} else {
		index = 0;
	}
	index++;
	console.log(index);
	console.log('Creando row...');
	var newRow = createRow(index);
	console.log(newRow);
	rows.append(newRow);
}

function delRow(el) {
	var row = $('#row' + el);
	console.log(row);
	row.remove();
}

function createRow(index) {
	// Create elements
	// table tags
	var tr = $('<tr>');
	var tdNum = $('<td>');
	var tdFecha = $('<td>');
	var tdHEnt = $('<td>');
	var tdHSal = $('<td>');
	var tdAcum = $('<td>');
	var tdElim = $('<td>');
	// inputs
	var inputFecha = $('<input type="text">');
	var inputHEnt = $('<input type="text">');
	var inputHSal = $('<input type="text">');
	var spanSuma = $('<span>');
	var inputAcum = $('<input type="hidden">');
	var inputSumaHrs = $('<input type="hidden">');
	var inputSumaMins = $('<input type="hidden">');
	var inputElim = $('<input type="button">');
	
	// Set attrs
	tr.attr('id', 'row' + index);
	inputFecha.attr({
		id: 'fecha' + index,
		name: 'fecha' + index,
		class: "input-small",
		placeholder: 'dd/mm/aa'
	});
	inputHEnt.attr({
		id: 'hEntrada' + index,
		name: 'hEntrada' + index,
		class: "input-small",
		placeholder: 'hh:mm'
	});
	inputHEnt.attr('data-reg', index);
	inputHSal.attr({
		id: 'hSalida' + index,
		name: 'hSalida' + index,
		class: "input-small",
		placeholder: 'hh:mm'
	});
	inputHSal.attr('data-reg', index);
	spanSuma.attr('id', 'suma' + index);
	inputAcum.attr({
		name: 'acum' + index,
		id: 'acum' + index
	});
	inputSumaHrs.attr({
		name: 'sumaHrs' + index,
		id: 'sumaHrs' + index
	});
	inputSumaMins.attr({
		name: 'sumaMins' + index,
		id: 'sumaMins' + index
	});
	inputSumaHrs.attr('data-tipo', 'hr');
	inputSumaMins.attr('data-tipo', 'min');
	inputElim.attr({
		id: 'elim' + index,
		onclick: 'delRow(' + index + ')',
		class: "btn btn-mini btn-danger",
		value: 'Eliminar'
	});
	
	// Active date&time pickers
	inputFecha.datepicker();
	inputHEnt.timepicker({});
	inputHSal.timepicker({});
	
	// Insert content
	tdNum.text(index);
	tdFecha.append(inputFecha);
	tdHEnt.append(inputHEnt);
	tdHSal.append(inputHSal);
	tdAcum.append(spanSuma);
	tdAcum.append(inputAcum);
	tdAcum.append(inputSumaHrs);
	tdAcum.append(inputSumaMins);
	tdElim.append(inputElim);
	
	// Add content
	tr.data('counter', index);
	tr.append(tdNum);
	tr.append(tdFecha);
	tr.append(tdHEnt);
	tr.append(tdHSal);
	tr.append(tdAcum);
	tr.append(tdElim);
	
	return tr;
}

// Simple function to calculate time difference between 2 Javascript date objects
function get_time_difference(earlierDate,laterDate)
{
       var nTotalDiff = laterDate.getTime() - earlierDate.getTime();
       var oDiff = new Object();
 
       oDiff.days = Math.floor(nTotalDiff/1000/60/60/24);
       nTotalDiff -= oDiff.days*1000*60*60*24;
 
       oDiff.hours = Math.floor(nTotalDiff/1000/60/60);
       nTotalDiff -= oDiff.hours*1000*60*60;
 
       oDiff.minutes = Math.floor(nTotalDiff/1000/60);
       nTotalDiff -= oDiff.minutes*1000*60;
 
       oDiff.seconds = Math.floor(nTotalDiff/1000);
 
       return oDiff;
}