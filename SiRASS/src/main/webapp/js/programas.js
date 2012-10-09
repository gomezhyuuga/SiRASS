$(document).ready(function() {
    
    vForm();
    
    asignarUI();
    
    $('#addLicen').click(function() {
        addLicen();
    });
    $('#addRespon').click(function() {
        addRespon();
    });
    $('#nextC').on('click', function() {
    	$('a[href="#tab2"]').fadeToggle()
    		.fadeToggle();
    });
    $('#nextP').on('click', function() {
    	$('a[href="#tab3"]').fadeToggle()
    		.fadeToggle();
    });
    $('#nextR').on('click', function() {
    	$('a[href="#tab4"]').fadeToggle()
    		.fadeToggle();
    });
    $('#nextO').on('click', function() {
    	$('a[href="#tab5"]').fadeToggle()
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

function vForm(){
    // FORM SUBMIT
    var options = { 
        success: programaOK,  // post-submit callback 
        error: programaError,
        timeout: 3000,
        url: "/SiRASS/sendPropuesta"
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
        return this.optional(element) || /^[a-záéíóú\s]+$/i.test(value);
    }, "Este campo solo puede contener letras");
    
    $('#form-sendP').validate({
        rules: {
            nomProgIns: {
                required: true,
                maxlength: 100
            },
            objProgIns: {
                required: true,
                maxlength: 400
            },
            justProgIns: {
                required: true,
                maxlength: 300
            },
            desProgIns: {
                required: true,
                maxlength: 500
            },
            recurProgIns: {
                required: true,
                maxlength: 100
            },
            evalProgIns: {
                required: true,
                maxlength: 400
            },
            resulProgIns: {
                required: true,
                maxlength: 300
            },
            tipoProgIns:{
                required: true
            },
            nombreOtroTipo: {
                required: function(element) {
                    return $('#tipoProgIns').val() == "sinRegistro";
                },
                maxlength: 45
            },
            duraProgIns:{
                required: true
            },
            vencimiento:{
                required: function(element) {
                    return $('#duraProgIns').val() == "2";
                },
                dateITA: true
            },
            alcanProgIns: "required",
            poblaProgIns: "required",
            lugarProgIns:{
                required: true,
                maxlength: 300
            },
            diasProgIns: "required",
            horaProgIns: "required",
            licenProgIns: {
                required: true,
                maxlength: 50,
                lettersonlyAcent: true
            },
            vacanProgIns: {
                required: true,
                maxlength: 3,
                digits: true
            },
            actProgIns: {
                required: true,
                maxlength: 400
            },
            respoIns:{
                required: true,
                maxlength: 100,
                lettersonlyAcent: true
            },
            cargoRespoIns:{
                required: true,
                maxlength: 100,
                lettersonlyAcent: true
            },
            emailInst:{
                required: true,
                maxlength: 30,
                email: true
            },
            obsProgIns:{
                required: true
            }
        }
    });
}

function asignarUI(){
    $.datepicker.setDefaults(
    {
        showAnim: "scale",
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        changeYear: false,
        defaultDate: '+6m',
        maxDate: "+12m +4w",
        minDate: "+6m",
        hideIfNoPrevNext: true
    });
    $.datepicker.regional['es'];
    
    $('#vencimiento').datepicker();
}
function addLicen(){
    var rows = $('#rows-acts');
    var index;
    if (rows.contents('tr:last') != null && rows.contents('tr:last') != undefined
        && rows.contents('tr:last').length != 0 ) {
        index = rows.contents('tr:last').attr('id').substring(3);
    } else {
        index = 0;
    }
    index++;
    var newRow = createRowActs(index);
    rows.append(newRow);
}

function addRespon(){
    var rows = $('#rows-respo');
    var index;
    if (rows.contents('tr:last') != null && rows.contents('tr:last') != undefined
        && rows.contents('tr:last').length != 0 ) {
        index = rows.contents('tr:last').attr('id').substring(3);
    } else {
        index = 0;
    }
    index++;
    var newRow = createRowRespo(index);
    rows.append(newRow);
}

function delRowAct(el) {
    var row = $('#row' + el);
    // console.log(row);
    row.remove();
}

function createRowActs(index){
    // Create elements
    // table tags
    var tr = $('<tr>');
    var tdLicen = $('<td>');
    var tdVacan = $('<td>');
    var tdActs = $('<td>');
    var tdElim = $('<td>');
    // inputs
    var inputLicen = $('<input type="text" />');
    var inputVacan = $('<input type="text" />');
    var inputActs = $('<textarea>');
    var inputElim = $('<input type="button" />');
        
    // Set attrs
    tr.attr('id', 'row' + index);
    inputLicen.attr({
        id: 'licenProgIns',
        name: 'licenProgIns',
        class: 'input-large',
        maxlength: '50'
    });
    inputVacan.attr({
        id: 'vacanProgIns',
        name: 'vacanProgIns',
        class: 'input-mini',
        maxlength: '3' 
    });
    inputActs.attr({
        id: 'actProgIns',
        name: 'actProgIns',
        maxlength: '400',
        rows: 5,
        placeholder: 'Escribe 5 actividades como como mínimo en MAX 400 letras'
    });
    inputElim.attr({
        id: 'elim' + index,
        onclick: 'delRowAct(' + index + ')',
        class: "btn btn-mini btn-danger",
        value: 'Eliminar'
    });
    // Insert content
    tdLicen.append(inputLicen);
    tdVacan.append(inputVacan);
    tdActs.append(inputActs);
    tdElim.append(inputElim);
	
    // Add content
    tr.data('counter', index);
    tr.append(tdLicen);
    tr.append(tdVacan);
    tr.append(tdActs);
    tr.append(tdElim);
	
    return tr;
}

function createRowRespo(index){
    // Create elements
    // table tags
    var tr = $('<tr>');
    var tdRespon = $('<td>');
    var tdCargo = $('<td>');
    var tdMail = $('<td>');
    var tdElim = $('<td>');
    // inputs
    var inputRespon = $('<input type="text" />');
    var inputCargo = $('<input type="text" />');
    var inputMail = $('<input type="text" />');
    var inputElim = $('<input type="button" />');
        
    // Set attrs
    tr.attr('id', 'row' + index);
    inputRespon.attr({
        id: 'respoIns',
        name: 'respoIns',
        class: 'input-medium',
        maxlength: '100'
    });
    inputCargo.attr({
        id: 'cargoRespoIns',
        name: 'cargoRespoIns',
        class: 'input-medium',
        maxlength: '100' 
    });
    inputMail.attr({
        id: 'emailInst',
        name: 'emailInst',
        class: 'input-medium',
        maxlength: '30'
    });
    inputElim.attr({
        id: 'elim' + index,
        onclick: 'delRowAct(' + index + ')',
        class: "btn btn-mini btn-danger",
        value: 'Eliminar'
    });
    // Insert content
    tdRespon.append(inputRespon);
    tdCargo.append(inputCargo);
    tdMail.append(inputMail);
    tdElim.append(inputElim);
	
    // Add content
    tr.data('counter', index);
    tr.append(tdRespon);
    tr.append(tdCargo);
    tr.append(tdMail);
    tr.append(tdElim);
	
    return tr;
}
function programaOK(responseText, statusText, xhr, $form) {
    if (responseText == "1") {
        console.log("Registro correcto :-)");
        createAlert('Registro exitoso!',
            'Haz registrado correctamente tu programa. Espera a que sea validado en las oficinas de la UACM, estate pendiente en el apartado <i class = "icon-exclamation-sign"></i>Avisos por si hay error en tu solicitud ',
            '#feedback', 'alert-success');
        bootbox.dialog('<p class="lead">Registro exitoso!<p>', [{
            "label" : "Aceptar",
            "class" : "btn-success",
            "callback": function() {
                document.location = "/SiRASS/institucion";
            }
        }]);
    } else if (responseText == "0") {
        console.log("Error registrando :-(");
        createAlert('Registro incorrecto!',
            'Hubo un error registrando tu programa. Revisa que los datos sean correctos e intenta de nuevo.',
            '#feedback', 'alert-error');
        bootbox.dialog('<p class="lead">Error en el registro! Intenta de nuevo, tal\n\
            vez haya un dato incorrecto<p>', [{
            "label" : "Cerrar",
            "class" : "btn-primary btn-danger"
        }]);
    }
}

function programaError(responseText, statusText, xhr, $form) {
    console.log("Error registrando :-(");
    createAlert('Registro incorrecto!',
        'Hubo un error registrando tu programa. Revisa que los datos sean correctos e intenta de nuevo.',
        '#feedback', 'alert-error');
    bootbox.dialog('<p class="lead">Error en el registro! Intenta de nuevo, tal\n\
            vez haya un dato incorrecto<p>', [{
        "label" : "Cerrar",
        "class" : "btn-primary btn-danger"
    }]);
}