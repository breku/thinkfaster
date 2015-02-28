<#include  "../../global/init.ftl" />
<html>
<head>
<#include "../../global/meta.ftl" />
<#include "../../global/header.ftl" />
    <title><@spring.message "kcal.title.default"/></title>
    <script src="<@spring.url '/strings.js'/>" type="text/javascript"></script>
    <script src="<@spring.url '/resources/js/jquery-ui/my-food-sortable.js'/>" type="text/javascript"></script>


</head>

<body>
<div class="globalWrapper">

<#include "../../component/navbar.ftl" />

    <div class="mainContent">
    <#include "myFoodContent.ftl" />
    </div>

<#include "../../global/footer.ftl" />
</div>
</body>
</html>