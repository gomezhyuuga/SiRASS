$(document).ready(function() {
	console.log("it works!");
	// Configuraciones
	$.datepicker.setDefaults(
	{
		showAnim: "blind",
		dateFormat: "dd/mm/yy",
		minDate: "-70y",
		maxDate: "-13y",
		changeMonth: true,
		changeYear: true
	});
	$.datepicker.regional['es'];
	
	// FORM SUBMIT
	var options = { 
	    success:       showResponse,  // post-submit callback 
	    clearForm: true,        // clear all form fields after successful submit 
	    error: showResponse,
	    resetForm: true       // reset the form after successful submit 
    };
    $.validator.setDefaults({
		debug: true,
		errorContainer: "#feedback",
		wrapper: 'p class="help-block"',
		validClass: "success",
		submitHandler: function(form) {
		     $(form).ajaxSubmit(options);
		}
    });
    jQuery.validator.addMethod("lettersonlyAcent", function(value, element) {
    	return this.optional(element) || /^[a-z-áéíóú]+$/i.test(value);
    }, "Letters only please");
    
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
				rangelength: [3, 15],
				lettersonlyAcent: true
			},
			aMaterno: {
				required: true,
				rangelength: [3, 15],
				lettersonlyAcent: true
			},
			email: {
				required: true,
				maxlength: 30,
				email: true
			},
			nacimiento: {
				required: true,
				date: true
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
			dCalle: "required",
			dCP: {
				required: true,
				digits: true,
				rangelength: [5, 5]
			},
			dColonia: "required",
			dDelegacion: "required",
			telCasa: {
				digits: true,
				rangelength: [8, 8]
			},
			telCel: {
				digits: true,
				rangelength: [10, 13]
			}
		}
	});
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