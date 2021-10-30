function traerInformacion() {
    $.ajax({
        url: 'http://localhost/api/Admin/all',
        type: 'GET',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",

        success: function (respuesta) {
            console.log(respuesta);
            $("#resultado").empty();
            let miTabla = '<table>';
            for (i=0; i<respuesta.length; i++) {
                miTabla += '<tr>';
                miTabla += '<td>' + respuesta[i].idAdmin + '</td>';
                miTabla += '<td>' + respuesta[i].email + '</td>';
                //miTabla += '<td>' + respuesta[i].password + '</td>';
                miTabla += '<td>' + respuesta[i].name + '</td>';
                miTabla += '<td><button onclick="editarRegistro('+respuesta[i].idAdmin+' )">Editar</button>';
                miTabla += '<td><button onclick="eliminarInformacion('+respuesta[i].idAdmin+' )">Borrar</button>';
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
		idAdmin: $("#idAdmin").val(),
        email: $("#email").val(),
        password: $("#password").val(),
        name: $("#name").val()
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax({    
        url: 'http://localhost/api/Admin/save',
	    data: datosJson,
        type : 'POST',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Guardado!!!");
			$("#idAdmin").val("");
            $("#email").val("");
			$("#password").val("");
			$("#name").val("");
        	traerInformacion();	
			}
		}
	});
}


function editarRegistro (id){
	$.ajax({    
    url : 'http://localhost/api/Admin/'+id,
    type : 'GET',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    success : function(respuesta) {
		console.log(respuesta+ "url" + "http://localhost/api/Admin/"+id);
        let miTabla = '<table>';
            $("#idAdmin").val(respuesta.idAdmin);
			$("#email").val(respuesta.email);
            $("#password").val(respuesta.password);
            $("#name").val(respuesta.name);
            $("#idAdmin").attr("readonly", true);
            $("#email").attr("readonly", true);
	},
    error : function(xhr, status) {
        alert('ha sucedido un problema:'+ status);
    }
});
}

function actualizarInformacion(){
	let misDatos = {
        idAdmin: $("#idAdmin").val(),
        //email: $("#email").val(),
		password: $("#password").val(),
        name: $("#name").val()
	};
	let datosJson = JSON.stringify(misDatos); 

	$.ajax(    
    'http://localhost/api/Admin/update',
	{data: datosJson,
    type : 'PUT',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Actualizado!");
            $("#idAdmin").val("");
            $("#email").val("");
			$("#password").val("");
            $("#name").val("");
			$("#idAdmin").attr("readonly", false);
            $("#email").attr("readonly", false);
        	traerInformacion();	
			}
		}
	});
}


function eliminarInformacion(id){
	let misDatos = {
       idAdmin: $("#idAdmin").val()
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax({    
        url: 'http://localhost/api/Admin/'+id,  
	    data: datosJson,
        type : 'DELETE',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		204 :  function() {
			alert("Eliminado!!!");
            $("#idAdmin").val("");
            $("#email").val("");
			$("#password").val("");
			$("#name").val("");
        	traerInformacion();	
			}
		}
	});
}