//当选择上级类型时，自动选择出入账类型
function setType(){
    var fid = $('#fid').val();
    var type;
    //选择“无”时，可以选择类型
    if(fid!=""){
        type = $("#foption_"+fid).attr("optType");
        if(type == 0){
            $("input[name='type']:eq(0)").attr("checked",'checked');
            $("input[name='type']:eq(0)").parent().addClass("am-active");
            $("input[name='type']:eq(0)").parent().removeAttr("disabled");
            $("input[name='type']:eq(1)").removeAttr("checked");
            $("input[name='type']:eq(1)").parent().removeClass("am-active");
            $("input[name='type']:eq(1)").parent().attr("disabled",'disabled');
        }else if(type == 1){
            $("input[name='type']:eq(0)").removeAttr("checked");
            $("input[name='type']:eq(0)").parent().removeClass("am-active");
            $("input[name='type']:eq(0)").parent().attr("disabled",'disabled');

            $("input[name='type']:eq(1)").attr("checked",'checked');
            $("input[name='type']:eq(1)").parent().addClass("am-active");
            $("input[name='type']:eq(1)").parent().removeAttr("disabled");
        }else{
            alert("Data Error!!!");
        }
    }else{
        $("input[name='type']:eq(0)").parent().removeAttr("disabled");
        $("input[name='type']:eq(1)").parent().removeAttr("disabled");
    }
}