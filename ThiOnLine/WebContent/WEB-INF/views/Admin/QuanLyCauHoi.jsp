<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layoutAdmin>
	<jsp:attribute name="sidebar">
		<li class="nav-item"><a href="QuanLyCauHoi">Danh sách câu hỏi</a></li>		
	</jsp:attribute>
	<jsp:attribute name="content">
							<div class="page-header page-header-default">
						<div class="page-header-content">
							<div class="page-title">
								<h3 class="media-heading">Danh sách câu hỏi</h3>
							</div>
						</div>
						<div class="breadcrumb-line">
							<ul class="breadcrumb">
								<li><a href="index.html">Home</a></li>
								<li class="active">Danh sách câu hỏi</li>
							</ul>
						</div>
					</div>
					<div class="content">
						<div class="panel panel-border-top d-flex">
							<div class="col">
								<a href="ThemCauHoi" class="btn btn-dark btn-labeled"> <b><i
							class="fas fa-plus-square"></i></b>Thêm câu hỏi
								</a>
							</div>
							<div class="col-6 input-group mr-2">
								<input type="text" class="form-control" placeholder="Tìm kiếm">
								<div class="input-group-prepend">
									<button type="button" class="btn btn-outline-secondary">
										Tìm</button>
								</div>
							</div>
						</div>
						<div class="table-reponsive">
							<table class="table table-striped table-hover">
								<thead class="custom-thead">
									<tr>

										<th scope="col">Mã câu hỏi</th>
										<th scope="col">Nội dung</th>
										<th scope="col">Môn học</th>
										<th scope="col">Cấp độ</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${dsCH.ds }" var="cauhoi">
										<tr>
											<td>
												<div class="dropdown">
													<span data-toggle="dropdown" class="dropdown-toggle">${cauhoi.maCauHoi}</span>
													<ul class="dropdown-menu">
														<li class="dropdown-item"><a class="delete-link"
												href="XoaCauHoi?macauhoi=${cauhoi.maCauHoi}">Xóa</a></li>
														<li class="dropdown-item"><a
												href="SuaCauHoi?id=${cauhoi.maCauHoi }">Xem và sửa</a></li>
													</ul>
												</div>
											</td>
											<td class="question">
												<div class="question-content">${cauhoi.noiDung}</div>
											</td>
											<td>${cauhoi.monHoc}</td>
											<td>${cauhoi.capDo}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div id="pagi-nav" class="pagination"></div>
	<script type="text/javascript">
		var soTrang = ${soTrang};
		var trang = ${trang};
		var parent = document.getElementById("pagi-nav");
		for (var i = 1; i <= soTrang; i++) {
			var child = document.createElement("a");
			child.setAttribute("href", "QuanLyCauHoi?page=" + i);
			if (i == trang)
				child.setAttribute("class", "active");
			child.innerHTML = i;
			parent.appendChild(child);
		}	
	</script>
	<script src="JS/delete-confirm.js" type="text/javascript"></script>
	</jsp:attribute>
</t:layoutAdmin>
