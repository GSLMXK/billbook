$(function () {
   initPage();
});
var HtmlUtil = {
    /*1.用浏览器内部转换器实现html转码*/
    htmlEncode:function (html){
        //1.首先动态创建一个容器标签元素，如DIV
        var temp = document.createElement ("div");
        //2.然后将要转换的字符串设置为这个元素的innerText(ie支持)或者textContent(火狐，google支持)
        (temp.textContent != undefined ) ? (temp.textContent = html) : (temp.innerText = html);
        //3.最后返回这个元素的innerHTML，即得到经过HTML编码转换的字符串了
        var output = temp.innerHTML;
        temp = null;
        return output;
    },
    /*2.用浏览器内部转换器实现html解码*/
    htmlDecode:function (text){
        //1.首先动态创建一个容器标签元素，如DIV
        var temp = document.createElement("div");
        //2.然后将要转换的字符串设置为这个元素的innerHTML(ie，火狐，google都支持)
        temp.innerHTML = text;
        //3.最后返回这个元素的innerText(ie支持)或者textContent(火狐，google支持)，即得到经过HTML解码的字符串了。
        var output = temp.innerText || temp.textContent;
        temp = null;
        return output;
    },
    /*3.用正则表达式实现html转码*/
    htmlEncodeByRegExp:function (str){
        var s = "";
        if(str.length == 0) return "";
        s = str.replace(/&/g,"&amp;");
        s = s.replace(/</g,"&lt;");
        s = s.replace(/>/g,"&gt;");
        s = s.replace(/ /g,"&nbsp;");
        s = s.replace(/\'/g,"&#39;");
        s = s.replace(/\"/g,"&quot;");
        return s;
    },
    /*4.用正则表达式实现html解码*/
    htmlDecodeByRegExp:function (str){
        var s = "";
        if(str.length == 0) return "";
        s = str.replace(/&amp;/g,"&");
        s = s.replace(/&lt;/g,"<");
        s = s.replace(/&gt;/g,">");
        s = s.replace(/&nbsp;/g," ");
        s = s.replace(/&#39;/g,"\'");
        s = s.replace(/&quot;/g,"\"");
        return s;
    }
};
//富文本加码
function editorShow(contentEle,showEle,isShow){
    //是否展示？解码：加码
    if(isShow){
        $('#'+showEle).text(HtmlUtil.htmlDecodeByRegExp($('#'+contentEle).val()));
    }else{
        var content = UE.getEditor(showEle).getContent();
        $('#'+contentEle).val(HtmlUtil.htmlEncodeByRegExp(content));
        return true;
    }
}
//打开模态框
function openModal(modalId,modalBefore,modelAfter){
    if(modalBefore){
        modalBefore();
    }
    $('#'+modalId).modal();
    if(modelAfter){
        modelAfter();
    }
}
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
//删除
function delM(id){
    window.location.href="delModel/"+id;
}
//查找
function search(id){
    window.location.href="list/"+id;
}
//翻页
function turnPage(page){
    var action = "list?pageSize=10&currentPage="+page;
    $("#searchForm").attr("action",action).submit();
}
//获取URL参数
function getUrlParm(paramName){
    var paramValue = "", isFound = !1;
    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
        var arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
        while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
    }
    return paramValue == "" && (paramValue = null), paramValue
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

function getRootPath() {
    var curWwwPath = window.document.location.href;		//获取主机地址之后的目录，如： /uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);		//获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);		//获取带"/"的方法名，如：/user
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht);
}
//页面初始化
function initPage(){}
