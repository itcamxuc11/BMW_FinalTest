$("#save").click(function() {
	var noiDung = $("#noi-dung").val();
	var dapAnA = $("#dap-an-a").val();
	var dapAnB = $("#dap-an-b").val();
	var dapAnC = $("#dap-an-c").val();
	var dapAnD = $("#dap-an-d").val();
	var dapAnDung = document.forms.themcauhoi.dapandung.value;
	var capDo = $("#cap-do").val();
	var monHoc = $("#mon-hoc").val();

	$.ajax({
		type : "Post",
		data : {
			noidung : noiDung,
			dapana : dapAnA,
			dapanb : dapAnB,
			dapanc : dapAnC,
			dapand : dapAnD,
			dapandung : dapAnDung,
			capdo : capDo,
			monhoc : monHoc
		},
		url : "ThemCauHoi",
		success : function(status) {
			if (status == "ok") {
				alert("Đã thêm");
				$("#noi-dung").val("");
			} else
				alert(status);
		}
	})
})

var Upload = function(file) {
	this.file = file;
};

Upload.prototype.getType = function() {
	return this.file.type;
};
Upload.prototype.getSize = function() {
	return this.file.size;
};
Upload.prototype.getName = function() {
	return this.file.name;
};
Upload.prototype.doUpload = function() {
	var that = this;
	var formData = new FormData();

	formData.append("file", this.file, this.getName());
	formData.append("upload_file", true);

	$.ajax({
		type : "POST",
		url : "ThemFileCauHoi",
		success : function(status) {
			if (status == "ok") {
				alert("Thêm thành công");
			} else
				alert(status);
		},
		error : function() {
			alert("Lõi không xác định");
		},
		async : true,
		data : formData,
		cache : false,
		contentType : false,
		processData : false,
		timeout : 60000
	// 1 phút
	});
};

$("#file-cau-hoi").on("change", function(e) {
	var file = $(this)[0].files[0];
	var upload = new Upload(file);
	$("#file-name").text(upload.getName());
	$("#upload-file").unbind();
	$("#upload-file").click(function() {
		upload.doUpload();
	})
})