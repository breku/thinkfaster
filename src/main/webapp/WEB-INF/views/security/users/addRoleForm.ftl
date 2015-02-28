<form action="/admin/users/addRole/" method="post">
    <div class="btn-group">
        <button class="btn dropdown-toggle" type="button" data-toggle="dropdown">
            <span class="glyphicon glyphicon-plus"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
        <#list allAuthorities as authority>
            <li><a href="#" onclick="submit_form(this,'/${user.id}/${authority}') ">${authority}</a></li>
        </#list>

        </ul>
    </div>
</form>