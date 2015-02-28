<#include  "../global/init.ftl" />
<html>
<head>
    <#include "../global/meta.ftl" />
    <#include "../global/header.ftl" />
    <title><@spring.message "kcal.title.default"/></title>
</head>

<body>
    <div class="globalWrapper">

        <#include "../component/navbar.ftl" />



        <div class="index">
            <#include "indexContent.ftl" />
        </div>


        <#include "../global/footer.ftl" />
    </div>
</body>
</html>