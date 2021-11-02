function traerInformacion() {
    $.ajax({
        url: 'http://localhost/api/Motorbike/all',
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
							 
								   <h5 class ="card-title"> ${respuesta[i].name}</h5> 		
								   <h6 class ="card-subtitle mb-2 text-muted">  ${respuesta[i].brand} - ${respuesta[i].year}</h6> 		
								   <p class= "card-text"> ${respuesta[i].description} <br> 		
														  ${respuesta[i].category.name}</p>
								   <button class="btn btn-primary" onclick="editarRegistro(${respuesta[i].id} )" >Editar</button>
								   <button  class="btn btn-danger" onclick="eliminarInformacion(${respuesta[i].id} )">Borrar</button>
								   
								</div>
							</div>
                       `
                // miTabla += '<tr>';
                // //miTabla += '<td>' + respuesta[i].id + '</td>';
                // miTabla += '<td>' + respuesta[i].name + '</td>';
                // miTabla += '<td>' + respuesta[i].brand + '</td>';
                // miTabla += '<td>' + respuesta[i].year + '</td>';
                // miTabla += '<td>' + respuesta[i].description + '</td>';
                // miTabla += '<td>' + respuesta[i].category.name + '</td>';
                // miTabla += '<td><button onclick="editarRegistro('+respuesta[i].id+' )">Editar</button>';
                // miTabla += '<td><button onclick="eliminarInformacion('+respuesta[i].id+' )">Borrar</button>';
                // miTabla += '</tr>';
            }
            miTabla += '</div></div>';
            $("#resultado").append(miTabla);
            //pintarSelect(0);
        },
        error: function (xhr, status) {
            alert('ha sucedido un problema:' + status);
        }
    });
}


function guardarInformacion(){
    let selected = $("#category").children(":selected").attr("value");
	let misDatos = {
		id: $("#id").val(),
        name: $("#name").val(),
        brand: $("#brand").val(),
        year: $("#year").val(),
        description: $("#description").val(),
        category: {id: selected}    
	};
	let datosJson = JSON.stringify(misDatos); 
	$.ajax({    
        url: 'http://localhost/api/Motorbike/save',
	    data: datosJson,
        type : 'POST',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Guardado!!!");
			$("#id").val("");
            $("#name").val("");
			$("#brand").val("");
			$("#year").val("");
            $("#description").val("");
            $("#category").val("");
        	traerInformacion();	
			}
		}
	});
}


function editarRegistro (id){
	$.ajax({    
    url : 'http://localhost/api/Motorbike/'+id,
    type : 'GET',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    success : function(respuesta) {
		console.log(respuesta+ "url" + "http://localhost/api/Motorbike/"+id);
        let miTabla = '<table>';
            $("#id").val(respuesta.id);
			$("#name").val(respuesta.name);
            $("#brand").val(respuesta.brand);
            $("#year").val(respuesta.year);
            $("#description").val(respuesta.description);
			$("#category").val(respuesta.category.id);
            $("#id").attr("readonly", true);
            $("#category").attr("readonly", true);
			//pintarSelect();
	},
    error : function(xhr, status) {
        alert('ha sucedido un problema:'+ status);
    }
});
}

function actualizarInformacion(){
    //let selected = $("#category").children(":selected").attr("value");
	let misDatos = {
        id: $("#id").val(),
        name: $("#name").val(),
		brand: $("#brand").val(),
        year: $("#year").val(),
        description: $("#description").val()
        //category: {id: selected}    
	};
	let datosJson = JSON.stringify(misDatos); 

	$.ajax(    
    'http://localhost/api/Motorbike/update',
	{data: datosJson,
    type : 'PUT',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    statusCode : {
		201 :  function() {
			alert("Actualizado!");
            $("#id").val("");
            $("#name").val("");
			$("#brand").val("");
            $("#year").val("");
            $("#description").val("");
			$("#category").val("");
			$("#id").attr("readonly", false);
            $("#category").attr("readonly", false);
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
        url: 'http://localhost/api/Motorbike/'+id,  
	    data: datosJson,
        type : 'DELETE',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
  
    statusCode : {
		204 :  function() {
			alert("Eliminado!!!");
            $("#id").val("");
            $("#name").val("");
			$("#brand").val("");
			$("#year").val("");
            $("#description").val("");
            $("#category").val("");
        	traerInformacion();	
			}
		}
	});
}

function pintarSelect(){
	$.ajax({    
    url : 'http://localhost/api/Category/all',
    type : 'GET',
    dataType : 'json',
    contentType: "application/json; charset=utf-8",
  
    success : function(respuesta) {
		console.log(respuesta);
		$("#category").empty();
		miSelect='<option id="" ></option>';
		for (i=0; i<respuesta.length; i++){
	        miSelect += '<option value='+ respuesta[i].id+ '>'+respuesta[i].name+'</option>'; 		
		}
	    $("#category").append(miSelect);    

	},
    error : function(xhr, status) {
        alert('ha sucedido un problema:'+ status);
    }
});
	
}	