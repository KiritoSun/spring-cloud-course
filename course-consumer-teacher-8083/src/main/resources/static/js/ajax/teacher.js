// 验证身份
function login(data) {
    $.ajax({
       method:"POST",
       type:"POST",
       url:"/login",
       data:data,
       success:function (msg) {
           switch (msg){
               case "success":{
                   window.location.href="/index";
                   break;
               }
               case "pno":{
                   dialog("密码不正确！");
                   break;
               }
               case "tno":{
                   dialog("账户id不存在！");
                   break;
               }
               case "error":{
                   dialog("后台出错！");
                   break;
               }
               default:
           }
       },error:function () {
            dialog("ajax出错！");
        }
    });
}