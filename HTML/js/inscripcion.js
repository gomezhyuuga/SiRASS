$(document).ready(function() {
	
	setupDatepickers();
	setupForm();
	var practicasDiv = $('#practicas');
    practicasDiv.hide();
    var practicasInputs = practicasDiv.contents().find('input');
    
    var tipoServicio = $('#tipoSS');
    // Cuando se pulse en servicio Social o practicas profeionales
    $('#ss, #pp').on('click', function() {
        var button = $(this);
        // Comprobar si ya estaba seleccionado
        if (!button.hasClass('active')) {
            // Establecer el tipo de servicio
            tipoServicio.val(button.val());
            // Mostrar u ocultar sección de prácticas profesionales
            if (button.attr('id') == "pp") {
                practicasDiv.slideDown();
            } else {
                practicasDiv.slideUp();
                // Restablecer los valores de los campos en caso de ocultarlo
                practicasInputs.val('');
            }
        }
    });
    $('#next').on('click', function() {
    	$('a[href="#datosPrograma"]').fadeToggle()
    		.delay(200)
    		.fadeToggle();
    });
	console.log("IT WORKS!");
});
// pre-submit callback 
function showRequest(formData, jqForm, options) { 
    // formData is an array; here we use $.param to convert it to a string to display it 
    // but the form plugin does this for you automatically when it submits the data 
    var queryString = $.param(formData); 
 
    // jqForm is a jQuery object encapsulating the form element.  To access the 
    // DOM element for the form do this: 
    // var formElement = jqForm[0]; 
 
    alert('About to submit: \n\n' + queryString); 
 
    // here we could return false to prevent the form from being submitted; 
    // returning anything other than false will allow the form submit to continue 
    return true; 
} 

function setupForm() {
	// FORM SUBMIT
	var options = { 
	    success: registroOK,  // post-submit callback 
	    error: registroError,
        beforeSubmit: showRequest,
        timeout: 3000,
	    url: "/SiRASS/FormReceiver"
	};
	$.validator.setDefaults({
	    debug: true,
	    errorContainer: "#feedback",
	    errorClass: "invalid",
	    ignore: "",
	    wrapper: 'p class="help-block"',
	    submitHandler: function(form) {
	        $(form).ajaxSubmit(options);
	    }
	});
	$('#form-inscripcion').validate({
		rules: {
			especialidad: {
				required: true
			},
			difundir: "required",
			semestre: "required",
			matricula: "required",
			promedio: "required",
			avanceCurso: "required",
			fechaIngreso: "required",
			idPrograma: "required",
			cvePrograma: "required",
			area: "required",
			programaInst: "required",
			cveProgramaInst: "required",
			diasAsistencia: "required",
			horaEntrada: {
				required: true
			},
			horaSalida: {
				required: true
			},
			fInicio: {
				required: true
			},
			fTermino: {
				required: true
			},
			// Institución
			institucionList: {
			    required: function(element) {
			        return $(element).val() != "unregistred";
			    }
			},
			nombreInstitucion: {
			    required: function(element) {
			        return $('#institucionList').val() == "unregistred";
			    },
			    maxlength: 150
			},
			plantelList: {
			    required: function(element) {
			        return $(element).val() != "unregistred";
			    }
			},
			nombrePlantel: {
			    required: function(element) {
			        return $('#plantelList').val() == "unregistred";
			    },
			    maxlength: 100
			}
		}
	});
}

function setupDatepickers() {
	$.datepicker.setDefaults(
	{
		showAnim: "blind",
		dateFormat: "dd/mm/yy",
		changeMonth: true,
		changeYear: true
	});
	$.datepicker.regional['es'];
	
	$('#fechaIngreso').datepicker();
	$('#fInicio').datepicker();
	$('#fTermino').datepicker();
	
	$('#horaEntrada, #horaSalida').timepicker({});
}

function registroOK(responseText, statusText, xhr, $form) {
    console.log("registroOK");
    console.log(responseText);
    console.log(statusText);
    console.log(xhr);
    if (responseText == "1") {
        console.log("Inscripción correcta :-)");
        var msg = 'Te haz inscrito correctamente. Ya puedes empezar a realizar tu Servicio Social.'
        createAlert('Inscripción correcta!',
            msg,
            '#feedback', 'alert-success');
        bootbox.dialog('<p class="lead">Inscripción correcta!' + msg + '<p>', [{
            "label" : "Continuar",
            "class" : "btn-success",
            "callback": function() {
                document.location = "/SiRASS/prestador";
            }
        }]);
    } else if (responseText == "0") {
        registroError();
    } else {
        registroError();
    }
}