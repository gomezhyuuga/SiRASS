// Activar elementos en sidebar
$(document).ready(function() {
    var activate = $('#sidebarActive').val();
    activate = '#sidebar li#' + activate;
    $(activate).addClass('active');
});

function createAlert(title, bodyAlert, position, alertClass) {
    var alert = $('<div class="alert alert-block">')
    var closeBtn = $('<a class="close" data-dismiss="alert" href="#">×</a>');
    var header = $('<h4 class="alert-heading">');
    var info = $('<p>');
	
    alert.addClass(alertClass);
    header.text(title);
    info.html(bodyAlert);
	
    alert.append(closeBtn);
    alert.append(header);
    alert.append(info);
	
    $(position).after(alert);
}

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

// post-submit callback 
function showResponse(responseText, statusText, xhr, $form)  { 
    // for normal html responses, the first argument to the success callback 
    // is the XMLHttpRequest object's responseText property 
 
    // if the ajaxForm method was passed an Options Object with the dataType 
    // property set to 'xml' then the first argument to the success callback 
    // is the XMLHttpRequest object's responseXML property 
 
    // if the ajaxForm method was passed an Options Object with the dataType 
    // property set to 'json' then the first argument to the success callback 
    // is the json data object returned by the server 
 
    alert('status: ' + statusText + '\n\nresponseText: \n' + responseText + 
        '\n\nThe output div should have already been updated with the responseText.'); 
}

function changePrograma(programas) {
    // Obtener el index seleccionado
    var seleccionado = programas.selectedIndex;
    var cvePrograma = $('#cvePrograma');
    var nombrePrograma = $('#nombrePrograma');
    
    // Obtener la clave del programa
    if (programas.options.item(seleccionado) != null) {
        var clave = programas.options.item(seleccionado).getAttribute("data-cve");
        // Obtener el id del programa
        var idPrograma = programas.options.item(seleccionado).getAttribute("value");
        var nombre = programas.options.item(seleccionado).text;
        if (idPrograma != 0 && idPrograma != "") {
            // Cambiar la clave del programa
            cvePrograma.val(clave);
            nombrePrograma.val(nombre);
        } else {
            // No se seleccionó ningún programa de la lista (value=0)
            cvePrograma.val('');
            nombrePrograma.val('');
        }
    } else {
        // No se seleccionó ningún programa de la lista (value=0)
        cvePrograma.val('');
        nombrePrograma.val('');
    }
}

function changeInstitucion(select) {
    var seleccionado = $(select);
    var campo;
    if (seleccionado.attr('id') == "institucionList") {
        campo = $('#otraInstitucion');
        $('#plantRegistrados').html('');
        // Se selecciona -- Selecciona una institución --
        if (seleccionado.val() == "") {
            $('#nombrePlantel').val('');
            $('#plantelList').prop('selectedIndex', 0);
        } else if (seleccionado.val() == "unregistred") {
            // Se selecciona otra
            $('#plantelList').prop('selectedIndex', 1);
            $('#nombrePlantel').val('');
            campo.fadeIn();
            $('#otroPlantel').fadeIn();
        } else {
            var selected = seleccionado.val();
            console.log("Pidiendo planteles de la institución: " + selected);
            // Cargar lista de planteles con AJAX
            $.get("/SiRASS/Services", {
                service: "plantelesByInst", 
                id: selected
            }, function(data) {
                console.log(data);
                $('#plantRegistrados').html(data);
            });
            $('#nombrePlantel').val('');
            $('#plantelList').prop('selectedIndex', 0);
            $('#otroPlantel').fadeOut();
            campo.fadeOut();
        }
    } else if (seleccionado.attr('id') == "plantelList") {
        campo = $('#otroPlantel');
        // Se selecciona otro
        if (seleccionado.val() == "unregistred") {
            campo.fadeIn();
        } else {
            $('#nombrePlantel').val('');
            campo.fadeOut();
        }
    }
}