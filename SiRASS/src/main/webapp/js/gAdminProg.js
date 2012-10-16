$(document).ready(function() {
    
    initFormChanger();
    
});
function initFormChanger() {

    $('#internoR, #externoR').on('click', function() {
        var presionado = $(this);
        if (!presionado.hasClass('active')) {
            var url = "../forms/regs";
            // Detectar el botón presionado para cargar el formulario
            if (presionado.attr('id') == "internoR") {
                url += "Inter";
            } else if (presionado.attr('id') == "externoR") {
                url += "Exter";
            }
            url += ".jsp";
            var form = $('#form-prog');
            form.hide();
            form.load(url, function() {
                form.fadeToggle();
            });
        }
    });
    $('#internoA, #externoA').on('click', function() {
        var presionado = $(this);
        if (!presionado.hasClass('active')) {
            var url = "../forms/acts";
            // Detectar el botón presionado para cargar el formulario
            if (presionado.attr('id') == "internoA") {
                url += "Inter";
            } else if (presionado.attr('id') == "externoA") {
                url += "Exter";
            }
            url += ".jsp";
            var form = $('#form-prog');
            form.hide();
            form.load(url, function() {
                form.fadeToggle();
            });
        }
    });
    $('#internoS, #externoS').on('click', function() {
        var presionado = $(this);
        if (!presionado.hasClass('active')) {
            var url = "../forms/suspen";
            // Detectar el botón presionado para cargar el formulario
            if (presionado.attr('id') == "internoS") {
                url += "Inter";
            } else if (presionado.attr('id') == "externoS") {
                url += "Exter";
            }
            url += ".jsp";
            var form = $('#form-prog');
            form.hide();
            form.load(url, function() {
                form.fadeToggle();
            });
        }
    });
    $('#internoB, #externoB').on('click', function() {
        var presionado = $(this);
        if (!presionado.hasClass('active')) {
            var url = "../forms/inact";
            // Detectar el botón presionado para cargar el formulario
            if (presionado.attr('id') == "internoB") {
                url += "Inter";
            } else if (presionado.attr('id') == "externoB") {
                url += "Exter";
            }
            url += ".jsp";
            var form = $('#form-prog');
            form.hide();
            form.load(url, function() {
                form.fadeToggle();
            });
        }
    });
}