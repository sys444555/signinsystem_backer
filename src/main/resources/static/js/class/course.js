$(function () {

    $.ajax({
        url:'http://localhost:8080/course/list',
        type:'GET', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "pageNo" : 1,
            "pageSize" : 20
        },
        timeout:50000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            if(data.code == 0){
                var dataList = data.data.list;

                for (var i = 0; i < dataList.length; i++) {
                    var html = "<tr style='text-align: center'>"
                        + "<td>#{index}</td>"
                        + "<td>#{name}</td>"
                        + "<td data-id='#{id}' style='color: firebrick;cursor: pointer' onclick='createClass(this)'>(点击这里) 添加班级</td>"
                        + "</tr>"

                    html = html.replace(/#{id}/g, dataList[i].id)
                    html = html.replace(/#{index}/g, i+1)
                    html = html.replace(/#{name}/, dataList[i].name)

                $("#t_table tbody").append(html)
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
                            url: '',
                            type: 'POST', //GET
                            async: true,    //或false,是否异步
                            headers: {
                                "token": getCookie("token")
                            },
                            data: {
                                "pageNo": 1,
                                "pageSize": 20
                            },
                            timeout: 5000,    //超时时间
                            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                            success: function (data) {
                                if (data.code == 0) {
                                    var dataList = data.data.list;

                                    for (var i = 0; i < dataList.length; i++) {
                                        var html = "<tr style='text-align: center'>"
                                            + "<td>#{index}</td>"
                                            + "<td>#{name}</td>"
                                            + "<td data-id='#{id}' style='color: firebrick' onclick='createClass(this)'>(点击这里) 添加班级</td>"
                                            + "</tr>"

                                        html = html.replace(/"#{id}"/g, dataList[i].id)
                                        html = html.replace(/#{index}/g, i+1)
                                        html = html.replace(/#{name}/, dataList[i].name)

                                        $("#t_table tbody").append(html)
                                    }
                                }
                            }
                        })
                    }
                })

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
        timeout:50000,    //超时时间
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
//-------------------------------------- ------------------------------------

function cc() {
    $("#course_create_alter").hide()
}


function saveClass() {
    if(!(isEmpty(sessionStorage.getItem("clickCourseId")))){
        alert("无法获取课程编号，请稍后再试！")
        return;
    }

    var form = $("#createClassForm").serialize();

    if(!(isEmpty($("input[name='className']").val()))){
        alert("班级名称未填写，请确认")
        return;
    }else if(!(isEmpty($("#teacherName").val()))){
        alert("任课老师未填写，请确认")
        return;
    } else if(form.indexOf("classHour=-1")>0){
        alert("课时未选择！")
        return;
    }else {
        $.ajax({
            url: 'http://localhost:8080/class/create',
            type: 'POST', //GET
            async: true,    //或false,是否异步
            headers: {
                "token": getCookie("token")
            },
            data: {
                "className": $("input[name=className]").val(),
                "classHour": $("select[name=classHour]").val(),
                "teacherName": $("#teacherName").val(),
                "cId": sessionStorage.getItem("clickCourseId")
            },
            timeout: 5000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data) {
                //console.log(data)；
                if (data.code == 0) {
                    alert("新增班级至课程成功，请在左侧班级查看详细!")
                    window.location.reload()
                }else{
                    alert("添加失败！")
                }
            },
            error: function () {
                alert("服务器异常，请稍后再试！")
            }
        })
    }
}

function createClass(obj) {


    sessionStorage.setItem("clickCourseId",$(obj).data().id);

    $("#class_create_alter").show();

}

function cancelClassModal1() {
    $("#class_create_alter")[0].reset;
    $("#class_create_alter").hide()
}

function saveCourse() {
    if(!(isEmpty($("#courseName").val()))){
        alert("信息为空，请确认！")
        return
    }
    $.ajax({
        url:'http://localhost:8080/course/create',
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "name" : $("#courseName").val()
        },
        timeout:50000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            if(data.code == 0){
                alert("新增成功！")
                window.location.reload()
            }else{
                alert("新增失败！")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })
}

function createCourse() {
    $("#course_create_alter").show()
}

function cancelClassModal() {
    $("#course_create_alter")[0].reset;
    $("#course_create_alter").hide()
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

