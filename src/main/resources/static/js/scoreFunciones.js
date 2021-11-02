function traerInformacion() {
    $.ajax({
        url: 'http://localhost/api/Score/all',
        type: 'GET',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",

        success: function (respuesta) {
            console.log(respuesta);
            $("#resultado").empty();
            let miTabla = '<div class="container"><div  class= "row">';
            for (i=0; i<respuesta.length; i++) {
                miTabla += `
			            	<div class="card m-2" >
								<div class="card-body" >
							 
								   <h5 class ="card-title"> ${respuesta[i].messageText}</h5> 		
								   <h6 class ="card-subtitle mb-2 text-muted">  ${respuesta[i].qualification}</h6> 		
								   <p class= "card-text"> ${respuesta[i].reservations.idReservation}</p>
								   <button class="btn btn-primary" onclick="editarRegistro(${respuesta[i].idScore} )" >Editar</button>
								   <button  class="btn btn-danger" onclick="eliminarInformacion(${respuesta[i].idScore} )">Borrar</button>
								   
								</div>
							</div>
                       `

                // miTabla += '<tr>';
                // //miTabla += '<td>' + respuesta[i].idScore + '</td>';
                // miTabla += '<td>' + respuesta[i].messageText + '</td>';
                // miTabla += '<td>' + respuesta[i].qualification + '</td>';
                // miTabla += '<td>' + respuesta[i].reservations.idReservation + '</td>';
                // miTabla += '<td><button onclick="editarRegistro('+respuesta[i].idScore+' )">Editar</button>';
                // miTabla += '<td><button onclick="eliminarInformacion('+respuesta[i].idScore+' )">Borrar</button>';
                // miTabla += '</tr>';
            }
            miTabla += '</div></div>';
            $("#resultado").append(miTabla);
        },
        error: function (xhr, status) {
            alert('ha sucedido un problema:' + status);
        }
    });
}


function guardarInformacion(){
    let selected = $("#reservations").children(":selected").attr("value");
	let misDatos = {
		idScore: $("#idScore").val(),
        messageText: $("#messageText").val(),
        qualification: $("#qualification").val(),
        reservations: {idReservation: selected}
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax({    
        url: 'http://localhost/api/Score/save',
	    data: datosJson,
        type : 'POST',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Guardado!!!");
			$("#idScore").val("");
            $("#messageText").val("");
            $("#qualification").val("");
            $("#reservations").val("");
        	traerInformacion();	
			}
		}
	});
}


function editarRegistro (id){
	$.ajax({    
    url : 'http://localhost/api/Score/'+id,
    type : 'GET',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    success : function(respuesta) {
		console.log(respuesta+ "url" + "http://localhost/api/Score/"+id);
        let miTabla = '<table>';
            $("#idScore").val(respuesta.idScore);
			$("#messageText").val(respuesta.messageText);
            $("#qualification").val(respuesta.qualification);
			$("#reservations").val(respuesta.reservations.idReservation);
            $("#idScore").attr("readonly", true);
			//pintarSelect();
	},
    error : function(xhr, status) {
        alert('ha sucedido un problema:'+ status);
    }
});
}

function actualizarInformacion(){
    let selected = $("#reservations").children(":selected").attr("value");
	let misDatos = {
        idScore: $("#idScore").val(),
        messageText: $("#messageText").val(),
        qualification: $("#qualification").val(),
        reservations: {idReservation: selected} 
	};
	let datosJson = JSON.stringify(misDatos); 

	$.ajax(    
    'http://localhost/api/Score/update',
	{data: datosJson,
    type : 'PUT',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Actualizado!");
            $("#idScore").val("");
            $("#messageText").val("");
            $("#qualification").val("");
			$("#reservations").val("");
			$("#idScore").attr("readonly", false);
        	traerInformacion();	
			}
		}
	});
}


function eliminarInformacion(id){
	let misDatos = {
       idScore: $("#idScore").val()
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax({    
        url: 'http://localhost/api/Score/'+id,  
	    data: datosJson,
        type : 'DELETE',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		204 :  function() {
			alert("Eliminado!!!");
            $("#idScore").val("");
            $("#messageText").val("");
            $("#qualification").val("");
            $("#reservations").val("");
        	traerInformacion();	
			}
		}
	});
}

function pintarSelect(){
	$.ajax({    
    url : 'http://localhost/api/Reservation/all',
    type : 'GET',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    success : function(respuesta) {
		console.log(respuesta);
		$("#reservations").empty();
		miSelect='<option id="" ></option>';
		for (i=0; i<respuesta.length; i++){
	        miSelect += '<option value='+ respuesta[i].idReservation+ '>'+respuesta[i].idReservation+'</option>'; 		
		}
	    $("#reservations").append(miSelect);    

	},
    error : function(xhr, status) {
        alert('ha sucedido un problema:'+ status);
    }
});
	
}	