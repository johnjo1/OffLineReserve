<%@ page contentType="text/html; charset=utf-8"
         import="javax.sql.DataSource, java.sql.Connection"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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

<!-- PAGE LOADING
================================================== -->
<div id="preloader"></div>

<!-- MAIN WRAPPER
================================================== -->
<div class="main-wrapper">

    <!-- HEADER
    ================================================== -->
    <%@include file="header.jsp"%>


    <!-- PAGE TITLE
    ================================================== -->
    <section class="page-title-section bg-img cover-background top-position1 left-overlay-dark"
             data-overlay-dark="9" >
        <div class="container">
            <div class="row text-center">
                <div class="col-md-12">
                    <h1>결제 상세</h1>
                </div>
                <div class="col-md-12">
                    <ul>
                        <li><a href="index.jsp">처음으로 </a></li>
                        <li><a href="#!">Reservation Details</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <!-- PORTFOLIO DETAILS
    ================================================== -->
    <section>
        <div class="container">
            <div class="col-12">


                <section class="bg-light">
                    <div class="container">
                        <div class="section-heading">
                            <span class="sub-title">payment</span>
                            <h2 class="h1 mb-0">예약 결제</h2>
                        </div>
                        <div class="col-md-12 text-center ">
                            <div class="card card-style4 border border-width-2 border-color-primary p-1-9 p-xl-5">
                                <div class="border-bottom pb-1-9 mb-1-9">
                                    <h6 class="mb-4 text-uppercase letter-spacing-2 text-primary">${reservationCourse.subjectName}</h6>
                                    <h4 class="display-5 display-xxl-4 mb-0 lh-1">${reservationCourse.courseName}</h4>
                                </div>
                                <div class="col-12">
                                    <div class="row bg-secondary border-radius-5 mb-1-9 p-1-9">
                                        <div class="col-sm-6 col-lg-3 mb-1-9 mb-lg-0">
                                            <div class="d-flex">
                                                <i class="ti-tag text-white display-18"></i>
                                                <div class="ms-3">
                                                    <h4 class="mb-1 h5 text-white">강좌명:</h4>
                                                    <span class="text-white opacity9">${reservationCourse.courseName}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-6 col-lg-3 mb-1-9 mb-lg-0">
                                            <div class="d-flex">
                                                <i class="ti-user text-white display-18"></i>
                                                <div class="ms-3">
                                                    <h4 class="mb-1 h5 text-white">강사명:</h4>
                                                    <span class="text-white opacity9">${reservationCourse.teacherName}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-6 col-lg-3 mb-1-9 mb-sm-0">
                                            <div class="d-flex">
                                                <i class="ti-timer text-white display-18"></i>
                                                <div class="ms-3">
                                                    <h4 class="mb-1 h5 text-white">개강일:</h4>
                                                    <span class="text-white opacity9">${reservationCourse.startDate}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-6 col-lg-3">
                                            <div class="d-flex">
                                                <i class="ti-money text-white display-18"></i>
                                                <div class="ms-3">
                                                    <h4 class="mb-1 h5 text-white">가격:</h4>
                                                    <span class="text-white opacity9">${reservationCourse.price}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <h3>강의 상세</h3>
                                    <p class="mb-1-4">${reservationCourse.description}</p>

                                    <div class="row mt-n1-9">
                                        <div class="col-lg-6 mt-1-9">
                                            <div class="card border-radius-5 h-100 border-color-light-black">
                                                <div class="card-body py-4 px-1-9 px-xl-2-6">
                                                    <h3 class="h5">환불 정책 약관</h3>
                                                    <ul class="list-style1 list-unstyled mb-0">
                                                        <li>환불 정책: 결제 후 환불 가능 여부 및 조건 확인
                                                        </li>
                                                        <li>비용 및 결제: 결제 금액, 추가 비용 여부 확인
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6 mt-1-9">
                                            <div class="card border-radius-5 h-100 border-color-light-black">
                                                <div class="card-body py-4 px-1-9 px-xl-2-6">
                                                    <h3 class="h5">수업 정책 약관</h3>
                                                    <ul class="list-style1 mb-0 list-unstyled">
                                                        <li>수업 변경 및 취소: 수업 일정 변경, 취소 시 규정 확인</li>
                                                        <li>강사 및 수업 내용: 강사 변경, 수업 내용 변경 가능 여부 확인</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="mt-1-9">
                                    <div>
                                        <form id="paymentForm${reservationCourse.reservationCourseId}" method="POST" action="/elearn/execute-payment.do">
                                            <input type="hidden" name="reservationCourseId" value="${reservationCourse.reservationCourseId}">
                                            <input type="hidden" name="reservationId" value="${reservationCourse.reservationId}">
                                            <input type="hidden" name="courseId" value="${reservationCourse.courseId}">
                                            <input type="hidden" name="paymentAmount" value="${reservationCourse.price}">
                                            <a href="#" class="butn bg-secondary" onclick="document.getElementById('paymentForm${reservationCourse.reservationCourseId}').submit();">
                                                <i class="far fa-gem icon-arrow before"></i><span class="label">결제하기</span><i class="far fa-gem icon-arrow after"></i>
                                            </a>
                                        </form>
                                        <form id="cancelForm${reservationCourse.reservationCourseId}" method="POST" action="/elearn/delete-reservation.do">
                                            <input type="hidden" name="reservationCourseId" value="${reservationCourse.reservationCourseId}">
                                            <a href="#" class="butn bg-secondary" onclick="document.getElementById('cancelForm${reservationCourse.reservationCourseId}').submit();">
                                                <i class="far fa-gem icon-arrow before"></i><span class="label">예약취소</span><i class="far fa-gem icon-arrow after"></i>
                                            </a>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
    </section>
    <div class="page-navigation mt-6">
        <div class="prev-page">
            <div class="page-info">
                <a href="#!">
                    <span class="image-prev"><img src="img/portfolio/prev-portfolio.jpg" alt="..."></span>
                    <div class="prev-link-page-info">
                        <h4 class="prev-title">Create Animation</h4>
                        <span class="date-details"><span class="create-date">March 20, 2023</span></span>
                    </div>
                </a>
            </div>
        </div>
        <div class="next-page">
            <div class="page-info">
                <a href="#!">
                    <div class="next-link-page-info">
                        <h4 class="next-title">Business Research</h4>
                        <span class="date-details"><span class="create-date">March 27, 2023</span></span>
                    </div>
                    <span class="image-next"><img src="img/portfolio/next-portfolio.jpg" alt="..."></span>
                </a>
            </div>
        </div>
    </div>

</div>
</section>

<!-- FOOTER
    ================================================== -->
<footer class="bg-dark">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-lg-3 mb-2-5 mb-lg-0">
                <a href="index.jsp" class="footer-logo">
                    <img src="img/logos/footer-light-logo.png" class="mb-4" alt="Footer Logo">
                </a>
                <p class="mb-1-6 text-white">
                    It's an ideal opportunity to begin investing your energy such that illuminates you.
                </p>
                <form class="quform newsletter" action="quform/newsletter-two.php" method="post"
                      enctype="multipart/form-data" onclick="">

                    <div class="quform-elements">

                        <div class="row">

                            <!-- Begin Text input element -->
                            <div class="col-md-12">
                                <div class="quform-element mb-0">
                                    <div class="quform-input">
                                        <input class="form-control" id="email_address" type="text"
                                               name="email_address" placeholder="Subscribe with us">
                                    </div>
                                </div>
                            </div>
                            <!-- End Text input element -->

                            <!-- Begin Submit button -->
                            <div class="col-md-12">
                                <div class="quform-submit-inner">
                                    <button class="btn btn-white text-primary m-0 px-2" type="submit"><i
                                            class="fas fa-paper-plane"></i></button>
                                </div>
                                <div class="quform-loading-wrap text-start"><span class="quform-loading"></span>
                                </div>
                            </div>
                            <!-- End Submit button -->

                        </div>

                    </div>

                </form>
            </div>
            <div class="col-md-6 col-lg-2 mb-2-5 mb-lg-0">
                <div class="ps-md-1-6 ps-lg-1-9">
                    <h3 class="text-primary h5 mb-2-2">About</h3>
                    <ul class="footer-list">
                        <li><a href="about.html">About Us</a></li>
                        <li><a href="courses-list.html">Courses</a></li>
                        <li><a href="instructors.html">Instructor</a></li>
                        <li><a href="event-list.html">Event</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-6 col-lg-3 mb-2-5 mb-md-0">
                <div class="ps-lg-1-9 ps-xl-2-5">
                    <h3 class="text-primary h5 mb-2-2">Link</h3>
                    <ul class="footer-list">
                        <li><a href="blog-grid.html">News &amp; Blogs</a></li>
                        <li><a href="portfolio.html">Portfolio</a></li>
                        <li><a href="faq.html">FAQ's</a></li>
                        <li><a href="contact.html">Contact</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-6 col-lg-4">
                <div class="ps-md-1-9">
                    <h3 class="text-primary h5 mb-2-2">Popular Courses</h3>
                    <div class="media footer-border">
                        <img class="pe-3 border-radius-5" src="img/content/footer-insta-01.jpg" alt="...">
                        <div class="media-body align-self-center">
                            <h4 class="h6 mb-2"><a href="blog-details.html"
                                                   class="text-white text-primary-hover">Plan of learning experiences.</a></h4>
                            <span class="text-white small">Mar. 30, 2023</span>
                        </div>
                    </div>
                    <div class="media">
                        <img class="pe-3 border-radius-5" src="img/content/footer-insta-02.jpg" alt="...">
                        <div class="media-body align-self-center">
                            <h4 class="h6 mb-2"><a href="blog-details.html" class="text-white text-primary-hover">A
                                supportive learning community</a>
                            </h4>
                            <span class="text-white small">Mar. 28, 2023</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-bar text-center">
            <p class="mb-0 text-white font-weight-500">&copy; <span class="current-year"></span> eLearn Powered
                by <a href="#!" class="text-secondary">Chitrakoot Web</a></p>
        </div>
    </div>
</footer>

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