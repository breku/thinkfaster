<form action="/admin/settings/logger" method="post">
    <div class="btn-group">
        <button class="btn dropdown-toggle" type="button" data-toggle="dropdown">
            +
        </button>
        <ul class="dropdown-menu" role="menu">
        <#list ["SEVERE","WARNING","INFO","CONFIG","FINE","FINER","FINEST"] as level>
            <li><a href="#" onclick="submit_form(this,'/${level}') ">${level}</a></li>
        </#list>
        </ul>
    </div>
</form>