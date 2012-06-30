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
//			var options = { 
//			        target:        '#output1',   // target element(s) to be updated with server response 
//			        beforeSubmit:  showRequest,  // pre-submit callback 
//			        success:       showResponse  // post-submit callback 
			 
			        // other available options: 
			        //url:       url         // override for form's 'action' attribute 
			        //type:      type        // 'get' or 'post', override for form's 'method' attribute 
			        //dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
			        //clearForm: true        // clear all form fields after successful submit 
			        //resetForm: true        // reset the form after successful submit 
			 
			        // $.ajax options can be used here too, for example: 
			        //timeout:   3000 
//			    }; 
//			$('#form-login').ajaxSubmit();
			form.submit();
//			console.log("Enviado!");
		},
		rules: {
			username: {
				required: true,
				rangelength: [6, 15]
			},
			password: {
				required: true,
				rangelength: [4, 16]
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