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
    <script>
        function delete_do() {
            var delete_do = "post_delete.do?post_id=${post.id}";
            window.open(delete_do, '_self');

        }
    </script>
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
    <section class="page-title-section bg-img cover-background top-position1 left-overlay-dark" data-overlay-dark="9"  >
        <div class="container">
            <div class="row text-center">
                <div class="col-md-12">
                    <h1>Update</h1>
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

    <!-- QUICK CONTACT
    ================================================== -->


    <!-- CONTACT FORM
    ================================================== -->
    <section class="bg-very-light-gray">
        <div class="container">
            <div style = "margin: 0 auto;" class="col-lg-6">
                <div class="faq-form">
                    <h2 class="mb-4 text-primary">Update</h2>
                    <form class="contact quform" name="f" action="post_update.do" method="post">
                        <div class="quform-elements">
                            <div>

                                <!-- Begin Text input element -->
                                <div class="col-md-6">
                                    <div class="quform-element form-group">
                                        <label for="111">제목<span class="quform-required">*</span></label>
                                        <div class="quform-input">
                                            <input class="form-control" id="111" type="text" name="subject" placeholder="${post.subject}" />
                                        </div>
                                    </div>
                                </div>
                                <!-- End Text input element -->

                                <!-- Begin Text input element -->
                                <div class="col-md-6">
                                    <div class="quform-element form-group">
                                        <label for="username">글쓴이<span class="quform-required">*</span></label>
                                        <div class="quform-input">
                                            <input class="form-control" disabled="disabled" id="username" type="text" value="${refull_name}" name="username" />
                                        </div>
                                    </div>
                                </div>
                                <!-- End Text input element -->

                                <!-- Begin Text input element -->
                                <div class="col-md-6">
                                    <div class="quform-element form-group">
                                        <label for="222">내용<span class="quform-required">*</span></label>
                                        <div class="quform-input">
                                            <textarea style="height: 150px; width:200%; max-width:200%;" class="form-control" id="222" name="content" placeholder="${post.content}"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <!-- End Text input element -->

                                <%--                                    <!-- Begin Text input element -->--%>
                                <%--                                    <div class="col-md-6">--%>
                                <%--                                        <div class="quform-element form-group">--%>
                                <%--                                            <label for="full_name">성명</label>--%>
                                <%--                                            <div class="quform-input">--%>
                                <%--                                                <input class="form-control" id="full_name" type="text" name="full_name" placeholder="성명" />--%>
                                <%--                                            </div>--%>
                                <%--                                        </div>--%>
                                <%--                                    </div>--%>
                                <%--                                    <!-- End Text input element -->--%>



                                <%--                                    <!-- Begin Captcha element -->--%>
                                <%--                                    <div class="col-md-12">--%>
                                <%--                                        <div class="quform-element">--%>
                                <%--                                            <div class="form-group">--%>
                                <%--                                                <label for="phone">핸드폰번호</label>--%>
                                <%--                                                <div class="quform-input">--%>
                                <%--                                                    <input class="form-control" id="phone" type="text" name="phone" placeholder="핸드폰 번호" />--%>
                                <%--                                                </div>--%>
                                <%--                                            </div>--%>

                                <%--                                        </div>--%>
                                <%--                                    </div>--%>
                                <%--                                    <!-- End Captcha element -->--%>

                                <!-- Begin Submit button -->

                                <div class="col-md-12">
                                    <div class="quform-submit-inner">
                                        <button type="button" class="butn secondary" onclick="submit()" ><i class="far fa-paper-plane icon-arrow before"></i><span class="label">수정</span><i class="far fa-paper-plane icon-arrow after"></i></button>
                                        <button type="button" class="butn secondary" onclick="delete_do()"><i class="far fa-paper-plane icon-arrow before"></i><span class="label">삭제</span><i class="far fa-paper-plane icon-arrow after"></i></button>
                                    </div>
                                    <div class="quform-loading-wrap text-start"><span class="quform-loading"></span></div>
                                </div>
                                <!-- End Submit button -->
                            </div>
                        </div>
                    </form>
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