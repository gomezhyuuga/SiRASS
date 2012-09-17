$(document).ready(function() {

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
    $('#form-InformeFinal').validate({
        rules: {
            intro: {
                required: true
            },
            objGeneral: {
                required: true
            },
            objEspecif: {
                required: true
            },
            metodologia: {
                required: true
            },
            actividades: {
                required: true
            },
            metas: {
                required: true
            },
            resultados: {
                required: true
            }
        }
    });
});

function formSuccess(responseText, statusText, xhr, $form) {
	if (responseText == "1") {
		var title = "Envío exitoso del reporte";
		var text = "Tu Informe Final ha sido enviado correctamente. Espera a que un supervisor ";
		text += "lo revise.";
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