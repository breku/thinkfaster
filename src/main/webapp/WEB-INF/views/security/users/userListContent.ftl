<div id="userListContent" class="container">

    <table class="table table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th><@spring.message "userListContent.table.username"/></th>
            <th><@spring.message "userListContent.table.email"/></th>
            <th><@spring.message "userListContent.table.creationDate"/></th>
            <th><@spring.message "userListContent.table.roles"/></th>
            <th>asdf</th>
            <th>fasdf</th>
        </tr>
        </thead>
    <#list userList as user>
        <tr>
            <td>${user_index + 1}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.creationDate?string("dd-MM-yyyy")}</td>
            <td>
                <#list user.authorities as authority>
                    ${authority}
                </#list>
            </td>
            <td><#include "addRoleForm.ftl" /></td>
            <td><#include "removeRoleForm.ftl" /></td>
        </tr>
    </#list>

    </table>



</div>