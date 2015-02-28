$(document).ready(function () {

    initialize_editable();
    initialize_sliders();

});


function submit_sliders(){
    var json = new Object();
    json.proteinPercent = $('.profile-slider-label-val-proteinPercent').text();
    json.carbohydratePercent= $('.profile-slider-label-val-carbohydratePercent').text();
    json.fatPercent= $('.profile-slider-label-val-fatPercent').text();
    json.protein = $('.profile-slider-label-val-protein').text();
    json.carbohydrate= $('.profile-slider-label-val-carbohydrate').text();
    json.fat= $('.profile-slider-label-val-fat').text();

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: $('#slider-form').attr('action'),
        data: JSON.stringify(json),
        success: function (response) {
            alert_succes(messages['profileContent.table.slider.submit.success']);
            updateSliderGramValues(response);
        },
        error: function(response, newValue) {
            alert_fail(messages['profileContent.table.slider.submit.fail']);

        }
    });

}

function initialize_sliders(){
    $('.profile-slider').each(function(){
        var sliderVal = $(this).attr('value');
        $(this).attr('data-slider-value',sliderVal);
    });

    $('.profile-slider').each(function(){
       $(this).slider({step: 1, min: 0, max: 100, tooltip: "hide"});
       var id = $(this).attr('id');
       var property = id.substr(7);
       $(this).on("slide",function(slideEvt){
           $('.profile-slider-label-val-'+property+'Percent').text(slideEvt.value);
       })
    });
}

function initialize_editable(){
    $.fn.editable.defaults.mode = 'inline';

    $('.editable').each(function(){

        $(this).editable({
            type: 'number',
            ajaxOptions: {
                contentType: "application/json"
            },
            params: function(params) {
                return JSON.stringify(params);
            },
            success: function(response, newValue) {
                alert_succes(messages['profileContent.table.slider.submit.success']);
                updateSliderGramValues(response);
            },
            error: function(response, newValue) {
                return messages["profileContent.error"];
            }
        });
    });
}

function updateSliderGramValues(response){
    $('.profile-slider-label-val-protein').text(response.protein);
    $('.profile-slider-label-val-carbohydrate').text(response.carbohydrate);
    $('.profile-slider-label-val-fat').text(response.fat);
}
