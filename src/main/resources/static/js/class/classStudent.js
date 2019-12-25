$(function () {

    var cid = getUrlParam("cid")

    sessionStorage.setItem("cid",cid)

    if($("#submitForm").is(":visible") == true){
        $("input[value='新增学员']").show();
        $("input[value='新增课时']").hide();
    }else{
        $("input[value='新增学员']").hide();
        $("input[value='新增课时']").show();
    }

    $("#courseBtn").attr("onclick","openCourse("+cid+")")


    if(cid == null || cid == undefined || cid == ""){ alert("拉取数据失败")}else {
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
                if (data.code == 0 && data.data.list.length > 0) {
                    var dataList = data.data.list;

                    for (var i = 0; i < dataList.length; i++) {
                        var html = "<tr data-cid='#{id}'>"
                            + "<td><input type='checkbox' ></td>"
                            + "<td>#{name}</td>"
                            + "<td>#{gender}</td>"
                            + "<td>#{birth}</td>"
                            + "<td>#{guarder}</td>"
                            + "<td>#{guarderPhone}</td>"
                            + "<td>#{address}</td>"
                            + "<td>#{classPackage}</td>"
                            + "<td>#{chargingStandard}</td>"
                            + "<td>#{buyClassHour}</td>"
                            + "<td>#{consumedClassHour}</td>"
                            + "<td>#{isValidity}</td>"
                            + "<td>#{periodOfValidity}</td>"
                            + "<td>#{price}</td>"
                            + "</tr>"

                        html = html.replace(/#{id}/g, dataList[i].id)
                        html = html.replace("#{name}", dataList[i].name)
                        html = html.replace("#{gender}", dataList[i].gender == 0 ? '男' : '女')
                        html = html.replace("#{birth}", dataList[i].birth)
                        html = html.replace("#{guarder}", dataList[i].guarder)
                        html = html.replace("#{guarderPhone}", dataList[i].guarderPhone)
                        html = html.replace("#{address}", dataList[i].address)
                        html = html.replace("#{classPackage}", dataList[i].classPackage)
                        html = html.replace("#{chargingStandard}", dataList[i].chargingStandard)
                        html = html.replace("#{buyClassHour}", dataList[i].buyClassHour)
                        html = html.replace("#{consumedClassHour}", dataList[i].buyClassHour - dataList[i].consumedClassHour)
                        html = html.replace("#{isValidity}", dataList[i].isValidity == 0 ? '无效' : '有效')
                        html = html.replace("#{periodOfValidity}", dataList[i].periodOfValidity)
                        html = html.replace("#{price}", dataList[i].price)


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
                                                + "<td>#{name}</td>"
                                                + "<td>#{gender}</td>"
                                                + "<td>#{birth}</td>"
                                                + "<td>#{guarder}</td>"
                                                + "<td>#{guarderPhone}</td>"
                                                + "<td>#{address}</td>"
                                                + "<td>#{classPackage}</td>"
                                                + "<td>#{chargingStandard}</td>"
                                                + "<td>#{buyClassHour}</td>"
                                                + "<td>#{consumedClassHour}</td>"
                                                + "<td>#{isValidity}</td>"
                                                + "<td>#{periodOfValidity}</td>"
                                                + "<td>#{price}</td>"
                                                + "</tr>"

                                            html = html.replace(/#{id}/g, dataList[i].id)
                                            html = html.replace("#{name}", dataList[i].name)
                                            html = html.replace("#{gender}", dataList[i].gender == 0 ? '男' : '女')
                                            html = html.replace("#{birth}", dataList[i].birth)
                                            html = html.replace("#{guarder}", dataList[i].guarder)
                                            html = html.replace("#{guarderPhone}", dataList[i].guarderPhone)
                                            html = html.replace("#{address}", dataList[i].address)
                                            html = html.replace("#{classPackage}", dataList[i].classPackage)
                                            html = html.replace("#{chargingStandard}", dataList[i].chargingStandard)
                                            html = html.replace("#{buyClassHour}", dataList[i].buyClassHour)
                                            html = html.replace("#{consumedClassHour}", dataList[i].consumedClassHour)
                                            html = html.replace("#{isValidity}", dataList[i].isValidity == 0 ? '无效' : '有效')
                                            html = html.replace("#{periodOfValidity}", dataList[i].periodOfValidity)
                                            html = html.replace("#{price}", dataList[i].price)


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

function createStudent() {
    $("#student_create_alter").show()
}
function cancelClassModal() {
    $("#student_create_alter")[0].reset
    $("#student_create_alter").hide()
}

function saveStudent() {
    //为真则不为空
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
                "classPackage":$("input[name=classPackage]").val(),
                "chargingStandard":$("input[name=chargingStandard]").val(),
                "buyClassHour":$("input[name=buyClassHour]").val(),
                "consumedClassHour":$("input[name=consumedClassHour]").val(),
                "isValidity":$("select[name=isValidity]").val(),
                "periodOfValidity":changeDate($("input[name=periodOfValidity]").val()),
                "price":$("input[name=price]").val(),
                "cid" : sessionStorage.getItem("cid")
            },
            timeout:5000,    //超时时间
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success:function(data){
                if(data.code == 0){
                    alert("新增学员成功！")
                    window.location.reload();
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

function openCourse(cid) {
    window.open("class_course.html?cid="+cid)
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
