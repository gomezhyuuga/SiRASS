$(document).ready(function() {
    $.datepicker.setDefaults(
    {
        showAnim: "clip",
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        changeYear: true
    });
    $.datepicker.regional['es'];

    $.validator.setDefaults({
        debug: true,
        errorContainer: "#feedback",
        errorClass: "invalid",
        wrapper: 'p class="help-block"',
        submitHandler: function(form) {
            var opts = {
                success: formSuccess,
                error: formError
            }
            $(form).ajaxSubmit(opts);
        }
    });
    $('#form-InformeBimensual').validate({
        rules: {
            inicioPeriodo: {
                required: true,
                dateITA: true
            },
            terminoPeriodo: {
                required: true,
                dateITA: true
            },
            horasAcumuladas: {
                required: true
            },
            horasBimestre: {
                required: true
            },
            horasAnteriores: {
                required: true
            },
            numReporte: {
                required: true,
                min: 1
            },
            actividades: {
                required: true
            }
        }
    });

    $('#inicioPeriodo').datepicker();
    $('#terminoPeriodo').datepicker(); 
    
    var hReporteEl = $('#horasAcumuladas');
	var hAntEl = $('#horasAnteriores');
	var hBimEl = $('#horasBimestre');
    $('#horasAnteriores, #horasBimestre').change(function() {
    	var hAnt = new Number(hAntEl.val());
    	var hBim = new Number(hBimEl.val());
    	if (isNaN(hAnt)) {
    		hAnt = new Number(0);
    	}
    	if (isNaN(hBim)) {
    		hBim = new Number(0);
    	}
    	// Sumar horas
    	var suma = new Number(hAnt + hBim);
    	hReporteEl.val(suma);
    });
});

function formSuccess(responseText, statusText, xhr, $form) {
	if (responseText == "1") {
		var title = "Envío exitoso del reporte";
		var text = "Tu Informe Bimensual ha sido enviado correctamente. Espera a que un supervisor ";
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