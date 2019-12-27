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
        timeout:50000,    //超时时间
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
                        + "<td><a href='javascript:void(0)' onclick='delStudent(#{id})' style='cursor: pointer;color: darkorange'>删除学员（重要操作）</a></td>"
                        + "<td><a href='javascript:void(0)' onclick='showStudentInfo(#{id})' style='cursor: pointer;color: firebrick'>查看详情</a></td>"
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
                                            + "<td><a href='javascript:void(0)' onclick='delStudent(#{id})' style='cursor: pointer;color: darkorange'>删除学员（重要操作）</a></td>"
                                            + "<td><a href='javascript:void(0)' onclick='showStudentInfo(#{id})' style='cursor: pointer;color: firebrick'>查看详情</a></td>"
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

function delStudent(studentId) {
    if(confirm("你将删除该名学员，并且删除学员包、已在班级，已在课时等相关信息，请确认?")){
        $.ajax({
            url:'http://localhost:8080/student/delete/'+studentId,
            type:'post', //GET
            async:true,    //或false,是否异步
            headers:{
                'token' : getCookie("token")
            },
            data:{

            },
            timeout:50000,    //超时时间
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success:function(data){
                //console.log(data)；
                if(data.code==0){
                    alert("删除成功！")
                    window.location.reload()
                }else{
                    alert("删除失败！")
                }
            },
            error:function () {
                alert("服务器异常，请稍后再试！")
            }
        })

    }
}

function cancelModal1() {
    $("#student_info_alter").hide()
}

function showStudentInfo(sid) {

    sessionStorage.setItem("studentId",sid)

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
        timeout:50000,    //超时时间
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

    $.ajax({
        url:'http://localhost:8080/student/getStudentById',
        type:'get', //GET
        async:true,    //或false,是否异步
        headers:{
            "token":getCookie("token")
        },
        data:{
            "sid":sid
        },
        timeout:50000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            //console.log(data)；
            if(data.code==0){
                $("#infoArea").empty();

                    var html = "<tr>"
                        + "<td>#{name}</td>"
                        + "<td>#{gender}</td>"
                        + "<td>#{birth}</td>"
                        + "<td>#{guarderPhone}</td>"
                    +"</tr>>"

                    html = html.replace(/#{name}/g,data.data.name)
                    html = html.replace(/#{gender}/g,data.data.gender==0?'男':'女')
                    html = html.replace(/#{birth}/g,data.data.birth)
                    html = html.replace(/#{guarderPhone}/g,data.data.guarderPhone)


                    $("#infoArea").append(html)
            }else{
                alert("获取学员信息失败！")
            }

        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })


}

function savePackageToStudent(sid) {

    var classPackage = $("#classPackage").val()
    var bugClassHour = $("#bugClassHour").val()
    var consumedClassHour = $("#consumedClassHour").val()
    var periodOfValidity = $("#periodOfValidity").val()
    var price = $("#price").val()


    if(isEmpty123(classPackage)){
        alert("课包未填写！")
        return
    }
    if(isEmpty123(price)){
        alert("收费标准未填写！")
        return
    }
    if(isEmpty123(bugClassHour)){
        alert("总课时未填写！")
        return
    }
    if(/[/D]/g.test(bugClassHour)){
        alert("总课时请输入数字！")
        return;
    }
    if(isEmpty123(consumedClassHour)){
        alert("未加入已耗课时未填写！")
        return
    }
    if(/[/D]/g.test(consumedClassHour)){
        alert("未加入已耗课时请输入数字！")
        return;
    }



    if(bugClassHour<consumedClassHour){
        alert("总课时不得低于已耗课时!")
        return;
    }

    if(isEmpty123(periodOfValidity)){
        alert("有效期未填写！")
        return
    }



    $.ajax({
        url:'http://localhost:8080/student/coursePackage/create',
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            classPackage : classPackage,
            buyClassHour:bugClassHour,
            consumedClassHour:consumedClassHour,
            periodOfValidity:periodOfValidity,
            price:price,
            studentId:sessionStorage.getItem("studentId"),
        },
        timeout:50000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            //console.log(data)；
            if(data.code==0){
                alert("添加成功，刷新页面！")
                window.location.reload()
            }else{
                alert("添加失败！")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })

}

function cancelClassModal3() {
    $("#student_info_alter").show()
    $("#add_package_alter").hide()
}

function addPackageToStuden() {

    $("#student_info_alter").hide()
    $("#add_package_alter").show()



    laydate.render({
        elem: '#periodOfValidity'
        ,min: getDate()
    });

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

    if(!(/^1[3456789]\d{9}$/.test($("#guarderPhone").val()))){
        alert("请输入正确联系方式！")
        return
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
        timeout:50000,    //超时时间
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

function alertInfo() {
    $("#update_student_alter").show()
    $("#student_info_alter").hide()


        $.ajax({
            url:'http://localhost:8080/student/getStudentById',
            type:'get', //GET
            async:true,    //或false,是否异步
            headers:{
                "token":getCookie("token")
            },
            data:{
                "sid":sessionStorage.getItem("studentId")
            },
            timeout:50000,    //超时时间
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success:function(data){
                //console.log(data)；
                if(data.code==0){;

                    $("#Sbirth").val(data.data.birth);
                    $("#Sname").val(data.data.name);
                    $("#Sphone").val(data.data.guarderPhone);
                    $("#Sgender").val(data.data.gender)

                }else{
                    alert("获取学员信息失败！")
                }

            },
            error:function () {
                alert("服务器异常，请稍后再试！")
            }
        })
}

function updateStudent() {
    if(isEmpty123($("#Sname").val())){
        alert("姓名未填写！")
        return
    }
    if(isEmpty123($("#Sgender").val())){
        alert("性别未填写！")
        return
    }
    if(isEmpty123($("#Sbirth").val())){
        alert("生日未填写！")
        return
    }
    if(isEmpty123($("#Sphone").val())){
        alert("联系方式未填写！！")
        return
    }

    if(!(/^1[3456789]\d{9}$/.test($("#Sphone").val()))){
        alert("请输入正确联系方式！")
        return
    }


    $.ajax({
        url:'http://localhost:8080/student/update',
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            "token":getCookie("token")
        },
        data:{
            id: sessionStorage.getItem("studentId"),
            name:$("#Sname").val(),
            birth:$("#Sbirth").val(),
            guarderPhone:$("#Sphone").val(),
            gender:$("#Sgender").val()
        },
        timeout:50000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            if(data.code==0){
                alert("修改成功！")
                window.location.reload();
            }else{
                alert("修改失败!")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })
}

function cancelClassModal4() {
    $("#update_student_alter").hide()
    $("#student_info_alter").show()
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

function isEmpty123(value) {
    if (value == null || value == "" || value == "undefined" || value == undefined || value == "null") {
        return true;
    }
    else {
        value = value.replace(/\s/g, "");
        if (value == "") {
            return true;
        }
        return false;
    }
}

function getDate() {

    var dateStr = new Date()

    var date =  dateStr.getFullYear() + "-" + (parseInt(dateStr.getMonth())+1) + "-" + dateStr.getDate()

    return date

}