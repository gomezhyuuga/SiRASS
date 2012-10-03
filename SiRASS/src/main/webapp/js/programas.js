$(document).ready(function() {
    asignarUI();
    
    console.log("IT WORKS!");
});
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
