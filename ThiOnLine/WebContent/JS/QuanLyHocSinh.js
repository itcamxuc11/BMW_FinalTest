$(document).ready(function() {
	$(".edit").click(function() {
        var tentk = this.id;
        var trr = document.getElementById("tr-"+tentk);
        var tttk = trr.getElementsByTagName("td");
        $("#heading-name").text(tttk[1].innerHTML);
        $("#new-ten-hs").val(tttk[1].innerHTML);
        $("#new-ngay-sinh").val(tttk[2].innerHTML);
        $("#new-dia-chi").val(tttk[3].innerHTML);
        $("#new-sdt").val(tttk[4].innerHTML);
        $("#save-change").click(function()
        {
            var tenhs = $("#new-ten-hs").val();
            var ngaysinh = $("#new-ngay-sinh").val();
            var diachi = $("#new-dia-chi").val();
            var sdt =  $("#new-sdt").val();
            $.ajax({
                type: "post",
                data:{
                    tentk:tentk,
                    tenhs:tenhs,
                    ngaysinh:ngaysinh,
                    diachi:diachi,
                    sdt:sdt
                 },
                 url:"QuanLyHocSinh",
                 success: function(status){
                    if(status=="ok") alert("Cập nhật thành công");
                    else alert(status);
                 }
            })
        })
        $.ajax({
            type : "GET",
            data : {
            TenTK : tentk
                },
            url : "LopHocThamGia",
            success : function(responsejson) {
                var parent = document.getElementById("list-class");
                parent.innerHTML = "";
                $.each(responsejson,
                    function(key,value) {
                    var tmp = document.createElement("div");
                    tmp.className = "d-flex list-member-item";
                    tmp.id = "block-"
                    + value['MaLop'];
                    tmp.innerHTML = '<p>'
                    + value['MaLop']
                    + '</p>'
                    + '<p>'
                    + value['TenLop']
                    + '</p>'
                    + '<div><button data-target="#accept-delete" data-toggle="modal"'
                    + 'id = "' + value['MaLop'] + '"'
                    +'class="btn delete-item btn-danger remove-n">Xóa</button></div>'
                    parent.appendChild(tmp);
                })
                $('.remove-n').click(function() {
                    var cl = this.id;
                    $(".accept-delete").unbind();$(".accept-delete").click(function() {
                        $.ajax({
                            type : "GET",
                            data : {
                            TenTK : tentk,
                            MaLop : cl
                            },
                            url : "XoaKhoiLop",
                            success : function(status) {
                                if (status == "done") {
                                var old = document.getElementById("block-"+ cl);
                                parent.removeChild(old);
                                $("#accept-delete").modal('hide');
                                } else alert(status);
                            }
                        })
                    })
                })
	        }
        })
	})
})

$(document).ready(function() {
    $("#add").click(function() {
        var tenTK = $("#ten-tk").val();
        var tenHS = $("#ten-hs").val();
        var matKhau = $("#mat-khau").val();
        var ngaySinh = $("#ngay-sinh").val();
        var diaChi = $("#dia-chi").val();
        var soDT = $("#sdt").val();

        var hocSinh = {
            TenTK : tenTK,
            TenNguoiDung : tenHS,
            MatKhau : matKhau,
            NgaySinh:ngaySinh,
            DiaChi : diaChi,
            SDT : soDT
        };
        $.ajax({
            url : "QuanLyHocSinh",
            type : "post",
            data : {
                hocSinh : JSON.stringify(hocSinh)
            },
            success : function(status) {
                if (status == "ok")
                    alert("Thêm thành công");
                else
                    alert(status);
            }
        })
    })
})

$(document).ready(
    function() {
        $(".close-modal").click(function() {
            $("#accept-delete").modal('hide');
     })
 })