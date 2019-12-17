$("#reg_sub").click(function () {
    var forData = $("#submitForm").serialize();
    forData = decodeURIComponent(forData, true);
    var token=getCookie("token")
    $.ajax({
        url: "../user/reg",
        data: forData,
        headers: {
            "token":token
        },
        type: "POST",
        dataType: "json",
        success: function (json) {
            console.log(json);
            if (json.code ==200) {
                alert("注册成功");
                location.href="reg.html";
               // window.location.reload(true);
            } else {
                alert(json.message);
                location.href="reg.html";
            }
        },
        "error":function(){
            alert("注册失败,请重新登录");
            location.href="login.html";
        }
    });
});
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

    if(arr=document.cookie.match(reg)){
        return unescape(arr[2]);
    }
    else{
        return null;
    }
}

