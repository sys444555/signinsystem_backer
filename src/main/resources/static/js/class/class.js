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
                    var html = "<tr data-cid='#{id}'>"
                        + "<td><input type='checkbox' ></td>"
                        + "<td>#{id}</td>"
                        + "<td>#{className}</td>"
                        + "<td>#{courseName}</td>"
                        + "<td>#{classHour}</td>"
                        + "<td>#{teacherName}</td>"
                        + "<td>#{createTime}</td>"
                        + "<td data-status='#{statusnum}'>#{status}</td>"
                        + "<td><a href='class_student.html?cid=#{id}' target='_blank'>查看详情</a></td>"
                        + "</tr>"

                    html = html.replace(/#{id}/g,dataList[i].id)
                    html = html.replace("#{className}",dataList[i].className)
                    html = html.replace("#{courseName}",dataList[i].courseName)
                    html = html.replace("#{classHour}",dataList[i].classHour)
                    html = html.replace("#{teacherName}",dataList[i].name)
                    html = html.replace("#{createTime}",dataList[i].createTime)
                    html = html.replace(/#{status}/g,dataList[i].status == 0? '禁用' : "启用")
                    html = html.replace("#{statusnum}",dataList[i].status)

                    $("tbody").append(html);

                }

                $(".M-box").pagination({
                    pageCount: data.data.pages,   //总页码ajax中的pages总页
                    coping: true,                 //是否开启首页和末页
                    jump: true,                   //是否开启跳页
                    homePage: '首页',
                    endPage: '末页',
                    prevContent: '上页',
                    nextContent: '下页',
                    current: data.data.pageNum,
                    callback: function (api) {
                        $.ajax({
                            url:'http://localhost:8080/class/list',
                            type:'GET', //GET
                            async:true,    //或false,是否异步
                            headers:{"token":getCookie("token")},
                            data: {pageNo: api.getCurrent(), pageSize: 20},
                            dataType: "json",
                            success: function (data) {
                                $("#class_td").html("")
                                if(data.code == 0 && data.data.list.length >0) {
                                    var dataList = data.data.list;

                                    for (var i = 0; i < dataList.length; i++) {
                                        var html = "<tr data-cid='#{id}'>"
                                            + "<td><input type='checkbox' ></td>"
                                            + "<td>#{id}</td>"
                                            + "<td>#{className}</td>"
                                            + "<td>#{courseName}</td>"
                                            + "<td>#{classHour}</td>"
                                            + "<td>#{teacherName}</td>"
                                            + "<td>#{createTime}</td>"
                                            + "<td>#{status}</td>"
                                            + "<td><a href='class_student.html?cid=#{id}' target='_blank'>查看详情</a></td>"
                                            + "</tr>"

                                        html = html.replace(/#{id}/g,dataList[i].id)
                                        html = html.replace("#{className}",dataList[i].className)
                                        html = html.replace("#{courseName}",dataList[i].courseName)
                                        html = html.replace("#{classHour}",dataList[i].classHour)
                                        html = html.replace("#{teacherName}",dataList[i].name)
                                        html = html.replace("#{createTime}",dataList[i].createTime)
                                        html = html.replace(/#{status}/g,dataList[i].status == 0? '禁用' : "启用")
                                        html = html.replace("#{statusnum}",dataList[i].status)

                                        $("#class_td").append(html);

                                    }
                                }
                            }
                        });
                    }
                });
            }

        }
    })

    //加载老师数据到选项框
    $.ajax({
        url:'http://localhost:8080/teacher/list',
        type:'GET', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{

        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){

            if(data.code == 0){
                var dataList = data.data
                for (var i = 0; i < dataList.length; i++) {
                    var html = "<option value='"+dataList[i].id+"'>"+dataList[i].name+"</option>"
                    $("#tt").append(html)
                }
            }
        },
        error:function () {
            alert("拉取老师数据失败!")
        }
    })




})

function createClass() {
    $("#class_create_alter").show()
}

function cancelClassModal() {
    $("#class_create_alter").hide()
    $("#createClassForm")[0].reset()
}

function saveClass() {

    var form = $("#createClassForm").serialize();

    if(form.indexOf("classHour=-1")>0){
        alert("课时未选择！")
        return;
    }else if(form.indexOf("tId=-1")>0){
        alert("任课教师未选择！")
        return;
    }else if(isEmpty($("input[name='className']").val()) || isEmpty($("input[name='courseName']").val())){
        alert("存在信息未填写，请确认")
        return;
    }else{
        $.ajax({
            url:'http://localhost:8080/class/create',
            type:'POST', //GET
            async:true,    //或false,是否异步
            headers:{
                "token" : getCookie("token")
            },
            data:{
                "className" : $("input[name=className]").val(),
                "courseName" : $("input[name=courseName]").val(),
                "classHour" : $("select[name=classHour]").val(),
                "tId": $("select[name=tId]").val()
            },
            timeout:5000,    //超时时间
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success:function(data){
                //console.log(data)；
                if(data.code == 0){
                    alert("新增成功!")
                    window.location.reload()
                }
            },
            error:function () {
                alert("服务器异常，请稍后再试！")
            }
        })
    }
}


function updateStatus() {
    if($("input:checked").length != 1){
        alert("请选择一条数据！")
        return;
    }

    var id = $("input:checked").parent().parent().data().cid;
    var status = $("input:checked").parent().parent().find(":last").prev().data().status == 0? 1 : 0;

    $.ajax({
        url:'http://localhost:8080/class/update/'+id+"/"+status,
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{

        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            //console.log(data)；
            if(data.code == 0){
                alert("修改成功!")
                window.location.reload()
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })
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

//如果为空 则返回true  不为空则返回false
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}