$(function () {

    $.ajax({
        url:'http://localhost:8080/class/list',
        type:'GET', //GET
        async:true,    //或false,是否异步
        headers:{"token":getCookie("token")},<div class="modal" id="application_detail_alter" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: auto;">
        <div class="modal-dialog" id="application_detail_dialog">
        <div class="modal-content" align="center" style="overflow: scroll; height: 500px; width: 800px">
        <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						×
</button>
    <h4 class="modal-title" id="myModalLabel2">
        招聘信息详情页面
        </h4>
        </div>
        <div class="modal-body">
        <table width="100%" align="absbottom" class="application_detail_tb">
        <form id="personnel_detail" method="post">
        <tr>
        <td align="left">名字：
<span id="name"></span>
        </td>
        <td align="absbottom" >性别：
<span id="gender"></span>
        </td>
        </tr>
        <tr>
        <td align="left">出生日期：
<span  id="birth_place"></span>
        </td>
        <td align="absbottom">手机：
<span id="phone"></span>
        </td>
        </tr>
        <tr>
        <td align="left">居住地：
<span id="live_place"></span>
        </td>
        <td align="absbottom">邮箱：
<span id="email"></span>
        </td>
        </tr>
        <tr>
        <td align="left">时间开始：
<span  id="education_start_date"></span>
        </td>
        <td align="absbottom">结束时间：
<span id="education_end_date"></span>
        </td>
        </tr>
        <tr>
        <td align="left">学历/学位：
<span id="education_grade"></span>
        </td>
        <td align="absbottom">是否全日制：
<span id="education_full_time"></span>
        </td>
        </tr>
        <tr>
        <td align="lett">学校：
<span id="education_school"></span>
        </td>
        <td align="absbottom">专业：
<span id="education_major"></span>
        </td>
        </tr>
        <tr>
        <td align="left">最近工作经历开始时间：
<span id="last_job_start_date"></span>
        </td>
        <td align="absbottom">最近工作经历结束时间：
<span id="last_job_end_date"></span>
        </td>
        </tr>
        <tr>
        <td align="left">公司：
<span id="last_job_company_name"></span>
        </td>
        <td align="absbottom">公司性质：
<span id="company_nature"></span>
        </td>
        </tr>
        <tr>
        <td align="left">行业：
<span id="industry"></span>
        </td>
        <td align="absbottom">职位：
<span  id="position"></span>
        </td>
        </tr>
        <tr>
        <td align="left">部门：
<span id="department"></span>
        </td>
        </tr>
        <tr>
        <td align="left">工作描述：
<span id="job_describtion"></span>
        </td>
        <td align="absbottom">证书：
<span name="pic" ><img id="certificate_url" src=""  width="210" height="150"/></span>
        </td>
        </tr>
        <tr>
        <td align="left">需要信息费：
<span id="need_info_fee"></span>
        </td>
        <td align="absbottom">需要快递费：
<span id="need_express_fee"></span>
        </td>
        </tr>
        <tr>
        <td align="left">招聘公司编号：
<span id="company_code"></span>
        </td>
        <td align="absbottom">招聘公司名字：
<span id="company_name"></span>
        </td>
        </tr>
        <tr>
        <td align="left">招聘公司负责人：
<span id="company_contact"></span>
        </td>
        <td align="absbottom">招聘公司联系方式：
<span id="company_phone"></span>
        </td>
        </tr>
        <tr>
        <td align="left">招聘公司地址：
<span id="company_address"></span>
        </td>
        <td align="absbottom">招聘公司邮箱：
<span id="company_email"></span>
        </td>
        </tr>
        </form>
        </table>

        </div>
        <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="update_order">
        提交保存
        </button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
        </button>

        </div>
        </div>
        <!-- /.modal-content -->
        </div>
        <!-- /.modal -->
        </div>
        data:{
            "pageNo": 1,
            "pageSize" :20
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text++
        success:function(data){
            //console.log(data)；

            if(data.code == 0 && data.data.list.length >0) {
                var dataList = data.data.list;

                for (var i = 0; i < dataList.length; i++) {
                    var html = "<tr>"
                        + "<td><input type='checkbox' data-cid='#{id}'></td>"
                        + "<td>#{id}</td>"
                        + "<td>#{className}</td>"
                        + "<td>#{courseName}</td>"
                        + "<td>#{classHour}</td>"
                        + "<td>#{teacherName}</td>"
                        + "<td>#{createTime}</td>"
                        + "<td>#{status}</td>"
                        + "</tr>"

                    html = html.replace("#{id}",dataList[i].id)
                    html = html.replace("#{className}",dataList[i].className)
                    html = html.replace("#{courseName}",dataList[i].courseName)
                    html = html.replace("#{classHour}",dataList[i].classHour)
                    html = html.replace("#{teacherName}",dataList[i].teacherName)
                    html = html.replace("#{createTime}",dataList[i].createTime)
                    html = html.replace("#{status}",dataList[i].status == 0? '禁用' : "启用")

                    $("tbody").append(html);

                }
            }

        }
    })

})

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
