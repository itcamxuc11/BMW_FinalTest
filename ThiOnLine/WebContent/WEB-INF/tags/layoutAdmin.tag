<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="sidebar" fragment="true" %>
<%@attribute name="content" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>G4-Saturday</title>
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
	href="css/main.css" />
</head>
<body>
	<div id="header">
		<div class="navbar-header">
			<nav class="navbar navbar-expand-sm navbar-dark fixed-top">
				<a class="navbar-brand" href="#"><span>Thi trắc nghiệm
						online</span></a>
				<ul class="navbar-nav mr-auto">
				</ul>
				<div class="navbar-brand dropdown">
					<a class="dropdown-toggle" href="#" id="navbardrop"
						data-toggle="dropdown"> <span><c:out value="${sessionScope.loginedUser.tenNguoiDung}" /></span>
					</a>
					<ul class="dropdown-menu">
						<li class="dropdown-item"><a href="DangXuat"><i
								class="icon-switch2"></i>Đổi mật khẩu</a></li>
						<li class="dropdown-item"><a href="DangXuat"><i
								class="icon-switch2"></i> Đăng xuất</a></li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
	<div class="page-container">
		<div class="page-content">
			<div class="row">
				<div class="d-none d-lg-block sidebar  sidebar-fixed-left pr-0">
					<div class="sidebar-content">
						<div class="sidebar-user">
							<div class="category-content">
								<div class="media">
									<div class="media-body">
										<span id="level" class="media-heading"><c:out value="${sessionScope.loginedUser.quyen}"></c:out></span>
										<div class="text-size-mini text-muted">
											<span class="fs-11">Việt Nam</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="sidebar-category">
							<nav class="category-content p-0">
								<ul class="navigation">
									<jsp:invoke fragment="sidebar"/>
								</ul>
							</nav>
						</div>
					</div>
				</div>
				<div id="content" class="col content-wrapper">
					<jsp:invoke fragment="content"/>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var a = document.getElementById("level");
		if(level.innerHTML==2) level.innerHTML="Quản lý lớp học";
		else if (level.innerHTML==3) level.innerHTML="Quản lý đề thi";
		else if(level.innerHTML==4) level.innerHTML="Quản lý câu hỏi";
	</script>
</body>
</html>