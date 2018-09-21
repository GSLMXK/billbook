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
