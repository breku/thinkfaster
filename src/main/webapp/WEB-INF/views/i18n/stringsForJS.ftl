<#import "/spring.ftl" as spring />

var messages = new Array();
<#list keys as key>
messages["${key}"] = "<@spring.message code="${key}"/>";
</#list>

