$(function () {

    laydate.render({
        elem: '#birth'
    });

    $.ajax({
        url:'http://localhost:8080/student/list',
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
        success:function(data){

            if(data.code == 0 && data.data.list.length >0){
                var dataList = data.data.list;

                for (var i = 0; i < dataList.length; i++) {
                    var html = "<tr data-id='#{id}'>"
                        + "<td>#{name}</td>"
                        + "<td>#{gender}</td>"
                        + "<td>#{birth}</td>"
                        + "<td>#{guarderPhone}</td>"
                        + "<td><a href='javascript:voide(0)' onclick='showStudentInfo(#{id})' style='cursor: pointer;color: firebrick'>查看详情</a></td>"
                    +"</tr>"

                    html = html.replace(/#{id}/g,dataList[i].id)
                    html = html.replace(/#{name}/g,dataList[i].name)
                    html = html.replace(/#{birth}/g,dataList[i].birth)
                    html = html.replace(/#{gender}/g,dataList[i].gender == 0 ? '男' : '女')
                    html = html.replace(/#{guarderPhone}/g,dataList[i].guarderPhone)

                    $("#student_td").append(html)

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
                            url: 'http://localhost:8080/student/list',
                            type: 'get', //GET
                            async: true,    //或false,是否异步
                            headers: {
                                "token": getCookie("token")
                            },
                            data: {
                                pageNo: api.getCurrent(),
                                pageSize: 20
                            },
                            timeout: 5000,    //超时时间
                            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                            success: function (data) {

                                $("#student_td").empty()

                                if (data.code == 0 && data.data.list.length > 0) {
                                    var dataList = data.data.list;

                                    for (var i = 0; i < dataList.length; i++) {
                                        var html = "<tr data-id='#{id}'>"
                                            + "<td>#{name}</td>"
                                            + "<td>#{gender}</td>"
                                            + "<td>#{birth}</td>"
                                            + "<td>#{guarderPhone}</td>"
                                            + "<td><a href='javascript:voide(0)' onclick='showStudentInfo(#{id})' style='cursor: pointer;color: firebrick'>查看详情</a></td>"
                                            + "</tr>"

                                        html = html.replace(/#{id}/g, dataList[i].id)
                                        html = html.replace(/#{name}/g, dataList[i].name)
                                        html = html.replace(/#{gender}/g, dataList[i].gender == 0 ? '男' : '女')
                                        html = html.replace(/#{guarderPhone}/g, dataList[i].guarderPhone)

                                        $("#student_td").append(html)

                                    }
                                }
                            }
                        })
                    }
                })


            }

        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })

})

function cancelModal1() {
    $("#student_info_alter").hide()
}

function showStudentInfo(sid) {

    $("#student_info_alter").show()

    $.ajax({
        url:'http://localhost:8080/student/get/coursePackage/'+sid,
        type:'get', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{

        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){

            $("#pkbody").empty()
            //console.log(data)；
            if(data.code == 0 ){
                var coursePackageEntityList = data.data[0].coursePackageEntityList

                for(var i =0 ;i<coursePackageEntityList.length ; i++){
                    var html = "<tr>"
                        + "<td>#{classPackage}</td>"
                        + "<td>#{price}</td>"
                        + "<td>#{buyClassHour}</td>"
                        + "<td>#{periodOfValidity}</td>"
                        + "<td>#{leftClassHour}</td>"
                    + "</tr>"

                    html = html.replace(/#{classPackage}/g,coursePackageEntityList[i].classPackage)
                    html = html.replace(/#{price}/g,coursePackageEntityList[i].price)
                    html = html.replace(/#{buyClassHour}/g,coursePackageEntityList[i].buyClassHour)
                    html = html.replace(/#{periodOfValidity}/g,coursePackageEntityList[i].periodOfValidity)
                    html = html.replace(/#{leftClassHour}/g,coursePackageEntityList[i].leftClassHour)

                    $("#pkbody").append(html)

                }


            }else{
                alert("读取学员信息失败!")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })
}

function saveStudent() {

    if(isEmpty($("#name").val())){
        alert("名字未填写！")
        return;
    }

    if(isEmpty($("#birth").val())){
        alert("生日未填写！")
        return;
    }

    if(isEmpty($("#guarderPhone").val())){
        alert("联系方式未填写！")
        return;
    }


    $.ajax({
        url:'http://localhost:8080/student/insert',
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "name" : $("#name").val(),
            "gender" : $("#gender").val(),
            "birth" : $("#birth").val(),
            "guarderPhone" : $("#guarderPhone").val()
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            //console.log(data)；
            if(data.code == 0){
                alert("新增学员进入学员录成功！")
                window.location.reload();
            }else{
                alert("新增学员进入学员录失败！！！")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })
}

function cancelModal() {
    $("#student_create_alter").hide()
}

function createStudent() {
    $("#student_create_alter").show()
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

function isEmpty(value){
    if(value == null || value == "" || value == "undefined" || value == undefined || value == "null"){
        return true;
    }
    else{
        value = value.replace(/\s/g,"");
        if(value == ""){
            return true;
        }
        return false;
    }
}
