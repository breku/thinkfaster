<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<div id="profileContent" class="container">

    <div class="alert alert-success fade alert-sticked" ></div>
    <div class="alert alert-danger fade alert-sticked" ></div>

    <h1><@spring.message "profileContent.header"/></h1>

    <table id="user-profile-table" class="table table-hover">
        <tr>
            <td colspan="3"><label><@spring.message "profileContent.table.height"/></label></td>
            <td><a href="#" id="weight" class="editable" data-name="height" data-type="text" data-pk="1"
                   data-url="/user/profile/edit">${userProfile.height}</a></td>
        </tr>
        <tr>
            <td colspan="3"><label><@spring.message "profileContent.table.weight"/></label></td>
            <td><a href="#" id="weight" class="editable" data-name="weight" data-type="text" data-pk="2"
                   data-url="/user/profile/edit">${userProfile.weight}</a></td>
        </tr>
        <tr>
            <td colspan="3"><label><@spring.message "profileContent.table.kcal"/></label></td>
            <td><a href="#" id="weight" class="editable" data-name="kcalPerDay" data-type="text"
                   data-pk="3" data-url="/user/profile/edit">${userProfile.kcalPerDay}</a></td>
        </tr>

        <tr>
            <td colspan="3"><label><@spring.message "profileContent.table.meals.number"/></label></td>
            <td><a href="#" id="mealsPerDay" class="editable" data-name="mealsPerDay" data-type="text"
                   data-pk="4" data-url="/user/profile/edit">${userProfile.mealsPerDay}</a></td>
        </tr>


    <#-- SLIDERS -->
        <tr>
        <@form.form id="slider-form" modelAttribute="sliderModel" method="post" action="/user/profile/edit/slider" role="form" cssClass="form">
            <td>
                <div class="form-group">
                    <@form.label path="" for="proteinPercent" ><span
                            class="profile-slider-label"><@spring.message "profileContent.table.slider.protein"/></span></@form.label>
                </div>
                <div class="form-group">
                    <@form.label path="" for="carbohydratePercent" ><span
                            class="profile-slider-label"><@spring.message "profileContent.table.slider.carbohydrate"/></span></@form.label>
                </div>
                <div class="form-group">
                    <@form.label path="" for="fatPercent" ><span
                            class="profile-slider-label"><@spring.message "profileContent.table.slider.fat"/></span></@form.label>
                </div>

            </td>
            <td>
                <div class="form-group">
                    <@form.input path="proteinPercent" class="profile-slider" id="slider-protein" type="text" value="${sliderModel.proteinPercent?int}"/>
                    <@form.errors path="proteinPercent" cssClass="has-error control-label" />
                </div>
                <div class="form-group">
                    <@form.input path="carbohydratePercent" class="profile-slider" id="slider-carbohydrate" type="text" value="${sliderModel.carbohydratePercent?int}"/>
                    <@form.errors path="carbohydratePercent" cssClass="has-error control-label" />
                </div>
                <div class="form-group">
                    <@form.input path="fatPercent" class="profile-slider" id="slider-fat" type="text" value="${sliderModel.fatPercent?int}" />
                    <@form.errors path="fatPercent" cssClass="has-error control-label" />
                </div>
                <div class="form-group">
                    <a href="#" onclick="submit_sliders()" class="btn btn-primary"><@spring.message "profileContent.table.slider.submit"/></a>
                </div>
            </td>

            <td>
                <div class="form-group"><span class="profile-slider-label-val-proteinPercent">${sliderModel.proteinPercent?int}</span>%
                </div>
                <div class="form-group"><span
                        class="profile-slider-label-val-carbohydratePercent">${sliderModel.carbohydratePercent?int}</span>%
                </div>
                <div class="form-group"><span class="profile-slider-label-val-fatPercent">${sliderModel.fatPercent?int}</span>%</div>
            </td>

            <td>
                <div class="form-group"><span class="profile-slider-label-val-protein">${sliderModel.protein?int}</span>g
                </div>
                <div class="form-group"><span
                        class="profile-slider-label-val-carbohydrate">${sliderModel.carbohydrate?int}</span>g
                </div>
                <div class="form-group"><span class="profile-slider-label-val-fat">${sliderModel.fat?int}</span>g</div>
            </td>
        </@form.form>
        </tr>


    </table>


</div>
