    var dapAnDung = document.getElementById("noi-dung").getAttribute("data-value");
    var capDo = document.getElementById("cap-do").getAttribute("data-value");
    var monHoc = document.getElementById("mon-hoc").getAttribute("data-value");
    if (dapAnDung!=""&&dapAnDung!=" ") document.querySelector('input[type="radio"][value="'+dapAnDung+'"]').checked = true;
    if (capDo!="" &&capDo!=" ") document.querySelector('option[value="'+capDo+'"]').selected = 'selected';
    if (monHoc!=""&&capDo!=" ") document.querySelector('option[value="'+monHoc+'"]').selected = 'selected';

    $("#save").click(function() {
            var maCauHoi = $("#ma-cau-hoi").text();
			var noiDung = $("#noi-dung").val();
			var dapAnA = $("#dap-an-a").val();
			var dapAnB = $("#dap-an-b").val();
			var dapAnC = $("#dap-an-c").val();
			var dapAnD = $("#dap-an-d").val();
			dapAnDung = document.forms.themcauhoi.dapandung.value;
			capDo = $("#cap-do").val();
			monHoc = $("#mon-hoc").val();

			$.ajax({
				type : "Post",
				data : {
                    macauhoi:maCauHoi,
					noidung : noiDung,
					dapana : dapAnA,
					dapanb : dapAnB,
					dapanc : dapAnC,
					dapand : dapAnD,
					dapandung : dapAnDung,
					capdo : capDo,
					monhoc : monHoc
				},
				url : "SuaCauHoi",
				success : function(status) {
					if (status == "ok") {
						alert("Cập nhật thành công");
					} else
						alert(status);
				}
			})
		})