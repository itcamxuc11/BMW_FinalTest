<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:layoutAdmin>
	<jsp:attribute name="sidebar">
		<li class="nav-item"><a href="QuanLyDeThi">Danh sách đề thi</a></li>
		<li data-toggle="modal" data-target="#refresh-exam" class="nav-item">Làm mới</li>
	</jsp:attribute>
	<jsp:attribute name="content">
		<p class="d-none" id="subject-id">${monHoc}</p>
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
			<div class="modal fade" id="refresh-exam">
				<div class="modal-dialog modal-sm">
					<form action="SuaDeThi" method="get">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Làm mới đề thi</h5>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
									<div class="col">
										<label class="text-primary m-0">Số câu dễ</label>
										<input value="${soCauHoi.soCauDe }" class="form-control mb-2"
									name="de" type="number">
									</div>
									<div class="col">
										<label class="text-primary m-0">Số câu trung bình</label>
										<input value="${soCauHoi.soCauTrungBinh }"
									class="form-control mb-2" name="trungBinh" type="number">
									</div>
									<div class="col">
										<label class="text-primary m-0">Số câu khó</label>
										<input value="${soCauHoi.soCauKho }" class="form-control mb-2"
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
			<div class="modal fade" id="nhap-ma-de">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Bạn có chắc chắn?</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<p class="p-0, pb-3">
								Mọi thay đổi của bạn sẽ được lưu lại, không thể trở lại trạng thái
								trước đó. <br>Hãy chắc chắn trước khi bấm xác nhận.
							</p>
						</div>
						<div class="modal-footer">
							<button id="btn-yes" type="button" class="btn btn-success">Xác
								nhận</button>
							<button type="button" class="btn btn-danger" data-dismiss="modal">Hủy</button>
						</div>
					</div>
				</div>
			</div>
	<script src="JS/LuuDeThi.js" type="text/javascript"></script>
	</jsp:attribute>
</t:layoutAdmin>

