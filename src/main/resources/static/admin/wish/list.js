function initPage(){
    getCountData();
}
//获取用户存款信息
function getCountData(){
    var url = "/Wish/countData";
    var data = {};
    $.ajax({
        type : "POST",
        url : url,
        data : data,
        dataType : "json",
        async: true,
        success : function(json) {
            var data = json['moneyCount'];
            console.log(data);
            $('#saveLeft').text((parseFloat(data[0]['all_save'])-parseFloat(data[0]['used'])).toFixed(2));
            $('#used').text(data[0]['used']);
            $('#history').text(data[0]['all_save']);
        }
    });
}
//获取心愿详细项
function getWish(id){
    var url = "/Wish/edit";
    var data = {id:id};
    $.ajax({
        type : "POST",
        url : url,
        data : data,
        dataType : "json",
        async: false,
        success : function(json) {
            var data = json['wish'];
            console.log(data);
            $("#wishName").val(data['name']);
            $("#wishMoney").val(data['money']);
            $("#moneyIn").val(data['moneyIn']);
            $("#wishComment").text(data['comment']);
            $("#isFinished").val(data['isFinished']);
            $("#wishId").val(data['id']);
            // $('#saveLeft').text(parseFloat(data[0]['all_save'])-parseFloat(data[0]['used']));
            // $('#used').text(data[0]['used']);
            // $('#history').text(data[0]['all_save']);
        }
    });
}
//保存心愿
function saveWish(){
    var action = "save";
    $("#addForm").attr("action",action).submit();
}
//完成心愿
function finishWish(id){
    var url = "/Wish/finished";
    var data = {id:id};
    $.ajax({
        type : "POST",
        url : url,
        data : data,
        dataType : "json",
        async: false,
        success : function(json) {
            var data = json;
            console.log(data);
            if(data == 1){
                alert("Success");
            }
            // $('#saveLeft').text(parseFloat(data[0]['all_save'])-parseFloat(data[0]['used']));
            // $('#used').text(data[0]['used']);
            // $('#history').text(data[0]['all_save']);
        }
    });
}