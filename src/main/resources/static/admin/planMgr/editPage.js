function addDetailRow(){
    var html = $('#detailList').html();
    html += "<tr>" +
            "<td></td>" +
            "</tr>"
    $('#detailList').html(html);
}