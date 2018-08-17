$(function() {
    getPageData();
});
//获取页面数据并填充
function getPageData(){
    var url = "/Report/pageData";
    var data = {};
    $.ajax({
        type : "POST",
        url : url,
        data : data,
        dataType : "json",
        async: true,
        success : function(json) {
            console.log(json['moneyOI']);
            var oi = json['moneyOI'];
            moneyOI(oi);
        }
    });
}

function moneyOI(oi){
    $('#out_mon').text(oi[0]['countMoney']);
    $('#in_mon').text(oi[1]['countMoney']);
}
