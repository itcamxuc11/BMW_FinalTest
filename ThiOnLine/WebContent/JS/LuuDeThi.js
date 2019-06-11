$(document).ready(function(){
    $("#save").click(function()
    {
        var maDeThi = $("#ma-de-thi").val();
        var a = document.getElementsByClassName("cau-hoi");
        var arr = new Array();
        for(var i=0;i<a.length;i++){
            arr.push(a[i].id);
         }
         $.ajax({
             url:"ThemDeThi",
             type:"post",
             data:{arr:JSON.stringify(arr),maDeThi:maDeThi},
             success:function(status){
                 if(status=="ok"){
                    alert("Thêm thành công");
                    window.location.href = "QuanLyDeThi";
                 }
                else alert(status);
             }
         })
    })
    
        $("#btn-yes").click(function(){
        var a = document.getElementsByClassName("cau-hoi");
        var arr = new Array();
        for(var i=0;i<a.length;i++){
            arr.push(a[i].id);
         }
         $.ajax({
            url:"SuaDeThi",
            type:"post",
            data:{arr:JSON.stringify(arr)},
            success:function(status){
                if(status=="ok"){
                   alert("Cập nhật thành công");
                   window.location.href = "QuanLyDeThi";
                }
               else alert(status);
            }
        })
    })
})


function deleteQuestion(el){
       var maCauHoi = el.getAttribute("data-ma");
       var old = document.getElementById(maCauHoi+"-block");
       document.getElementById("test-block").removeChild(old);

}
