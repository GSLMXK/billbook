function initPage(){
    var searchDate = $("#searchDate").val();
    if(searchDate == ""){
        searchDate = dateFtt("yyyy-MM",new Date());
        $("#searchDate").val(searchDate);
    }
    $('#searchDate').datetimepicker({
        minView: 'month',
        format: 'yyyy-mm',
        initialDate: searchDate,
        autoclose:true
    });
    getBillInfo();
}

//获取当前月份的收支信息
function getBillInfo(){
    var url = "/BillMgr/countData";
    var data = {searchDate:$("#searchDate").val()};
    $.ajax({
        type : "POST",
        url : url,
        data : data,
        dataType : "json",
        async: true,
        success : function(json) {
            console.log(json['moneyOI']);
            var oi = json['moneyOI'];
            $('#monthIn').text(oi[1]['countMoney']);
            $('#monthOut').text(oi[0]['countMoney']);
        }
    });
}