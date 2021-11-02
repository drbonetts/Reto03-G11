function traerInformacion() {
    $.ajax({
        url: 'http://150.230.77.12/api/Client/all',
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
							 
								   <h5 class ="card-title"> ${respuesta[i].idClient} - ${respuesta[i].name}</h5> 		
								   <h6 class ="card-subtitle mb-2 text-muted">  ${respuesta[i].email}</h6> 		
								   <p class= "card-text"> ${respuesta[i].age}</p>
								   <button class="btn btn-primary" onclick="editarRegistro(${respuesta[i].idClient} )" >Editar</button>
								   <button  class="btn btn-danger" onclick="eliminarInformacion(${respuesta[i].idClient} )">Borrar</button>
								   
								</div>
							</div>
                       `
                // miTabla += '<tr>';
                // miTabla += '<td>' + respuesta[i].idClient + '</td>';
                // miTabla += '<td>' + respuesta[i].email + '</td>';
                // //miTabla += '<td>' + respuesta[i].password + '</td>';
                // miTabla += '<td>' + respuesta[i].name + '</td>';
                // miTabla += '<td>' + respuesta[i].age + '</td>';
                // miTabla += '<td><button onclick="editarRegistro('+respuesta[i].idClient+' )">Editar</button>';
                // miTabla += '<td><button onclick="eliminarInformacion('+respuesta[i].idClient+' )">Borrar</button>';
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