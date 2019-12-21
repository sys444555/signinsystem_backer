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
                        + "<td data-status='#{statusnum}'>#{status}</td>"
                        + "<td id='td#{id}'><a href='javascript:void(0)' target='_blank' onclick='showClassInfo(#{id},\"#{className}\",\"#{courseName}\",\"#{classHour}\",\"#{teacherName}\")'>查看详情</a></td>"
                        + "</tr>"

                    html = html.replace(/#{id}/g,dataList[i].id)
                    html = html.replace(/#{className}/g,dataList[i].className)
                    html = html.replace(/#{courseName}/g,dataList[i].name)
                    html = html.replace(/#{classHour}/g,dataList[i].classHour)
                    html = html.replace(/#{teacherName}/g,dataList[i].teacherName)
                    html = html.replace("#{createTime}",dataList[i].createTime)
                    html = html.replace(/#{status}/g,dataList[i].status == 0? '禁用' : "启用")
                    html = html.replace("#{statusnum}",dataList[i].status)

                    $("#class_td").append(html);

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
                                            + "<td id='td#{id}'><a href='javascript:void(0)' onclick='showClassInfo(#{id},\"#{className}\",\"#{courseName}\",\"#{classHour}\",\"#{teacherName}\"})' target='_blank'>查看详情</a></td>"
                                            + "</tr>"

                                        html = html.replace(/#{id}/g,dataList[i].id)
                                        html = html.replace(/#{className}/g,dataList[i].className)
                                        html = html.replace(/#{courseName}/g,dataList[i].name)
                                        html = html.replace(/#{classHour}/g,dataList[i].classHour)
                                        html = html.replace(/#{teacherName}/g,dataList[i].teacherName)
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

})

function addStudent() {

    $("#class_student_alter").show();
    $("#class_info_alter").hide()

}

function showClassInfo(classId,className,courseName,classHour,teacherName) {

    sessionStorage.setItem("classId",classId)

    if(isEmpty(classId)) {
        alert("班级id无法获取！")
        return;
    }
    $("#class_info_alter").show()

    $(".classInfoHead table").empty()
    //初始化信息
    var html = "<tr>" +
            "<td>班级名："+className+"</td>" +
            "<td>班级课时："+classHour+"</td>" +
        "</tr>" +
        "<tr>" +
            "<td>所属课程："+courseName+"</td>" +
            "<td>负责老师："+teacherName+"</td>" +
        "</tr>"

    $(".classInfoHead table").append(html)

    //加载当前班的所有学员
    $.ajax({
        url:'http://localhost:8080/class/getStudent/'+classId,
        type:'get', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "pageNo" : 1,
            "pageSize":99999
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){

            $("#studentTable tbody").empty();

            //console.log(data)；
            if(data.code == 0){

                var dataList = data.data.list

                for(var i = 0;i<dataList.length ; i++){

                    var html = '<tr data-id="#{id}">'
                    + '<td class="td_lable"><span>#{name}</span></td>'
                    + '<td class="td_lable" onclick="addLesson(#{id})" style="cursor: pointer"><span style="color: lightsalmon">为该学员添加课包</span></td>'
                    + '<td class="td_lable"><a href="javascript:void(0)" onclick="showStudent(#{id})">查看详情</a></td>>'
                    + '<tr>'

                    html = html.replace(/#{id}/g,dataList[i].id)
                    html = html.replace(/#{name}/g,dataList[i].name)

                    $("#studentTable tbody").append(html)

                }

            }else{
                alert("获取班级学员信息失败！")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })

    //加载课时信息
    $.ajax({
        url:'http://localhost:8080/class/lesson/list',
        type:'get', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "pageNo":1,
            "pageSize":9999,
            "cid" : sessionStorage.getItem("classId")
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            $("#lessonTable tbody").empty();
            if(data.code == 0){

                var dataList = data.data.list

                for(var i = 0;i<dataList.length ; i++){


                    var html = '<tr data-id="#{id}">'
                    + '<td class="td_lable"><span>#{name}</span></td>'
                    + '<td class="td_lable"><span>课时段： #{time}</span></td>'
                    + '<td class="td_lable"><span>节课 加字段</span></td>'
                    + '<td class="td_lable"><a href="javascript:void(0)" onclick="showLessonInfo(#{id}})">详情操作</a></td>'
                    + '<tr>'

                    html = html.replace(/#{id}/g,dataList[i].id)
                    html = html.replace(/#{name}/g,dataList[i].name)
                    html = html.replace(/#{time}/g,dataList[i].startDate + " - " + dataList[i].endDate)

                    $("#lessonTable tbody").append(html)
                }
            }else{
                alert("获取该班级课时失败！")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })


}

var studentId;

function showStudent(studentId) {

    studentId = studentId;



}


function cancelClassModal1() {
    $("#class_info_alter").hide()
}

function saveStudent() {

    if(isEmpty(sessionStorage.getItem("classId"))){
        alert("获取班级id失败")
        return;
    }

    if(isEmptyForm()){
        $.ajax({
            url:'http://localhost:8080/student/create',
            type:'POST', //GET
            async:true,    //或false,是否异步
            headers:{
                "token":getCookie("token")
            },
            data:{
                "name":$("input[name=name]").val(),
                "gender":$("select[name=gender]").val(),
                "birth":changeDate($("input[name=birth]").val()),
                "guarder":$("input[name=guarder]").val(),
                "guarderPhone":$("input[name=guarderPhone]").val(),
                "address":$("input[name=address]").val(),
                "cid" : sessionStorage.getItem("classId")
            },
            timeout:5000,    //超时时间
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success:function(data){
                if(data.code == 0){
                    alert("新增学员成功，请在班级查看详情查看！")
                    $("#class_student_alter").hide()
                    // $("#class_info_alter").show().reload()
                    //模拟点击一下事件
                }else{
                    alert("新增学员失败！")
                }
            },
            error:function () {
                alert("服务器存在异常，请稍后再试！")
            }
        })
    }else{
        alert("信息有为空，请确认！")
        return
    }

}

function cancelClassModal() {
    $("#class_student_alter").hide()
    $("#class_info_alter").show()
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

//修改日期格式
function changeDate(str) {
    return str.replace(/-/g,"/");
}