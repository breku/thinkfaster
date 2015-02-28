<#macro mealList  mealNumber foodEntrySet="">

    <#if foodEntrySet?? && foodEntrySet?is_sequence>

        <div>
            <ul id="MEAL${mealNumber}" class="list-group col-md-2">
                <a class="list-group-item active" href="#"><@spring.message "myFoodContent.meal"/> ${mealNumber + 1}</a>
                <#list foodEntrySet as entry>
                    <li id="${entry.key}" class="list-group-item sortable-enabled">${entry.value.name}</li>
                </#list>
            </ul>
        </div>


    </#if>

</#macro>