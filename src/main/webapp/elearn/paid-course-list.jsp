<%@ page contentType="text/html; charset=utf-8"
         import="javax.sql.DataSource, java.sql.Connection"%>
<%@ page import="org.chulgang.hrd.reservation.dto.ReservationCardResponse" %>
<%@ page import="java.util.List" %>
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
                    <h1>수강 강의</h1>
                </div>
                <div class="col-md-12">
                    <ul>
                        <li><a href="index.jsp">처음으로 </a></li>
                        <li><a href="#!">Paid List</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <!-- EVENT LIST
    ================================================== -->
    <section>
        <div class="container">
            <div class="section-heading">
                <span class="sub-title">reservation</span>
                <h2 class="h1 mb-0">수강 목록</h2>
            </div>
            <div class="row g-xxl-5 mt-n2-9">
                <c:forEach var="payment" items="${payments}">
                    <div class="col-xl-6 mt-2-9">
                        <div class="row g-0 event-wrapper">
                            <div class="col-md-5 cover-background" >
                            </div>
                            <div class="col-md-7">
                                <div class="p-1-6 p-sm-1-9">
                                    <span class="badge-soft mb-3">${payment.courseName}</span>
                                    <h4 class="font-weight-800 h5 mb-3"><a href="/elearn/paid-course-detail.do?payedCourseId=${payment.payedCourseId}">${payment.courseName}</a></h4>
                                    <p class="mb-3 alt-font font-weight-500">${payment.courseDescription}</p>
                                    <div class="dotted-seprator pt-4 mt-4"></div>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <p class="mb-0 text-primary font-weight-600"><i
                                                class="ti-calendar me-2"></i><span class="text-primary"> ${payment.startDate} - ${payment.endDate}</span></p>
                                        <p class="mb-0 text-primary font-weight-600"><i
                                                class="ti-location-pin me-2"></i><span
                                                class="text-primary">${payment.payedAmount}</span></p>
                                    </div>
                                    <h1><span class="badge bg-primary">${payment.refunded ? "환불완료" : "수강중"}</span></h1>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="text-center mt-6 mt-lg-7">
                        <div class="pagination text-extra-dark-gray">
                            <ul>
                                <c:if test="${currentPage > 1}">
                                    <li><a href="payment-list.do?page=${currentPage - 1}" class="me-3"><i class="fas fa-long-arrow-alt-left"></i></a></li>
                                </c:if>
                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <li class="${currentPage == i ? 'active' : ''}"><a href="payment-list.do?page=${i}" class="me-2">${i}</a></li>
                                </c:forEach>
                                <c:if test="${currentPage < totalPages}">
                                    <li><a href="payment-list.do?page=${currentPage + 1}" class="me-3"><i class="fas fa-long-arrow-alt-right"></i></a></li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
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