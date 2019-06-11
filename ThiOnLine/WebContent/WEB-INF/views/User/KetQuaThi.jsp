<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>User</title>
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
	href="css/calendar.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="css/user.css" />
</head>

<body>
	<div class="toot">
		<jsp:include page="_Header.jsp"></jsp:include>
		<section id="body-block" class="bg-whitesmoke pb-5">
			<div class="container anti-fixed" style="min-height: 100vh">
				<div class="bg-white p-4">
					<h1 class="text-info mb-2">Điểm của bạn: ${Diem}</h1>
					<h1 class="text-info mb-3">
						Thời gian làm bài: <span>${phut} Phút </span>${giay} giây
					</h1>
					<div>
						<a href="ketqua.pdf" class="btn btn-success text-white" download>Tải xuống pdf</a> <a
							class=" btn btn-primary text-white">Về trang chủ</a>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>