<#include  "../global/init.ftl" />
<html>
<head>
    <#include "../global/meta.ftl" />
    <#include "../global/header.ftl" />
    <title><@spring.message "kcal.title.default"/></title>
</head>

<body>
<div class="globalWrapper">

        <div style="text-align: center" class="container">
            <h1><@spring.message code="error.message"/></h1>
            <h5><@spring.message code="error.statusCode"/>: ${statusCode}</h5>
            <div style="width: 50%; margin: 0 auto; text-align: center;">
                <img class="index-img" src="/resources/img/error/error.gif" style="width: 400px; height: 400px;">
            </div>
            <h4><a href="/"><@spring.message code="error.return"/></a></h4>
        </div>

</div>
</body>
</html>