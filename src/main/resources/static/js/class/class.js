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
                        + "<td>#{className}</td>"
                        + "<td>#{classHour}</td>"
                        + "<td>#{teacherName}</td>"
                        + "<td data-status='#{statusnum}'>#{status}</td>"
                        + "<td><a href='javascript:void(0)' style='cursor: pointer;color: darkorange' onclick='delClass(#{id})'>删除该班级</a></td>"
                        + "<td id='td#{id}'><a href='javascript:void(0)' style='color: firebrick;cursor: pointer' target='_blank' onclick='showClassInfo(#{id},\"#{className}\",\"#{classHour}\",\"#{teacherName}\")'>查看详情</a></td>"
                        + "</tr>"

                    html = html.replace(/#{id}/g,dataList[i].id)
                    html = html.replace(/#{className}/g,dataList[i].className)
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
                                            + "<td>#{className}</td>"
                                            + "<td>#{classHour}</td>"
                                            + "<td>#{teacherName}</td>"
                                            + "<td data-status='#{statusnum}'>#{status}</td>"
                                            + "<td><a href='javascript:void(0)' style='cursor: pointer;color: darkorange' onclick='delClass(#{id})'>删除该班级</a></td>"
                                            + "<td id='td#{id}'><a href='javascript:void(0)' style='color: firebrick;cursor: pointer' target='_blank' onclick='showClassInfo(#{id},\"#{className}\",\"#{classHour}\",\"#{teacherName}\")'>查看详情</a></td>"
                                            + "</tr>"

                                        html = html.replace(/#{id}/g,dataList[i].id)
                                        html = html.replace(/#{className}/g,dataList[i].className)
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

function delClass(classId) {

    if(isEmpty(classId)){
        alert("无法获取当前班级的ID")
        return;
    }

    if(confirm("你确定要删除班级吗？")){
        $.ajax({
            url:'http://localhost:8080/class/delete/' + classId,
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
                if(data.code==0){
                    alert("删除成功！")
                    window.location.reload();
                }else{
                    alert("删除失败");
                }
            },
            error:function () {
                alert("服务器异常，请稍后再试！")
            }
        })

    }

}

function addStudent() {

    laydate.render({
        elem: '#birth'
    });

    $("#createStudentForm")[0].reset

    $("#class_student_alter").show();
    $("#class_info_alter").hide()

}

function showClassInfo(classId,className,classHour,teacherName) {

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
                    // + '<td class="td_lable"><a href="javascript:void(0)" style="cursor: pointer;color: firebrick" onclick="removeStudentOnClass(#{id})">移出班级</a></td>'
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
                    + '<td class="td_lable"><span>#{lessonNow}</span></td>'
                    + '<td class="td_lable"><a href="javascript:void(0)" onclick="showLessonInfo(#{id})">详情操作</a></td>'
                    + '<tr>'

                    html = html.replace(/#{id}/g,dataList[i].id)
                    html = html.replace(/#{name}/g,dataList[i].name)
                    html = html.replace(/#{time}/g,dataList[i].startDate + " - " + dataList[i].endDate)
                    html = html.replace(/#{lessonNow}/g, '第'+(i+1)+'节')

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
function cancelClassModal5() {

    $("#class_info_alter").show()
    $("#show_lesson_alter").hide()

}

function showLessonInfo(id) {


    $("#show_lesson_alter").show()
    $("#class_info_alter").hide();

    if(isEmpty(id)){
        alert("课时id无法获取，请稍后再试！")
        return;
    }
    sessionStorage.setItem("lessonId",id)

    //获取当前lession回显信息

    $.ajax({
        url:'http://localhost:8080/class/lesson/get/'+id,
        type:'get', //GET
        async:true,    //或false,是否异步
        headers:{
            token : getCookie("token")
        },
        data:{

        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            $("#lesson_info tbody").empty()
            //console.log(data)；
            if(data.code == 0){

                var html = "<tr>"
                    +  "<td class='td_lable'>课程名：#{name}</td>"
                    +  "<td class='td_lable'>课程消耗：#{classHour}</td>"
                    + "</tr>"
                    + "<tr>"
                    +  "<td class='td_lable'>开始时间：#{startDate}</td>"
                    +  "<td class='td_lable'>结束时间：#{endDate}</td>"
                    + "</tr>"

                html = html.replace(/#{name}/g,data.data.name)
                html = html.replace(/#{classHour}/g,data.data.classHour)
                html = html.replace(/#{startDate}/g,data.data.startDate)
                html = html.replace(/#{endDate}/g,data.data.endDate)

                $("#lesson_info tbody").append(html)

            }else{
                alert("获取课程失败！")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })


    $.ajax({
        url:'http://localhost:8080/class/lesson/student/list/'+id,
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

            $("#lesson_studentInfo tbody").empty()

            var classId = sessionStorage.getItem("classId");

            if(data.code==0){
                for (var i =0 ; i<data.data.length;i++){
                    var html = "<tr>"
                    + "<td>#{name}</td>"
                    + "#{status}"
                    + "<td>#{signTime}</td>"
                    + "#{yc}"
                    + "#{qd}"
                    + "</tr>>"

                    html = html.replace("#{yc}",data.data[i].status==0 ? "<td><a href='javascript:void(0)' onclick='removeStudentOnLesson(#{id})' style='cursor: pointer;color: firebrick'>移出课时</a></td>" :"<td></td>")
                    html = html.replace("#{qd}",data.data[i].status==0 ? "<td><a href='javascript:void(0)' onclick='qiandao(#{id},"+id+")' id='qd#{index}'>签到</a></td>" : "<td></td>")
                    html = html.replace("#{index}",i)
                    html = html.replace(/#{id}/g,data.data[i].id)
                    html = html.replace(/#{name}/g,data.data[i].name)
                    html = html.replace(/#{status}/g,data.data[i].status == 0 ? '<td styl="color: firebrick">未签到</td>' : '<td style="color: mediumspringgreen">已签到</td>')
                    html = html.replace(/#{signTime}/g,data.data[i].signTime == null? "":data.data[i].signTime)


                    if(data.data[i].status == 1 ){
                        $("#qd"+i).attr("data-status",1)
                    }

                    $("#lesson_studentInfo tbody").append(html)
                }

            }else{
                alert("获取课时学员失败！")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })


}

function removeStudentOnLesson(sid) {
    $.ajax({
        url:'http://localhost:8080/class/lesson/remove/student/'+sid,
        type:'post', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "lessonId" : sessionStorage.getItem("lessonId")
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            //console.log(data)；
            if(data.code ==0) {
                alert("移除成功！")
                window.location.reload()
                $("#class_info_alter").show();
            }else{
                alert("移除失败！")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })
}


function removeStudent(sid,lessonid,classid) {

    if(isEmpty(sid) || isEmpty((lessonid) || isEmpty(classid))){
        alert("获取信息失败，请稍后再试！")
        return
    }

    $.ajax({
        url:'http://localhost:8080/class/lesson/remove/student/'+sid,
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "lessonId" : lessonid
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            alert("移除成功")
            console.log("a")

            window.location.reload()

        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })

}

function qiandao(sid,lesssonId) {

    if(confirm("是否发送签到短信？")) {
        $.ajax({
            url:'http://localhost:8080/class/lesson/student/sign',
            type:'POST', //GET
            async:true,    //或false,是否异步
            headers:{
                "token":getCookie("token")
            },
            data:{
                lessonId:lesssonId,
                studentId:sid
            },
            timeout:5000,    //超时时间
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success:function(data){
                if(data.code == 0){
                    alert("已发送短信成功！")
                    window.location.reload()
                }else if(data.code == 999){
                    alert(data.msg)
                }
            },
            error:function () {
                alert("服务器异常，请稍后再试！")
            }
        })
    }

}


function saveLesson() {


    var name = $("#add_lesson_alter input[name=name]").val()

    var classHour = $("select[name=classHour]").val()

    //"2019-12-13 01:01:01 - 2019-12-13 02:02:06"
    var dataRange = $("#dataRange").val();

    var timeRange = $("#timeRange").val();

    var period = $("select[name=period]").val()

    var times = $("#times").val()

    if(isEmpty123(name)){
        alert("课时名字未填写！")
        return
    }
    if(isEmpty123(classHour)){
        alert("课时耗损量未填写！")
        return
    }
    if(isEmpty123(dataRange)){
        alert("举行开始日期未填写！")
        return
    }
    if(isEmpty123(timeRange)){
        alert("时间段未填写！")
        return
    }
    if($("#times").is(":visible")){
        if(isEmpty123(times)){
            alert("上课节数未填写！")
            return
        }
    }



    if(sessionStorage.getItem("classId")==undefined){
        alert("获取班级id失败")
    }

    // if(!(isEmpty(name) && isEmpty(classHour) && isEmpty(dataRange) && isEmpty(period))){
    //     alert("存在信息未填写，请确认！")
    //     return
    // }

    $.ajax({
        url:'http://localhost:8080/class/lesson/create',
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "name" : name,
            "classHour" : classHour,
            "dataRange" : dataRange,
            "timeRange":timeRange,
            "period" : period,
            "times" : times,
            "classId" : sessionStorage.getItem("classId")
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

function showTimes(obj) {
    $(obj).find(":checked").val() != 0 ?  $(obj).find(":checked").parent().parent().next().show() : $(obj).find(":checked").parent().parent().next().hide()

}

function addLesson() {

    $("#class_info_alter").hide()
    $("#add_lesson_alter").show()

    laydate.render({
        elem : '#dataRange',
    })

    laydate.render({
        elem: '#timeRange'
        ,type: 'time'
        ,range: true
    });

}

function cancelClassModal4() {
    $("#class_info_alter").show()
    $("#add_lesson_alter").hide()
}


var studentId;

function showStudent(studentId) {

    studentId = studentId;
    sessionStorage.setItem("sid",studentId)

    $("#student_info_alter").show()

    $.ajax({
        url:'http://localhost:8080/student/getStudentById',
        type:'get', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "sid" : studentId
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            $("#studentTable1").empty()
            if(data.code == 0 ){

                var html = '<tr data-id="#{id}">'
                    + '<td class="td_lable"> #{name}</td>'
                    + '<td class="td_lable"> #{gender}</td>'
                    +'</tr>'
                    + '<tr>'
                    + '<td class="td_lable">#{birth}</td>'
                    + '<td class="td_lable">#{guarderPhone}</td>'
                    + '</tr>'

                html = html.replace(/#{id}/g,data.data.id)
                html = html.replace(/#{name}/g,data.data.name)
                html = html.replace(/#{gender}/g,data.data.gender==0? '男' : '女')
                html = html.replace(/#{birth}/g,data.data.birth)
                html = html.replace(/#{address}/g,data.data.address)
                html = html.replace(/#{guarder}/g,data.data.guarder)
                html = html.replace(/#{guarderPhone}/g,data.data.guarderPhone)

                $("#studentTable1").append(html)

            }else{
                alert("查询学生信息失败！")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })

    $.ajax({
        url:'http://localhost:8080/student/coursePackage/list/'+studentId,
        type:'get', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            classId : sessionStorage.getItem("classId")
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            $("#studentCourseArea tbody").empty()
            if(data.code == 0 ){

                var default1 = data.data.defaultUse;

                for(var i=0;i<data.data.coursePackageList.length;i++){

                    var html = '<tr data-id="#{id}">'
                        + '<td class="td_lable"> #{classPackage}</td>'
                        + '<td class="td_lable"> #{price}</td>'
                        + '<td class="td_lable"> #{buyClassHour}</td>'
                        + '<td class="td_lable">#{isValidity}</td>'
                        + '<td class="td_lable">#{periodOfValidity}</td>'
                        + '<td class="td_lable">#{MR}</td>'
                        + '</tr>'

                    html = html.replace(/#{id}/g,data.data.coursePackageList[i].id)
                    html = html.replace(/#{classPackage}/g,data.data.coursePackageList[i].classPackage)
                    html = html.replace(/#{price}/g,data.data.coursePackageList[i].price)
                    html = html.replace(/#{buyClassHour}/g,data.data.coursePackageList[i].buyClassHour)
                    html = html.replace(/#{isValidity}/g,data.data.coursePackageList[i].isValidity==0?'无效':'有效')
                    html = html.replace(/#{periodOfValidity}/g,data.data.coursePackageList[i].periodOfValidity)
                    html = html.replace(/#{MR}/g,data.data.coursePackageList[i].id == default1? '<span style="color: firebrick">默认</span>':'<a href="javascript:void(0)" onclick="changeMR('+data.data.coursePackageList[i].id+')">修改默认</a>')



                    $("#studentCourseArea tbody").append(html)

                }
            }else{
                alert("查询课包信息失败！")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })
}

function addStudentToLesson() {

    $("#show_studenList_alter").show()
    $("#show_lesson_alter").hide()


    if( isEmpty(sessionStorage.getItem("classId"))){
        alert("获取班级id失败！")
        return
    }

    $.ajax({
        url:'http://localhost:8080/class/lesson/student/absent/list/'+   sessionStorage.getItem("lessonId"),
        type:'get', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "classId" : sessionStorage.getItem("classId")
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            $("#studentLesson tbody").empty()
           if(data.code ==0){
               for(var i =0;i<data.data.length;i++){
                   var html = "<tr data-id='#{id}'>"
                   + "<td>#{name}</td>"
                   +"<td><input type='button' class='btn-green' value='加入课程' onclick='insertStudent(#{id})'></td>"
                   + "</tr>"

                   html = html.replace(/#{id}/g,data.data[i].id)
                   html = html.replace(/#{name}/g,data.data[i].name)

                   $("#studentLesson tbody").append(html)
               }
           }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })

}

function importStudent() {
    $("#show_studenAll_alter").show()
    $("#class_info_alter").hide()

    $.ajax({
        url:'http://localhost:8080/class/student/absent/list/'+sessionStorage.getItem("classId"),
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
            $("#studentClass tbody").empty()
            //console.log(data)；
            if(data.code == 0){
                for(var i=0;i<data.data.length;i++){
                    var html = "<tr data-id='#{id}'>"
                    + "<td>#{name}</td>"
                    + "<td><input type='button' class='btn-green' value='加入班级' onclick='insertStudentToClass(#{id})'></td>"
                    + "</tr>>"

                    html = html.replace(/#{id}/g,data.data[i].id)
                    html = html.replace(/#{name}/g,data.data[i].name)

                    $("#studentClass tbody").append(html)
                }

            }else{
                alert("读取学员信息失败！")
            }
        },

        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })

}

function insertStudentToClass(sid) {
    if($("#studentClass").find("tr[data-id="+sid+"]").attr("data-select") == 1){
        $("#studentClass").find("tr[data-id="+sid+"]").find("td").next().children().val("加入班级")
        $("#studentClass").find("tr[data-id="+sid+"]").removeAttr("data-select")
    }else{
        $("#studentClass").find("tr[data-id="+sid+"]").find("td").next().children().val("移出班级")
        $("#studentClass").find("tr[data-id="+sid+"]").attr("data-select","1")
    }

}

function saveStudenToClass() {
    var sList = $("#studentClass").find("tr[data-select=1]")

    var list = [];

    if(sList.length == 0){
        alert("你没有添加任何学员！")
        return;
    }

    for(var i =0;i<sList.length;i++){
        list.push( $("#studentClass").find("tr[data-select=1]")[i].dataset.id)
    }

    $.ajax({
        url:'http://localhost:8080/class/students/create',
        type:'POST', //GET
        async:true,    //或false,是否异步,
        traditional:true,
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "classId" : sessionStorage.getItem("classId"),
            studentList:list
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            //console.log(data)；
            if(data.code==0){
                alert("添加学员进班成功！")
                window.location.reload();
            }else{
                alert("添加学员进班失败!")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })


}


function cancelClassModal7() {
    $("#show_studenAll_alter").hide()
    $("#class_info_alter").show()

}

function saveStudenToLesson() {

    var list = $("#studentLesson").find("tr[data-select=1]");

    var studentList = []

    for(var i=0;i<list.length ;i++){
        studentList.push(list[i].dataset.id)
    }

    $.ajax({
        url:'http://localhost:8080/class/lesson/students/create',
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        traditional: true,
        data:{
            coid : sessionStorage.getItem("lessonId"),
            studentList : studentList
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            //console.log(data)；
            if(data.code==0){
                alert("添加学员至课时成功！")
                window.location.reload()
            }else{
                alert("添加失败")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })



}

function insertStudent(sid) {

    if($("#studentLesson").find("tr[data-id="+sid+"]").attr("data-select") == 1){
        $("#studentLesson").find("tr[data-id="+sid+"]").find("td").next().children().val("加入课程")
        $("#studentLesson").find("tr[data-id="+sid+"]").removeAttr("data-select")
    }else{
        $("#studentLesson").find("tr[data-id="+sid+"]").find("td").next().children().val("移出课程")
        $("#studentLesson").find("tr[data-id="+sid+"]").attr("data-select","1")
    }

}

function cancelClassModal6() {
    $("#show_studenList_alter").hide()
    $("#show_lesson_alter").show()
}


function changeMR(id) {

    if(isEmpty(id) || isEmpty(sessionStorage.getItem("sid")) || isEmpty(sessionStorage.getItem("classId"))){
        alert("获取参数失败，请稍后再试！")
        return;
    }

    $.ajax({
        url:'http://localhost:8080/student/coursePackage/set',
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            "studentId" : sessionStorage.getItem("sid"),
            "classId" : sessionStorage.getItem("classId"),
            "cpid":id
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            if(data.code == 0){
                alert("修改默认课包成功，签到将扣除本课包！")
                window.location.reload()
            }else{
                alert("修改默认状态失败")
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
    if(isEmpty123(consumedClassHour)){
        alert("未加入已耗课时未填写！")
        return
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
            studentId:sessionStorage.getItem("sid"),
            classId:sessionStorage.getItem("classId")
        },
        timeout:5000,    //超时时间
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


function addPackageToStuden() {

    $("#student_info_alter").hide()
    $("#add_package_alter").show()

    laydate.render({
        elem: '#periodOfValidity'
    });

}

function cancelClassModal3() {
    $("#add_package_alter").hide()
    $("#student_info_alter").show()
}

function cancelClassModal2() {
    $("#student_info_alter").hide()
    $("#class_info_alter").show()
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
                    window.location.reload()
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

function isEmptyForm1(){
    var flag=true;
    $("#addPackage").find("input").each(function(i,item){
        if(item.value=="" || item.value == undefined || item.value == null){
            flag=false;
            return false;
        }
    });
    return flag;
}

function isEmptyForm(){
    var flag=true;
    $("#createStudentForm").find("input").each(function(i,item){
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


function isEmpty123(value){
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
