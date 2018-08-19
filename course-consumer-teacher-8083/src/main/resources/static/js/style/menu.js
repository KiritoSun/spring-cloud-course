$(function () {
    // 菜单栏击中效果
    var menu = get();
    console.log(menu);
    if(menu==null) $(".course-menu li[name='index']").addClass("menu-focus");
    else $(".course-menu li[name='"+menu+"']").addClass("menu-focus");
    // 菜单栏点击
    $(".course-menu li").click(function () {
        var menu = $(this).attr("name");
        switch (menu){
            case "index":{
                save("index");
                window.location.href="/index";
                break;
            }
            case "info":{
                save("info");
                window.location.href="/info";
                break;
            }
            case "add":{
                save("add");
                window.location.href="/add";
                break;
            }
            case "course":{
                save("course");

                break;
            }
            case "student":{
                save("student");

                break;
            }
            case "exit":{
                clear();

                break;
            }
            default:break;
        }
    });
    
    // 存储到session缓存
    function save(data) {
        sessionStorage.setItem("menu",data);
    }
    
    // 取出session缓存
    function get() {
        return sessionStorage.getItem("menu");
    }

    // 清除class操作
    function clear() {
        sessionStorage.removeItem("menu");
    }
})