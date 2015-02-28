<#macro carouselItem active=false slide="">

<#assign itemClass>item</#assign>
<#if active>
    <#assign itemClass>${itemClass} + active</#assign>
</#if>

<div class="${itemClass}">
    <h2>Slide ${slide}</h2>
    <div class="carousel-caption">
        <img class="carousel-item-img" src="/resources/img/carousel/horse${slide}.png"/>
        <h3>Slide label</h3>
        <p>Lorem ipsum dolor sit amet consectetur…</p>
    </div>
</div>
</#macro>