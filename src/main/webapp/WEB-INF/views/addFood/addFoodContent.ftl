<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<div class="container" >

<@form.form id="addNewFoodForm" modelAttribute="foodModelAttribute" method="post" action="/addNew" role="form" cssClass="form-inline">
    <div class="form-group col-xs-1-5">
        <@form.label path="" for="name" ><@spring.message code="addFoodContent.form.name"/></@form.label>
        <@form.input id="name" path="name" cssClass="form-control input-sm-width" />
    </div>
    <div class="form-group col-xs-1-5">
        <@form.label path="" for="count" ><@spring.message code="addFoodContent.form.count"/></@form.label>
        <@form.select id="count" path="count" items=countTypes cssClass="form-control input-sm-width" />

        <#--<@form.input  id="count" path="count" cssClass="form-control input-sm-width" />-->
    </div>
    <div class="form-group col-xs-1-5">
        <@form.label path="" for="weight" ><@spring.message code="addFoodContent.form.avgweight"/></@form.label>
        <@form.input  id="weight" path="weight" cssClass="form-control input-sm-width" />
    </div>
    <div class="form-group col-xs-1-5">
        <@form.label path="" for="protein" ><@spring.message code="addFoodContent.form.protein"/></@form.label>
        <@form.input id="protein" path="protein" cssClass="form-control input-sm-width" />
    </div>
    <div class="form-group col-xs-1-5">
        <@form.label path="" for="carbohydrate" ><@spring.message code="addFoodContent.form.carbohydrate"/></@form.label>
        <@form.input  id="carbohydrate" path="carbohydrate" cssClass="form-control input-sm-width"  />
    </div>
    <div class="form-group col-xs-1-5">
        <@form.label path="" for="fat" ><@spring.message code="addFoodContent.form.fat"/></@form.label>
        <@form.input  id="fat" path="fat" cssClass="form-control input-sm-width"  />
    </div>
    <div class="form-group col-xs-1-5">
        <@form.label path="" for="kcal" ><@spring.message code="addFoodContent.form.kcal"/></@form.label>
        <@form.input  id="kcal" path="kcal" cssClass="form-control input-sm-width"  />
    </div>
    <div class="form-group col-xs-1-5">
        <input type="submit" value="<@spring.message "addNewContent.submit"/>" class="btn btn-primary"/>
    </div>
</@form.form>




</div>

