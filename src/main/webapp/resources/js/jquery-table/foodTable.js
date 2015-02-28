google.load('visualization', '1.0', {'packages':['corechart']});


$(document).ready(function () {
    init_tooltips();
    init_chart();
});



function init_chart() {
    var protein = $('#piechart').attr('protein');
    var carbohydrate = $('#piechart').attr('carbohydrate');
    var fat = $('#piechart').attr('fat');
    google.setOnLoadCallback(drawChart(protein,carbohydrate,fat));
}

function drawChart(protein, carbohydrate, fat) {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Topping');
    data.addColumn('number', 'Slices');
    data.addRows([
        [messages['foodContentHeader.protein'], parseFloat(protein.toString().replace(',','.'))],
        [messages['foodContentHeader.carbohydrate'], parseFloat(carbohydrate.toString().replace(',','.'))],
        [messages['foodContentHeader.fat'], parseFloat(fat.toString().replace(',','.'))]
    ]);

    // Set chart options
    var options = {
        'width':450,
        'height':200,
        'is3D': true,
        'tooltipText': 'percentage'
    };
    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart($('#piechart')[0]);
    chart.draw(data, options);
}

function init_tooltips(){
    $('#foodTable').bootstrapTable({
        onLoadSuccess: function (data) {
            initialize_tooltips();
            return true;
        }
    });
}




function add_remove_formatter(value, row, index) {
    var add_remove_class = row.used ? 'remove' : 'add';
    var button_class = row.used ? 'btn-warning' : 'btn-primary';

    return [
        '<div class="btn-group" data-toggle="buttons">',
        create_count_link(5, add_remove_class, button_class),
        create_count_link(25, add_remove_class, button_class),
        create_count_link(100, add_remove_class, button_class),
        '</div>'
    ].join('');
}

function create_count_link(val, add_remove_class, button_class) {
    return [
            '<button class="' + add_remove_class + ' ' + add_remove_class + val + ' ' + button_class + ' btn">',
        val,
        '</button>'
    ].join('');
}


function createFood(row, weightFactor) {
    var food = new Object();
    food.foodId = row.id;
    food.kcal = row.kcal;
    food.fat = row.fat;
    food.protein = row.protein;
    food.carbohydrate = row.carbohydrate;
    food.weightFactor = weightFactor;
    return food;
}

function ajaxAddFood(food) {
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/food/add",
        data: JSON.stringify(food),
        success: function (response) {

            $('#foodTable').bootstrapTable('refresh', {'silent': 'true'});

            if(response.message=="AE"){
                alert_succes(messages['foodContent.table.add.success']);
            }else if(response.message=="AU"){
                alert_succes(messages['foodContent.table.update.success']);
            }
            update_header(response);
        }
    });
}

function update_food_table_header(userMealSum, userProfile) {

    $('#protein-header-val').text(userMealSum.protein+'g');
    $('#carbohydrate-header-val').text(userMealSum.carbohydrate+'g');
    $('#fat-header-val').text(userMealSum.fat+'g');
    $('#weight-header-val').text(userMealSum.weight+'g');
    $('#kcal-header-val').text(userMealSum.kcal);


    $('#kcal-progress-bar').attr('aria-valuenow',((userMealSum.kcal/userProfile.kcalPerDay)*100));
    $('#kcal-progress-bar').css('width', ((userMealSum.kcal/userProfile.kcalPerDay)*100) +'%');
}

function ajaxRemoveFood(food) {
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/food/remove",
        data: JSON.stringify(food),
        success: function (response) {

            $('#foodTable').bootstrapTable('refresh', {'silent': 'true'});

            if(response.message=="RE"){
                alert_succes(messages['foodContent.table.remove.success']);
            }else if(response.message=="RU"){
                alert_succes(messages['foodContent.table.update.success']);
            }
            update_header(response);


        }
    });
}

function update_header(response) {
    update_food_table_header(response.userMealSum, response.userProfile);
    drawChart(response.userMealSum.proteinPercent,response.userMealSum.carbohydratePercent,response.userMealSum.fatPercent);

}

window.add_remove_events = {
    'click .add5': function (e, value, row, index) {
        var food = createFood(row, 0.05);
        ajaxAddFood(food);
    },
    'click .add25': function (e, value, row, index) {
        var food = createFood(row, 0.25);
        ajaxAddFood(food);
    },
    'click .add100': function (e, value, row, index) {
        var food = createFood(row, 1.0);
        ajaxAddFood(food);
    },
    'click .remove5': function (e, value, row, index) {
        var food = createFood(row, 0.05);
        ajaxRemoveFood(food);
    },
    'click .remove25': function (e, value, row, index) {
        var food = createFood(row, 0.25);
        ajaxRemoveFood(food);
    },
    'click .remove100': function (e, value, row, index) {
        var food = createFood(row, 1.0);
        ajaxRemoveFood(food);
    }
};





function add_remove_one_piece_formatter(value, row, index) {
    var add_remove_class = row.used ? 'remove' : 'add';
    var button_class = row.used ? 'btn-warning glyphicon glyphicon-minus' : 'btn-primary glyphicon glyphicon-plus';

    tooltip_name = row.countType.name;

    if($.cookie("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE") === "en"){
        tooltip_name = row.countType.nameAng;
    }

    return [
        '<div class="btn-group" data-toggle="tooltip" data-placement="left" title="'+ tooltip_name +': '+ row.countType.weight + 'g">',
        create_count_link('', add_remove_class, button_class),
        '</div>'
    ].join('');
}

window.add_remove_one_piece_events = {
    'click .glyphicon-plus': function (e, value, row, index) {
        var food = createFood(row, row.countType.weight/100);
        ajaxAddFood(food);
    },
    'click .glyphicon-minus': function (e, value, row, index) {
        var food = createFood(row, row.countType.weight/100);
        ajaxRemoveFood(food);
    }
};


function rowStyle(row, index) {
    if (row.used) {
        return {
            classes: 'user-row'
        };
    }
    return {};
}



