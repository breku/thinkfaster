<div id="foodContentHeader">
<h1><@spring.message "foodContentHeader.h"/></h1>

<table id="user-profile-table" class="table">
    <tr>
        <td class="col-xs-1-5"><@spring.message "foodContentHeader.kcal"/></td>
        <td class="col-xs-1-5" id="kcal-header-val">${userMealSum.kcal}</td>
        <td>
            <div class="progress">
                <div id="kcal-progress-bar" class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="${(userMealSum.kcal/userProfile.kcalPerDay*100)?int}" aria-valuemin="0" aria-valuemax="100" style="width: ${(userMealSum.kcal/userProfile.kcalPerDay*100)?int}%">
                    <span class="sr-only" />
                </div>
            </div>
        </td>
    </tr>
    <tr>
        <td><@spring.message "foodContentHeader.weight"/></td>
        <td colspan="2" id="weight-header-val">${userMealSum.weight}g</td>
    </tr>
    <tr>
        <td><@spring.message "foodContentHeader.protein"/></td>
        <td id="protein-header-val">${userMealSum.protein}g</td>
        <td rowspan="3" style="text-align: center">
            <div id="piechart" protein=${userMealSum.proteinPercent} carbohydrate=${userMealSum.carbohydratePercent} fat=${userMealSum.fatPercent}></div>
        </td>
    </tr>
    <tr>
        <td><@spring.message "foodContentHeader.carbohydrate"/></td>
        <td id="carbohydrate-header-val">${userMealSum.carbohydrate}g</td>
    </tr>
    <tr>
        <td><@spring.message "foodContentHeader.fat"/></td>
        <td id="fat-header-val">${userMealSum.fat}g</td>
    </tr>
</table>



</div>