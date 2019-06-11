<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layoutAdmin>
	<jsp:attribute name="sidebar">
			<li class="nav-item"><a style="color: white" href="QuanLyLopHoc">Lớp học</a></li>
			<li class="nav-item"><a style="color: white" href="QuanLyHocSinh"> Học sinh</a></li>
	</jsp:attribute>
	<jsp:attribute name="content">
							<div class="page-header page-header-default">
						<div class="page-header-content">
							<div class="page-title">
								<h3 class="media-heading">Danh sách học sinh</h3>
							</div>
						</div>
						<div class="breadcrumb-line">
							<ul class="breadcrumb">
								<li><a href="index.html">Home</a></li>
								<li class="active">Danh sách học sinh</li>
							</ul>
						</div>
					</div>
					<div class="content">
						<div class="panel panel-border-top d-flex">
							<div class="col">
								<button data-target="#theHocSinh" data-toggle="modal"
						class="btn btn-dark btn-labeled">
									<b><i class="fas fa-plus-square"></i></b>Thêm học sinh
								</button>
							</div>
							<form action="TimKiemHocSinh" method="post"
					class="col-6 input-group mr-2">
								<input name="k" type="text" class="form-control"
						placeholder="Tìm kiếm">
								<div class="input-group-prepend">
									<button type="submit" class="btn btn-outline-secondary">
										Tìm</button>
								</div>
							</form>
						</div>
						<div class="table-reponsive">
							<table class="table table-striped table-hover">
								<thead class="custom-thead">
									<tr>
										<th>Tên tài khoản</th>
										<th>Tên học sinh</th>
										<th>Ngày sinh</th>
										<th>Địa chỉ</th>
										<th>Số điện thoại</th>
										<th>Số lớp học</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${DShocsinh}" var="hocsinh">
										<tr id="tr-${hocsinh.tenTK }">
											<td>
												<div class="dropdown">
													<span data-toggle="dropdown" class="dropdown-toggle">${hocsinh.tenTK}</span>
													<ul class="dropdown-menu">
														<li data-ten="${hocsinh.tenNguoiDung}" data-target="#fac"
												id="${hocsinh.tenTK}" data-toggle="modal"
												class="dropdown-item edit">Xem và sửa</li>
														<li class="dropdown-item"><a
												href="XoaHocSinh?tenTK=${hocsinh.tenTK}">Xóa</a></li>
													</ul>
												</div>
											</td>
											<td>${hocsinh.tenNguoiDung}</td>
											<td>${hocsinh.ngaySinh}</td>
											<td>${hocsinh.diaChi}</td>
											<td>${hocsinh.SDT}</td>
											<td>${hocsinh.soLuongLopHoc}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<p style="color: red">${error}</p>
					<div id="pagi-nav" class="pagination">
						<a href="#">&laquo;</a>
					</div>
					<div id="fac" class="modal full-question-content">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 id="heading-name" class="modal-title"></h4>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>
								<div class="modal-body">
									<ul class="nav nav-tabs">
									  <li class="nav-item">
									    <a class="nav-link active" data-toggle="tab" href="#tab1">Thông tin học sinh</a>
									  </li>
									  <li class="nav-item">
									    <a class="nav-link" data-toggle="tab" href="#tab2">Lớp học đã tham gia</a>
									  </li>
									</ul>									
									<div class="tab-content">
										<div id="tab1" class="tab-pane active mt-2">
												<div class="form-group">
													<label class="control-label">Tên học sinh</label> <input
										id="new-ten-hs" type="text" class="form-control" />
												</div>
												<div class="form-group">
													<label class="control-label">Ngày Sinh</label> <input
										id="new-ngay-sinh" name="maDeThi" type="date"
										class="form-control" />
												</div>
												<div class="form-group">
													<label class="control-label">Địa chỉ</label> <input
										id="new-dia-chi" type="text" class="form-control" />
												</div>
												<div class="form-group">
													<label class="control-label">Số điện thoại</label> <input
										id="new-sdt" type="text" class="form-control" />
												</div>
										</div>
										<div id="tab2" class="tab-pane">
											<div class="user-profile d-flex">
												<div class="col">
													<div class="list-members" id="list-class"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button id="save-change" type="button" class="btn btn-success">Lưu</button>
									<button type="button" class="btn btn-danger"
							data-dismiss="modal">Hủy</button>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="modal fade" id="accept-delete">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Bạn có chắc chăn?</h4>
									<button type="button" class="close close-modal">&times;</button>
								</div>
								<div class="modal-body">Xác nhận xóa học sinh ra khỏi lớp
									học?</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-danger accept-delete">Xóa</button>
									<button type="button" class="btn btn-secondary close-modal">Close</button>
								</div>
							</div>
						</div>
					</div>
	<div class="modal fade" id="theHocSinh">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Thêm học sinh</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label class="control-label">Tên tài khoản</label> <input
								id="ten-tk" type="text" class="form-control" />
					</div>
					<div class="form-group">
						<label class="control-label">Tên học sinh</label> <input
								id="ten-hs" type="text" class="form-control" />
					</div>
					<div class="form-group">
						<label class="control-label">Mật khẩu</label> <input id="mat-khau"
								type="text" class="form-control" />
					</div>
					<div class="form-group">
						<label class="control-label">Ngày Sinh</label> <input
								id="ngay-sinh" name="maDeThi" type="date" class="form-control" />
					</div>
					<div class="form-group">
						<label class="control-label">Địa chỉ</label> <input id="dia-chi"
								name="maDeThi" type="text" class="form-control" />
					</div>
					<div class="form-group">
						<label class="control-label">Số điện thoại</label> <input id="sdt"
								name="maDeThi" type="text" class="form-control" />
					</div>
				</div>
				<div class="modal-footer">
					<button id="add" type="button" class="btn btn-success">Thêm</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<script src="JS/QuanLyHocSinh.js" type="text/javascript"></script>
	<script type="text/javascript">
		var soTrang = ${soTrang};
		var trang = ${trang};
		var parent = document.getElementById("pagi-nav");
		for (var i = 1; i <= soTrang; i++) {
			var child = document.createElement("a");
			child.setAttribute("href", "QuanLyHocSinh?page=" + i);
			if (i == trang)
				child.setAttribute("class", "active");
			child.innerHTML = i;
			parent.appendChild(child);
		}
		var child = document.createElement("a");
		child.innerHTML = "&raquo;" ;
		parent.appendChild(child);
	</script>
	</jsp:attribute>
</t:layoutAdmin>