<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=utf-8"
         import="javax.sql.DataSource, java.sql.Connection"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.*" %>


    <!-- FOOTER ================================================== -->
    <footer class="bg-dark">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-lg-3 mb-2-5 mb-lg-0">
                    <a href="index.jsp" class="footer-logo">
                        <a href ="index.jsp"><img style="width: 108%;" src="img/banner/header.png"></a>
                    </a>
                    <form class="quform newsletter" action="quform/newsletter-two.php" method="post" enctype="multipart/form-data" onclick="">

                        <div class="quform-elements">

                            <div class="row">

                                <!-- Begin Text input element -->
                                <div class="col-md-12">
                                    <div class="quform-element mb-0">

                                    </div>
                                </div>
                                <!-- End Text input element -->

                                <!-- Begin Submit button -->
                                <div class="col-md-12">

                                    <div class="quform-loading-wrap text-start"><span class="quform-loading"></span></div>
                                </div>
                                <!-- End Submit button -->
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-6 col-lg-2 mb-2-5 mb-lg-0">
                    <div class="ps-md-1-6 ps-lg-1-9">
                        <h3 class="text-primary h5 mb-2-2">회원</h3>
                        <ul class="footer-list">
                            <li><a href="signUpform.do">회원가입</a></li>
                            <li><a href="loginForm.do">로그인</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3 mb-2-5 mb-md-0">
                    <div class="ps-lg-1-9 ps-xl-2-5">
                        <h3 class="text-primary h5 mb-2-2">질문</h3>
                        <ul class="footer-list">
                            <li><a href="post_listForm.do">목록</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4">
                    <div class="ps-md-1-9">
                        <h3 class="text-primary h5 mb-2-2">강의</h3>
                        <ul class="footer-list">
                            <li><a href="courses.do">강좌 목록</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="footer-bar text-center">
                <p class="mb-0 text-white font-weight-500">&copy; <span class="current-year"></span> Produced by<a href="index.jsp" class="text-secondary"> HRD</a></p>
            </div>
        </div>
    </footer>

