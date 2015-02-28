
$(document).ready(function () {

    $('.list-group').sortable({
        connectWith: "ul",
        items: ".sortable-enabled",
        receive: function( event, ui ) {}
    });

    $( ".list-group" ).on( "sortreceive", function( event, ui ) {

        var json = new Object();
        json.foodId = $(ui.item).attr('id').replace(/[^0-9]/,'');
        json.sender= $(ui.sender).attr('id');
        json.target = $(event.target).attr('id');

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/user/food/my/update",
            data: JSON.stringify(json),
            success: function (response) {
                alert_succes(messages['myFoodContent.update.success']);
            }
        });

    });


});