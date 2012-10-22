$(document).ready(function() {
    console.log("it works!");
    //	createAlert("Tituo", "textooo", "#feedback", "alert-error");
    //	bootbox.dialog('<p class="lead">Registro exitoso!<p>', [{
    //	    "label" : "Iniciar sesi&oacute;n",
    //	    "class" : "btn-success",
    //	    "callback": function() {
    //	        document.location = "http://localhost:8084/SiRASS/";
    //	    }
    //	}, {
    //	    "label" : "Cerrar",
    //	    "class" : "btn-primary",
    //	    "callback": function() {
    //	        console.log("Primary button");
    //	    }
    //	}]);
    // Configuraciones
    $.datepicker.setDefaults(
    {
        showAnim: "scale",
        dateFormat: "dd/mm/yy",
        minDate: "-70y",
        maxDate: "-13y",
        changeMonth: true,
        changeYear: true
    });
    $.datepicker.regional['es'];
	
    // FORM SUBMIT
    var options = { 
        success:       registroOK,  // post-submit callback 
        error: registroError,
        url: "/SiRASS/Signup"
//        resetForm: true       // reset the form after successful submit 
    };
    $.validator.setDefaults({
        debug: true,
        errorContainer: "#feedback",
        errorClass: "invalid",
        wrapper: 'p class="help-block"',
        submitHandler: function(form) {
            $(form).ajaxSubmit(options);
        }
    });
    jQuery.validator.addMethod("lettersonlyAcent", function(value, element) {
        return this.optional(element) || /^[a-záéíóú\s]+$/i.test(value);
    }, "Este campo solo puede contener letras");
    
    // Asignar datepickers
    $('#nacimiento').datepicker();
    
    $('#form-signup').validate({
        rules: {
            nombre: {
                required: true,
                rangelength: [3, 25],
                lettersonlyAcent: true
            },
            aPaterno: {
                required: true,
                rangelength: [2, 15],
                lettersonlyAcent: true
            },
            aMaterno: {
                required: true,
                rangelength: [2, 15],
                lettersonlyAcent: true
            },
            email: {
                required: true,
                maxlength: 30,
                email: true
            },
            nacimiento: {
                required: true,
                dateITA: true
            },
            username: {
                required: true,
                nowhitespace: true,
                rangelength: [6, 15]
            },
            password: {
                required: true,
                nowhitespace: true,
                rangelength: [6, 16]
            },
            passwordVerif: {
                required: true,
                nowhitespace: true,
                rangelength: [6, 16],
                equalTo: "#password"
            },
            dCalle: {
                required: true,
                maxlength: 40
            },
            dCP: {
                required: true,
                digits: true,
                rangelength: [5, 5]
            },
            dColonia: {
                required: true,
                maxlength: 20
            },
            dDelegacion: {
                required: true,
                maxlength: 20
            },
            telCasa: {
                digits: true,
                rangelength: [8, 8]
            },
            telCel: {
                digits: true,
                rangelength: [10, 13]
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
            },
            domicilio: "required",
            area: "required",
            responsable: "required",
            cargo: "required",
            tel: {
                required: true,
                digits: true,
                rangelength: [8, 20]
            },
            telExt: {
                required: false,
                rangelength: [1, 10]
            }
        }
    });
	
    initFormChanger();
});

function initFormChanger() {

    $('#btnPrestador, #btnInstitucion').on('click', function() {
        var presionado = $(this);
        if (!presionado.hasClass('active')) {
            var url = "../forms/signup";
            // Detectar el botón presionado para cargar el formulario
            if (presionado.attr('id') == "btnPrestador") {
                url += "Prestador";
            } else if (presionado.attr('id') == "btnInstitucion") {
                url += "Institucion";
            }
            url += ".jsp";
            var form = $('#form-signup');
            form.hide();
            form.load(url, function() {
                form.fadeToggle();
            });
        }
    });
}

function registroOK(responseText, statusText, xhr, $form) {
    if (responseText == "1") {
        console.log("Registro correcto :-)");
        createAlert('Registro exitoso!',
            'Te haz registrado correctamente. Ya puedes <strong>Iniciar sesi&oacute;n</strong>.',
            '#feedback', 'alert-success');
        bootbox.dialog('<p class="lead">Registro exitoso!<p>', [{
            "label" : "Iniciar sesi&oacute;n",
            "class" : "btn-success",
            "callback": function() {
                document.location = "/SiRASS/";
            }
        }, {
            "label" : "Cerrar",
            "class" : "btn-primary",
            "callback": function() {
                console.log("Primary button");
            }
        }]);
    } else if (responseText == "0") {
        console.log("Error registrando :-(");
        createAlert('Registro incorrecto!',
            'Hubo un error registrando tu cuenta. Revisa que tus datos sean correctos e intenta de nuevo.',
            '#feedback', 'alert-error');
        bootbox.dialog('<p class="lead">Error en el registro! Intenta de nuevo, tal\n\
            vez haya un dato incorrecto<p>', [{
            "label" : "Cerrar",
            "class" : "btn-primary btn-danger"
        }]);
    } else if (responseText == "1062") {
        console.log("Usuario repetido!");
        createAlert('Usuario repetido!!',
            'El nombre de usuario que escogiste ya existe. <strong>C&aacute;mbialo</strong> e intenta de nuevo..',
            '#feedback', 'alert-warning');
        bootbox.dialog('<p class="lead">El usuairo ya existe, escoge otro por favor.<p>', [{
            "label" : "Cerrar",
            "class" : "btn-primary btn-danger"
        }]);
    }
}

function registroError(responseText, statusText, xhr, $form) {
    console.log("Error registrando :-(");
    createAlert('Registro incorrecto!',
        'Hubo un error registrando tu cuenta. Revisa que tus datos sean correctos e intenta de nuevo.',
        '#feedback', 'alert-error');
    bootbox.dialog('<p class="lead">Error en el registro! Intenta de nuevo, tal\n\
            vez haya un dato incorrecto<p>', [{
        "label" : "Cerrar",
        "class" : "btn-primary btn-danger"
    }]);
}