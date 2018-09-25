$(function () {
   initPage();
});

//添加
function add(){
    window.location.href="add";
}
//修改
function edit(id){
    window.location.href="edit/"+id;
}
//删除
function del(id){
    window.location.href="delete/"+id;
}
//查找
function search(id){
    window.location.href="list/"+id;
}
//翻页
function turnPage(page){
    window.location.href="list?pageSize=10&currentPage="+page;
}
//获取URL参数
function getUrlParm(){

}
//获取公告
function getNotice(){

}
//时间格式化处理
function dateFtt(fmt,date)
{ //author: meizz
    var o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S"  : date.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}
//url 访问地址   parm  传递参数   async 是否异步false  true
function getAjaxData(url, parm, async){
    $.ajax({
        type : "POST",
        url : url,
        data : parm,
        dataType : "json",
        async: async,
        success : function(json) {
            return json;
        }
    });
}
//页面初始化
function initPage(){}
