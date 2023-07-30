function myFunction_A(numero) {
	var numero_celle = numero.value;
		
	if((isNaN(numero_celle)==true)||(numero_celle==0)){
		var Table = document.getElementById("tbody_A");
		Table.innerHTML = "";
	}	else	{
		for(var i = 1; i <= numero_celle; i++){
			var table = document.getElementById("tbody_A");
			var input = document.createElement("INPUT");
			var input_1 = document.createElement("INPUT");
			var input_2 = document.createElement("INPUT");
			
			var row = table.insertRow(-1);
			var row_1 = table.insertRow(-1);
			var row_2 = table.insertRow(-1);
					
			var label = row.insertCell(0);
			var label_1	= row_1.insertCell(0);
			var label_2 = row_2.insertCell(0);
					
			var input = row.insertCell(1);
			var input_1 = row_1.insertCell(1);
			var input_2 = row_2.insertCell(1);
					
			label.innerHTML="<label for='nodo"+i+"' class='label_B' id='n"+i+"'><strong>nome n."+i+":</strong></label>";
			input.innerHTML="<input type='text' name='nodo'  id='nodo"+i+"' size='10' placeholder='obbligatorio' maxlength='10'/>";
					
			label_1.innerHTML="<label for='minnodo"+i+"' class='label_B' id='mn"+i+"'><strong>attributo min n."+i+":</strong></label>";
			input_1.innerHTML="<input type='text' name='minnodo'  id='minnodo"+i+"' size='10' placeholder='obbligatorio' maxlength='10'/>";
					
			label_2.innerHTML="<label for='maxnodo"+i+"' class='label_B' id='mxn"+i+"'><strong>attributo max n"+i+":</strong></label>";
			input_2.innerHTML="<input type='text' name='maxnodo'  id='maxnodo"+i+"' size='10' placeholder='obbligatorio' maxlength='10'/>";
		}
	}
}
		
function myFunction_B(numero) {
	var numero_celle = numero.value;
			
	if((isNaN(numero_celle)==true)||(numero_celle==0)){
		var Table = document.getElementById("tbody_B");
		Table.innerHTML = "";
	}	else	{
		for(var i = 1; i <= numero_celle; i++){
			var table = document.getElementById("tbody_B");
			var input = document.createElement("INPUT");
			var input_1 = document.createElement("INPUT");
			var input_2 = document.createElement("INPUT");
			var row = table.insertRow(-1);
			var row_1 = table.insertRow(-1);
			var row_2 = table.insertRow(-1);
					
			var label = row.insertCell(0);
			var label_1	= row_1.insertCell(0);
			var label_2 = row_2.insertCell(0);
					
			var input = row.insertCell(1);
			var input_1 = row_1.insertCell(1);
			var input_2 = row_2.insertCell(1);
					
			label.innerHTML="<label for='arco'"+i+"' class='label_B' id='e"+i+"'><strong>nome n."+i+":</strong></label>";
			input.innerHTML="<input type='text' name='arco' id='arco"+i+"' size='10' placeholder='obbligatorio' maxlength='10'/>";
					
			label_1.innerHTML="<label for='minarco'"+i+"' class='label_B' id='me"+i+"'><strong>attributo min n."+i+":</strong></label>";
			input_1.innerHTML="<input type='text' name='minarco' id='minarco"+i+"' size='10' placeholder='obbligatorio'  maxlength='10'/>";
					
			label_2.innerHTML="<label for='maxarco'"+i+"' class='label_B' id='mxe"+i+"'><strong>attributo max n"+i+":</strong></label>";
			input_2.innerHTML="<input type='text' name='maxarco' id='maxarco"+i+"' size='10' placeholder='obbligatorio' maxlength='10'/>";
		}
	}
}


function func_A(input, label){
	input.classList.toggle("error");
	label.classList.toggle("error_A");
	setTimeout(function(){input.classList.toggle("error");},3500);
	setTimeout(function(){label.classList.toggle("error_A");},3500);
}

function func_B(input_1, label_1, input_2, label_2){
	input_1.classList.toggle("error");
	input_2.classList.toggle("error");
	label_1.classList.toggle("error_A");
	label_2.classList.toggle("error_A");
	setTimeout(function(){input_1.classList.toggle("error");},3500);
	setTimeout(function(){input_2.classList.toggle("error");},3500);
	setTimeout(function(){label_1.classList.toggle("error_A");},3500);
	setTimeout(function(){label_2.classList.toggle("error_A");},3500);
}

function verify(){
	//verifica che il nome dell'albero sia diverso da null
	if (document.forms['requisiti'].nomeAlbero.value == 0) {
		var input = document.getElementById('nomeAlbero');
		var label = document.getElementById('A');
		var message = document.getElementById("alertmessage");
		var alert = document.getElementById("alert");
		
		message.innerHTML =  "Warning: provide Name!";
		func_A(input,label); 
		setTimeout(function(){alert.style.bottom = '-30px';},3500);
		alert.style.bottom = '0';
		
		return false;
	}
	
	//verifica che split size e depth siano diversi da null
	if (document.forms['requisiti'].splitSize.value == 0) {
		if(document.forms['requisiti'].depth.value ==0){
			var input_1 = document.getElementById('splitSize');
			var input_2 = document.getElementById('depth');
			var label_1 = document.getElementById('B');
			var label_2 = document.getElementById('C');
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			
			func_B(input_1, label_1, input_2, label_2);
			message.innerHTML =  "Warning: provide Split size and depth!";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		} 
	}
	
	//verifica che split size sia diverso da nullo
	if ((document.forms['requisiti'].splitSize.value == 0)) {
		var split = document.getElementById('splitSize');
		var label = document.getElementById('B');
		var message = document.getElementById("alertmessage");
		var alert = document.getElementById("alert");
		
		func_A(split, label);
		message.innerHTML =  "Warning: provide Split Size!";
		alert.style.bottom = '0';
		setTimeout(function(){alert.style.bottom = '-30px';},3500);
		return false;
	}
	
	//verifica che split size sia un numero
	if(isNaN(document.forms['requisiti'].splitSize.value)){
		var split = document.getElementById('splitSize');
		var label = document.getElementById('B');
		var message = document.getElementById("alertmessage");
		var alert = document.getElementById("alert");
		
		func_A(split, label);
		message.innerHTML =  "Warning: Split Size is a not a number!";
		alert.style.bottom = '0';
		setTimeout(function(){alert.style.bottom = '-30px';},3500);
		return false;
	}
	
	//verifica che depth sia diverso da nullo
	if (document.forms['requisiti'].depth.value == 0) {
		var split = document.getElementById('depth');
		var label = document.getElementById('C');
		var message = document.getElementById("alertmessage");
		var alert = document.getElementById("alert");
		
		func_A(split, label);
		message.innerHTML =  "Warning: provide Depth!";
		alert.style.bottom = '0';
		setTimeout(function(){alert.style.bottom = '-30px';},3500);
		return false;
	}
	
	//verifica che depth sia un numero
	if(isNaN(document.forms['requisiti'].depth.value)){
		var split = document.getElementById('depth');
		var label = document.getElementById('C');
		var message = document.getElementById("alertmessage");
		var alert = document.getElementById("alert");
		
		func_A(split, label);
		message.innerHTML =  "Warning: depth is a not a number!";
		alert.style.bottom = '0';
		setTimeout(function(){alert.style.bottom = '-30px';},3500);
		return false;
	}
	
	//verica che numero nodi e numero archi non siano nulli
	if (document.forms['requisiti'].numeroNodi.value == 0){
		if(document.forms['requisiti'].numeroArchi.value ==0){
			var input_1 = document.getElementById('numeroNodi');
			var input_2 = document.getElementById('numeroArchi');
			var label_1 = document.getElementById('D');
			var label_2 = document.getElementById('E');
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			func_B(input_1, label_1, input_2, label_2);
			message.innerHTML =  "Warning: provide Node number and Edge number!";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		}
	}
	
	//verifica input dinamici
	//nodi
	for(var i = 1; i <= numeroNodi.value ; i++){
		if(document.getElementById('nodo'+i).value == 0){
			var input = document.getElementById('nodo'+i);
			var label = document.getElementById('n'+i);
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			func_A(input, label);
			message.innerHTML =  "Warning: Provide name of node n."+i+"!";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		}
	}
	
	//verifica che il valore/i di minnodo non siano nulli
	for(var i = 1; i <= numeroNodi.value; i++){
		if(document.getElementById('minnodo'+i).value == 0){
			var input = document.getElementById('minnodo'+i);
			var label = document.getElementById('mn'+i);
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			func_A(input, label);
			message.innerHTML =  "Warning: Value of attribute min n."+i+" is null!";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		}
	}
	
	//verifica che il valore/i di minnodo siano numeri
	for(var i = 1; i <= numeroNodi.value; i++){
		if(isNaN(document.getElementById('minnodo'+i).value)){
			var input = document.getElementById('minnodo'+i);
			var label = document.getElementById('mn'+i);
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			func_A(input, label);
			message.innerHTML =  "Warning: Value of attribute min n."+i+" is not a number!";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		}
	}
	
	//verifica che il valore/i di maxnodo non siano nulli
	for(var i = 1; i <= numeroNodi.value; i++){
		if(document.getElementById('maxnodo'+i).value == 0){
			var input = document.getElementById('maxnodo'+i);
			var label = document.getElementById('mxn'+i);
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			func_A(input, label);
			message.innerHTML =  "Warning: Value of attribute max n."+i+" is null!";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		}
	}
	
	//verifica che il valore/i di maxnodo siano numeri
	for(var i = 1; i <= numeroNodi.value; i++){
		if(isNaN(document.getElementById('maxnodo'+i).value)){
			var input = document.getElementById('maxnodo'+i);
			var label = document.getElementById('mxn'+i);
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			func_A(input, label);
			message.innerHTML =  "Warning: Value of attribute max n."+i+" is not a number!";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		}
	}
	
	//confronto nodi
	for(var g = 1; g <= numeroNodi.value; g++){
		var t = document.getElementById('minnodo'+g).value;
		var j = document.getElementById('maxnodo'+g).value;
		if(t > j){
			var input_1 = document.getElementById('minnodo'+g);
			var input_2 = document.getElementById('maxnodo'+g);
			var label_1 = document.getElementById('mn'+g);
			var label_2 = document.getElementById('mxn'+g);
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			func_B(input_1 , label_1, input_2, label_2);
			message.innerHTML =  "Warning: attributo min n."+g+" can not be greater than attributo max n."+g+"!";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		}
	}
	
	//archi
	for(var i = 1; i <= numeroArchi.value ; i++){
		if(document.getElementById('arco'+i).value == 0){
			var input = document.getElementById('arco'+i);
			var label = document.getElementById('e'+i);
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			func_A(input, label);
			message.innerHTML =  "Warning: Provide name of edge n."+i+"!";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		}
	}
	
	//verifica che il valore/i di minarco non siano nulli
	for(var i = 1; i <= numeroArchi.value; i++){
		if(document.getElementById('minarco'+i).value == 0){
			var input = document.getElementById('minarco'+i);
			var label = document.getElementById('me'+i);
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			func_A(input, label);
			message.innerHTML =  "Warning: Value of attribute min n."+i+" is null!";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		}
	}
	
	//verifica che il valore/i di minarco siano numeri
	for(var i = 1; i <= numeroArchi.value; i++){
		if(isNaN(document.getElementById('minarco'+i).value)){
			var input = document.getElementById('minarco'+i);
			var label = document.getElementById('me'+i);
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			func_A(input, label);
			message.innerHTML =  "Warning: Value of attribute min n."+i+" is not a number!";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		}
	}
	
	//verifica che il valore/i di maxarco non siano nulli
	for(var i = 1; i <= numeroArchi.value; i++){
		if(document.getElementById('maxarco'+i).value == 0){
			var input = document.getElementById('maxarco'+i);
			var label = document.getElementById('mxe'+i);
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			func_A(input, label);
			message.innerHTML =  "Warning: Value of attribute max n."+i+" is null!";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		}
	}
	
	//verifica che il valore/i di maxarco siano numeri
	for(var i = 1; i <= numeroArchi.value; i++){
		if(isNaN(document.getElementById('maxarco'+i).value)){
			var input = document.getElementById('maxarco'+i);
			var label = document.getElementById('mxe'+i);
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			func_A(input, label);
			message.innerHTML =  "Warning: Value of attribute max n."+i+" is not a number!";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		}
	}
	//confronto archi
	for(var g = 1; g <= numeroArchi.value; g++){
		var t = document.getElementById('minarco'+g).value;
		var j = document.getElementById('maxarco'+g).value;
		
		if(t > j){
			var input_1 = document.getElementById('minarco'+g);
			var input_2 = document.getElementById('maxarco'+g);
			var label_1 = document.getElementById('me'+g);
			var label_2 = document.getElementById('mxe'+g);
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			func_B(input_1, label_1, input_2, label_2);
			message.innerHTML =  "attributo min n."+g+" can not be greater than attribute max n."+g+"";
			alert.style.bottom = '0';
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			return false;
		}
	}
	document.forms['requisiti'].submit();
}

function verify_B(){
	if(document.forms['requisiti_B'].name.value == 0){
		var input = document.getElementById('name');
		var label = document.getElementById('F');
		var message = document.getElementById("alertmessage");
		var alert = document.getElementById("alert");
		
		message.innerHTML =  "Warning: provide Name!";
		func_A(input,label); 
		setTimeout(function(){alert.style.bottom = '-30px';},3500);
		alert.style.bottom = '0';
		
		return false;
	}
	
	if(document.forms['requisiti_B'].A_node.value == 0){
		if(document.forms['requisiti_B'].B_node.value == 0){
			var input_1 = document.getElementById('A_node');
			var input_2 = document.getElementById('B_node');
			var label_1 = document.getElementById('G');
			var label_2 = document.getElementById('I');
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
			
			message.innerHTML =  "Warning: provide First node and Second node!";
			func_B(input_1, label_1, input_2, label_2); 
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			alert.style.bottom = '0';
			
			return false;
		}
	}
	
	if(document.forms['requisiti_B'].A_node.value == undefined){
		var input = document.getElementById('A_node');
		var label = document.getElementById('G');
		var message = document.getElementById("alertmessage");
		var alert = document.getElementById("alert");
		
		message.innerHTML =  "Warning: provide First node!";
		func_A(input,label); 
		setTimeout(function(){alert.style.bottom = '-30px';},3500);
		alert.style.bottom = '0';
		
		return false;
	}
	
	if(isNaN(document.forms['requisiti_B'].A_node.value)){
		var input = document.getElementById('A_node');
		var label = document.getElementById('G');
		var message = document.getElementById("alertmessage");
		var alert = document.getElementById("alert");
		
		message.innerHTML =  "Warning: First node is not a number!";
		func_A(input,label); 
		setTimeout(function(){alert.style.bottom = '-30px';},3500);
		alert.style.bottom = '0';
		
		return false;
	}
	
	/*
	if(document.forms['requisiti_B'].B_node.value == 0){
		var input = document.getElementById('B_node');
		var label = document.getElementById('I');
		var message = document.getElementById("alertmessage");
		var alert = document.getElementById("alert");
		
		message.innerHTML =  "Warning: provide Second node!";
		func_A(input,label); 
		setTimeout(function(){alert.style.bottom = '-30px';},3500);
		alert.style.bottom = '0';
		
		return false;
	}
	*/
	if(isNaN(document.forms['requisiti_B'].B_node.value)){
		var input = document.getElementById('B_node');
		var label = document.getElementById('I');
		var message = document.getElementById("alertmessage");
		var alert = document.getElementById("alert");
		
		message.innerHTML =  "Warning: Second node is not a number!";
		func_A(input,label); 
		setTimeout(function(){alert.style.bottom = '-30px';},3500);
		alert.style.bottom = '0';
		
		return false;
	}
	
	document.forms['requisiti_B'].submit();
}

function verify_C(){
	
	var table = document.getElementById('table_B');
    var hollow = table.rows[table.rows.length-1].cells[c=0].innerHTML;
	
	if(hollow == "-1"){
		
		var input = document.getElementById('quary');
			var label = document.getElementById('L');
			var message = document.getElementById("alertmessage");
			var alert = document.getElementById("alert");
		
			message.innerHTML =  "Warning: the table is empty!";
			func_A(input,label); 
			setTimeout(function(){alert.style.bottom = '-30px';},3500);
			alert.style.bottom = '0';
		
			return false;
	}
	
	
	if(document.forms['requisiti_B'].quary.value == 0){
		var input = document.getElementById('quary');
		var label = document.getElementById('L');
		var message = document.getElementById("alertmessage");
		var alert = document.getElementById("alert");
		
		message.innerHTML =  "Warning: provide Tree Name to delete!";
		func_A(input,label); 
		setTimeout(function(){alert.style.bottom = '-30px';},3500);
		alert.style.bottom = '0';
		
		return false;
	}
	
	document.forms['requisiti_B'].action='Request_Info_D';
}