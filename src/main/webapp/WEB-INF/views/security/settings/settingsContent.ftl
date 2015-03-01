<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<div id="settingsContent" class="container">


    <table class="table table-hover">
        <thead>
        <tr>
            <th><@spring.message "settingsContent.table.property"/></th>
            <th><@spring.message "settingsContent.table.currentValue"/></th>
            <th><@spring.message "settingsContent.table.newValue"/></th>
        </tr>
        </thead>
        <tr>
            <td><@spring.message "settingsContent.form.logger"/></td>
            <td>${currentLoggerLevel}</td>
            <td><#include "loggerForm.ftl"/></td>
        </tr>


        <tr>
            <td><@spring.message "settingsContent.table.userList"/></td>
            <td></td>
            <td><a href="/admin/users/list"><@spring.message "settingsContent.table.link"/></a></td>
        </tr>
        <tr>
            <td><@spring.message "settingsContent.table.addNew"/></td>
            <td></td>
            <td><a href="/add/food"><@spring.message "settingsContent.table.link"/></a></td>
        </tr>
        <tr>
            <td><@spring.message "settingsContent.table.addNewCountTypes"/></td>
            <td></td>
            <td><a href="/add/countTypes"><@spring.message "settingsContent.table.link"/></a></td>
        </tr>

    </table>


</div>
