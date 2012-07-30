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

function reloadPage() {
	window.location.reload();
}

function registroError(responseText, statusText, xhr, $form) {
    console.log("Error haciendo tu inscripción :-(");
    createAlert('Registro de inscripción incorrecta!',
        'Hubo un error haciendo tu inscripción. Revisa que tus datos sean correctos e intenta de nuevo.',
        '#feedback', 'alert-error');
    bootbox.dialog('<p class="lead">Error en la inscripción! Intenta de nuevo, tal\n\
        vez haya un dato incorrecto<p>', [{
        "label" : "Cerrar",
        "class" : "btn-primary btn-danger"
    }]);
}

function inscribirPrestador(el) {
	var id = el.getAttribute('data-id');
	var msg = '<div id="feedback"></div><p class="lead">Estás a punto de aceptar esta inscripción.<p/>';
	msg += '<h2>Número de control</h2>';
	msg += '<form name="form-inscribirPrestador" id="form-inscribirPrestador" action="/Inscribir" method="POST">';
	msg += '<div class="input-append">'
	msg += '<input type="text" id="numControl" name="numControl" />';
	msg += '<button onclick="generarNumControl()" type="button" class="btn btn-primary">Generar</button>';
	msg += '</div>';
	msg += '<h6>Favor de revisar el número de control. Si hay algún error en el generado, introdúcelo manualmente.</h6>';
	msg += '</form>';
	msg += '<h6 class="right">Aceptando inscripción con ID: ' + id + '</h6>';
	bootbox.dialog(msg, [{
		'label': 'Cancelar',
		'class': 'btn-danger'
	}, {
		'label': 'Inscribir',
		'class': 'btn-success',
		'callback': function() {
			console.log('Inscribiendo...');
			var nControl = $('input[name="numControl"]').val();
			$('#feedback').html('');
			if (nControl == "" || nControl == null || nControl == undefined) {
				var fpanel = $('#feedback');
				var alert = $('<div class="alert alert-error">');
				alert.text('Debes introducir un número de control válido.');
				fpanel.html(alert);
				return false;
			} else {
				$.ajax({
					url: '/SiRASS/Services',
					data: {
						service: 'inscribirPrestador',
						numControl: nControl,
						idInscripcion: id
					}
				})
				.done(function(msg) {
					if (msg == "1") {
						bootbox.hideAll();
						bootbox.alert('<p class="lead">Prestador inscrito correctamente</p>', function() {
							reloadPage();
						});
					} else {
						bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
					}
				})
				.fail(function() {
					bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
				});
				return false;
			}
		}
	}]);
}

function rechazarPrestador(el) {
	var id = el.getAttribute('data-id');
	var msg = '<p class="lead">&iquest;Estás seguro que deseas rechazar esta inscripción?</p>';
	msg += '<h6 class="right">Rechazando inscripción con ID: ' + id + '</h6>';
	bootbox.dialog(msg, [{
		'label': 'Cancelar',
		'class': 'btn-danger'
	}, {
		'label': 'OK',
		'class': 'btn-success',
		'callback': function() {
			console.log('Rechazando inscripción...');
			$.ajax({
				url: '/SiRASS/Services',
				data: {
					service: 'rechazarInscripcion',
					idInscripcion: id
				}
			})
			.done(function(msg) {
				if (msg == "1") {
					bootbox.hideAll();
					bootbox.alert('<p class="lead">Inscripción rechazada</p>', function() {
						reloadPage();
					});
				} else {
					bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
				}
			})
			.fail(function() {
				bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
			});
			return false;
		}
	}]);
}

function validarInscripcion(el) {
	var id = el.getAttribute('data-id');
	var msg = '<p class="lead">&iquest;Estás seguro que deseas validar esta inscripción?</p>';
	msg += '<h6 class="right">Validando inscripción con ID: ' + id + '</h6>';
	bootbox.dialog(msg, [{
		'label': 'Cancelar',
		'class': 'btn-danger'
	}, {
		'label': 'OK',
		'class': 'btn-success',
		'callback': function() {
			console.log('Aceptando inscripción...');
			$.ajax({
				url: '/SiRASS/Services',
				data: {
					service: 'validarInscripcion',
					idInscripcion: id
				}
			})
			.done(function(msg) {
				if (msg == "1") {
                    var m = 'Inscripción aceptada. Ahora, si el prestador ya entregó todos';
                    m += 'sus documentos, accede al área Inscripciones <b>Correctas</b> e inscribe al prestador.';
					bootbox.hideAll();
					bootbox.alert('<p class="lead">' + m + '</p>', function() {
						reloadPage();
					});
				} else {
					bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
				}
			})
			.fail(function() {
				bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
			});
			return false;
		}
	}]);
}

function reactivarInscripcion(el) {
	var id = el.getAttribute('data-id');
	var msg = '<p class="lead">Estás a punto de reactivar esta inscripción.</p>';
	msg += '<p>Una vez reactivada el prestador nuevamente podrá enviar sus reportes.<p/>';
	msg += '<h6 class="right">Reactivando inscripción con ID: ' + id + '</h6>';
	bootbox.dialog(msg, [{
		'label': 'Cancelar',
		'class': 'btn-danger'
	}, {
		'label': 'Reactivar',
		'class': 'btn-success',
		'callback': function() {
			console.log('Reactivando...');
			$.ajax({
				url: '/SiRASS/Services',
				data: {
					service: 'reactivarInscripcion',
					idInscripcion: id
				}
			})
			.done(function(msg) {
				if (msg == "1") {
					bootbox.hideAll();
					bootbox.alert('<p class="lead">Inscripción reactivada correctamente.</p>', function() {
						reloadPage();
					});
				} else {
					bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
				}
			})
			.fail(function() {
				bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
			});
			return false;
		}
	}]);
}

function eliminarInscripcion(el) {
	var id = el.getAttribute('data-id');
	var msg = '<p class="lead">Estás a punto de eliminar esta inscripción.</p>';
	msg += '<p>Una vez eliminada también se borrarán todos los reportes asociados a ésta.</p>';
	msg += '<h2>&iquest;Continuar de todas formas?</h2>';
	bootbox.dialog(msg, [{
		'label': 'Cancelar',
		'class': 'btn-danger'
	}, {
		'label': 'Eliminar',
		'class': 'btn-success',
		'callback': function() {
			console.log('Eliminando...');
			$.ajax({
				url: '/SiRASS/Services',
				data: {
					service: 'eliminarInscripcion',
					idInscripcion: id
				}
			})
			.done(function(msg) {
				if (msg == "1") {
					bootbox.hideAll();
					bootbox.alert('<p class="lead">Inscripción eliminada correctamente</p>', function() {
						reloadPage();
					});
				} else {
					bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
				}
			})
			.fail(function() {
				bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
			});
			return false;
		}
	}]);
}

function suspenderIncripcion(el) {
	var id = el.getAttribute('data-id');
	var msg = '<p class="lead">Estás a punto de suspender esta inscripción</p>';
	msg += '<p>Si la inscripción es suspendida, el prestador <strong>ya no podrá enviar reportes</strong>.</p>';
	msg += '<h2>&iquest;Continuar de todas formas?</h2>';
	msg += '<h6 class="right">Suspendiendo inscripción ' + id + '</h6>';
	bootbox.dialog(msg, [{
		'label': 'Cancelar',
		'class': 'btn-danger'
	}, {
		'label': 'Suspender',
		'class': 'btn-warning',
		'callback': function() {
			console.log('Suspendiendo...');
			$.ajax({
				url: '/SiRASS/Services',
				data: {
					service: 'suspenderInscripcion',
					idInscripcion: id
				}
			})
			.done(function(msg) {
				if (msg == "1") {
					bootbox.hideAll();
					bootbox.alert('<p class="lead">Inscripción suspendida correctamente</p>', function() {
						reloadPage();
					});
				} else {
					bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
				}
			})
			.fail(function() {
				bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
			});
			return false;
		}
	}]);
}
function cancelarInscripcion(el) {
	var id = el.getAttribute('data-id');
	var msg = '<p class="lead">Estás a punto de cancelar esta inscripción</p>';
	msg += '<p>Si la inscripción es cancelada, el prestador <strong>ya no podrá enviar reportes</strong>.</p>';
	msg += '<h2>&iquest;Continuar de todas formas?</h2>';
	msg += '<h6 class="right">Suspendiendo inscripción ' + id + '</h6>';
	bootbox.dialog(msg, [{
		'label': 'Cancelar',
		'class': 'btn-danger'
	}, {
		'label': 'Suspender',
		'class': 'btn-warning',
		'callback': function() {
			console.log('Suspendiendo...');
			$.ajax({
				url: '/SiRASS/Services',
				data: {
					service: 'cancelarInscripcion',
					idInscripcion: id
				}
			})
			.done(function(msg) {
				if (msg == "1") {
					bootbox.hideAll();
					bootbox.alert('<p class="lead">Inscripción cancelada correctamente</p>', function() {
						reloadPage();
					});
				} else {
					bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
				}
			})
			.fail(function() {
				bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
			});
			return false;
		}
	}]);
}

function liberarServicio(el) {
	var id = el.getAttribute('data-id');
	var msg = '<p class="lead">Estás a punto de liberar el servicio de esta inscripción</p>';
	msg += '<p>Si la inscripción es liberada, se establecerá como que el prestador <b>cumplió con 480 horas de servicio</b>.</p>';
	msg += '<h2>&iquest;Continuar de todas formas?</h2>';
	bootbox.dialog(msg, [{
		'label': 'Cancelar',
		'class': 'btn-danger'
	}, {
		'label': 'Liberar',
		'class': 'btn-success',
		'callback': function() {
			console.log('Liberando servicio...');
			$.ajax({
				url: '/SiRASS/Services',
				data: {
					service: 'liberarServicio',
					idInscripcion: id
				}
			})
			.done(function(msg) {
				if (msg == "1") {
					bootbox.hideAll();
					bootbox.alert('<p class="lead">Servicio Social liberado</p>', function() {
						reloadPage();
					});
				} else {
					bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
				}
			})
			.fail(function() {
				bootbox.alert('<p class="lead">Ha ocurrido un error. Intenta de nuevo.</p>');
			});
			return false;
		}
	}]);
}

function generarNumControl() {
	console.log('Generando...');
	$('#numControl').val('SS-E-TEST');
}