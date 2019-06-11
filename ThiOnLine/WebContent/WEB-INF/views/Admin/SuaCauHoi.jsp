<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layoutAdmin>
	<jsp:attribute name="sidebar">
		<li class="nav-item"><a href="QuanLyCauHoi">Danh sách câu hỏi</a></li>
	</jsp:attribute>
	<jsp:attribute name="content">
		<div class="page-header page-header-default mt-2">
			<div class="breadcrumb-line">
				<div class="breadcrumb">
					<h5 class="m-0 text-primary">
						Sửa câu hỏi/Mã câu hỏi: <span id="ma-cau-hoi">${cauHoi.maCauHoi}</span>
					</h5>
				</div>
			</div>
		</div>
		<form class="needs-validation" name="themcauhoi">
			<div class="modal-body">
				<div class="question-content p-3">
					<h5 class="text-primary">Nội dung câu hỏi</h5>
					<textarea data-value="${cauHoi.dapAnDung}" rows="3"
							class="form-control" id="noi-dung">${cauHoi.noiDung }</textarea>
				</div>
				<div class="answer-content p-3">
					<h5 class="text-primary">Đáp án</h5>
					<div class="choice-1 input-group mb-3">
						<div class="rdb">
							<input value="A" class="custom-radio" name="dapandung"
									type="radio">
						</div>
						<input value="${cauHoi.dapAnA}" id="dap-an-a" type="text"
								class="form-control">
					</div>
					<div class="choice-2 input-group mb-3">
						<div class="rdb">
							<input value="B" class="custom-radio" name="dapandung"
									type="radio">
						</div>
						<input value="${cauHoi.dapAnB}" id="dap-an-b" type="text"
								class="form-control">
					</div>
					<div class="choice-3 input-group mb-3">
						<div class="rdb">
							<input value="C" class="custom-radio" name="dapandung"
									type="radio">
						</div>
						<input value="${cauHoi.dapAnC}" id="dap-an-c" type="text"
								class="form-control">
					</div>
					<div class="choice-4 input-group mb-3">
						<div class="rdb">
							<input value="D" class="custom-radio" name="dapandung"
									type="radio">
						</div>
						<input value="${cauHoi.dapAnA}" id="dap-an-d" type="text"
								class="form-control">
					</div>
				</div>
				<div class="question-type p-3">
					<h5 class="text-primary">Loại câu hỏi</h5>
					<div class="d-flex">
						<div class="col">
							<select data-value="${cauHoi.capDo}" class="custom-select"
									id="cap-do">
								<option value="1" selected>Dễ</option>
								<option value="2">Trung bình</option>
								<option value="3">Khó</option>
							</select>
						</div>
						<div class="col pr-0">
							<select data-value="${cauHoi.monHoc}" class="custom-select"
									id="mon-hoc">
								<option value="Math" selected>Toán</option>
								<option value="Biological">Sinh Học</option>
								<option value="English">Tiếng Anh</option>
								<option value="Physical">Vật Lí</option>
							</select>
						</div>
					</div>
				</div>
			</div>
				<div class="col d-flex flex-row-reverse">
					<button type="button" id="save" class="btn mr-3 btn-success">Lưu</button>
				</div>
		</form>
<script src="JS/SuaCauHoi.js" type="text/javascript"></script>
	</jsp:attribute>
</t:layoutAdmin>