<#include "../../util/formMacros.ftl"/>
<@form.form id="newHorseForm" modelAttribute="horseModelAttribute" method="post" action="/user/horses/add" role="form" cssClass="form">

    <@formInput "name" "addNewHorseForm.name" />
    <@formInput "breed" "addNewHorseForm.breed" />
    <@formInput "birthDate" "addNewHorseForm.birthDate" />
    <@formInput "color" "addNewHorseForm.color" />
    <@formInput "sex" "addNewHorseForm.sex" />
    <@formInput "owner" "addNewHorseForm.owner" />

    <@formSubmit "addNewHorseForm.submit" />

</@form.form>