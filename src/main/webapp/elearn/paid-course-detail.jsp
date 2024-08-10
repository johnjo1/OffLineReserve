<%@ page contentType="text/html; charset=utf-8"
         import="javax.sql.DataSource, java.sql.Connection"%>
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
                    <h1>수강 강좌</h1>
                </div>
                <div class="col-md-12">
                    <ul>
                        <li><a href="index.jsp">바로가기</a></li>
                        <li><a href="paid-course-list.jsp">결제목록</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <!-- EVENT DETAILS
    ================================================== -->
    <section class="courses">
        <div class="container">
            <div class="row">
                <!--  start courses list left-->
                <div class="col-md-12 col-lg-8 mb-2-9 mb-lg-0">
                    <div class="row">
                        <div class="col-md-12 mb-1-6 mb-md-1-9">

                        </div>
                        <div class="col-md-12">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="event-seprator">
                                        <h2 class="text-primary">${payedCourse.courseName}</h2>
                                        <ul
                                                class="event-info-list mb-4 pb-4 borders-bottom border-color-medium-gray">
                                            <li><i class="ti-calendar pe-2 text-secondary"></i>${payedCourse.startDate}</li>
                                            <li><i class="ti-time pe-2 text-secondary"></i>${payedCourse.period}</li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-md-12 mb-1-9">
                                    <p class="alt-font font-weight-500 text-color">${payedCourse.description}</p>
                                </div>


                                <div class="col-md-12 event-seprator">
                                    <h3>학원위치</h3>
                                    <p class="alt-font font-weight-500 text-color mb-1-6">
                                        서울특별시 금천구 벚꽃로 44길 24 1201호
                                    </p>
                                    <ul class="event-meta mb-4 ps-0">
                                        <li><i class="ti-location-pin"></i>벚꽃로 44길 24</li>
                                        <li><i class="ti-mobile"></i>02 (123) 4567 </li>
                                    </ul>
                                    <iframe width="100%" height="400" id="gmap_canvas" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3166.114215052936!2d126.88035067629376!3d37.481631028991345!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357c9e1d8db9e377%3A0x5608c055f2ed61ca!2z6rCA7IKw65SU7KeA7YS464uo7KeA!5e0!3m2!1sko!2skr!4v1722558403789!5m2!1sko!2skr"  style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--  end courses list left-->

                <!--  start courses list right-->
                <div class="col-md-12 col-lg-4">
                    <div class="ps-lg-1-6 ps-xl-1-9">

                        <div class="sidebar">

                            <div class="widget">
                                <div class="widget-title">
                                    <h3>강의 정보</h3>
                                </div>
                                <ul class="course-list">
                                    <li><span><i class="ti-ticket pe-2"></i>가격</span><span>${payedCourse.price}</span>
                                    </li>
                                    <li><span><i class="ti-calendar pe-2"></i>수강 시작일</span><span>${payedCourse.startDate}</span>
                                    </li>
                                    <li><span><i class="ti-calendar pe-2"></i>수강 종료일</span><span>${payedCourse.lastDate}</span>
                                    </li>
                                    <li><span><i class="ti-time pe-2"></i>시간</span><span>${payedCourse.period}
                                                pm</span></li>
                                    <li><span><i class="ti-location-pin pe-2"></i>강의실</span><span>${payedCourse.classRoomName}</span></li>
                                    <li><span><i class="ti-star pe-2"></i>별점</span><span>${payedCourse.averageScore}</span></li>
                                </ul>
                            </div>

                            <div class="widget">
                                <div class="widget-title">
                                    <h3>Share</h3>
                                </div>
                                <ul class="social-icons mb-0 ps-0">
                                    <li><a href="#!"><i class="fab fa-facebook-f"></i></a></li>
                                    <li><a href="#!"><i class="fab fa-twitter"></i></a></li>
                                    <li><a href="#!"><i class="fab fa-instagram"></i></a></li>
                                    <li><a href="#!"><i class="fab fa-youtube"></i></a></li>
                                    <li><a href="#!"><i class="fab fa-linkedin-in"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!--  end courses list right-->
            </div>
        </div>
    </section>

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