<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=utf-8"
         import="javax.sql.DataSource, java.sql.Connection"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.*" %>
<%@ page import="org.chulgang.hrd.users.dto.UsersLoginResponse" %>
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
<!--<c:choose>
    <c:when test="${empty dto}">
    </c:when>
    <c:otherwise>
        <font style="color:green" style="align:center">${dto.email}</font>
        ${dto.email}님 환영합니당^^
        <br/>
        <a href="logout.do?">로그아웃</a>
    </c:otherwise>
</c:choose> -->
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
        <section class="page-title-section bg-img cover-background top-position1 left-overlay-dark" data-overlay-dark="9" >
            <div class="container">
                <div class="row text-center">
                    <div class="col-md-12">
                        <h1>마이페이지</h1>
                    </div>
                    <div class="col-md-12">
                        <ul>
                            <li><a href="../index.jsp">처음으로 </a></li>
                            <li><a href="#!">Contact</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>

        <!-- QUICK CONTACT
        ================================================== -->
<%
    UsersLoginResponse dto = (UsersLoginResponse)session.getAttribute("dto");
%>

        <!-- CONTACT FORM
        ================================================== -->
        <section class="bg-very-light-gray py-md-8 pb-lg-0">
            <div class="container">
                <div class="row align-items-end">

                    <div class="col-lg-6" style = "margin: 0 auto;">
                        <div class="faq-form">
                            <h2 class="mb-4 text-primary">마이페이지</h2>
                            <div >
                                <button class="butn secondary" style="background-color:#ef5c72" type="button"><a href="chargeForm.do" class="far fa-paper-plane icon-arrow before">충전하기</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<span>나의캐시:&nbsp&nbsp</span><span>${current_amount}원</span></button>
                            </div>
                            <br>
                            <form class="contact quform" name="f" action="myPage.do" method="post" >
                                <div class="quform-elements">

                                        <!-- Begin Text input element -->
                                        <div class="col-md-6">
                                            <div class="quform-element form-group">
                                                <label for="email">이메일<span class="quform-required">*</span></label>
                                                <div class="quform-input">
                                                    <input class="form-control" id="email" type="text" name="email" placeholder="이메일" value="${dto.email}" />
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End Text input element -->

                                        <!-- Begin Text input element -->
                                        <div class="col-md-6">
                                            <div class="quform-element form-group">
                                                <label for="username">아이디<span class="quform-required">*</span></label>
                                                <div class="quform-input">
                                                    <input class="form-control" id="username" type="text" name="username" placeholder="아이디" value="${dto.username}"disabled />
                                                    <input class="form-control" id="username2" type="hidden" name="username" placeholder="아이디" value="${dto.username}" />

                                                </div>
                                            </div>
                                        </div>
                                        <!-- End Text input element -->

                                        <!-- Begin Text input element -->
                                        <div class="col-md-6">
                                            <div class="quform-element form-group">
                                                <label for="password">비밀번호<span class="quform-required">*</span></label>
                                                <div class="quform-input">
                                                    <input class="form-control" id="password" type="password" name="password" placeholder="비밀번호" />
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End Text input element -->

                                        <!-- Begin Text input element -->
                                        <div class="col-md-6">
                                            <div class="quform-element form-group">
                                                <label for="full_name">성명</label>
                                                <div class="quform-input">
                                                    <input class="form-control" id="full_name" type="text" name="full_name" placeholder="성명"value="${dto.full_name}" />
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End Text input element -->



                                        <!-- Begin Captcha element -->
                                        <div class="col-md-12">
                                            <div class="quform-element">
                                                <div class="form-group">
                                                    <label for="phone">핸드폰번호</label>
                                                    <div class="quform-input">
                                                        <input class="form-control" id="phone" type="text" name="phone" placeholder="핸드폰 번호" value="${dto.phone}" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End Captcha element -->

                                        <!-- Begin Submit button -->
                                        <div class="col-md-12">
                                            <div class="quform-submit-inner">
                                                <button class="butn secondary" type="button" onclick="submit()" ><i class="far fa-paper-plane icon-arrow before"></i><span class="label">수정</span><i class="far fa-paper-plane icon-arrow after"></i></button>
                                            </div>
                                            <div class="quform-loading-wrap text-start"><span class="quform-loading"></span></div>
                                        </div>
                                        <!-- End Submit button -->


                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
<script>
    function submit(){
        f.submit();
    }

</script>
        <!-- MAP 지도 제거했음
        ================================================== -->


        <!-- FOOTER
        ================================================== -->
      <%@include file="footer.jsp"%>
<script>
    let flag = ${flag};
    if(flag== -1){
        alert('비밀번호가 틀립니다.')
    }
</script>
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