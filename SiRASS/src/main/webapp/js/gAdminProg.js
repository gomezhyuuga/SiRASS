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
    $('#Admin, #Prestador, #Instituto').on('click', function(){
        var presionado = $(this);
        var ad = $('#Admin');
        var pr = $('#Prestador');
        var ins = $('#Instituto');
        if (!presionado.hasClass('active')) {
            var url = "./";
            if(presionado.attr('id') == "Prestador"){
                url+="Prestadores/index.jsp";
            }
            if(presionado.attr('id') == "Instituto"){
                url+="Institucion/index.jsp";
            }else if(presionado.attr('id') == "Admin"){
                url+="Administrador/";
            }
            var form = $('#form');
            form.hide();
            form.load(url, function(){
                form.fadeToggle();
            });
            ad.parent().removeClass('active');
            pr.parent().removeClass('active');
            ins.parent().removeClass('active');
            presionado.parent().addClass('active');
        }
    })
}
function changeDiv(el){
    var presionado = $(el);
    var ad = $('#Admin');
    var pr = $('#Prestador');
    var ins = $('#Instituto');
    var idelement = el.getAttribute('data-id');
    var url;
    if(presionado.attr('name') == "Admin"){
        url = "./Administrador/detalles.jsp?id=";
        url+=idelement;
    }
    if(presionado.attr('name') == "Pres"){
        url = "./Prestadores/detalles.jsp?id=";
        url+=idelement;
    }
    if(presionado.attr('name') == "Inst"){
        var idplant = document.getElementById('Plantel').value;
        var idIns = document.getElementById('idIns').value;
        url = "./Institucion/detalles.jsp?id=";
        url+=idelement;
        url+="&idP=";
        url+=idplant;
        url+="&idI="+idIns;
    }
    var form = $('#form-interno');
    form.hide();
    form.load(url, function(){
        form.fadeToggle();
        createInput(idelement);
    });
    ad.parent().removeClass('active');
    pr.parent().removeClass('active');
    ins.parent().removeClass('active');
}
function createInput(idEl){
    var div = $('#bodyId');
    var inputId = $('<input type="hidden" />');
    inputId.attr({
        id: 'id',
        name: 'id',
        value: idEl
    });
    div.append(inputId);
}