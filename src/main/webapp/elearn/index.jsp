<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=utf-8"
         import="javax.sql.DataSource, java.sql.Connection"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- metas -->
    <meta charset="utf-8" />
    <meta name="author" content="Chitrakoot Web" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="keywords" content="Online Education Learning Template" />
    <meta name="description" content="eLearn - Online Education Learning Template" />

    <!-- title  -->
    <title>eLearn - Online Education Learning Template</title>

    <!-- favicon -->
    <link rel="shortcut icon" href="img/logos/favicon.png" />
    <link rel="apple-touch-icon" href="img/logos/apple-touch-icon-57x57.png" />
    <link rel="apple-touch-icon" sizes="72x72" href="img/logos/apple-touch-icon-72x72.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="img/logos/apple-touch-icon-114x114.png" />

    <!-- plugins -->
    <link rel="stylesheet" href="css/plugins.css" />

    <!-- search css -->
    <link rel="stylesheet" href="search/search.css" />

    <!-- quform css -->
    <link rel="stylesheet" href="quform/css/base.css">

    <!-- core style css -->
    <link href="css/styles.css" rel="stylesheet" />

    <!-- custom style css -->
    <link href="css/custom.css" rel="stylesheet" />

</head>

<body>
<!-- 나중에 쓸 예정 <c:choose>
    <c:when test="${empty dto}">
    </c:when>
    <c:otherwise>
        <font style="color:green" style="align:center">${dto.email}</font>
        님 환영합니당^^
        <br/>
        <a href="logout.do?">로그아웃</a>
    </c:otherwise>
</c:choose>-->
<!-- PAGE LOADING
================================================== -->
<div id="preloader"></div>

<!-- MAIN WRAPPER
================================================== -->
<div class="main-wrapper">

    <!-- HEADER
    ================================================== -->
   <%@include file="header.jsp"%>
    <!-- BANNER
    ================================================== -->
    <section class="top-position1 p-0">
        <div class="container-fluid px-0">
            <div class="slider-fade1 owl-carousel owl-theme w-100">
                <div class="item bg-img cover-background pt-6 pb-10 pt-sm-6 pb-sm-14 py-md-16 py-lg-20 py-xxl-24 left-overlay-dark" data-overlay-dark="8" >
                    <div class="container pt-6 pt-md-0">
                        <div class="row align-items-center">
                            <div class="col-md-10 col-lg-8 col-xl-7 mb-1-9 mb-lg-0 py-6 position-relative">
                                <span class="h5 text-secondary">예약사이트에 오신것을 환영합니다.</span>
                                <h1 class="display-1 font-weight-800 mb-2-6 title text-white">편리하게 예약해요!</h1>
                                <c:choose>
                                    <c:when test="${empty dto}">
                                        <a href="signUpForm.do" class="butn my-1 mx-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">회원가입</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                        <a href="loginForm.do" class="butn white my-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">로그인</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="courses.do" class="butn white my-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">강좌 목록</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                        <a href="reservation-list.do" class="butn white my-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">예약 대기</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                        <a href="post_listForm.do" class="butn white my-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">질문 목록</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="item bg-img cover-background pt-6 pb-10 pt-sm-6 pb-sm-14 py-md-16 py-lg-20 py-xxl-24 left-overlay-dark" data-overlay-dark="8" >
                    <div class="container pt-6 pt-md-0">
                        <div class="row align-items-center">
                            <div class="col-md-10 col-lg-8 col-xl-7 mb-1-9 mb-lg-0 py-6 position-relative">
                                <span class="h5 text-secondary">예약사이트에 오신것을 환영합니다.</span>
                                <h2 class="display-1 font-weight-800 mb-2-6 title text-white">손 쉬운 원터치 예약</h2>
                                <c:choose>
                                    <c:when test="${empty dto}">
                                        <a href="signUpForm.do" class="butn my-1 mx-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">회원가입</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                        <a href="loginForm.do" class="butn white my-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">로그인</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="courses.do" class="butn white my-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">강좌 목록</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                        <a href="reservation-list.do" class="butn white my-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">예약 대기</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                        <a href="post_listForm.do" class="butn white my-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">질문 목록</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="item bg-img cover-background pt-6 pb-10 pt-sm-6 pb-sm-14 py-md-16 py-lg-20 py-xxl-24 left-overlay-dark" data-overlay-dark="8" >
                    <div class="container pt-6 pt-md-0">
                        <div class="row align-items-center">
                            <div class="col-md-10 col-lg-8 col-xl-7 mb-1-9 mb-lg-0 py-6 position-relative">
                                <span class="h5 text-secondary">예약사이트에 오신것을 환영합니다.</span>
                                <h2 class="display-1 font-weight-800 mb-2-6 title text-white">강의 예약 홈페이지</h2>
                                <c:choose>
                                    <c:when test="${empty dto}">
                                        <a href="signUpForm.do" class="butn my-1 mx-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">회원가입</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                        <a href="loginForm.do" class="butn white my-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">로그인</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="courses.do" class="butn white my-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">강좌 목록</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                        <a href="reservation-list.do" class="butn white my-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">예약 대기</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                        <a href="post_listForm.do" class="butn white my-1"><i class="fas fa-plus-circle icon-arrow before"></i><span class="label">질문 목록</span><i class="fas fa-plus-circle icon-arrow after"></i></a>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="triangle-shape top-15 right-10 z-index-9 d-none d-md-block"></div>
        <div class="square-shape top-25 left-5 z-index-9 d-none d-xl-block"></div>
        <div class="shape-five z-index-9 right-10 bottom-15"></div>
    </section>

    <!-- INFORMATION
    ================================================== -->
    <section class="p-0 overflow-visible service-block">
        <div class="container">
            <div class="row mt-n1-9">
                <div class="col-md-6 col-lg-4 mt-1-9">
                    <div class="card card-style3 h-100">
                        <div class="card-body px-1-9 py-2-3">
                            <div class="mb-3 d-flex align-items-center">
                                <div class="card-icon">
                                    <i class="ti-rocket"></i>
                                </div>
                                <h4 class="ms-4 mb-0">회원관리</h4>
                            </div>
                            <div>
                                <p class="mb-3">회원가입 바로가기 입니다.</p>
                                <a href="signUpForm.do" class="butn-style1 secondary">회원가입</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 mt-1-9">
                    <div class="card card-style3 h-100">
                        <div class="card-body px-1-9 py-2-3">
                            <div class="mb-3 d-flex align-items-center">
                                <div class="card-icon">
                                    <i class="ti-world"></i>
                                </div>
                                <h4 class="ms-4 mb-0">질문 목록</h4>
                            </div>
                            <div>
                                <p class="mb-3">질문 목록 바로가기 입니다</p>
                                <a href="post_listForm.do" class="butn-style1 secondary">목록</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 mt-1-9">
                    <div class="card card-style3 h-100">
                        <div class="card-body px-1-9 py-2-3">
                            <div class="mb-3 d-flex align-items-center">
                                <div class="card-icon">
                                    <i class="ti-user"></i>
                                </div>
                                <h4 class="ms-4 mb-0">강좌</h4>
                            </div>
                            <div>
                                <p class="mb-3">강좌 바로가기 입니다.</p>
                                <a href="courses.do" class="butn-style1 secondary">강좌</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- ABOUTUS
    ================================================== -->


    <!-- FOOTER
    ================================================== -->
    <%@include file="footer.jsp"%>

</div>

<!-- start scroll to top -->
<a href="#!" class="scroll-to-top"><i class="fas fa-angle-up" aria-hidden="true"></i></a>
<!-- end scroll to top -->

<!-- all js include start -->

<!-- jQuery -->
<script src="js/jquery.min.js"></script>

<!-- modernizr js -->
<script src="js/popper.min.js"></script>

<!-- bootstrap -->
<script src="js/bootstrap.min.js"></script>

<!-- search -->
<script src="search/search.js"></script>

<!-- navigation -->
<script src="js/nav-menu.js"></script>

<!-- tab -->
<script src="js/easy.responsive.tabs.js"></script>

<!-- owl carousel -->
<script src="js/owl.carousel.js"></script>

<!-- jquery.counterup.min -->
<script src="js/jquery.counterup.min.js"></script>

<!-- stellar js -->
<script src="js/jquery.stellar.min.js"></script>

<!-- waypoints js -->
<script src="js/waypoints.min.js"></script>

<!-- countdown js -->
<script src="js/countdown.js"></script>

<!-- jquery.magnific-popup js -->
<script src="js/jquery.magnific-popup.min.js"></script>

<!-- lightgallery js -->
<script src="js/lightgallery-all.js"></script>

<!-- mousewheel js -->
<script src="js/jquery.mousewheel.min.js"></script>

<!-- custom scripts -->
<script src="js/main.js"></script>

<!-- form plugins js -->
<script src="quform/js/plugins.js"></script>

<!-- form scripts js -->
<script src="quform/js/scripts.js"></script>

<!-- all js include end -->

</body>

</html>