<form action="/admin/users/removeRole" method="post">
    <div class="btn-group">
        <button class="btn dropdown-toggle" type="button" data-toggle="dropdown">
            <span class="glyphicon glyphicon-minus"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
        <#list user.authorities as authority>
            <li><a href="#" onclick="submit_form(this,'/${user.id}/${authority}') ">${authority}</a></li>
        </#list>
        </ul>
    </div>
</form>