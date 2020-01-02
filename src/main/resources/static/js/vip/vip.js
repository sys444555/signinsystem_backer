$(function () {

    $.ajax({
        url:'http://localhost:8080/business/list',
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
                        + "<td>#{companyName}</td>"
                        + "<td>#{phone}</td>"
                        + "<td>#{username}</td>"
                        + "<td>#{password}</td>"
                        + "<td>#{msnBuyNumber}</td>"
                        + "<td>#{msnLeftNumber}</td>"
                        + "<td><a href='javascript:void(0)' style='cursor: pointer;color: firebrick' onclick='showAddMsn(#{id})'>添加短信条数</a></td>"
                        + "<td><a href='javascript:void(0)' onclick='delCompany(#{id})' style='cursor: pointer;color: darkorange'>删除加盟商（重要操作）</a></td>"
                        +"</tr>"

                    html = html.replace(/#{id}/g,dataList[i].id)
                    html = html.replace(/#{name}/g,dataList[i].name)
                    html = html.replace(/#{companyName}/g,dataList[i].companyName)
                    html = html.replace(/#{phone}/g,dataList[i].phone)
                    html = html.replace(/#{username}/g,dataList[i].username)
                    html = html.replace(/#{password}/g,dataList[i].password)
                    html = html.replace(/#{msnBuyNumber}/g,dataList[i].msnBuyNumber)
                    html = html.replace(/#{msnLeftNumber}/g,dataList[i].msnLeftNumber)

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
                            url: 'http://localhost:8080/business/list',
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
                                            + "<td>#{companyName}</td>"
                                            + "<td>#{phone}</td>"
                                            + "<td>#{username}</td>"
                                            + "<td>#{password}</td>"
                                            + "<td>#{msnBuyNumber}</td>"
                                            + "<td>#{msnLeftNumber}</td>"
                                            + "<td><a href='javascript:void(0)' style='cursor: pointer;color: firebrick' onclick='showAddMsn(#{id})'>添加短信条数</a></td>"
                                            + "<td><a href='javascript:void(0)' onclick='delCompany(#{id})' style='cursor: pointer;color: darkorange'>删除加盟商（重要操作）</a></td>"
                                            +"</tr>"

                                        html = html.replace(/#{id}/g,dataList[i].id)
                                        html = html.replace(/#{companyName}/g,dataList[i].companyName)
                                        html = html.replace(/#{phone}/g,dataList[i].phone)
                                        html = html.replace(/#{username}/g,dataList[i].username)
                                        html = html.replace(/#{password}/g,dataList[i].password)
                                        html = html.replace(/#{msnBuyNumber}/g,dataList[i].msnBuyNumber)
                                        html = html.replace(/#{msnLeftNumber}/g,dataList[i].msnLeftNumber)


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

function saveCompany() {
    if(isEmpty123($("#companyName").val())){
        alert("企业名称未填写!")
        return;
    }
    if(isEmpty123($("#name").val())){
        alert("负责人未填写!")
        return;
    }
    if(isEmpty123($("#username").val())){
        alert("登录账号未填写!")
        return;
    }
    if(isEmpty123($("#password").val())){
        alert("登录密码未填写!")
        return;
    }
    if(isEmpty123($("#phone").val())){
        alert("联系电话未填写!")
        return;
    }
    if(isEmpty123($("#msnBuyNumber").val())){
        alert("短信条数未填写!")
        return;
    }

    $.ajax({
        url:'http://localhost:8080/business/create',
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            "token" : getCookie("token")
        },
        data:{
            companyName :$("#companyName").val(),
            name :$("#name").val(),
            username :$("#username").val(),
            password :$("#password").val(),
            phone :$("#phone").val(),
            msnBuyNumber :$("#msnBuyNumber").val()
        },
        timeout:50000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            //console.log(data)；
            if(data.code==0){
                alert("添加加盟商成功!")
                window.location.reload()
            }else if(data.code==888){
                alert(data.msg+",请申请别的账户！");
            }else{
                alert("添加加盟商失败")
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })

}

function delCompany(comId) {
    if(confirm("是否需要删除加盟商信息？")){
        $.ajax({
            url:'http://localhost:8080/business/delete/'+comId,
            type:'POST', //GET
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

function saveMsn() {

    if(isEmpty123($("#msnNumber").val())){
        alert("添加数目还未填写！");
        return;
    }

    $.ajax({
        url:'http://localhost:8080/business/add/msnNumber',
        type:'POST', //GET
        async:true,    //或false,是否异步
        headers:{
            token:getCookie("token")
        },
        data:{
           "businessId" : sessionStorage.getItem("companyId"),
            "msnNumber":$("#msnNumber").val()
        },
        timeout:50000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            //console.log(data)；
            if(data.code==0){
                alert("已经增加条数成功！")
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

function cancelModal() {
    $("#company_create_alter").hide()
}
function createStudent() {
    $("#company_create_alter").show()
}

function showAddMsn(comId) {
    sessionStorage.setItem("companyId",comId)
    $("#company_msn_alter").show()

}

function cancelModal1() {
    $("#company_msn_alter").hide()
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