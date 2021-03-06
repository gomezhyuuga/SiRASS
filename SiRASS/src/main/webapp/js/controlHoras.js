$(document).ready(function() {
	init();
});

function init() {
	$.datepicker.setDefaults(
	{
		showAnim: "scale",
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
		calcularTotal();
	});
	
	$('#help1').tooltip();
	
	$('#autoAdd').click(function(e) {
		e.preventDefault();
		bootbox.prompt("Ingresa la hora de entrada", function(result) {
			var hora = result;
			bootbox.prompt("Ingresa el minuto en el que ingresaste", function(result2) {
				var min = result2;
				smartFill(hora, min);
			});
		});
	});
	
	$.validator.setDefaults({
		debug: true,
		errorContainer: "#feedback",
		errorClass: "invalid",
		wrapper: 'p class="help-block"',
		ignore: "",
		submitHandler: function(form) {
			var opts = {
				success: formSuccess,
				error: formError
			}
			$(form).ajaxSubmit(opts);
		}
	});
	
	$('#form-controlHoras').validate({
		rules: {
			nReporte: {
				required: true,
				min: 1,
				max: 10,
				digits: true
			},
			supervisor: {
				required: true,
				maxlength: 45
			},
			fInicio: {
				required: true,
				dateITA: true
			},
			fTermino: {
				required: true,
				dateITA: true
			},
			fecha1: {
				required: true,
				dateITA: true
			},
			hEntrada1: "required",
			hSalida1: "required",
			acum1: "required",
			hrsReporte: "required",
			minsReporte: "required",
			totalReporte: "required",
			hrsAcumuladas: "required",
			minsAcumulados: "required",
			totalAcumulado: "required"
		},
		messages: {
			hrsReporte: "Debes ingresar registros de horas!",
			minsReporte: "Debes ingresar registros de horas!",
			totalReporte: "Debes ingresar registros de horas!",
			hrsAcumuladas: "Debes ingresar registros de horas!",
			minsAcumulados: "Debes ingresar registros de horas!",
			totalAcumulado: "Debes ingresar registros de horas!",
			acum1: "Horas incorrectas",
			fTermino: {
				dateITA: "Formato de fecha inválido"
			},
			fInicio: {
				dateITA: "Formato de fecha inválido"
			}
		}
	});
}

function formSuccess(responseText, statusText, xhr, $form) {
	if (responseText == "1") {
		var title = "Envío exitoso del reporte";
		var text = "Tu control de horas ha sido enviado correctamente. Espera a que un supervisor ";
		text += "lo revise y valide tus horas.";
		createAlert(title, text, '#feedback', 'alert-success');
		bootbox.dialog('<p class="lead">' + text + '<p>', [{
		    "label" : "Cerrar",
		    "class" : "btn-primary btn-success",
		    "callback": reloadPage
		}]);
	} else {
		formError();
	}
}

function formError(responseText, statusText, xhr, $form) {
	var title = "Envío de reporte fallido";
	var text = "Ha ocurrido un error enviando tu reporte, por favor revisa que los datos que ";
	text += "estás enviando sean correctos.";
	createAlert(title, text, '#feedback', 'alert-error');
	bootbox.dialog('<p class="lead">' + text + '<p>', [{
	    "label" : "Cerrar",
	    "class" : "btn-primary btn-danger"
	}]);
}

function smartFill(h, m) {
	console.log("Llenado inteligente...");
	var horaEntrada = 8;
	var minEntrada = 0;
	if (h != NaN && h != null && h != 0) {
		horaEntrada = h;
	}
	if (m != NaN && m != null) {
		minEntrada = m;
	}
	var horaSalida;
	// Pedir horas
	/****/
	calcularTotal();
	var rows = $('#rows-horas');
	var index;
	if (rows.contents('tr:last') != null && rows.contents('tr:last') != undefined
		&& rows.contents('tr:last').length != 0 ) {
		index = rows.contents('tr:last').attr('id').substring(3);
	} else {
		index = 0;
	}
	index++;
	var hrsReporte = Number($('.hrsReporte').text());
	console.log(index);
	console.log(hrsReporte);
	
	horaSalida = Number(horaEntrada) + Number(4);
	
	// Estilizar horas
	var hEntStr = "";
	var hSalStr = "";
	if (horaEntrada < 10) {
		hEntStr += "0" + horaEntrada;
	} else {
		hEntStr += "" + horaEntrada;
	}
	if (horaSalida < 10) {
		hSalStr = "0" + horaSalida;
	} else {
		hSalStr = "" + horaSalida;
	}
	hEntStr += ":";
	hSalStr += ":";
	if (minEntrada < 10) {
		hEntStr += "0" + Number(minEntrada);
		hSalStr += "0" + Number(minEntrada);
	} else {
		hEntStr += "" + minEntrada;
		hSalStr += "" + minEntrada;
	}
	console.log(hEntStr);
	console.log(hSalStr);
	
	// Establecer fechas
	var date = new Date();
	var anio = date.getFullYear();
	var mes = date.getMonth();
	var dia = 1;
	if (mes == 0) {
		mes = 11;
	} else {
		mes--;
	}
	date = new Date(anio, mes, dia, 0, 0, 0, 0);
	console.log(dia + "." + mes + "." + anio);
	console.log(date);
	
	var stop = false;
	while (hrsReporte < 80 && !stop) {
		var fecha = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
		if (date.getDay() != 0 && date.getDay() != 6) {
			addHour();
			$('#hEntrada' + index).val(hEntStr);
			$('#hSalida' + index).val(hSalStr);
			$('#fecha' + index).val(fecha);
			$('#suma' + index).text('4:00');
			$('#acum' + index).val('4:00');
			$('#sumaHrs' + index).val('4');
			$('#sumaMins' + index).val('0');
			hrsReporte += 4;
			index++;
			calcularTotal();
		}
		date.setDate(date.getDate() + 1);
		if (date.getDate() >= 30 ) {
			stop = true;
		}
	}
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
	// console.log("Calculando horas....");
	// console.log(dateText);
	// console.log("hour: " + inst.hour);
	// console.log("min: " + inst.minute);
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
			// console.log('Calculando diferencia');
			var oDiff = get_time_difference(dEntrada, dSalida);
			// console.log(oDiff);
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
	calcularTotal();
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
	// console.log(index);
	// console.log('Creando row...');
	var newRow = createRow(index);
	// console.log(newRow);
	rows.append(newRow);
}

function delRow(el) {
	var row = $('#row' + el);
	// console.log(row);
	row.remove();
	calcularTotal();
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
	inputHEnt.timepicker({
		onSelect: function(dateText, inst) {
			calcularHoras(dateText, inst);
		}
	});
	inputHSal.timepicker({
		onSelect: function(dateText, inst) {
			calcularHoras(dateText, inst);
		}
	});
	
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

function calcularTotal() {
	var hrsAcumEl = $('input[data-tipo="hr"]');
	var minsAcumEl = $('input[data-tipo="min"]');
	var hrsAcum = 0;
	var minsAcum = 0;
	hrsAcumEl.each(function(index) {
		var el = $(this);
		if (el.val() != "") {
			hrsAcum += Number(el.val());
		}
	});
	minsAcumEl.each(function(index) {
		var el = $(this);
		if (el.val() != "") {
			minsAcum += Number(el.val());
		}
	});
	if (minsAcum >= 60) {
		hrsAcum += Math.floor(minsAcum / 60);
		minsAcum = minsAcum % 60;
	}
	$('.hrsReporte').text(hrsAcum);
	$('.minsReporte').text(minsAcum);
	$('.totalReporte').text(hrsAcum + ':' + minsAcum);
	$('input[name="hrsReporte"]').val(hrsAcum);
	$('input[name="minsReporte"]').val(minsAcum);
	$('input[name="totalReporte"]').val(hrsAcum + ':' + minsAcum);
	
	// Calcular acumulado
	var hrsAnteriores = $('.hrsAnteriores');
	var minsAnteriores = $('.minsAnteriores');
	var hrsSuma = Number(hrsAnteriores.text());
	var minsSuma = Number(minsAnteriores.text());
	hrsSuma += Number(hrsAcum);
	minsSuma += Number(minsAcum);
	$('input[name="hrsAcumuladas"]').val(hrsSuma);
	$('input[name="minsAcumulados"]').val(minsSuma);
	
	$('.hrsAcumuladas').text(hrsSuma);
	$('.minsAcumulados').text(minsSuma);
	$('.totalAcumulado').text(hrsSuma + ':' + minsSuma);
	$('input[name="totalAcumulado"]').val(hrsSuma + ':' + minsSuma);
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