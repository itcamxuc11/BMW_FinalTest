<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

         <header id="header">
            <nav id="navbar-user" class="navbar custom-navbar navbar-expand-sm navbar-dark fixed-top">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="logo">
                                <img class="img-fluid" src="images\logo.png">
                            </div>
                        </div>
                        <div class="col">
                            <div class="d-flex flex-row-reverse">
                                <div class="navbar-item">
                                    <div class="dropdown">
                                        <div data-toggle="dropdown" class="d-inline-block">
                                            <span class="size-18 d-none d-md-inline"><c:out value="${sessionScope.loginedUser.tenNguoiDung}"></c:out></span>
                                            <span class="size-18 dropdown-toggle"></span>
                                        </div>
                                        <ul class="dropdown-menu">
                                            <li class="dropdown-item">Đổi mật khẩu</li>
                                            <li class="dropdown-item"><a class="text-dark" href="DangXuat">Đăng xuất</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="navbar-item">
                                    <div class="navbar-image user-avatar">
                                        <a href=""><img class="rounded-circle" src="images\user-avt.png"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
        </header>