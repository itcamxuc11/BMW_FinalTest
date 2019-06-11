<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>User</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="css/user.css" />
    <style>
        #body-block{
            padding-top: 70px;
            padding-bottom: 200px;
        }
    </style>
</head>

<body>
    <div class="root">
    	<jsp:include page="_Header.jsp"></jsp:include>
        <section id="body-block" class="bg-whitesmoke">
            <div class="container">
                <div class="page-content d-flex">
                    <div class="test-helper d-none d-lg-block">
                        <div id="helper-body" class="bg-white">
                            <div class="time-count">
                                <h2 class="text-primary"><strong>Còn lại</strong></h2>
                                <h3 class="text-danger">
                                    <strong>
                                        <span id="minute">${luotThi.thoiLuong }</span>
                                        <span>: </span>
                                        <span id="second">00</span>
                                    </strong>
                                </h3>
                            </div>
                            <div id="questions-complete" class="questions-complete pt-5 pb-5">
                                <h2 class="text-primary"><strong>Câu hỏi</strong></h2>
                            </div>
                            <div class="test-name">
                                <h3 class="text-primary">${luotThi.tieuDe }</h3>
                            </div>
                        </div>
                    </div>
                    <div class="test-content">
                    <c:forEach items="${DSCauHoi}" var="cauhoi">
                    	<ul class="question">
                            <li>
                                <div class="frage">
                                    <div class="row">
                                        <div class="col-2 d-none d-md-block">
                                            <div class="number bg-dark"><span data-ma="${cauhoi.maCauHoi }" class="index-question"></span></div>
                                        </div>
                                        <div class="col-md-10">
                                            <div class="question-content">
                                                <p>${cauhoi.noiDung }</p>
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
                                </div>
                            </li>
                        </ul>         
                    </c:forEach>        
                        <button data-toggle="modal" data-target="#accept-submit" class="btn mt-2 btn-primary">Nội bài</button>
                    </div>
                </div>
            </div>
        </section>
        <div class="modal fade" id="accept-submit">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Xác nhận nộp bài</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        Sau khi nộp bài bạn sẽ không thể tiếp tục làm bài cũng như xem lại câu hỏi,
                        bạn có chắc chắn?
                    </div>
                    <div class="modal-footer">
                        <button type="submit" id="btn-accept-submit" class="btn btn-success">Nộp</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                    </div>
                </div>
            </div>
        </div>
    </div> 
</body>
<script src="JS/PostMethod.js" type="text/javascript"></script>
  <script src="JS/Thi.js" type="text/javascript"></script>
</html>