function traerInformacion() {
    $.ajax({
        url: 'http://150.230.77.12/api/Client/all',
        type: 'GET',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",

        success: function (respuesta) {
            console.log(respuesta);
            $("#resultado").empty();
            let miTabla = '<table>';
            for (i=0; i<respuesta.length; i++) {
                miTabla += '<tr>';
                miTabla += '<td>' + respuesta[i].idClient + '</td>';
                miTabla += '<td>' + respuesta[i].email + '</td>';
                //miTabla += '<td>' + respuesta[i].password + '</td>';
                miTabla += '<td>' + respuesta[i].name + '</td>';
                miTabla += '<td>' + respuesta[i].age + '</td>';
                miTabla += '<td><button onclick="editarRegistro('+respuesta[i].idClient+' )">Editar</button>';
                miTabla += '<td><button onclick="eliminarInformacion('+respuesta[i].idClient+' )">Borrar</button>';
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
		idClient: $("#idClient").val(),
        email: $("#email").val(),
        password: $("#password").val(),
        name: $("#name").val(),
        age: $("#age").val()
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax({    
        url: 'http://150.230.77.12/api/Client/save',
	    data: datosJson,
        type : 'POST',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Guardado!!!");
			$("#idClient").val("");
            $("#email").val("");
			$("#password").val("");
			$("#name").val("");
            $("#age").val("");
        	traerInformacion();	
			}
		}
	});
}


function editarRegistro (id){
	$.ajax({    
    url : 'http://150.230.77.12/api/Client/'+id,
    type : 'GET',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    success : function(respuesta) {
		console.log(respuesta+ "url" + "http://150.230.77.12/api/Client/"+id);
        let miTabla = '<table>';
            $("#idClient").val(respuesta.idClient);
			$("#email").val(respuesta.email);
            $("#password").val(respuesta.password);
            $("#name").val(respuesta.name);
            $("#age").val(respuesta.age);
            $("#idClient").attr("readonly", true);
            $("#email").attr("readonly", true);
			//pintarSelect();
	},
    error : function(xhr, status) {
        alert('ha sucedido un problema:'+ status);
    }
});
}

function actualizarInformacion(){
	let misDatos = {
        idClient: $("#idClient").val(),
        //email: $("#email").val(),
		password: $("#password").val(),
        name: $("#name").val(),
        age: $("#age").val()
	};
	let datosJson = JSON.stringify(misDatos); 

	$.ajax(    
    'http://150.230.77.12/api/Client/update',
	{data: datosJson,
    type : 'PUT',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Actualizado!");
            $("#idClient").val("");
            $("#email").val("");
			$("#password").val("");
            $("#name").val("");
            $("#age").val("");
			$("#idClient").attr("readonly", false);
            $("#email").attr("readonly", false);
        	traerInformacion();	
			}
		}
	});
}


function eliminarInformacion(id){
	let misDatos = {
       idClient: $("#idClient").val()
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax({    
        url: 'http://150.230.77.12/api/Client/'+id,  
	    data: datosJson,
        type : 'DELETE',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		204 :  function() {
			alert("Eliminado!!!");
            $("#idClient").val("");
            $("#email").val("");
			$("#password").val("");
			$("#name").val("");
            $("#age").val("");
        	traerInformacion();	
			}
		}
	});
}