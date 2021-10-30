function traerInformacion() {
    $.ajax({
        url: 'http://150.230.77.12/api/Reservation/all',
        type: 'GET',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",

        success: function (respuesta) {
            console.log(respuesta);
            $("#resultado").empty();
            let horaFechaActuales = new Date();
            var fecha = horaFechaActuales.toLocaleString();
            let miTabla = '<table>';
            for (i=0; i<respuesta.length; i++) {
                miTabla += '<tr>';
                //miTabla += '<td>' + respuesta[i].idReservation + '</td>';
                miTabla += '<td>' + respuesta[i].startDate + '</td>';
                miTabla += '<td>' + respuesta[i].devolutionDate + '</td>';
                miTabla += '<td>' + respuesta[i].motorbike.name + '</td>';
                miTabla += '<td>' + respuesta[i].client.name + '</td>';
                miTabla += '<td>' + respuesta[i].status + '</td>';
                miTabla += '<td>' + fecha + '</td>';
                miTabla += '<td><button onclick="editarRegistro('+respuesta[i].idReservation+' )">Editar</button>';
                miTabla += '<td><button onclick="eliminarInformacion('+respuesta[i].idReservation+' )">Borrar</button>';
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
		idReservation: $("#idReservation").val(),
        startDate: $("#startDate").val(),
        devolutionDate: $("#devolutionDate").val(),
        motorbike: {id: selected},  
        client: {idClient: selected2}   
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax({    
        url: 'http://150.230.77.12/api/Reservation/save',
	    data: datosJson,
        type : 'POST',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Guardado!!!");
			$("#idReservation").val("");
            $("#startDate").val("");
			$("#devolutionDate").val("");
            $("#motorbike").val("");
            $("#client").val("");
        	traerInformacion();	
			}
		}
	});
}


function editarRegistro (id){
	$.ajax({    
    url : 'http://150.230.77.12/api/Reservation/'+id,
    type : 'GET',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    success : function(respuesta) {
		console.log(respuesta+ "url" + "http://150.230.77.12/api/Reservation/"+id);
        let miTabla = '<table>';
            $("#idReservation").val(respuesta.idReservation);
			$("#startDate").val(respuesta.startDate);
            $("#devolutionDate").val(respuesta.devolutionDate);
			$("#motorbike").val(respuesta.motorbike.id);
            $("#client").val(respuesta.client.idClient);
            $("#idReservation").attr("readonly", true);
            $("#motorbike").attr("readonly", true);
            $("#client").attr("readonly", true);
			//pintarSelect();
	},
    error : function(xhr, status) {
        alert('ha sucedido un problema:'+ status);
    }
});
}

function actualizarInformacion(){
    //let selected = $("#motorbike").children(":selected").attr("value");
    //let selected2 = $("#client").children(":selected").attr("value");
	let misDatos = {
        idReservation: $("#idReservation").val(),
        startDate: $("#startDate").val(),
		devolutionDate: $("#devolutionDate").val()
        //motorbike: {id: selected},  
        //client: {idClient: selected2}     
	};
	let datosJson = JSON.stringify(misDatos); 

	$.ajax(    
    'http://150.230.77.12/api/Reservation/update',
	{data: datosJson,
    type : 'PUT',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Actualizado!");
            $("#idReservation").val("");
            $("#startDate").val("");
			$("#devolutionDate").val("");
			$("#motorbike").val("");
            $("#client").val("");
			$("#idReservation").attr("readonly", false);
            $("#motorbike").attr("readonly", false);
            $("#client").attr("readonly", false);
        	traerInformacion();	
			}
		}
	});
}


function eliminarInformacion(id){
	let misDatos = {
       idReservation: $("#idReservation").val()
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax({    
        url: 'http://150.230.77.12/api/Reservation/'+id,  
	    data: datosJson,
        type : 'DELETE',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		204 :  function() {
			alert("Eliminado!!!");
            $("#idReservation").val("");
            $("#startDate").val("");
			$("#devolutionDate").val("");
            $("#motorbike").val("");
            $("#client").val("");
        	traerInformacion();	
			}
		}
	});
}

function pintarSelect(){
	$.ajax({    
    url : 'http://150.230.77.12/api/Motorbike/all',
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
    url : 'http://150.230.77.12/api/Client/all',
    type : 'GET',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    success : function(respuesta) {
		console.log(respuesta);
		$("#client").empty();
		miSelect='<option idClient="" ></option>';
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