<%@page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=utf-8"
         import="javax.sql.DataSource, java.sql.Connection" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.*" %>

<!--<c:choose>
    <c:when test="${empty dto}">
    </c:when>
    <c:otherwise>
        <font style="color:green" style="align:center">${dto.email}</font>
        님 환영합니당^^
        <br/>
        <a href="logout.do?">로그아웃</a>
    </c:otherwise>
</c:choose>-->
<!-- HEADER
================================================== -->
<header class="header-style1 menu_area-light">

    <div class="navbar-default border-bottom border-color-light-white">

        <!-- start top search -->
        <div class="top-search bg-primary">
            <div class="container">
                <for class="search-form" action="search.html" method="GET" accept-charset="utf-8">
                    <div class="input-group">
                                <span class="input-group-addon cursor-pointer">
                                    <button class="search-form_submit fas fa-search text-white" type="submit"></button>
                                </span>
                        <input type="text" class="search-form_input form-control" name="s" autocomplete="off"
                               placeholder="Type & hit enter...">
                        <span class="input-group-addon close-search mt-1"><i class="fas fa-times"></i></span>
                    </div>
                </for>
            </div>
        </div>
        <!-- end top search -->

        <div class="container">
            <div class="row align-items-center">
                <div class="col-12 col-lg-12">
                    <div class="menu_area alt-font">
                        <nav class="navbar navbar-expand-lg navbar-light p-0">
                            <div>
                                <!-- start logo -->
                                <a href ="index.jsp"><img style="width:31.7%" src="img/banner/header.png"></a>
                                <!-- end logo -->
                            </div>

                            <div class="navbar-toggler bg-primary"></div>

                            <!-- start menu area -->
                            <ul class="navbar-nav ms-auto" id="nav" style="display: none;">
                                <li><a href="index.jsp">처음으로 </a></li>
                                <li><a href="#!">강좌 목록</a>
                                    <ul>
                                        <li><a href="courses.do">강좌 목록</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#!">예약확인</a>
                                    <ul>
                                        <li><a href="reservation-list.do">예약 대기</a></li>
                                        <li><a href="payment-list.do">수강 강좌</a></li>
                                    </ul>
                                </li>
                                <li><a href="post_listForm.do">질문 목록</a></li>
                                <c:choose>
                                    <c:when test="${empty dto}">
                                        <li><a href="signUpForm.do">회원가입</a></li>
                                        <li><a href="loginForm.do">로그인</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="myPageForm.do">마이페이지</a></li>
                                        <li><a href="logout.do">로그아웃</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                            </ul>
                            <!-- end menu area -->

                            <!-- start attribute navigation -->
                            <div class="attr-nav align-items-xl-center ms-xl-auto main-font">

                            </div>
                            <!-- end attribute navigation -->
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>