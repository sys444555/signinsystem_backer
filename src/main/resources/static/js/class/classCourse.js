$(function () {

    var cid = getUrlParam("cid")

    sessionStorage.setItem("cid",cid)

    $("#courseBtn").attr("onclick","openCourse("+cid+")")


    if(cid == null || cid == undefined || cid == ""){ alert("拉取数据失败")}else {
        $.ajax({
            url:'http://localhost:8080/class/course/list',
            type:'get', //GET
            async:true,    //或false,是否异步
            headers:{
                "token" : getCookie("token")
            },
            data:{
                pageNo : 1,
                pageSize : 20,
                cid:cid
            },
            timeout:5000,    //超时时间
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success:function(data) {
                if (data.code == 0 && data.data.list.length > 0) {
                    var dataList = data.data.list;

                    for (var i = 0; i < dataList.length; i++) {
                        var html = "<tr data-cid='#{id}'>"
                            + "<td><input type='checkbox' ></td>"
                            + "<td>#{id}</td>"
                            + "<td>#{name}</td>"
                            + "<td>#{classHour}</td>"
                            + "<td>#{startDate}</td>"
                            + "<td>#{endData}</td>"
                            + "<td>#{status}</td>"
                            + "<td><span onclick='addStudentToCourse(#{id})' style='cursor: pointer'>添加学员</span></td>"
                            + "<td><span onclick='showCourse(#{id}})' style='cursor: pointer'>查看详情</span></td>"
                            + "</tr>"

                        html = html.replace(/#{id}/g, dataList[i].id)
                        html = html.replace("#{name}", dataList[i].name)
                        html = html.replace("#{classHour}", dataList[i].classHour)
                        html = html.replace("#{startDate}", dataList[i].startDate)
                        html = html.replace("#{endData}", dataList[i].endDate)
                        html = html.replace("#{status}", dataList[i].status == 0 ? '未进行' : dataList[i].status == 1? '进行中' : '已结束')

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
                                url:'http://localhost:8080/class/getStudent/'+cid,
                                type:'get', //GET
                                async:true,    //或false,是否异步
                                headers:{
                                    "token" : getCookie("token")
                                },
                                data:{
                                    pageNo : 1,
                                    pageSize : 20
                                },
                                timeout:5000,    //超时时间
                                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                                success:function(data) {

                                    $("tbody").html("")

                                    if (data.code == 0 && data.data.list.length > 0) {
                                        var dataList = data.data.list;

                                        for (var i = 0; i < dataList.length; i++) {
                                            var html = "<tr data-cid='#{id}'>"
                                                + "<td><input type='checkbox' ></td>"
                                                + "<td>#{id}</td>"
                                                + "<td>#{name}</td>"
                                                + "<td>#{classHour}</td>"
                                                + "<td>#{startDate}</td>"
                                                + "<td>#{endData}</td>"
                                                + "<td>#{status}</td>"
                                                + "<td><span onclick='addStudentToCourse(#{id})'>添加学员</span></td>"
                                                + "<td><span onclick='showCourse(#{id}})'></span></td>"
                                                + "</tr>"

                                            html = html.replace(/#{id}/g, dataList[i].id)
                                            html = html.replace("#{name}", dataList[i].name)
                                            html = html.replace("#{classHour}", dataList[i].classHour)
                                            html = html.replace("#{startDate}", dataList[i].startDate)
                                            html = html.replace("#{endData}", dataList[i].endData)
                                            html = html.replace("#{status}", dataList[i].status == 0 ? '未进行' : dataList[i].status == 1? '进行中' : '已结束')

                                            $("tbody").append(html);

                                        }
                                    }
                                }
                            })
                        }
                    });

                }
            }
        })
    }
})

function createCourse() {
    $("#course_create_alter").show()

    laydate.render({
        elem : '#dataRange',
        type: 'datetime',
        range: true
    })

}

function saveStudentToCourse(coid) {

    var cl = $("#studentList").find("div[data-status=1]");

    var studentList = [];

    for(var i=0 ;i<cl.length;i++){
        studentList.push(cl[i].dataset.sid)
    }

    $.ajax({
        url:'http://localhost:8080/class/course/students/create',
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        traditional: true,//这里设置为true
        data:{

            "coid" : coid,
            "studentList" : studentList
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            //console.log(data)；
            if(data.code == 0){
                alert("学生已经添加到课程")
                window.location.reload()
            }else{
                alert("学生添加课程失败！")
            }
        },
        error:function () {
            alert("服务器存在异常，请稍后再试！")
        }
    })


}

function addStudentToCourse(coid) {
    $("#insert_student_alter").show();

    $("#studentList").empty()

    $.ajax({
        url:'http://localhost:8080/class/getStudent/'+sessionStorage.getItem("cid"),
        type:'GET', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            pageNo : 1,
            pageSize : 20
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            //console.log(data)；

            if (data.code == 0 && data.data.list.length > 0) {

                $("#saveStudent").attr("onclick","saveStudentToCourse("+coid+")")

                var dataList = data.data.list;

                for (var i = 0; i < dataList.length; i++) {
                    var html = '<div style="width: 200px;margin-bottom: 10px" id="studentDiv" data-sid="#{id}">'
                    + '<span>#{name}</span>'
                    + '<input  class="btn-add" style="margin-left: 50px" type="button" value="加入课时" onclick="changeTextAndStatus(this)"/>'

                    html = html.replace("#{id}",dataList[i].id)
                    html = html.replace("#{name}",dataList[i].name)

                    $("#studentList").append(html)

                }
            }
        }
    })


}

function changeTextAndStatus(obj) {
    if($(obj).parent().attr("data-status") == undefined){
        $(obj).val("移出课时")
        $(obj).parent().attr("data-status",1)
    }else {
        $(obj).val("加入课时")
        $(obj).parent().removeAttr("data-status")
    }
}



function saveCourse() {

    var name = $("input[name=name]").val()

    var classHour = $("select[name=classHour]").val()

    //"2019-12-13 01:01:01 - 2019-12-13 02:02:06"
    var dataRange = $("#dataRange").val();

    if((!isEmpty(name) && isEmpty(classHour) && isEmpty(dataRange))){
        alert("存在信息未填写，请确认！")
        return
    }

    $.ajax({
        url:'http://localhost:8080/class/course/create',
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "name" : name,
            "classHour" : classHour,
            "dataRange" : dataRange,
            "classId": sessionStorage.getItem("cid")
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            if(data.code == 0){
                alert("添加课时成功！")
                window.location.reload()
            }else{
                alert("添加课时失败！")
            }
        },
        error:function () {
            alert("服务器存在异常，请稍后再试！")
        }
    })

}


function cancelClassModal() {
    $("#course_create_alter")[0].reset
    $("#course_create_alter").hide()
}

function cancelClassModal1() {
    $("#insert_student_alter")[0].reset
    $("#insert_student_alter").hide()
}


function isEmptyForm(){
    var flag=true;
    $(".modal-body").find("input").each(function(i,item){
        if(item.value=="" || item.value == undefined || item.value == null){
            flag=false;
            return false;
        }
    });
    return flag;
}


function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象

    var r = window.location.search.substr(1).match(reg); //匹配目标参数

    if (r != null) return unescape(r[2]);
    return null; //返回参数值

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

//修改日期格式
function changeDate(str) {
    return str.replace(/-/g,"/");
}

//如果为空 则返回true  不为空则返回false
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return false;
    }else{
        return true;
    }
}

