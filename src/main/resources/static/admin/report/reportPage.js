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
    getData();
}

function randomColor() {
    var r=Math.floor(Math.random()*256);
    var g=Math.floor(Math.random()*256);
    var b=Math.floor(Math.random()*256);
    return "rgb("+r+','+g+','+b+")";
}
//加载饼图
function loadPieData(pageData) {
    var i = 0;
    var labels = [], data=[], backgroundColor=[];
    var details = "";
    for(var key in pageData) {
        details += "<div>" + pageData[key]['pTypeName'] + "-" + pageData[key]['typeName'] + "：" + pageData[key]['countMoney'] + "</div>";
        // 遍历json字符串,将json字符串插入到新建的两个数组中,填充饼状的数据
        labels.push(pageData[key]['typeName']);
        data.push(parseFloat(pageData[key]['countMoney']));
        backgroundColor.push(randomColor());
    }
    $('#details').html(details);
    var config = {
        type: 'pie',
        data: {
            datasets: [{
                data: data,
                backgroundColor: backgroundColor,
            }],
            labels: labels
        },
        options: {
            responsive: true
        }
    };
    var ctx = document.getElementById('chart-area').getContext('2d');
    var myBarChart = new Chart(ctx, config);
}
//获取页面数据
function getData(){
    var type = getUrlParm('type');
    type = type==null?0:type;
    var url = "/Report/reportPageData";
    var data = {type:type,month:$("#searchDate").val()};
    $.ajax({
        type : "POST",
        url : url,
        data : data,
        dataType : "json",
        async: false,
        success : function(json) {
            loadPieData(json['reportList']);
        }
    });
}
function clearCanvas(){
    $('#chart-area').remove();
    $('#canvas-holder').append('<canvas id="chart-area"></canvas>');
}