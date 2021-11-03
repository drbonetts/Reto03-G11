function traerInformacion() {
    $.ajax({
        url: 'http://150.230.77.12/api/Reservation/report/{startDate}/{devolutionDate}',
        type: 'GET',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",

        success: function (respuesta) {
            console.log(respuesta);
            $("#resultado").empty();
            let miTabla = '<table>';
            for (i=0; i<respuesta.length; i++) {
                miTabla += '<tr>';
                for (j=0; j<respuesta[i].ReservacionAmount1.length; j++) {
                     miTabla += '<td>' + respuesta[i].ReservacionAmount1[j]+ '</td>';
                }
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
