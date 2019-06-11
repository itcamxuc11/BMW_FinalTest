var listDate = document.getElementsByClassName("fake-date")
for (var i = 0; i < listDate.length; i++) {
	var str = listDate[i].getAttribute("data-date");
	var d = str.substring(0, 10);
	var t = str.substring(11, 16);
	listDate[i].setAttribute("value", d + "T" + t);
}

$("#save").click(function() {
	var maDeThi = document.getElementById("ma-de").innerHTML;
	var maLop = $("#ma-lop").val();
	var tieuDe = $("#tieu-de").val();
	var thoiLuong = $("#thoi-luong").val();
	var batDau = $("#bat-dau").val();
	var ketThuc = $("#ket-thuc").val();
	var soLanLamBai = $("#so-lan").val();
	
	var BG_DeThi = {
		MaLop : maLop,
		MaDeThi : maDeThi,
		TieuDe : tieuDe,
		TGBatDau : batDau,
		TGKetThuc : ketThuc,
		ThoiLuong : thoiLuong,
		SoLanLamBai : soLanLamBai
	};
	$.ajax({
		type : "Post",
		data : {
			querry : JSON.stringify(BG_DeThi)
		},
		url : "GiaoDeThi",
		success : function(status) {
			if (status == "ok") {
				alert("Đã thêm");
				location.reload();
			} else
				alert(status);
		}
	})
})

$(".save-change").click(function()
{
    var maDeThi = document.getElementById("ma-de").innerHTML;
    var maLop = $(this).data("target");
    var block = document.getElementById("block-"+maLop);
    var listInput = block.getElementsByClassName("form-control");
    var tieuDe = listInput[1].value;
    var thoiLuong  = listInput[2].value;
    var soLanLamBai = listInput[3].value;
    var batDau = listInput[4].value;
    var ketThuc = listInput[5].value;
    var BG_DeThi = { MaLop: maLop, MaDeThi: maDeThi, TieuDe: tieuDe,TGBatDau:batDau, TGKetThuc:ketThuc, ThoiLuong: thoiLuong, SoLanLamBai: soLanLamBai };
    $.ajax({
        type: "Post",
        data: {add:true, querry: JSON.stringify(BG_DeThi)},
        url: "GiaoDeThi",
        success: function (status) {
            if (status == "ok") {
                parent = document.getElementById("lop");
                alert("Cập nhật thành công");
               location.reload();
            }
            else alert(status);
        }
    })
})





