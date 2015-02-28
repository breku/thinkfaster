<#include "mealMacro.ftl"/>
<div id="foodContent" class="container">

    <h1>My food</h1>

    <div class="alert alert-success fade alert-sticked"></div>


    <div>
        <div class="col-md-4">
            <ul id="DEFAULT" class="list-group">
                <a class="list-group-item active" href="#"><@spring.message "myFoodContent.meal.notAssigned"/></a>
            <#list foodEntrySetDefault as entry>
                <li id="${entry.key}" class="list-group-item sortable-enabled">${entry.value.name}</li>
            </#list>
            </ul>
        </div>


        <div class="col-md-8">

        <@mealList 0 foodEntrySetMeal0/>
        <@mealList 1 foodEntrySetMeal1/>
        <@mealList 2 foodEntrySetMeal2/>
        <@mealList 3 foodEntrySetMeal3/>
        <@mealList 4 foodEntrySetMeal4/>
        <@mealList 5 foodEntrySetMeal5/>
        </div>


    </div>


</div>
