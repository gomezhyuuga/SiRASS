$(document).ready(function() {
    
    if(document.getElementById('idR').value == 'Institucion'){
        vForm();
    }
    if(document.getElementById('idR').value == 'Prestador'){
        vFormPres();
    }
    if(document.getElementById('idR').value == 'Administrador'){
        vFormAdmin();
    }
    
    $('#next').on('click', function() {
            $('a[href="#tab2"]').fadeToggle()
            .fadeToggle();
        });
        
});

function vForm(){
    // FORM SUBMIT
    var options = { 
        success: datosOK,  // post-submit callback 
        error: datosError,
        timeout: 3000,
        url: "/SiRASS/actualDate"
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
    
    jQuery.validator.addMethod("lettersonlyAcent", function(value, element) {
        return this.optional(element) || /^[a-záéíóú.\s]+$/i.test(value);
    }, "Este campo solo puede contener letras");
    
    $('#gInstitu').validate({
        rules: {
            domiU: {
                required: true,
                maxlength: 60
            },
            responU: {
                required: true,
                maxlength: 50
            },
            cargoResU: {
                required: true,
                maxlength: 50
            },
            telU: {
                required: true,
                digits: true
            },
            telExtU: {
                required: true,
                digits: true
            },
            emailInst: {
                required: true,
                email: true
            },
            npassword:{
                nowhitespace: true,
                rangelength: [6, 16]
            },
            passwordVeif:{
                nowhitespace: true,
                rangelength: [6, 16],
                equalTo: "#npassword"
            }
        }
    });
}

function vFormPres(){
    // FORM SUBMIT
    var options = { 
        success: datosOK,  // post-submit callback 
        error: datosError,
        timeout: 3000,
        url: "/SiRASS/actualDate"
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
    
    jQuery.validator.addMethod("lettersonlyAcent", function(value, element) {
        return this.optional(element) || /^[a-záéíóú.\s]+$/i.test(value);
    }, "Este campo solo puede contener letras");
    
    $('#gPrestador').validate({
        rules: {
            nControl: {
                required: true
            },
            nombrePres: {
                required: true,
                maxlength: 25
            },
            aPaterno: {
                required: true,
                maxlength: 15
            },
            aMaterno: {
                required: true,
                maxlength: 15
            },
            fNac: {
                required: true
            },
            calle: {
                required: true,
                maxlength: 40
            },
            nExt: {
                required: true,
                maxlength: 7
            },
            nInt: {
                required: true,
                maxlength: 7
            },
            col: {
                required: true,
                maxlength: 20
            },
            dele: {
                required: true,
                maxlength: 20
            },
            telC: {
                required: true,
                maxlength: 8,
                digits: true
            },
            telMov: {
                required: true,
                maxlength: 13,
                digits: true
            },
            emailPres: {
                required: true,
                email: true
            },
            npassword:{
                nowhitespace: true,
                rangelength: [6, 16]
            },
            passwordVeif:{
                nowhitespace: true,
                rangelength: [6, 16],
                equalTo: "#npassword"
            }
        }
    });
}

function vFormAdmin(){
    // FORM SUBMIT
    var options = { 
        success: datosOK,  // post-submit callback 
        error: datosError,
        timeout: 3000,
        url: "/SiRASS/actualDate"
    };
    $.validator.setDefaults({
        debug: true,
        errorContainer: "#feedback",
        errorClass: "invalid",
        ignore: "",
        wrapper: 'p class="help-block"',
        submitHandler: function(form) {
            $(form).ajaxSubmit(options);
            alert(document.getElementById('npassword').value);
        }
    });
    
    jQuery.validator.addMethod("lettersonlyAcent", function(value, element) {
        return this.optional(element) || /^[a-záéíóú.\s]+$/i.test(value);
    }, "Este campo solo puede contener letras");
    
    $('#gAdmin').validate({
        rules: {
            nombre: {
                required: true,
                maxlength: 25
            },
            apPaterno: {
                required: true,
                maxlength: 15
            },
            aMaterno: {
                required: true,
                maxlength: 15
            },
            fNac: {
                required: true
            },
            cargo: {
                required: true,
                maxlength: 100
            },
            emailPres: {
                required: true,
                email: true
            },
            npassword:{
                nowhitespace: true,
                rangelength: [6, 16]
            },
            passwordVeif:{
                nowhitespace: true,
                rangelength: [6, 16],
                equalTo: "#npassword"
            }
        }
    });
}

function datosOK(responseText, statusText, xhr, $form) {
    if (responseText == "1") {
        console.log("Actualización correcta :-)");
        createAlert('Actualización correcta!',
            'Haz actualizado correctamente tus datos.',
            '#feedback', 'alert-success');
        bootbox.dialog('<p class="lead">Actualización correcta! = ¬ D', [{
            "label" : "Aceptar",
            "class" : "btn-success",
            "callback": function() {
                reloadPage();
            }
        }]);
    } else if (responseText == "0") {
        console.log("Error actualizando :-(");
        createAlert('Actualización incorrecta!',
            'Hubo un error actualizando tus datos. Revisa que los datos sean correctos e intenta de nuevo.',
            '#feedback', 'alert-error');
        bootbox.dialog('<p class="lead">Error en la actualización! Intenta de nuevo, tal\n\
            vez haya un dato incorrecto<p>', [{
            "label" : "Cerrar",
            "class" : "btn-primary btn-danger"
        }]);
    }
}

function datosError(responseText, statusText, xhr, $form) {
    console.log("Error actualizando :-(");
    createAlert('Actualización incorrecta!',
        'Hubo un error actualizando tus datos. Revisa que los datos sean correctos e intenta de nuevo.',
        '#feedback', 'alert-error');
    bootbox.dialog('<p class="lead">Error en la actualización! Intenta de nuevo, tal\n\
            vez haya un dato incorrecto<p>', [{
        "label" : "Cerrar",
        "class" : "btn-primary btn-danger"
    }]);
}