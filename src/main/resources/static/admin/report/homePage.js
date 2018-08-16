$(function() {
    getPageData();
});
function getPageData(){
    var url = "/Report/pageData";
    var data = {};
    var result = getAjaxData(url,data,true);
    alert(result);
}
