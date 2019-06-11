<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:layoutAdmin>
	<jsp:attribute name="sidebar">
		<li class="nav-item"><a href="QuanLyDeThi">Danh sách đề thi</a></li>
		<li data-toggle="modal" data-target="#refresh-exam"
			class="nav-item">Làm mới</li>
	</jsp:attribute>
	<jsp:attribute name="content">
					<h3 class="text-danger">${error}</h3>
			<div class="col p-4">
				<div id="test-block" class="test-content">
					<c:forEach items="${dsCauHoi}" var="cauhoi">
						<ul id="${cauhoi.maCauHoi}-block" class="question">
							<li>
								<div class="frage">
									<div class="row">
										<div onclick="deleteQuestion(this)"
											data-ma="${cauhoi.maCauHoi}" class="col-2">
											<img data-toggle="modal" data-target="#accept-delete"
												class="img-fluid delete-button" src="images/delete.png">
										</div>
										<div class="col-md-10">
											<div class="question-content">
												<p id="${cauhoi.maCauHoi}" class="cau-hoi">${cauhoi.noiDung }</p>
											</div>
										</div>
									</div>
								</div>
								<div class="answorten">
									<div class="answer-content">
										<input data-choice="A" type="radio" name=${cauhoi.maCauHoi }>
										<span>${cauhoi.dapAnA}</span>
									</div>
									<div class="answer-content">
										<input data-choice="B" type="radio" name=${cauhoi.maCauHoi }>
										<span>${cauhoi.dapAnB}</span>
									</div>
									<div class="answer-content">
										<input data-choice="C" type="radio" name=${cauhoi.maCauHoi }>
										<span>${cauhoi.dapAnC}</span>
									</div>
									<div class="answer-content">
										<input data-choice="D" type="radio" name=${cauhoi.maCauHoi }>
										<span>${cauhoi.dapAnD}</span>
									</div>
									<div class="answer-content">
										<span>Đáp án đúng</span> <span>${cauhoi.dapAnDung}</span>
									</div>
								</div>
							</li>
						</ul>
					</c:forEach>
					<button data-target="#nhap-ma-de" data-toggle="modal"
						class="btn mt-2 btn-primary">Lưu</button>
				</div>
			</div>
	<div class="modal fade" id="nhap-ma-de">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Nhập mã đề</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<p class="pb-3">
						<span>Chọn một mã đê thi trước khi lưu</span><br> <span>Gợi
							ý: Hãy chọn mã đề liên quan đến nội dung,thời gian hiện tại hoặc
							kết hợp cả hai để tránh trùng lặp</span>
					</p>
					<div class="form-group">
						<label for="ma-de-thi">Mã đề:</label> <input maxlength="20"
							placeholder="Eg: Math-Dec-2-2018" type="text"
							class="form-control" id="ma-de-thi">
					</div>
				</div>
				<div class="modal-footer">
					<button id="save" type="button" class="btn btn-success">Xác
						nhận</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Hủy</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="refresh-exam">
		<div class="modal-dialog modal-sm">
			<form action="ThemDeThi" method="get">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Làm mới đề thi</h5>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<div class="col">
							<label class="text-primary m-0">Số câu dễ</label> <input
								value="${soLuongCauHoi.soCauDe }" class="form-control mb-2" name="de"
								type="number">
						</div>
						<div class="col">
							<label class="text-primary m-0">Số câu trung bình</label> <input
								value="${soLuongCauHoi.soCauTrungBinh }" class="form-control mb-2"
								name="trungBinh" type="number">
						</div>
						<div class="col">
							<label class="text-primary m-0">Số câu khó</label> <input
								value="${soLuongCauHoi.soCauKho }" class="form-control mb-2"
								name="kho" type="number">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-success">Tiếp tục</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Hủy</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="JS/LuuDeThi.js" type="text/javascript"></script>
	</jsp:attribute>
</t:layoutAdmin>
