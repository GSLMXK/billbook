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
//翻页
function turnPage(page){
    window.location.href="list?pageSize=5&currentPage="+page;
}
//获取URL参数
function getUrlParm(){

}
//获取公告
function getNotice(){

}