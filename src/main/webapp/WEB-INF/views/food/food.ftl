<#include  "../global/init.ftl" />
<html>
<head>
<#include "../global/meta.ftl" />
<#include "../global/header.ftl" />
    <script  src="<@spring.url 'https://www.google.com/jsapi'/>" type="text/javascript"></script>
    <script src="<@spring.url '/strings.js'/>" type="text/javascript"></script>
    <script src="<@spring.url '/resources/js/jquery-table/foodTable.js'/>" type="text/javascript"></script>
    <title><@spring.message "kcal.title.default"/></title>
</head>

<body>
<div class="globalWrapper">

<#include "../component/navbar.ftl" />


    <div class="mainContent">
    <#include "foodContent.ftl" />
    </div>

<#include "../global/footer.ftl" />
</div>
</body>
</html>