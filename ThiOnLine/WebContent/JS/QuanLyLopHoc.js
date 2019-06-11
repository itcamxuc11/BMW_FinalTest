var daTaiXuong1 = false;
var daTaiXuong2 = false;
	
$(document).ready(function () {
	$(".close-modal").click(function () {
		$("#accept-delete").modal('hide');
	})
		
	$(".edit").click(function () {
		var maLop = $(this).data("ma");
		var tenLop = $(this).data("ten");
		var ngayKetThuc = $(this).data("end");
		$("#modal-title").text(tenLop + " -- " + maLop);
		$("#class-name").val(tenLop);
		$("#class-end").val(ngayKetThuc);
		var listHSChuaVao = document.getElementById("list-not-in");
		var LisHSDaVao = document.getElementById("list-in");
		daTaiXuong1 = false;
		daTaiXuong2 = false;
		$("#nav-not-in").click(function () {
			if (daTaiXuong1 == false) {
				LayDSHocSinhChuaVaoLop(maLop, listHSChuaVao);
				daTaiXuong1 = true;
			}
		})
		$("#nav-in").click(function () {
			if (daTaiXuong2 == false) {
				LayDSHocSinhDaVaoLop(maLop, LisHSDaVao);
				daTaiXuong2 = true;
			}
		})
		
		var button = document.getElementById("accept-edit");
		button.onclick = function()
		{
		    var tenLop = $("#class-name").val();
		    var ngayKetThuc = $("#class-end").val();
		    $.ajax({
		        url:"CapNhatLopHoc",
		        type:"post",
		        data: {maLop:maLop, tenLop:tenLop, ngayKetThuc:ngayKetThuc},
		        success: function(status)
		        {
		            if(status=="ok") {
		                alert("Cập nhật thành công");
		                location.reload();
		            }
		            else alert(status);
		        }
		    })
		}		
	})
})



function LayDSHocSinhChuaVaoLop(maLop, listHSChuaVao) {
	$.ajax({
		url: "QuanLyLopHoc",
		type: "post",
		data: {
			tool: "them",
			maLop: maLop
		},
		success: function (responsejson) {
			listHSChuaVao.innerHTML = "";
			$.each(responsejson, function (key, value) {
				var tmp = document.createElement("div");
				tmp.className = "d-flex list-member-item";
				tmp.id = "not-in-" + value['TenTK'];
				tmp.innerHTML = '<p>' + value['TenTK'] + '</p>' + '<p>' +
					value['TenHocSinh'] + '</p>' + '<div><button ' +
					'data-ma = "' + value['TenTK'] + '"' + 'data-ten = "' +
					value['TenHocSinh'] + '"' +
					'class="btn btn-success add-h">Thêm</button></div>'
				listHSChuaVao.appendChild(tmp);
			})
			$(".add-h").click(
				function () {
					var tenTK = $(this).data("ma");
					var tenHocSinh = $(this).data("ten");
					$.ajax({
						url: ("XoaKhoiLop"),
						type: "post",
						data: {
							tool: "them",
							tenTK: tenTK,
							maLop: maLop
						},
						success: function (status) {
							if (status == "ok") {
								var old = document.getElementById("not-in-" +tenTK);
								listHSChuaVao.removeChild(old);
								daTaiXuong2 = false;
							} else
								alert(status);
						}
					})
				})
		}
	})
}

function LayDSHocSinhDaVaoLop(maLop, LisHSDaVao) {
	$.ajax({
		url: "QuanLyLopHoc",
		type: "post",
		data: {
			tool: "xoa",
			maLop: maLop
		},
		success: function (responsejson) {
			LisHSDaVao.innerHTML = "";
			$.each(responsejson, function (key, value) {
				var tmp = document.createElement("div");
				tmp.className = "d-flex list-member-item";
				tmp.id = "in-" + value['TenTK'];
				tmp.innerHTML = '<p>' +
					value['TenTK'] +
					'</p>' +
					'<p>' +
					value['TenHocSinh'] +
					'</p>' +
					'<div><button data-target="#accept-delete" data-toggle="modal"' +
					'data-ma = "' +
					value['TenTK'] +
					'"' +
					'data-ten = "' +
					value['TenHocSinh'] +
					'"' +
					'class="btn btn-danger delete-h">Xóa</button></div>'
				LisHSDaVao.appendChild(tmp);
			})
			$(".delete-h").click(function () {
				var tenTK = $(this).data("ma");
				var tenHocSinh = $(this).data("ten");
				$("#veri-question").text(
					"Bạn muốn xóa" + tenHocSinh +
					" ra khỏi lớp?");
				$("#btn-accept-delete").unbind();
				$("#btn-accept-delete").click(
					function () {
						$.ajax({
							url: "XoaKhoiLop",
							type: "post",
							data: {
								tool: "xoa",
								tenTK: tenTK,
								maLop: maLop
							},
							success: function (
								status) {
								if (status == "ok") {
									var old = document.getElementById("in-" +tenTK);
									LisHSDaVao.removeChild(old);
									$("#accept-delete").modal('hide');
									daTaiXuong1 = false;
								} else
									alert(status);
							}
						})
					})
			})
		}
	})
}


var btn = document.getElementById("accept-add");
btn.onclick = function()
{
    var tenLop = $("#ten-lop").val();
    var ngayKetThuc = $("#ket-thuc").val();
    $.ajax({
        url:"CapNhatLopHoc",
        type:"post",
        data: {tenLop:tenLop, ngayKetThuc:ngayKetThuc},
        success: function(status)
        {
            if(status=="ok") {
                alert("Thêm thành công");
                location.reload();
            }
            else alert(status);
        }
    })
}




