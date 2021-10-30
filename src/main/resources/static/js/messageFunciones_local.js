function traerInformacion() {
    $.ajax({
        url: 'http://localhost/api/Message/all',
        type: 'GET',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",

        success: function (respuesta) {
            console.log(respuesta);
            $("#resultado").empty();
            let miTabla = '<table>';
            for (i=0; i<respuesta.length; i++) {
                miTabla += '<tr>';
                //miTabla += '<td>' + respuesta[i].idMessage + '</td>';
                miTabla += '<td>' + respuesta[i].messageText + '</td>';
                miTabla += '<td>' + respuesta[i].motorbike.name + '</td>';
                miTabla += '<td>' + respuesta[i].client.name + '</td>';
                miTabla += '<td><button onclick="editarRegistro('+respuesta[i].idMessage+' )">Editar</button>';
                miTabla += '<td><button onclick="eliminarInformacion('+respuesta[i].idMessage+' )">Borrar</button>';
                miTabla += '</tr>';
            }
            miTabla += '</table>';
            $("#resultado").append(miTabla);
        },
        error: function (xhr, status) {
            alert('ha sucedido un problema:' + status);
        }
    });
}


function guardarInformacion(){
    let selected = $("#motorbike").children(":selected").attr("value");
    let selected2 = $("#client").children(":selected").attr("value");
	let misDatos = {
		idMessage: $("#idMessage").val(),
        messageText: $("#messageText").val(),
        motorbike: {id: selected},
        client: {idClient: selected2}    
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax({    
        url: 'http://localhost/api/Message/save',
	    data: datosJson,
        type : 'POST',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Guardado!!!");
			$("#idMessage").val("");
            $("#messageText").val("");
            $("#motorbike").val("");
            $("#client").val("");
        	traerInformacion();	
			}
		}
	});
}


function editarRegistro (id){
	$.ajax({    
    url : 'http://localhost/api/Message/'+id,
    type : 'GET',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    success : function(respuesta) {
		console.log(respuesta+ "url" + "http://localhost/api/Message/"+id);
        let miTabla = '<table>';
            $("#idMessage").val(respuesta.idMessage);
			$("#messageText").val(respuesta.messageText);
			$("#motorbike").val(respuesta.motorbike.id);
            $("#client").val(respuesta.client.idClient);
            $("#idMessage").attr("readonly", true);
            $("#motorbike").attr("readonly", true);
			//pintarSelect();
            //pintarSelect2();
	},
    error : function(xhr, status) {
        alert('ha sucedido un problema:'+ status);
    }
});
}

function actualizarInformacion(){
    //let selected = $("#motorbike").children(":selected").attr("value");
    let selected2 = $("#client").children(":selected").attr("value");
	let misDatos = {
        idMessage: $("#idMessage").val(),
        messageText: $("#messageText").val(),
        //motorbike: {id: selected},   
        client: {idClient: selected2}  
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax(    
    'http://localhost/api/Message/update',
	{data: datosJson,
    type : 'PUT',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Actualizado!");
            $("#idMessage").val("");
            $("#messageText").val("");
			$("#motorbike").val("");
            $("#client").val("");
			$("#idMessage").attr("readonly", false);
            $("#motorbike").attr("readonly", false);
        	traerInformacion();	
			}
		}
	});
}


function eliminarInformacion(id){
	let misDatos = {
       idMessage: $("#idMessage").val()
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax({    
        url: 'http://localhost/api/Message/'+id,  
	    data: datosJson,
        type : 'DELETE',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		204 :  function() {
			alert("Eliminado!!!");
            $("#idMessage").val("");
            $("#messageText").val("");
            $("#motorbike").val("");
            $("#client").val("");
        	traerInformacion();	
			}
		}
	});
}

function pintarSelect(){
	$.ajax({    
    url : 'http://localhost/api/Motorbike/all',
    type : 'GET',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    success : function(respuesta) {
		console.log(respuesta);
		$("#motorbike").empty();
		miSelect='<option id="" ></option>';
		for (i=0; i<respuesta.length; i++){
	        miSelect += '<option value='+ respuesta[i].id+ '>'+respuesta[i].name+'</option>'; 		
		}
	    $("#motorbike").append(miSelect);    

	},
    error : function(xhr, status) {
        alert('ha sucedido un problema:'+ status);
    }
});
	
}	


function pintarSelect2(){
	$.ajax({    
    url : 'http://localhost/api/Client/all',
    type : 'GET',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    success : function(respuesta) {
		console.log(respuesta);
		$("#client").empty();
		miSelect='<option id="" ></option>';
		for (i=0; i<respuesta.length; i++){
	        miSelect += '<option value='+ respuesta[i].idClient+ '>'+respuesta[i].name+'</option>'; 		
		}
	    $("#client").append(miSelect);    

	},
    error : function(xhr, status) {
        alert('ha sucedido un problema:'+ status);
    }
});
	
}

function pintartodosSelect(){
    pintarSelect();
    pintarSelect2();
}