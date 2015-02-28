<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<div class="container" >
<@form.form id="loginForm" modelAttribute="userModelAttribute" method="post" action="/login" role="form" cssClass="form">
    <div class="form-group col-xs-5-login">
        <@form.label path="" for="username" ><@spring.message code="loginFormContent.username"/></@form.label>
        <@form.input id="username" path="username" cssClass="form-control" />
    </div>
    <div class="form-group col-xs-5-login" >
        <@form.label path="" for="password" ><@spring.message code="loginFormContent.password"/></@form.label>
        <@form.input type="password" id="password" path="password" cssClass="form-control" />
    </div>

    <div class="form-group col-xs-5-login">
        <input type="submit" value="<@spring.message "loginFormContent.submit"/>" class="btn btn-primary"/>
    </div>
</@form.form>




</div>

