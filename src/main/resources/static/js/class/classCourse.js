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

function saveCourse() {

    var name = $("input[name=name]").val()

    var classHour = $("select[name=classHour]").val()

    //"2019-12-13 01:01:01 - 2019-12-13 02:02:06"
    var dataRange = $("#dataRange").val();

    if(isEmpty(name) && isEmpty(classHour) && isEmpty(dataRange)){
        alert("存在信息未填写，请确认！")
        return
    }

    $.ajax({
        url:'',
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "name" : name,
            "classHour" : classHour,
            "dataRange" : dataRange,
            "cid": sessionStorage.getItem("cid")
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