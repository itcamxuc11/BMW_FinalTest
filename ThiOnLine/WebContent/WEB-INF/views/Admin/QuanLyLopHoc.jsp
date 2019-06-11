<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layoutAdmin>
	<jsp:attribute name="sidebar">
		<li class="nav-item"><a style="color: white"
			href="QuanLyLopHoc">Lớp học</a></li>
		<li class="nav-item"><a style="color: white"
			href="QuanLyHocSinh"> Học sinh</a></li>		
	</jsp:attribute>
	<jsp:attribute name="content">
			<div class="page-header page-header-default">
				<div class="page-header-content">
					<div class="page-title">
						<h3 class="media-heading">Danh sách lớp học</h3>
					</div>
				</div>
					<div class="breadcrumb-line">
						<ul class="breadcrumb">
							<li><a href="index.html">Home</a></li>
							<li class="active">Danh sách lớp học</li>
						</ul>
					</div>
				</div>
				<div class="content">
					<div class="panel panel-border-top d-flex">
						<div class="col-12">
							<button data-target="#add-elment" data-toggle="modal"
								class="btn btn-dark btn-labeled">
								<b><i class="fas fa-plus-square"></i></b>Thêm lớp học
							</button>
						</div>
						<form method="post"
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
									<th>Mã lớp học</th>
									<th>Tên lớp học</th>
									<th>Ngày kết thúc</th>
									<th>Sỉ số hiện tại</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${dsLopHoc }" var="lophoc">
									<tr>
										<td>
											<div class="dropdown">
												<span data-toggle="dropdown" class="dropdown-toggle">${lophoc.maLop }</span>
												<ul class="dropdown-menu">
													<li class="dropdown-item"><a class="delete-link"
														href="XoaLopHoc?maLop=${lophoc.maLop }">Xóa</a></li>
													<li data-ten="${lophoc.tenLop}" data-ma="${lophoc.maLop}"
														data-end="${lophoc.ngayKetThuc}" data-target="#fac"
														data-toggle="modal" class="edit dropdown-item">Xem
														và sửa</li>
												</ul>
											</div>
										</td>
										<td>${lophoc.tenLop}</td>
										<td>${lophoc.ngayKetThuc}</td>
										<td>${lophoc.soHocSinh}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="pagination">
					<a href="#">&laquo;</a><a class="active" href="#">1</a> <a href="#">&raquo;</a>
				</div>
	
				<div id="fac" class="modal full-question-content">
				    <div class="modal-dialog modal-lg">
				        <div class="modal-content">
				            <div class="modal-header">
				                <h4 id="modal-title" class="modal-title"></h4>
				                <button type="button" class="close" data-dismiss="modal">&times;</button>
				            </div>
				            <div class="modal-body">
				                <div class="class-profile">
				                    <div>
				                        <ul class="nav nav-tabs pr-30">
				                            <li class="nav-item">
				                                <a class="nav-link active" data-toggle="tab" href="#info-class">
				                                    Thông tin
				                                </a>
				                            </li>
				                            <li class="nav-item"><a id="nav-in" class="nav-link"
				                                data-toggle="tab" href="#list-in">Đã tham gia</a>
				                            </li>
				                            <li class="nav-item"><a id="nav-not-in"
				                                class="nav-link" data-toggle="tab"
				                                href="#list-not-in">Thêm học sinh</a>
				                            </li>
				                            <li class="ml-auto nav-link">
				                                <input placeholder="Tìm" class="search">
				                            </li>
				                        </ul>
				                        <div class="tab-content">
				                            <div id="info-class" style="overflow-y:auto" class="tab-pane fade list-members active show">
				                                <div class="col d-flex">
						                             <div class="col">
					                                    <label class="label-input mt-2">Tên lớp học</label>
					                                    <input id="class-name" class="form-control">
					                                </div>
					                                <div class="col">
					                                    <label class="label-input mt-2">Ngày kết thúc</label>
					                                    <input id="class-end" type="date" class="form-control">
					                                </div>					                                
				                                </div>		
				                                <div class="db-col mt-3">
				                                	<button id="accept-edit" class="btn float-right btn-success">Lưu</button>
				                                </div>										
				                            </div>
				                            <div id="list-not-in" class="tab-pane fade list-members">
				                            </div>
				                            <div id="list-in" class="list-members  tab-pane fade">
				                            </div>
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
				                        <div id="veri-question" class="modal-body"></div>
				                        <div class="modal-footer">
				                            <button id="btn-accept-delete" type="button"
				                                class="btn btn-danger">Xóa</button>
				                            <button type="button" class="btn btn-secondary close-modal">Close</button>
				                        </div>
				                    </div>
				                </div>
				            </div>
				        </div>
				    </div>
				</div>
				<div class="modal fade" id="add-elment">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Nhập thông tin lớp học</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div>
								<div class="col">
									<label class="label-input mt-2">Tên lớp học</label>
									 <input id="ten-lop" type="text" class="form-control my-input"> 
									 <label class="label-input">Ngày kết thúc</label> 
									 <input id="ket-thuc" type="date" class="form-control my-input">
								</div>
							</div>
							<div class="modal-footer">
								<button id="accept-add" class="btn btn-success">Thêm</button>
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">Hủy</button>
							</div>
						</div>
					</div>
				</div>
	<script src="JS/QuanLyLopHoc.js" type="text/javascript"></script>
	<script src="JS/delete-confirm.js" type="text/javascript"></script>
	</jsp:attribute>
</t:layoutAdmin>
</body>
</html>