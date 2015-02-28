<div id="foodContent" class="container">

    <div class="alert alert-success fade alert-sticked" ></div>

    <#include "foodContentHeader.ftl"/>


    <table id="foodTable"  data-sort-name="used" data-url="/json/food.json" data-cache="false" data-search="true" data-row-style="rowStyle" onLoadSuccess="initialize_tooltips()">
        <thead>
        <tr>
            <th data-field="used" data-visible="false"></th>
            <th data-field="id" data-visible="false"></th>
            <th data-field="countType" data-align="center" data-formatter="add_remove_one_piece_formatter" data-events="add_remove_one_piece_events"><@spring.message "foodContent.table.addremoveonepiece"/></th>
            <th data-field="addRemove" data-align="center" data-formatter="add_remove_formatter" data-events="add_remove_events"><@spring.message "foodContent.table.addremove"/></th>
            <#if .lang=="en">
                <th data-field="nameAng" data-sortable="true"><@spring.message "foodContent.table.name"/></th>
            <#else>
                <th data-field="name" data-sortable="true"><@spring.message "foodContent.table.name"/></th>
            </#if>

            <th data-field="weight"><@spring.message "foodContent.table.count"/></th>
            <th data-field="protein" data-sortable="true"><@spring.message "foodContent.table.protein"/></th>
            <th data-field="carbohydrate" data-sortable="true"><@spring.message "foodContent.table.carbohydrate"/></th>
            <th data-field="fat" data-sortable="true"><@spring.message "foodContent.table.fat"/></th>
            <th data-field="kcal" data-sortable="true"><@spring.message "foodContent.table.kcal"/></th>
        </tr>
        </thead>
    </table>

</div>