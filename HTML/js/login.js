$(document).ready(function() {
	var loginForm = $('#form-login');
	
	// Validator
	loginForm.validate({
		debug: true,
		validClass: "success",
		errorContainer: "#feedback",
		errorLabelContainer: "#feedback ul",
		wrapper: "li",
		submitHandler: function(form) {
			form.submit();
		},
		rules: {
			username: {
				required: true,
				nowhitespace: true,
				rangelength: [6, 15]
			},
			password: {
				required: true,
				nowhitespace: true,
				rangelength: [6, 16]
			}
		},
		messages: {
			username: {
				required: "Debes introducir tu nombre de usuario",
				rangelength: jQuery.format("Tu usuario debe ser de al menos {0} - {1} caracteres")
			},
			password: {
				required: "Debes introducir tu contrase&ntilde;a",
				rangelength: jQuery.format("La contrase&ntilde;a debe ser de al menos {0} - {1} caracteres")
			}
		}
	});
	
	if ($('#errorLogin').val() == "true") {
		$('#modal-login').modal('show');
	}
	console.log("it works!");
});

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