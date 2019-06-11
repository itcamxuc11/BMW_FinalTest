<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layoutAdmin>
	<jsp:attribute name="sidebar">
		<li class="nav-item"><a href="QuanLyCauHoi">Danh sách câu hỏi</a></li>
	</jsp:attribute>
	<jsp:attribute name="content">
		<ul class="nav nav-tabs mt-3 bg-transparent">
					<li class="nav-item"><a
						style="font-size: 20px; font-weight: 700" class="nav-link active"
						data-toggle="tab" href="#home">Nhập câu hỏi</a></li>
					<li class="nav-item"><a
						style="font-size: 20px; font-weight: 700" class="nav-link"
						data-toggle="tab" href="#menu1">Thêm bằng File</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active container" id="home">
				<form class="needs-validation" name="themcauhoi">
					<div class="modal-body">
						<div class="question-content p-3">
							<h5 class="text-primary">Nội dung câu hỏi</h5>
							<textarea rows="3" class="form-control" 
							id="noi-dung"></textarea>
						</div>
						<div class="answer-content p-3">
							<h5 class="text-primary">Đáp án</h5>
							<div class="choice-1 input-group mb-3">
								<div class="rdb">
									<input value="A" class="custom-radio" name="dapandung"
										type="radio">
								</div>
								<input id="dap-an-a" type="text" class="form-control">
							</div>
							<div class="choice-2 input-group mb-3">
								<div class="rdb">
									<input value="B" class="custom-radio" name="dapandung"
										type="radio">
								</div>
								<input id="dap-an-b" type="text" class="form-control">
							</div>
							<div class="choice-3 input-group mb-3">
								<div class="rdb">
									<input value="C" class="custom-radio" name="dapandung"
										type="radio">
								</div>
								<input id="dap-an-c" type="text" class="form-control">
							</div>
							<div class="choice-4 input-group mb-3">
								<div class="rdb">
									<input value="D" class="custom-radio" name="dapandung"
										type="radio">
								</div>
								<input id="dap-an-d" type="text" class="form-control">
							</div>
						</div>
						<div class="question-type p-3">
							<h5 class="text-primary">Loại câu hỏi</h5>
							<div class="d-flex">
								<div class="col">
									<select class="custom-select" id="cap-do">
										<option value="1" selected>Dễ</option>
										<option value="2">Trung bình</option>
										<option value="3">Khó</option>
									</select>
								</div>
								<div class="col pr-0">
									<select class="custom-select" id="mon-hoc">
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
			</div>
			<div class="tab-pane container" id="menu1">
				<h5 class="text-primary mt-3">Chọn file câu hỏi</h5>
				<div class="mt-2 position-relative">
					<input type="file" class="custom-file-input" id="file-cau-hoi">
					<label id="file-name" class="custom-file-label"
						for="file-cau-hoi">Chọn tệp</label>
					<button id="upload-file" class="btn btn-primary mt-2">Gửi</button>
				</div>
			</div>
		</div>
		<script src="JS/ThemCauHoi.js"></script>
	</jsp:attribute>
</t:layoutAdmin>

