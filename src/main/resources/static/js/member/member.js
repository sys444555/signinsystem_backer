$(function () {

    $.ajax({
        url:'http://localhost:8080/member/list',
        headers:{
            "token" : getCookie("token")
        },
        data:{pageNo:1,pageSize:20},
        type:'GET', //GET
        async:true,    //或false,是否异步
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            if(data.code == 0 && data.data.length >0){
                var dataList = data.data;

                for(var i=0;i<dataList.length;i++){
                    var html = "<tr>"
                    + "<th></th>"
                    + "<th>#{id}</th>"
                    + "<th>#{name}</th>"
                    + "<th>#{gender}</th>"
                    + "<th>#{birth}</th>"
                    + "<th>#{guarder}</th>"
                    + "<th>#{guarderPhone}</th>"
                    + "<th>#{address}</th>"
                    + "<th>#{createTime}</th>"
                    + "<th>#{status}</th>"

                }

                //<tr>
                // 							<th></th>
                // 							<th>序号</th>
                // 							<th>学生姓名</th>
                // 							<th>学生性别</th>
                // 							<th>学生生日</th>
                // 							<th>学生监护人</th>
                // 							<th>监护人手机</th>
                // 							<th>住址</th>
                // 							<th>创建时间</th>
                // 							<th>状态</th>
                // 						</tr>
            }
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
