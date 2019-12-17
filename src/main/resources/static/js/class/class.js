$(function () {

    $.ajax({
        url:'http://localhost:8080/class/list',
        type:'GET', //GET
        async:true,    //或false,是否异步
        headers:{"token":getCookie("token")},
        data:{
            "pageNo": 1,
            "pageSize" :20
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text++
        success:function(data){
            //console.log(data)；

            if(data.code == 0 && data.data.list.length >0) {
                var dataList = data.data.list;

                for (var i = 0; i < dataList.length; i++) {
                    var html = "<tr>"
                        + "<td><input type='checkbox' data-cid='#{id}'></td>"
                        + "<td>#{id}</td>"
                        + "<td>#{className}</td>"
                        + "<td>#{courseName}</td>"
                        + "<td>#{classHour}</td>"
                        + "<td>#{teacherName}</td>"
                        + "<td>#{createTime}</td>"
                        + "<td>#{status}</td>"
                        + "</tr>"

                    html = html.replace("#{id}",dataList[i].id)
                    html = html.replace("#{className}",dataList[i].className)
                    html = html.replace("#{courseName}",dataList[i].courseName)
                    html = html.replace("#{classHour}",dataList[i].classHour)
                    html = html.replace("#{teacherName}",dataList[i].teacherName)
                    html = html.replace("#{createTime}",dataList[i].createTime)
                    html = html.replace("#{status}",dataList[i].status == 0? '禁用' : "启用")

                    $("tbody").append(html);

                }
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