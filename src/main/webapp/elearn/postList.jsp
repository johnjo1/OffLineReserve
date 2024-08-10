<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
        <section class="page-title-section bg-img cover-background top-position1 left-overlay-dark" data-overlay-dark="9">
            <div class="container">
                <div class="row text-center">
                    <div class="col-md-12">
                        <h1>질문 목록</h1>
                    </div>
                    <div class="col-md-12">
                        <ul>
                            <li><a href="index.jsp">처음으로 </a></li>
                            <li><a href="post_listForm.do">질문 목록</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>

        <!-- Instructors
        ================================================== -->
        <section>
            <div class="container">
                <div class="section-heading">
                    <span class="sub-title">Q&A</span>
                    <h2 class="h1 mb-0">질문 목록</h2>
                </div>

                <div class="row">
                    <c:forEach items="${list_post}" var = "list_post">
                    <div class="col-lg-4 col-md-6 mb-1-6 mb-md-1-9">
                        <div class="team-style1 text-center">
                            <img src="https://img.freepik.com/free-vector/cute-background-abstract_125964-598.jpg?w=1380&t=st=1722578355~exp=1722578955~hmac=8ba6adfa8f64948c1ad3f1f5c218566fbb52f74df5e79cf4f99d7a9cbefb6a10" alt="..." style="border-radius: 5px;">
                            <div class="team-info">
                                <h3 href="post.do" class="text-primary mb-1 h4">${list_post.full_name}</h3>
                                <span class="font-weight-600 text-secondary">강사님</span>
                            </div>
                            <div class="team-overlay">
                                <div class="d-table h-100 w-100">
                                    <div class="d-table-cell align-middle">
                                        <h3><a href="post.do?refull_name=${list_post.full_name}" class="text-white">${list_post.full_name}</a></h3>
                                        <p class="text-white mb-0">오늘도 행복한 하루 되세요 ^^!</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
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