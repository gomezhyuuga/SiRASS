$(document).ready(function() {
    asignarUI();
    $('#addLicen').click(function() {
        addLicen();
    });
    $('#addRespon').click(function() {
        addRespon();
    });
    
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