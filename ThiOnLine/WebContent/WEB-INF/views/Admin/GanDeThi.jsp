<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:layoutAdmin>
	<jsp:attribute name="sidebar">
		<li class="nav-item"><a href="QuanLyDeThi">Danh Sách đề thi</a></li>		
	</jsp:attribute>
	<jsp:attribute name="content">
						<div class="page-header page-header-default">
					<div class="page-header-content">
						<div class="page-title">
							<h3 class="media-heading">
								Gán đề thi - <span id="ma-de">${maDe}</span>
							</h3>
						</div>
					</div>
					<div class="breadcrumb-line">
						<div class="breadcrumb">
							<h5 class="text-primary m-0 dropdown-toggle"
						data-toggle="collapse" data-target="#gan-de">
								<a><b>Gán lớp mới</b></a>
							</h5>
						</div>
					</div>
				</div>
				<div id="gan-de" class="collapse">
					<div class="d-flex col flex-wrap">
						<div class="col-6 col-lg-4">
							<label class="label-input">Lớp học</label> <select id="ma-lop"
						class="form-control my-input">
								<c:forEach items="${lopChuaGan }" var="lop">
									<option value="${lop.maLop }">${lop.tenLop}--${lop.maLop}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-6 col-lg-4">
							<label class="label-input">Tiêu đề</label> <input id="tieu-de"
						type="text" class="form-control my-input">
						</div>
						<div class="col-6 col-lg-4">
							<label class="label-input">Thời lượng</label> <input
						id="thoi-luong" type="number" class="form-control my-input">
						</div>
						<div class="col-6 col-lg-4">
							<label class="label-input">Số lần làm bài</label> <input
						id="so-lan" type="number" class="form-control my-input">
						</div>
						<div class="col-6 col-lg-4">
							<label class="label-input">Bắt đầu</label> <input id="bat-dau"
						type="datetime-local" class="form-control my-input">
						</div>
						<div class="col-6 col-lg-4">
							<label class="label-input">Kết thúc</label> <input id="ket-thuc"
						type="datetime-local" class="form-control my-input">
						</div>
						<div class="col-12 mt-3 d-flex flex-row-reverse mb-3">
							<button id="save" class="btn btn-success mr-2">Thêm</button>
						</div>
					</div>
				</div>
				<div class="breadcrumb mt-3"
			style="background: #fcfcfc; border: 1px solid #ddd">
					<h5 class="text-primary m-0">
						<b>Đã được gán</b>
					</h5>
				</div>
				<h5 class="m-0">${error}</h5>
				<div id="test-block" class="test-content">
					<div class="db-col">
						<hr>
					</div>
					<c:forEach items="${lopDaGan}" var="lop">
						<div>
							<div id="block-${lop.maLop}" class="d-flex col flex-wrap">
								<div class="col-6 col-lg-4">
									<label class="label-input">Lớp học</label> <input data-ma=""
								value="${lop.tenLop }" type="text" class="form-control my-input"
								readonly="readonly">
								</div>
								<div class="col-6 col-lg-4">
									<label class="label-input">Tiêu đề</label> <input
								value="${lop.tieuDe }" type="text" class="form-control my-input">
								</div>
								<div class="col-6 col-lg-4">
									<label class="label-input">Thời lượng</label> <input
								value="${lop.thoiLuong }" type="number"
								class="form-control my-input">
								</div>
								<div class="col-6 col-lg-4">
									<label class="label-input">Số lần làm bài</label> <input
								value="${lop.soLanLamBai }" type="number"
								class="form-control my-input">
								</div>
								<div class="col-6 col-lg-4">
									<label class="label-input">Bắt đầu</label> <input
								data-date="${lop.TGBatDau }" type="datetime-local"
								class="form-control fake-date my-input">
								</div>
								<div class="col-6 col-lg-4">
									<label class="label-input">Kết thúc</label> <input
								data-date="${lop.TGKetThuc }" type="datetime-local"
								class="form-control fake-date my-input">
								</div>
								<div class="col-12 mt-3 d-flex flex-row-reverse">
									<a href="DongDeThi?made=${maDe}&lop=${lop.maLop}" class="btn btn-danger">Xóa</a>
									<button data-target="${lop.maLop }"
								class="save-change btn btn-success mr-2">Lưu thay đổi</button>
								</div>
							</div>
							<div class="db-col">
								<hr>
							</div>
						</div>
					</c:forEach>
				</div>
				<script src="JS/GanDeThi.js"></script>
	</jsp:attribute>
</t:layoutAdmin>