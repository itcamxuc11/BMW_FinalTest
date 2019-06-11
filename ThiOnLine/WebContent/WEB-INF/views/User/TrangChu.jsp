<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="css/user.css" />
</head>

<body>
	<div id="root">
		<section id="header">
			<nav id="navbar-home"
				class="navbar custom-navbar navbar-expand-sm navbar-dark fixed-top">
				<div class="container">
					<div class="row">
						<div class="col">
							<div class="logo">
								<img class="img-fluid" src="images\logo.png">
							</div>
						</div>
						<div class="col">
							<div class="d-flex flex-row-reverse">
								<div class="dropdown d-block d-md-none">
									<div class="custom-dropdown" data-toggle="dropdown">
										<span class="icon-bar"></span> <span class="icon-bar"></span>
										<span class="icon-bar"></span>
									</div>
									<ul class="dropdown-menu">
										<li class="dropdown-item">Home</li>
										<li class="dropdown-item">Giới thiệu</li>
										<li class="dropdown-item">Liên hệ</li>
										<li data-toggle="modal" data-target="#login-modal"
											class="dropdown-item">Đăng nhập</li>
									</ul>
								</div>

								<button data-toggle="modal" data-target="#login-modal"
									id="login" class="d-none d-md-block navbar-item">Đăng
									nhập</button>
								<div class="d-none d-md-block navbar-item">
									<span>Liên hệ</span>
								</div>
								<div class="d-none d-md-block navbar-item">
									<span>Giới thiệu</span>
								</div>
								<div class="d-none d-md-block navbar-item">
									<span>Home</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</nav>
			<div id="header-main">
				<div class="banner">
					<div class="banner-content d-none d-md-block">
						<div class="earth"></div>
					</div>
					<div class="banner-content col-12 col-md-6"
						style="text-align: center">
						<h2 class="banner-item m-0 text-white">Thi trắc nghiệm online</h2>
						<button data-toggle="modal" data-target="#signup-modal"
							class="banner-item sign-up">Đăng kí tài khoản</button>
					</div>
					<div class="banner-content d-block d-md-none"
						style="text-align: center">
						<h3 class="size-18 banner-item mt-4 text-white">
							<a href="">Tôi đã có tài khoản</a>
						</h3>
					</div>
				</div>
				<div class="subjects-list d-none d-lg-flex">
					<div>
						<ul>
							<li class="subject">
								<div>
									<span class="subject-icon chemistry"></span> <span>Hóa
										Học</span>
								</div>
							</li>
							<li class="subject">
								<div>
									<span class="physical subject-icon"></span> <span>Vật Lí</span>
								</div>
							</li>
							<li class="subject">
								<div>
									<span class="subject-icon math"></span> <span>Toán Học</span>
								</div>
							</li>
							<li class="subject">
								<div>
									<span class="subject-icon biology"></span> <span>Sinh
										Học</span>
								</div>
							</li>
							<li class="subject">
								<div>
									<span class="subject-icon it"></span> <span>Tin Học</span>
								</div>
							</li>
							<li class="subject">
								<div>
									<span class="subject-icon english"></span> <span>Tiếng
										Anh</span>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</section>
		<jsp:include page="_BodyStatic.jsp"></jsp:include>
		<div class="modal" id="login-modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<div class="card card-signin my-5">
							<div class="card-body">
								<h5 class="card-title text-center">Đăng Nhập</h5>
								<form class="form-signin" action="Home" method="post">
									<div class="form-label-group">
										<input type="text" name="tenTK" class="form-control"
											placeholder="Tên tài khoản">
									</div>
									<div class="form-label-group">
										<input type="password" name="matKhau" class="form-control"
											placeholder="Mật Khảu">
									</div>
									<button id="dn"
										class="btn btn-lg btn-primary btn-block text-uppercase"
										type="submit">Đăng Nhập</button>
									<hr class="my-4">
								</form>
								<p>
									<a class="text-primary">Quên mật khẩu </a><span>hoặc </span><a
										class="text-primary">Chưa có tài khoản</a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal" id="signup-modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<div class="card card-signin my-5">
							<div class="card-body mb-0">
								<h5 class="card-title text-center">Đăng Kí</h5>
								<form class="form-signin" method="post">
									<div class="form-label-group">
										<input type="text" id="ten" class="form-control"
											placeholder="Tên của bạn">
									</div>
									<div class="form-label-group">
										<input type="text" id="tenTK" class="form-control"
											placeholder="Tên tài khoản">
									</div>
									<div class="form-label-group">
										<input type="password" id="pass" class="form-control"
											placeholder="Mật khẩu">
									</div>
									<div class="form-label-group">
										<input type="password" id="repass" class="form-control"
											placeholder="Nhập lại mật Khảu">
									</div>
									<button id="dk" class="btn btn-lg btn-primary btn-block text-uppercase"
										type="button">Đăng Kí</button>
									<hr class="my-4">
								</form>
								<p id="signup-result" class="error-mesage text-danger"></p>
							</div>
						</div>
					</div>
				</div>
			</div>


		</div>
		<jsp:include page="_Footer.jsp"></jsp:include>
	</div>
	<script>
            $(document).ready(function () {
                $("#dk").click(function () {
                    var tenTK = $("#tenTK").val();
                    var ten = $("#ten").val();
                    var pass = $("#pass").val();
                    var repass = $("#repass").val();
                    $.ajax({
                        url: "DangKi",
                        type: "post",
                        data: { tenTK: tenTK, ten: ten, pass:pass, repass:repass },
                        success: function (result) {
                            $("#signup-result").text(result);
                        }
                    })
                })
            })
        </script>
</body>
</html>