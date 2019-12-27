$(function () {
    $.ajax({
        url:'http://localhost:8080/business/user/get',
        type:'get', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{

        },
        timeout:50000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            //console.log(data)；
            if(data.code == 0){
                $("#yue_fen").html(data.data.msnLeftNumber)

                if(data.data.msnLeftNumber < 100){
                    alert("你的短信套餐包，已经不足100条，请联系负责人进行续费，谢谢！")
                }
            }


        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })
})

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