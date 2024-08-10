<%@ page contentType="text/html; charset=utf-8"
         import="javax.sql.DataSource, java.sql.Connection" %>
<%@ page import="org.chulgang.hrd.course.dto.GetCoursesResponse" %>
<%@ page import="static org.chulgang.hrd.course.util.RequestConstant.COURSES_ATTRIBUTE_NAME" %>
<%@ page import="static org.chulgang.hrd.course.util.RequestConstant.SIZE_PARAMETER_NAME" %>
<%@ page import="static org.chulgang.hrd.course.util.RequestConstant.*" %>
<%@ page import="org.chulgang.hrd.users.dto.UsersLoginResponse" %>

<%
    GetCoursesResponse getCoursesResponse = (GetCoursesResponse) request.getAttribute(COURSES_ATTRIBUTE_NAME);
    int pageCount = (int) request.getAttribute(PAGE_COUNT_PARAMETER_NAME);
    int size = (int) request.getAttribute(SIZE_PARAMETER_NAME);
    int pageNumber = (int) request.getAttribute(PAGE_NUMBER_PARAMETER_NAME);
    UsersLoginResponse usersLoginResponse
            = (UsersLoginResponse) request.getSession().getAttribute(LOGIN_SESSION_ATTRIBUTE_NAME);
    boolean isTeacher = usersLoginResponse != null && usersLoginResponse.getRole().equals("teacher");
%>

<html lang="en">
<head>
    <!-- metas -->
    <meta charset="utf-8"/>
    <meta name="author" content="Chitrakoot Web"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="keywords" content="Online Education Learning Template"/>
    <meta name="description" content="eLearn - Online Education Learning Template"/>

    <!-- title  -->
    <title>HRD 강좌 목록</title>

    <!-- favicon -->
    <link rel="shortcut icon" href="img/logos/favicon.png"/>
    <link rel="apple-touch-icon" href="img/logos/apple-touch-icon-57x57.png"/>
    <link rel="apple-touch-icon" sizes="72x72" href="img/logos/apple-touch-icon-72x72.png"/>
    <link rel="apple-touch-icon" sizes="114x114" href="img/logos/apple-touch-icon-114x114.png"/>

    <!-- plugins -->
    <link rel="stylesheet" href="css/plugins.css"/>

    <!-- search css -->
    <link rel="stylesheet" href="search/search.css"/>

    <!-- quform css -->
    <link rel="stylesheet" href="quform/css/base.css">

    <!-- core style css -->
    <link href="css/styles.css" rel="stylesheet"/>

    <!-- custom style css -->
    <link href="css/custom.css" rel="stylesheet"/>

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
    <%@include file="header.jsp" %>
    <!-- PAGE TITLE
    ================================================== -->
    <section class="page-title-section bg-img cover-background top-position1 left-overlay-dark" data-overlay-dark="9">
        <div class="container">
            <div class="row text-center">
                <div class="col-md-12">
                    <h1>강좌 목록</h1>
                </div>
                <div class="col-md-12">
                    <ul>
                        <li><a href="index.jsp">처음으로 </a></li>
                        <li><a href="#!">강좌 목록</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <!-- ONLINE COURSES
    ================================================== -->
    <section>
        <div class="container">
            <div class="section-heading">
                <span class="sub-title">COURSES</span>
                <h2 class="h1 mb-0">강좌 목록</h2>
            </div>
            <div class="row g-xxl-5 mt-n2-6">
                    <% for (int i = 0; i < getCoursesResponse.size(); i++) {
                %>
                <div class="col-md-6 col-xl-4 mt-2-6">
                    <div class="card card-style1 p-0 h-100">
                        <div class="card-img rounded-0">
                            <a href="courses-list.html"
                               class="course-tag"><%= getCoursesResponse.get(i).getSubjectName() %>
                            </a>
                            <a href="#!"><i class="far fa-heart"></i></a>
                        </div>
                        <div class="card-body position-relative pt-0 px-1-9 pb-1-9">
                            <div class="card-author d-flex">
                                <h4 class="mb-0 h6">강사: <%= getCoursesResponse.get(i).getTeacherName() %>
                                </h4>
                            </div>
                            <div class="pt-6">
                                <h3 class="h4 mb-4"><a
                                        href="course-details.do?id=<%= getCoursesResponse.get(i).getId() %>"><%= getCoursesResponse.get(i).getName() %>
                                </a></h3>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="display-30"><i
                                            class="ti-agenda me-2"></i><%= getCoursesResponse.get(i).getStartDate() %>
                                        ~ <%= getCoursesResponse.get(i).getLastDate() %>
                                    </div>
                                    <div class="display-30"><i
                                            class="ti-user me-2"></i><%= getCoursesResponse.get(i).getRemainedSeat() %>
                                    </div>
                                    <div class="display-30"><i
                                            class="fas fa-star me-1 display-32 text-warning"></i><%= getCoursesResponse.get(i).getAverageScore() %>
                                    </div>
                                </div>
                                <div class="dotted-seprator pt-4 mt-4 d-flex justify-content-between align-items-center">
                                    <%
                                        if (getCoursesResponse.get(i).getRemainedSeat() <= 0) {
                                    %>
                                    <span class="badge-soft">신청 종료</span>
                                    <%
                                    } else {
                                    %>
                                    <span class="badge-soft">신청 진행 중</span>
                                    <%
                                        }
                                    %>
                                    <h5 class="text-primary mb-0"><%= getCoursesResponse.get(i).getPrice() %>원</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                    <%
                    }
                %>
                <div class="row">
                    <div class="col-sm-12">
                        <!-- start pager  -->
                        <div class="text-center mt-6 mt-lg-7">
                            <div class="pagination text-extra-dark-gray">
                                <ul>
                                    <%
                                        for (int i = 1; i <= pageCount; i++) {
                                            if (i == pageNumber) {
                                    %>
                                    <li class="active"><a href="#!" class="page-numbers"><%= i %>
                                    </a></li>
                                    <%
                                            continue;
                                        }
                                    %>
                                    <li>
                                        <a href=<%= GET_COURSES_SECOND_REQUEST_URL %>?size=<%= size %>&pageNumber=<%= i %>
                                           class="page-numbers"><%= i %>
                                        </a></li>
                                    <%
                                        }
                                    %>
                                </ul>
                            </div>
                        </div>
                        <!-- end pager -->
                    </div>
                    <div class="text-end mt-4">
                        <% if (isTeacher) { %>
                        <a href="<%= REGISTER_COURSE_FIRST_REQUEST_URL %>" class="btn btn-primary">강좌 개설</a>
                        <% } %></div>
                </div>
    </section>

    <section>
        <div class="container mt-4">
            <form id="pageSizeForm" method="GET" action="<%= GET_COURSES_SECOND_REQUEST_URL %>" style="text-align: right;">
                <div class="row">
                    <div class="col-md-12">
                        <label for="pageSize">페이지 크기:</label>
                        <select id="pageSize" name="size" class="form-select" style="width: auto; display: inline-block;" onchange="document.getElementById('pageSizeForm').submit()">
                            <option value="3" <%= size == 3 ? "selected" : "" %>>3</option>
                            <option value="6" <%= size == 6 ? "selected" : "" %>>6</option>
                            <option value="9" <%= size == 9 ? "selected" : "" %>>9</option>
                        </select>
                        <input type="hidden" name="pageNumber" value="<%= pageNumber %>"/>
                    </div>
                </div>
            </form>
        </div>
    </section>

    <!-- FOOTER
    ================================================== -->

</div>
<%@include file="footer.jsp" %>
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