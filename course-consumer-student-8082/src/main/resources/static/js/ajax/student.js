// 学生登录验证
function login(data) {
    $.ajax({
        method:"POST",
        type:"POST",
        url:"/login.action",
        data:data,
        success:function (msg) {
            switch (msg){
                case "success":{
                    window.location.href="/index";
                    break;
                }
                case "ino":{
                    dialog("账户id不存在！");
                    break;
                }
                case "pno":{
                    dialog("密码不正确！");
                    break;
                }
                case "error":{
                    dialog("后台出错！");
                    break;
                }
                default:break;
            }
        }
        ,error:function () {
            dialog("ajax出错！");
        }
    });
}

// 修改学生信息
function update_studentInfo(data) {
    $.ajax({
       method:"POST",
       type:"POST",
       url:"/updateStudentInfo",
       data:data,
       success:function (msg) {
           if(msg=="success"){
               dialog("修改信息成功！");
           }else{
               dialog("修改信息失败！");
           }
       },error:function () {
            dialog("ajax出错！");
        }
    });
}

// 学生选课操作
function select_studentCourse(data) {
    $.ajax({
       method:"POST",
       type:"POST",
       url:"/selectCourse",
       data:data,
        success:function (msg) {
            switch (msg){
                case "success":{
                    dialog("选课成功！");
                    break;
                }
                case "npoint":{
                    dialog("积分不够！");
                    break;
                }
                case "tno":{
                    dialog("上课时间冲突！");
                    break;
                }
                case "no":{
                    dialog("该课程已选！");
                    break;
                }
                case "error":{
                    dialog("选课失败！");
                    break;
                }
                default:break;
            }
        },error:function () {
            dialog("ajax出错！");
        }
    });
}

// 修改选课积分
function update_point(data) {
    $.ajax({
        method:"POST",
        type:"POST",
        url:"/updatePoint",
        data:data,
        success:function (msg) {
            if(msg=="success"){
                dialog("修改积分成功！");
            }else{
                dialog("修改积分失败！");
            }
        },error:function () {
            dialog("ajax出错！");
        }
    });
}

// 退课
function remove_course(data) {
    $.ajax({
        method:"POST",
        type:"POST",
        url:"/removeSelect",
        data:data,
        success:function (msg) {
            if(msg=="success"){
                $("#"+data['id']).parent("tr").remove();
                dialog("退课成功！");
            }else{
                dialog("退课失败！");
            }
        },error:function () {
            dialog("ajax出错！");
        }
    });
}

// 生成课表
function create_table() {
    $.ajax({
        method:"POST",
        type:"POST",
        url:"/createTable",
        data:{},
        success:function (msg) {
            if(msg=="null"){

            }else{
                var json = JSON.parse(msg);
                for(var e in json){
                    $("#"+json[e].time).html(json[e].name);
                }
            }
        },error:function () {
            dialog("ajax出错！");
        }
    });
}