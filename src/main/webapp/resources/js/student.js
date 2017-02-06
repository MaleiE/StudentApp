$(document).ready(function (){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $('.del1').submit(function(event) {
        $.ajax({
            url: $(event.target).attr("action"),
            type: "POST",


            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);

            },

            success: function() {
                var tr = $(event.target).closest("tr");
                tr.fadeIn(1000).fadeOut(200, function(){
                    tr.remove();})
            }
        });
        $('.modal.in').modal('hide');

        event.preventDefault();
    });


    $('#yearRevenueInput').bootstrapBirthday({
        widget: {
            wrapper: {
                tag: 'div',
                class: 'row'
            },
            wrapperYear: {
                use: true,
                tag: 'div',
                class: 'col-sm-4'
            },
            wrapperMonth: {
                use: true,
                tag: 'div',
                class: 'col-sm-4'
            },
            wrapperDay: {
                use: true,
                tag: 'div',
                class: 'col-sm-4'
            },
            selectYear: {
                name: 'birthday[year]',
                class: 'form-control'
            },
            selectMonth: {
                name: 'birthday[month]',
                class: 'form-control'
            },
            selectDay: {
                name: 'birthday[day]',
                class: 'form-control'
            }
        }
    });

    $('#saveStudent').submit(function (e) {
        $.post('/studentAdd', $(this).serialize(), function (response) {
            if (response.hasError == true){
                $('#error').html(response.status);
            } else {
                $('#myModal').modal('hide');
                $('#success').html(response.status);
                $('#studentTableResponse').last().append(
                    '<tr>' +
                    '<td>' + response.student.id + '</td>' +
                    '<td>' + response.student.firsName + '</td>' +
                    '<td>' + response.student.lastName + '</td>' +
                    '<td>' + response.student.yearRevenue + '</td>' +
                    '<td>'+
                    '<form action="/student/update/'+response.student.id+'">'+
                    '<button type="submit" class="btn btn-default">Обновить</button>'+
                    '</form>'+
                    '</td>'+
                    '<td>'+
                    '<button class="btn btn-default" data-toggle="modal" data-target="#deleteModal'+response.student.id+'">'+
                    'Удалить студента </button>'+
                    '<div class="modal fade bs-example-modal-sm" id="deleteModal'+response.student.id+'" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">'+
                    '<div class="modal-dialog modal-sm">'+
                    '<div class="modal-content">'+
                    '<div class="modal-header">'+
                    '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'+
                    '<h4 class="modal-title" id="deleteModalLabel">Удалить</h4>'+
                    '</div>'+
                    '<div class="modal-body">'+
                    '<div class="btn-group">'+
                    '<form action="/student/delete/'+response.student.id+'">'+
                    '<button type="button" class="btn btn-warning" data-dismiss="modal">Отмена</button>'+
                    '<input  type="submit" class="btn btn-success" value="Удалить"/>'+
                    '</form>'+
                    '</div>'+
                    '</div>'+
                    '</div>'+
                    '</div>'+
                    '</div>'+
                    '</td>'+
                    '</tr>'
                );
                clearInputs()
            }
        });
        e.preventDefault();
        clearMessages()
    });
});

function clearInputs() {
    $('input[id*="Input"]').each(function () {
        $(this).val('');
    });
}

function clearMessages() {
    $(".error").empty();
    $(".success").empty();
}