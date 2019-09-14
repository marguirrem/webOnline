// var userData = '{"idJob":"' + idJob + '", "idEvt":"' + idEvt + '"}'
$(document).ready(function () {

    $.ajax({
        type: "GET",
        url: 'http://ec2-18-222-247-158.us-east-2.compute.amazonaws.com:9200/api/v1/categorias',
        data: {format: 'json'},
        dataType: "json",

        success: function (resultado) {
            var body = $("#tbodyApi");
            $.each(resultado, function (index, value) {
                body.append("<tr><td>" + value.descripcion + "</td> <td> <button class='btn btn-info'>Editar</button></td> <td> <button class='btn btn-danger'>Eliminar</button> </td>    </tr>")
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("Peticion: " + errorThrown);
        }
    });

});