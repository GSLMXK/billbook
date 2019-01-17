//当选择上级类型时，自动选择出入账类型
function setType(){
    var fid = $('#fid').val();
    var type;
    //选择“无”时，可以选择类型
    if(fid!=""){
        type = $("#foption_"+fid).attr("optType");
        $("input[name='type']:eq("+type+")").attr("checked",'checked');
        $("input[name='type']:eq("+type+")").parent().addClass("am-active");
        $("input[name='type']:eq("+type+")").parent().removeAttr("disabled");
        $("input[name='type']").each(function (){
            if($(this).val()!=type){
                $(this).removeAttr("checked");
                $(this).parent().removeClass("am-active");
                $(this).parent().attr("disabled",'disabled');
            }
        });
    }else{
        $("input[name='type']").each(function (){
            $(this).parent().removeAttr("disabled");
        });
    }
}