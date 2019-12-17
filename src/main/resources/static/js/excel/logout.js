/** Excel导出  **/
function logout() {
    var token = getCookie("token")
    console.log(token)
    $.ajax({
        url: "../house/logout",
        headers: {
            "token": token
        },
        type: "POST",
        async: true,
        success: function () {
            alert("确定退出吗")
            location.href = "login.html";
        },
        "error": function () {
            alert("导出失败");
            window.location.reload(true);
        }
    });
}
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
