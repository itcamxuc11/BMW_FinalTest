<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layoutAdmin>
	<jsp:attribute name="sidebar">
		<li class="nav-item"><a href="QuanLyDeThi">Danh Sách đề thi</a></li>		
	</jsp:attribute>
	<jsp:attribute name="content">
							<div class="page-header page-header-default">
						<div class="page-header-content">
							<div class="page-title">
								<h3 class="media-heading">Danh sách đề thi</h3>
							</div>
						</div>
						<div class="breadcrumb-line">
							<ul class="breadcrumb">
								<li><a href="index.html">Home</a></li>
								<li class="active">Danh sách đề thi</li>
							</ul>
						</div>
					</div>
					<div class="content">
						<div class="panel panel-border-top">
							<div class="col-12">
								<button data-target="#new-exam" data-toggle="modal"
						class="btn btn-dark btn-labeled">
									<b><i class="fas fa-plus-square"></i></b>Thêm đề thi
								</button>
							</div>
						</div>
						<div class="table-reponsive">
							<table class="table table-striped table-hover">
								<thead class="custom-thead">
									<tr>
										<th>Mã đề thi</th>
										<th>Môn học</th>
										<th>Số câu dễ</th>
										<th>Số câu trung bình</th>
										<th>Số câu khó</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${DSDeThi }" var="deThi">
										<tr>
											<td>
												<div class="dropdown">
													<span data-toggle="dropdown" class="dropdown-toggle">${deThi.maDeThi }</span>
													<ul class="dropdown-menu">
														<li class="dropdown-item"><a class="delete-link"
												href="XoaDeThi?maDe=${deThi.maDeThi}">Xóa</a></li>
														<li data-sub="${deThi.maMonHoc}"
												data-id="${deThi.maDeThi }" class="dropdown-item edit-exam">Sửa</li>
														<li class="dropdown-item"><a
												href="GiaoDeThi?maDe=${deThi.maDeThi }">Gán đề thi</a></li>
													</ul>
												</div>
											</td>
											<td>${deThi.maMonHoc }</td>
											<td>${deThi.soCauDe }</td>
											<td>${deThi.soCauTrungBinh }</td>
											<td>${deThi.soCauKho }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div id="pagi-nav" class="pagination">
						<a href="#">&laquo;</a>
					</div>
					<div class="modal fade" id="new-exam">
						<div class="modal-dialog">
							<div class="modal-content">
								<form action="ThemDeThi">
									<div class="modal-header">
										<h4 class="modal-title">Dề thi mới</h4>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>
									<div class="modal-body">
										<div class="d-flex mb-3">
											<div class="col">
												<label class="control-label">Môn học</label> <select
										id="mon-hoc" name="monHoc" class="custom-select">
													<c:forEach items="${dsMonHoc }" var="monHoc">
														<option value="${monHoc.maMonHoc }"><span>${monHoc.tenMonHoc}-</span><span>${monHoc.maMonHoc}</span></option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="d-flex">
											<div class="col">
												<div class="form-group">
													<label class="control-label">Số câu dễ</label> <input
											value="0" id="cau-de" name="de" type="number"
											class="form-control" />
												</div>
											</div>
											<div class="col">
												<div class="form-group">
													<label class="control-label">Số câu trung bình</label> <input
											value="0" id="cau-tb" name="trungBinh" type="number"
											class="form-control" />
												</div>
											</div>
											<div class="col">
												<div class="form-group">
													<label class="control-label">Số câu khó</label> <input
											value="0" id="cau-kho" name="kho" type="number"
											class="form-control" />
												</div>
											</div>
										</div>
										<div style="text-align: center" id="gui" class="col mt-4 pb-3">
										</div>
									</div>
									<div class="modal-footer">
										<button type="submit" class="btn btn-primary">Tiếp
											tục</button>
										<button type="button" class="btn btn-danger"
								data-dismiss="modal">Close</button>
									</div>
								</form>
							</div>
						</div>
					</div>
	<script src="JS/QuanLyDeThi.js" type="text/javascript"></script>
	<script src="JS/delete-confirm.js" type="text/javascript"></script>
	<script type="text/javascript">
	var soTrang = ${soTrang};
	var trang = ${trang};
		var parent = document.getElementById("pagi-nav");
		for (var i = 1; i <= soTrang; i++) {
			var child = document.createElement("a");
			child.setAttribute("href", "QuanLyDeThi?page=" + i);
			if (i == trang)
				child.setAttribute("class", "active");
			child.innerHTML = i;
			parent.appendChild(child);
		}
		var child = document.createElement("a");
		child.innerHTML = "&raquo;";
		parent.appendChild(child);
	</script>
	</jsp:attribute>
</t:layoutAdmin>