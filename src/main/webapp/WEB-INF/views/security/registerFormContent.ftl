<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<div class="container" >
<@form.form id="registerForm" modelAttribute="registrationModelAttribute" method="post" action="/register" role="form" cssClass="form">
    <div class="form-group col-xs-5-login">
        <@form.label path="" for="username" ><@spring.message code="registerFormContent.username"/></@form.label>
        <@form.input id="username" value="" path="username" cssClass="form-control" />
        <@form.errors path="username" cssClass="has-error control-label" />
    </div>
    <div class="form-group col-xs-5-login" >
        <@form.label path="" for="email" ><@spring.message code="registerFormContent.email"/></@form.label>
        <@form.input id="email" type="email"  value="" path="email" cssClass="form-control" />
        <@form.errors path="email" cssClass="has-error control-label" />
    </div>
    <div class="form-group col-xs-5-login" >
        <@form.label path="" for="password" ><@spring.message code="registerFormContent.password"/></@form.label>
        <@form.input id="password" value="" type="password"  path="password" cssClass="form-control" />
        <@form.errors path="password" cssClass="has-error control-label" />
    </div>
    <div class="form-group col-xs-5-login" >
        <@form.label path="" for="confirmPassword" ><@spring.message code="registerFormContent.confirmPassword"/></@form.label>
        <@form.input id="confirmPassword" value="" type="password"  path="confirmPassword" cssClass="form-control" />
        <@form.errors path="confirmPassword" cssClass="has-error control-label" />
    </div>


    <div class="form-group col-xs-5-login">
        <input type="submit" value="<@spring.message "registerFormContent.submit"/>" class="btn btn-primary"/>
    </div>
</@form.form>




</div>

