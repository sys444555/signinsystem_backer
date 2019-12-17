$(function () {
    function showInfoList() {
        var token = getCookie("token")
        console.log(token)
        $.ajax({
            url: "../house/list",
            data: {pageNo: 1, pageSize:  20},
            type: "get",
            dataType: "json",
            async:true,
            success: function (data) {
                for (var i = 0; i < data.data.list.length; i++) {
                    var html = '<tr>'
                        + '<td class="bs-checkbox"  style="width: 36px;" data-field="state" tabindex="0"><input id="#{cusId}" type="checkbox" name="checkbox"/></td>'
                        + '<td>#{id}</td>'
                        + '<td>#{name}</td>'
                        + '<td>#{phone}</td>'
                        + '<td>#{identityNumber}</td>'
                        + '<td>#{clientName}</td>'
                        + '<td>#{clientPhone}</td>'
                        + '<td>#{clientIdentityCard}</td>'
                        + '<td>#{clientArrageTime}</td>'
                        + '<td>#{status}</td>'
                        + '</tr>';
                    html = html.replace("#{cusId}", "cus"+i);
                    html = html.replace("#{id}", i+1);
                    html = html.replace("#{name}", data.data.list[i].name);
                    html = html.replace("#{phone}", data.data.list[i].phone);
                    html = html.replace("#{identityNumber}", data.data.list[i].identityNumber);
                    html = html.replace("#{clientName}", data.data.list[i].clientName);
                    html = html.replace("#{clientPhone}", data.data.list[i].clientPhone);
                    html = html.replace("#{clientIdentityCard}", data.data.list[i].clientIdentityCard);
                    html = html.replace("#{clientArrageTime}", data.data.list[i].clientArrageTime);
                    if(data.data.list[i].status == 0){
                        html = html.replace("#{status}", "<select id='selector'onchange=\"onchangeFunction(this)\"><option value=\"0\" selected>未到访</option><option value=\"1\">服务中</option><option value=\"2\">已成交</option></select>")
                    }else if (data.data.list[i].status == 1){
                        html = html.replace("#{status}", "<select id='selector' onchange=\"onchangeFunction(this)\"><option value=\"1\"  selected>服务中</option><option value=\"2\">已成交</option></select>")

                    }else if(data.data.list[i].status == 2){
                        html = html.replace("#{status}", "已成交");
                    }else {
                        html = html.replace("#{status}", "已失效");
                    }

                    $("#house_td").append(html);
                    localStorage.setItem("cus" + i, JSON.stringify(data.data.list[i]))
                }

                $(".M-box").pagination({
                    pageCount: data.data.pages,   //总页码ajax中的pages总页
                    coping: true,                 //是否开启首页和末页
                    jump: true,                   //是否开启跳页
                    homePage: '首页',
                    endPage: '末页',
                    prevContent: '上页',
                    nextContent: '下页',
                    current: data.data.pageNo,
                    callback: function (api) {
                        $("#house_td").empty();
                        $.ajax({
                            url: "../house/list",
                            data: {pageNo: api.getCurrent(), pageSize: 20},
                            type: "get",
                            dataType: "json",
                            success: function (data) {
                                for (var i = 0; i < data.data.list.length; i++) {
                                    var html = '<tr>'
                                        + '<td class="bs-checkbox"  style="width: 36px;" data-field="state" tabindex="0"><input id="#{cusId}" type="checkbox" name="checkbox"/></td>'
                                        + '<td>#{id}</td>'
                                        + '<td>#{name}</td>'
                                        + '<td>#{phone}</td>'
                                        + '<td>#{identityNumber}</td>'
                                        + '<td>#{clientName}</td>'
                                        + '<td>#{clientPhone}</td>'
                                        + '<td>#{clientIdentityCard}</td>'
                                        + '<td>#{clientArrageTime}</td>'
                                        + '<td>#{status}</td>'
                                        + '</tr>';
                                    html = html.replace("#{cusId}", "cus"+i);
                                    html = html.replace("#{id}", i+1);
                                    html = html.replace("#{name}", data.data.list[i].name);
                                    html = html.replace("#{phone}", data.data.list[i].phone);
                                    html = html.replace("#{identityNumber}", data.data.list[i].identityNumber);
                                    html = html.replace("#{clientName}", data.data.list[i].clientName);
                                    html = html.replace("#{clientPhone}", data.data.list[i].clientPhone);
                                    html = html.replace("#{clientIdentityCard}", data.data.list[i].clientIdentityCard);
                                    html = html.replace("#{clientArrageTime}", data.data.list[i].clientArrageTime);
                                    if(data.data.list[i].status == 0){
                                        html = html.replace("#{status}", "<select id='selector'onchange=\"onchangeFunction(this)\"'><option value=\"0\" selected>未到访</option><option value=\"1\">服务中</option><option value=\"2\">已成交</option></select>")
                                    }else if (data.data.list[i].status == 1){
                                        html = html.replace("#{status}", "<select id='selector'onchange=\"onchangeFunction(this)\"><option value=\"1\"  selected>服务中</option><option value=\"2\">已成交</option></select>")

                                    }else if(data.data.list[i].status == 2){
                                        html = html.replace("#{status}", "已成交");
                                    }else {
                                        html = html.replace("#{status}", "已失效");
                                    }

                                    $("#house_td").append(html);
                                    localStorage.setItem("cus" + i, JSON.stringify(data.data.list[i]))
                                }
                            }
                        });
                    }
                });

            }
        })
    }
    showInfoList(6)

});


/**
 * 查询经纪人的客户信息
 */
  /* function search(){
    var cname=$("#c_name").val();
    var token = getCookie("token")
    console.log(cname)
    $.ajax({
        url: "../house/select/",
        headers: {
            "token": token
        },
        data: {agent: cname},
        type: "POST",
        dataType: "json",
        async: true,
        success: function (data) {

                if (data.data.length == 0) {
                    alert("没有这个经纪人");
                    window.location.reload(true);
                }
                $("#t_table tbody").html("");
                console.log(data);
                for (var i = 0; i < data.data.length; i++) {
                    var html = '<tr>'
                        + '<td class="bs-checkbox"  style="width: 36px;" data-field="state" tabindex="0"><input id="#{cusId}" type="checkbox" name="checkbox"/></td>'
                        + '<td>#{id}</td>'
                        + '<td>#{agent}</td>'
                        + '<td>#{phone}</td>'
                        + '<td>#{idcard}</td>'
                        + '<td>#{referrals}</td>'
                        + '<td>#{r_phone}</td>'
                        + '<td>#{r_idcard}</td>'
                        + '<td>#{time}</td>'
                        + '<td>#{come}</td>'
                        + '</tr>';

                    html = html.replace("#{cusId}", "cus" + i);
                    html = html.replace("#{id}", i);
                    html = html.replace("#{agent}",data.data[i].agent);
                    html = html.replace("#{phone}",data.data[i].phone);
                    html = html.replace("#{idcard}", data.data[i].idcard);
                    html = html.replace("#{referrals}",data.data[i].referrals);
                    html = html.replace("#{r_phone}",data.data[i].rphone);
                    html = html.replace("#{r_idcard}",data.data[i].ridcard);
                    html = html.replace("#{time}",data.data[i].time);
                    html = html.replace("#{come}", data.data[i].come == 0 ? "未到访" : "到访");
                    $("#house_td").append(html);
                    localStorage.setItem("cus" + i, JSON.stringify(data.data[i]))

            }
        }
    });
};
*/


function onchangeFunction(obj) {
    var firstValue = $(obj).children(":first").val();
    console.log(firstValue)
    var selectedValue = $(obj).val();
    console.log(selectedValue)
    if(firstValue != selectedValue){
        $(obj).css("background-color","#1687ff")
    }else {
        $(obj).css("background-color","")
    }
}


function updateStatus() {
    var length = 0;
    var checkId = "";
    var checkboxs = document.getElementsByName("checkbox");
    for (var i = 0; i < checkboxs.length; i++) {
        if (checkboxs[i].checked) {
            length++;
            checkId = checkboxs[i];
        }
    }
    if (length <= 0 || length > 1) {
        alert("请选择单条数据操作!!!")
        checkId = "";
        return;
    } else {
        var data = JSON.parse(localStorage.getItem(checkId.id));

        var id = data.id;
        var token = getCookie("token");
        var statusVal = $(checkId).parent().siblings().last().children().find("option:selected").val();
        var statusText = $(checkId).parent().siblings().last().children().find("option:selected").text();

        if(statusText == null || statusText == ''){
            alert("此条数据不能修改")
            return
        }
        $.confirm({
            title: '提示',
            content: '确认把该条信息状态更改为' + statusText + '吗?',
            type: 'green',
            icon: 'glyphicon glyphicon-question-sign',
            buttons: {
                ok: {
                    text: '确认',
                    action: function() {
                        $.ajax({
                            url: "../house/update/" + id,
                            data: {"status":statusVal},
                            headers: {
                                "token": token
                            },
                            type: "POST",
                            dataType: "json",
                            async: true,
                            success: function (json) {
                                if (json.code == 0) {
                                    alert("修改成功");
                                    //局部页面刷新
                                    window.location.reload(true);
                                } else {
                                    alert("修改失败");
                                }
                            },
                            error: function (json) {
                                console.log(json)
                                alert("您登录信息已过期，请重新登录");
                                window.history.back(-1);
                            }
                        });
                    }
                },
                cancel: {
                    text: '取消'
                }
            }
        })
    }
}

/** Excel导出  **/
function exportExcel() {
    var token = getCookie("token")
    console.log(token)
    $.ajax({
        url: "../house/excel",
        type: "GET",
        async: true,
        success: function () {
            window.location.href="http://122.51.78.195:8080/house/excel";
            // if (json.code == 0) {
            //     //  alert("导出成功");
            //     //window.location.reload(true);
            //     window.location.href="http://localhost:8080/house/excel";
            // } else {
            //     alert("导出失败");
            //     window.location.reload(true);
            // }
        }
    });
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
