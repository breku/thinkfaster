function submit_form(element, action) {
    while (element) {
        element = element.parentNode;
        if (element.tagName.toLowerCase() == "form") {
            element.action += action;
            element.submit();
            return;
        }
    }
    return 0; //error: no form found in ancestors
}

function alert_fail(message){
    var alertElem = $(".alert.alert-danger");
    alert(alertElem,message);
}

function alert(alertElem, message){
    $(alertElem).text(message)
    $(alertElem).addClass('fade-in');
    window.setTimeout(function() {
        $(alertElem).removeClass('fade-in');
        $(alertElem).addClass('fade');
    }, 1000);
}

function alert_succes(message){
    var alertElem = $(".alert.alert-success");
    alert(alertElem,message);
}

function initialize_tooltips(){
    $("[data-toggle='tooltip']").tooltip({animation:false});
}