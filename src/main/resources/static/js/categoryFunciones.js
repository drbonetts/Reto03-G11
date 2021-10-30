function traerInformacion() {
    $.ajax({
        url: 'http://150.230.77.12/api/Category/all',
        type: 'GET',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",

        success: function (respuesta) {
            console.log(respuesta);
            $("#resultado").empty();
            let miTabla = '<table>';
            for (i=0; i<respuesta.length; i++) {
                miTabla += '<tr>';
                //miTabla += '<td>' + respuesta[i].id + '</td>';
                miTabla += '<td>' + respuesta[i].name + '</td>';
                miTabla += '<td>' + respuesta[i].description + '</td>';
                for (j=0; j<respuesta[i].motorbikes.length; j++) {
                    miTabla += '<td>' + respuesta[i].motorbikes[j].name+ '</td>';
                }
                miTabla += '<td><button onclick="editarRegistro('+respuesta[i].id+' )">Editar</button>';
                miTabla += '<td><button onclick="eliminarInformacion('+respuesta[i].id+' )">Borrar</button>';
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
	let misDatos = {
		id: $("#id").val(),
        name: $("#name").val(),
        description: $("#description").val()
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax({    
        url: 'http://150.230.77.12/api/Category/save',
	    data: datosJson,
        type : 'POST',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Guardado!!!");
			$("#id").val("");
            $("#name").val("");
            $("#description").val("");
        	traerInformacion();	
			}
		}
	});
}


function editarRegistro (id){
	$.ajax({    
    url : 'http://150.230.77.12/api/Category/'+id,
    type : 'GET',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    success : function(respuesta) {
		console.log(respuesta+ "url" + "http://150.230.77.12/api/Category/"+id);
        let miTabla = '<table>';
            $("#id").val(respuesta.id);
			$("#name").val(respuesta.name);
            $("#description").val(respuesta.description);
            $("#id").attr("readonly", true);
			//pintarSelect();
	},
    error : function(xhr, status) {
        alert('ha sucedido un problema:'+ status);
    }
});
}

function actualizarInformacion(){
	let misDatos = {
        id: $("#id").val(),
        name: $("#name").val(),
        description: $("#description").val()
	};
	let datosJson = JSON.stringify(misDatos); 

	$.ajax(    
    'http://150.230.77.12/api/Category/update',
	{data: datosJson,
    type : 'PUT',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Actualizado!");
            $("#id").val("");
            $("#name").val("");
            $("#description").val("");
			$("#id").attr("readonly", false);
        	traerInformacion();	
			}
		}
	});
}


function eliminarInformacion(id){
	let misDatos = {
       id: $("#id").val()
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax({    
        url: 'http://150.230.77.12/api/Category/'+id,  
	    data: datosJson,
        type : 'DELETE',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		204 :  function() {
			alert("Eliminado!!!");
            $("#id").val("");
            $("#name").val("");
            $("#description").val("");
        	traerInformacion();	
			}
		}
	});
}