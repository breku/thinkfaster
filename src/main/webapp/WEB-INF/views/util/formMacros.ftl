<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>

<#macro formInput inputName="" springMessage="">
<div class="form-group col-xs-5-login">
<@form.label path="" for="${inputName}" ><@spring.message code="${springMessage}"/></@form.label>
        <@form.input id="${inputName}" value="" path="${inputName}" cssClass="form-control" />
        <@form.errors path="${inputName}" cssClass="has-error control-label" />
</div>
</#macro>

<#macro formSubmit springMessage="">
<div class="form-group col-xs-5-login">
    <input type="submit" value="<@spring.message "${springMessage}"/>" class="btn btn-primary"/>
</div>
</#macro>