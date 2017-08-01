$(document).ready(function () {

        $('.btn-submit').click(function (e) {

            var $formId = $(this).parents('form');
            var formAction = $formId.attr('action');
            var $error = $('<span class="error"></span>');

            $('li', $formId).removeClass('error');
            $('span.error').remove();


            $('.required', $formId).each(function () {
                var inputVal = $(this).val();
                var $parentTag = $(this).parent();
                if (inputVal == '') {
                    $parentTag.addClass('error').append($error.clone().text('Required Field'));
                }
            });


            if ($('span.error').length > 0) {
                $('span.error').each(function () {

                    var distance = 15;
                    var width = $(this).outerWidth();
                    var start = width + distance;

                    $(this).show().css({
                        display: 'block',
                        opacity: 0,
                        right: -start + 'px'
                    }).animate({
                        right: (-width - 8) + 'px',
                        opacity: 1
                    }, 'slow');
                });
            } else {
                createPet();
            }
            e.preventDefault();
        });

        function createPet() {
            $.ajax('pet/json', {
                method: 'post',
                data: JSON.stringify({name: $('#name').val(), owner: $('#owner').val(), sex: $('#sex').val()}),
                complete: function (data) {
                    loadPets();
                }
            });
        }

        // TODO: DATA?
    function loadPets() {
        $.ajax('pet/json', {
            method : 'get',
            success: function(data) {
                var table = "<table>";
                table += "<tr><th>Name</th><th>Owner</th><th>Sex</th></tr>"
                var size = data.length;
                for (var i=0;i!=size;++i) {
                    table += "<tr><td>"+data[i].name+"</td><td>"+data[i].owner+"</td><td>"+data[i].sex+"</td></tr>"
                }
                table += "</table>"
                $('#pets').html(table);
            }
        });
    }
    }
);