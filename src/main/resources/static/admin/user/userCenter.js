function registPost () {
    $.ajax({
        type: "post",
        url: "uploadPhoto",
        data: new FormData($("#userPhoto")[0]),
        processData: false,
        contentType: false,
    }).success(function(message) {
        console.log(message)
    }).fail(function(err){
        console.log(err)
    })
}