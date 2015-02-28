<#include "carousel-macros.ftl"/>
<div id="index-carousel" class="carousel slide" data-ride="carousel">

    <!-- Carousel indicators -->

    <ol class="carousel-indicators">
        <li data-target="#index-carousel" data-slide-to="0" class="active"></li>
        <li data-target="#index-carousel" data-slide-to="1"></li>
        <li data-target="#index-carousel" data-slide-to="2"></li>
    </ol>

    <!-- Carousel items -->

    <div class="carousel-inner">
        <@carouselItem active=true slide=1/>
        <@carouselItem slide=2/>
        <@carouselItem slide=3/>
    </div>

    <!-- Carousel nav -->

    <a class="carousel-control left" href="#index-carousel" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
    </a>

    <a class="carousel-control right" href="#index-carousel" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
    </a>

</div>

