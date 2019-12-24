function login() {
    var forData = $("#submitForm").serialize();
    forData = decodeURIComponent(forData, true);
    var username = $("#username").val();
    $.ajax({
        url: "../login",
        data: forData,
        type: "POST",
        dataType: "json",
        success: function (json) {
            console.log(json);
            if (json.code == 200) {
                setCookie("token", json.message);
                sessionStorage.setItem("username", username);
                location.href = "index.html";
            } else {
                alert(json.message);
            }
        },
        "error": function () {
            alert("您登录信息已过期，请重新登录");
            window.location.href="http://localhost:8080/login";
        }
    });

}

function setCookie(name,value)
{
    var exp = new Date();
    exp.setTime(exp.getTime() + 2*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

/*回车事件*/
function EnterPress(e){ //传入 event
    var e = e || window.event;
    if(e.keyCode == 13){
      login();
    }
}


