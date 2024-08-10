<%@ page contentType="text/html; charset=utf-8"
         import="javax.sql.DataSource, java.sql.Connection" %>
<%@ page import="org.chulgang.hrd.course.dto.GetCourseResponse" %>
<%@ page import="static org.chulgang.hrd.course.util.RequestConstant.COURSE_DOMAIN_NAME" %>

<%
    GetCourseResponse getCourseResponse = (GetCourseResponse) request.getAttribute(COURSE_DOMAIN_NAME);
    float averageScore = getCourseResponse.getAverageScore();
    int fullStars = (int) averageScore;
    boolean hasHalfStar = (averageScore % 1) >= 0.5;
%>
<html lang="en">

<head>
    <!-- metas -->
    <meta name="author" content="Chitrakoot Web"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="keywords" content="Online Education Learning Template"/>
    <meta name="description" content="eLearn - Online Education Learning Template"/>

    <!-- title  -->
    <title>강좌 상세 페이지</title>

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
                    <h1>Course Details</h1>
                </div>
                <div class="col-md-12">
                    <ul>
                        <li><a href="index.jsp">처음으로 </a></li>
                        <li><a href="#!">Course Details</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <!-- COURSES DETAILS
    ================================================== -->
    <section class="courses">
        <div class="container">
            <div class="row">

                <div class="col-md-12 col-lg-8 mb-2-9 mb-lg-0">
                    <div class="row">
                        <div class="col-md-12 mb-1-6 mb-md-1-9">
                            <div class="courses-info mb-4">

                                <div class="bg-light rounded py-4 px-4 mb-3">
                                    <div class="section-heading mb-0 text-start">
                                        <span class="sub-title"><%= getCourseResponse.getSubjectName() %></span>
                                    </div>
                                    <h2 class="h1 mb-0"><%= getCourseResponse.getName() %>
                                    </h2>
                                </div>

                                <div class="course-meta bg-primary rounded py-4 px-4 overflow-hidden">
                                    <div class="item author mb-lg-1-6 mb-xl-0">
                                        <div class="desc">
                                            <h4 class="display-29 display-xl-28 font-weight-800 mb-1 text-white">
                                                강사명</h4>
                                            <a href="#!"
                                               class="display-30 font-weight-600 text-white"><%= getCourseResponse.getTeacherName() %>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="item dates">
                                        <h4 class="display-29 display-xl-28 font-weight-800 mb-1 text-white">
                                            업데이트 시간</h4>
                                        <span class="display-30 font-weight-600 text-white"><%= getCourseResponse.getModifiedAt() == null || getCourseResponse.getModifiedAt().equals("null") ? getCourseResponse.getCreatedAt() : getCourseResponse.getModifiedAt() %></span>
                                    </div>
                                    <div class="item me-0 ratings mb-lg-1-6 mb-xl-0">
                                        <h4 class="display-29 display-xl-28 font-weight-800 mb-1 text-white">강좌 평점</h4>

                                        <% for (int i = 0; i < fullStars; i++) { %>
                                        <i class="fas fa-star"></i>
                                        <% } %>

                                        <% if (hasHalfStar) { %>
                                        <i class="fas fa-star-half-alt"></i>
                                        <% } %>

                                        <% for (int i = 0; i < (5 - fullStars - (hasHalfStar ? 1 : 0)); i++) { %>
                                        <i class="far fa-star"></i> <!-- 빈 별 -->
                                        <% } %>

                                        <span class="text-white font-weight-700"><%= averageScore %></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12 mb-1-6 mb-md-2-9">
                            <div class="horizontaltab tab-style1">
                                <div class="resp-tabs-container hor_1">
                                    <div>
                                        <div class="row">
                                            <div class="col-md-12 mb-1-6 mb-lg-1-9">
                                                <h3><%= getCourseResponse.getName() %>
                                                </h3>
                                                </br>
                                                <%= getCourseResponse.getDescription() %>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div id="accordion" class="accordion-style1">
                                                    <div class="card">
                                                        <div class="card-header" id="headingOne">
                                                            <h5 class="mb-0">
                                                                <button class="btn btn-link" data-bs-toggle="collapse"
                                                                        data-bs-target="#collapseOne"
                                                                        aria-expanded="true"
                                                                        aria-controls="collapseOne">
                                                                    Section 1: Welcome to the Courses
                                                                </button>
                                                            </h5>
                                                        </div>
                                                        <div id="collapseOne" class="collapse show"
                                                             aria-labelledby="headingOne" data-bs-parent="#accordion">
                                                            <div class="card-body">
                                                                <ul class="curriculum-lists">
                                                                    <li>
                                                                        <div class="titles">
                                                                            <i class="fas fa-play-circle"></i>
                                                                            <h3>
                                                                                Lecture 1.0
                                                                            </h3>
                                                                            <h5 class="display-29 display-lg-28 display-xl-27 mb-0">
                                                                                <a href="#!">Introduction of java</a>
                                                                            </h5>
                                                                            <div class="access-type">
                                                                                <i class="fas fa-eye"></i>
                                                                            </div>
                                                                        </div>
                                                                        <div class="intro">
                                                                            <div class="item">
                                                                                <p class="alt-font">
                                                                                    Published - 15 Aug
                                                                                </p>
                                                                            </div>
                                                                            <div class="item">
                                                                                <p class="alt-font">
                                                                                    Duration: 1 hours 30 min
                                                                                </p>
                                                                                <a href="#!">Preview</a>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                    <li>
                                                                        <div class="titles">
                                                                            <i class="fas fa-file"></i>
                                                                            <h3>
                                                                                Lecture 1.2
                                                                            </h3>
                                                                            <h5 class="display-29 display-lg-28 display-xl-27 mb-0">
                                                                                <a href="#!">Basic development</a>
                                                                            </h5>
                                                                            <div class="access-type">
                                                                                <i class="ti-lock"></i>
                                                                            </div>
                                                                        </div>
                                                                        <div class="intro">
                                                                            <div class="item">
                                                                                <p class="alt-font">
                                                                                    Published - 28 Apr
                                                                                </p>
                                                                            </div>
                                                                            <div class="item">
                                                                                <p class="alt-font">
                                                                                    Duration: 3 hour 45 min
                                                                                </p>
                                                                                <a href="#!">Preview</a>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card">
                                                        <div class="card-header" id="headingTwo">
                                                            <h5 class="mb-0">
                                                                <button class="btn btn-link collapsed"
                                                                        data-bs-toggle="collapse"
                                                                        data-bs-target="#collapseTwo"
                                                                        aria-expanded="false"
                                                                        aria-controls="collapseTwo">
                                                                    Section 2: How to use Java
                                                                </button>
                                                            </h5>
                                                        </div>
                                                        <div id="collapseTwo" class="collapse"
                                                             aria-labelledby="headingTwo" data-bs-parent="#accordion">
                                                            <div class="card-body">
                                                                <ul class="curriculum-lists">
                                                                    <li>
                                                                        <div class="titles">
                                                                            <i class="fas fa-play-circle"></i>
                                                                            <h3>
                                                                                Lecture 1.0
                                                                            </h3>
                                                                            <h5 class="display-29 display-lg-28 display-xl-27 mb-0">
                                                                                <a href="#!">Introduction of java</a>
                                                                            </h5>
                                                                            <div class="access-type">
                                                                                <i class="fas fa-eye"></i>
                                                                            </div>
                                                                        </div>
                                                                        <div class="intro">
                                                                            <div class="item">
                                                                                <p class="alt-font">
                                                                                    Published - 15 Aug
                                                                                </p>
                                                                            </div>
                                                                            <div class="item">
                                                                                <p class="alt-font">
                                                                                    Duration: 1 hour 30 min
                                                                                </p>
                                                                                <a href="#!">Preview</a>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                    <li>
                                                                        <div class="titles">
                                                                            <i class="fas fa-file"></i>
                                                                            <h3>
                                                                                Lecture 1.2
                                                                            </h3>
                                                                            <h5 class="display-29 display-lg-28 display-xl-27 mb-0">
                                                                                <a href="#!">Basic development</a>
                                                                            </h5>
                                                                            <div class="access-type">
                                                                                <i class="ti-lock"></i>
                                                                            </div>
                                                                        </div>
                                                                        <div class="intro">
                                                                            <div class="item">
                                                                                <p class="alt-font">
                                                                                    Published - 28 Apr
                                                                                </p>
                                                                            </div>
                                                                            <div class="item">
                                                                                <p class="alt-font">
                                                                                    Duration: 3 hour 45 min
                                                                                </p>
                                                                                <a href="#!">Preview</a>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card">
                                                        <div class="card-header" id="headingThree">
                                                            <h5 class="mb-0">
                                                                <button class="btn btn-link collapsed"
                                                                        data-bs-toggle="collapse"
                                                                        data-bs-target="#collapseThree"
                                                                        aria-expanded="false"
                                                                        aria-controls="collapseThree">
                                                                    Section 3: Final chapters
                                                                </button>
                                                            </h5>
                                                        </div>
                                                        <div id="collapseThree" class="collapse"
                                                             aria-labelledby="headingThree" data-bs-parent="#accordion">
                                                            <div class="card-body">
                                                                <ul class="curriculum-lists">
                                                                    <li>
                                                                        <div class="titles">
                                                                            <i class="fas fa-play-circle"></i>
                                                                            <h3>
                                                                                Lecture 1.0
                                                                            </h3>
                                                                            <h5 class="display-29 display-lg-28 display-xl-27 mb-0">
                                                                                <a href="#!">Introduction of java</a>
                                                                            </h5>
                                                                            <div class="access-type">
                                                                                <i class="fas fa-eye"></i>
                                                                            </div>
                                                                        </div>
                                                                        <div class="intro">
                                                                            <div class="item">
                                                                                <p class="alt-font">
                                                                                    Published - 15 Aug
                                                                                </p>
                                                                            </div>
                                                                            <div class="item">
                                                                                <p class="alt-font">
                                                                                    Duration: 1 hour 30 min
                                                                                </p>
                                                                                <a href="#!">Preview</a>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                    <li>
                                                                        <div class="titles">
                                                                            <i class="fas fa-file"></i>
                                                                            <h3>
                                                                                Lecture 1.2
                                                                            </h3>
                                                                            <h5 class="display-29 display-lg-28 display-xl-27 mb-0">
                                                                                <a href="#!">Basic development</a>
                                                                            </h5>
                                                                            <div class="access-type">
                                                                                <i class="ti-lock"></i>
                                                                            </div>
                                                                        </div>
                                                                        <div class="intro">
                                                                            <div class="item">
                                                                                <p class="alt-font">
                                                                                    Published - 28 Apr
                                                                                </p>
                                                                            </div>
                                                                            <div class="item">
                                                                                <p class="alt-font">
                                                                                    Duration: 3 hour 45 min
                                                                                </p>
                                                                                <a href="#!">Preview</a>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="row mb-1-6 mb-lg-1-9 mb-xl-2-5">
                                            <div class="col-md-12">
                                                <h4 class="display-27 display-md-25 display-xl-20 font-weight-800 mb-1-6 text-capitalize">
                                                    Reviews</h4>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="bg-very-light-gray text-center">
                                                            <div class="rating-box">
                                                                <span class="rating-number">5</span>
                                                                <ul class="list-unstyled">
                                                                    <li><i class="fa fa-star"></i></li>
                                                                    <li><i class="fa fa-star"></i></li>
                                                                    <li><i class="fa fa-star"></i></li>
                                                                    <li><i class="fa fa-star"></i></li>
                                                                    <li><i class="fa fa-star"></i></li>
                                                                </ul>
                                                                <span class="display-30 text-color font-weight-800">8 Ratings</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="row">
                                                            <div class="col-sm-12 mb-3">
                                                                <div class="progress-text">
                                                                    <div class="row">
                                                                        <div class="col-7">5 Stars</div>
                                                                        <div class="col-5 text-end">95%</div>
                                                                    </div>
                                                                </div>
                                                                <div class="progress progress-medium">
                                                                    <div class="animated custom-bar progress-bar slideInLeft"
                                                                         style="width: 95%;" aria-valuemax="100"
                                                                         aria-valuemin="0" aria-valuenow="10"
                                                                         role="progressbar"></div>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-12 mb-3">
                                                                <div class="progress-text">
                                                                    <div class="row">
                                                                        <div class="col-7">4 Stars</div>
                                                                        <div class="col-5 text-end">70%</div>
                                                                    </div>
                                                                </div>
                                                                <div class="progress progress-medium">
                                                                    <div class="animated custom-bar progress-bar slideInLeft"
                                                                         style="width: 70%;" aria-valuemax="100"
                                                                         aria-valuemin="0" aria-valuenow="10"
                                                                         role="progressbar"></div>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-12 mb-3">
                                                                <div class="progress-text">
                                                                    <div class="row">
                                                                        <div class="col-7">3 Stars</div>
                                                                        <div class="col-5 text-end">45%</div>
                                                                    </div>
                                                                </div>
                                                                <div class="progress progress-medium">
                                                                    <div class="animated custom-bar progress-bar slideInLeft"
                                                                         style="width: 45%;" aria-valuemax="100"
                                                                         aria-valuemin="0" aria-valuenow="10"
                                                                         role="progressbar"></div>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-12 mb-3">
                                                                <div class="progress-text">
                                                                    <div class="row">
                                                                        <div class="col-7">2 Stars</div>
                                                                        <div class="col-5 text-end">30%</div>
                                                                    </div>
                                                                </div>
                                                                <div class="progress progress-medium">
                                                                    <div class="animated custom-bar progress-bar slideInLeft"
                                                                         style="width: 30%;" aria-valuemax="100"
                                                                         aria-valuemin="0" aria-valuenow="10"
                                                                         role="progressbar"></div>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-12">
                                                                <div class="progress-text">
                                                                    <div class="row">
                                                                        <div class="col-7">1 Stars</div>
                                                                        <div class="col-5 text-end">15%</div>
                                                                    </div>
                                                                </div>
                                                                <div class="progress progress-medium">
                                                                    <div class="animated custom-bar progress-bar slideInLeft"
                                                                         style="width: 15%;" aria-valuemax="100"
                                                                         aria-valuemin="0" aria-valuenow="10"
                                                                         role="progressbar"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row mb-1-6 mb-lg-1-9 mb-xl-2-5">
                                            <h4 class="display-27 display-md-25 display-lg-24 display-xl-20 font-weight-800 mb-1-6 text-capitalize">
                                                Comments</h4>
                                            <div class="col-lg-12">
                                                <div class="comment-box">
                                                    <div class="author-thumb">
                                                        <img class="border-radius-50" src="img/avatar/avatar-15.jpg"
                                                             alt="...">
                                                    </div>
                                                    <div class="ps-10 ps-md-11">
                                                        <div class="mb-3">
                                                            <h6 class="display-29 display-lg-28 font-weight-800"><a
                                                                    href="#!">Denis Irwin</a></h6>
                                                            <div class="review-rating">
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                            </div>
                                                        </div>
                                                        <p class="mb-3 display-30 display-md-29 alt-font text-color font-weight-500">
                                                            Excepteur sint occaecat cupidatat non proident, sunt in
                                                            culpa qui officia deserunt mollit anim id est laborum.</p>
                                                        <div class="font-weight-800">
                                                            <a href="#!" class="display-30 display-md-29"> <i
                                                                    class="fa fa-reply display-31 pe-2"
                                                                    aria-hidden="true"></i> Reply </a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="comment-box">
                                                    <div class="author-thumb">
                                                        <img class="border-radius-50" src="img/avatar/avatar-10.jpg"
                                                             alt="...">
                                                    </div>
                                                    <div class="ps-10 ps-md-11">
                                                        <div class="mb-3">
                                                            <h6 class="display-29 display-lg-28 font-weight-800"><a
                                                                    href="#!">Bruno Roach</a></h6>
                                                            <div class="review-rating">
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                            </div>
                                                        </div>
                                                        <p class="mb-3 display-30 display-md-29 alt-font text-color font-weight-500">
                                                            Excepteur sint occaecat cupidatat non proident, sunt in
                                                            culpa qui officia deserunt mollit anim id est laborum.</p>
                                                        <div class="font-weight-800">
                                                            <a href="#!" class="display-30 display-md-29"> <i
                                                                    class="fa fa-reply display-31 pe-2"
                                                                    aria-hidden="true"></i> Reply </a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="comment-box">
                                                    <div class="author-thumb">
                                                        <img class="border-radius-50" src="img/avatar/avatar-13.jpg"
                                                             alt="...">
                                                    </div>
                                                    <div class="ps-10 ps-md-11">
                                                        <div class="mb-3">
                                                            <h6 class="display-29 display-lg-28 font-weight-800"><a
                                                                    href="#!">John Martin</a></h6>
                                                            <div class="review-rating">
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                            </div>
                                                        </div>
                                                        <p class="mb-3 display-30 display-md-29 alt-font text-color font-weight-500">
                                                            Excepteur sint occaecat cupidatat non proident, sunt in
                                                            culpa qui officia deserunt mollit anim id est laborum.</p>
                                                        <div class="font-weight-800">
                                                            <a href="#!" class="display-30 display-md-29"> <i
                                                                    class="fa fa-reply display-31 pe-2"
                                                                    aria-hidden="true"></i> Reply </a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="comment-box mb-0">
                                                    <div class="author-thumb">
                                                        <img class="border-radius-50" src="img/avatar/avatar-09.jpg"
                                                             alt="...">
                                                    </div>
                                                    <div class="ps-10 ps-md-11">
                                                        <div class="mb-3">
                                                            <h6 class="display-29 display-lg-28 font-weight-800"><a
                                                                    href="#!">John Martin</a></h6>
                                                            <div class="review-rating">
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                                <i class="fas fa-star"></i>
                                                            </div>
                                                        </div>
                                                        <p class="mb-3 display-29 alt-font text-color font-weight-500">
                                                            Excepteur sint occaecat cupidatat non proident, sunt in
                                                            culpa qui officia deserunt mollit anim id est laborum.</p>
                                                        <div class="font-weight-800">
                                                            <a href="#!" class="display-30 display-md-29"> <i
                                                                    class="fa fa-reply display-31 pe-2"
                                                                    aria-hidden="true"></i> Reply </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="comment-form">
                                                <h4 class="display-27 display-md-25 display-lg-24 display-xl-20 font-weight-800 mb-1-6 text-capitalize">
                                                    Leave A Comment</h4>
                                                <!-- Form -->
                                                <form>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <textarea name="reply" rows="6" class="form-control h-100"
                                                                      placeholder="Your Reply"></textarea>
                                                        </div>
                                                        <div class="col-sm-6">
                                                            <div class="form-group">
                                                                <input type="text" class="form-control" name="name"
                                                                       placeholder="Your Name">
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-6">
                                                            <div class="form-group">
                                                                <input type="email" class="form-control" name="email"
                                                                       placeholder="Email Address">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <button type="submit" class="butn"><i
                                                                class="fas fa-plus-circle icon-arrow before"></i><span
                                                                class="label">Submit</span><i
                                                                class="fas fa-plus-circle icon-arrow after"></i>
                                                        </button>
                                                    </div>
                                                </form>
                                                <!-- End Form -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--  start courses list right-->
                <div class="col-md-12 col-lg-4">
                    <div class="ps-lg-1-6 ps-xl-1-9">
                        <div class="sidebar">
                            <div class="widget">
                                <div class="widget-title">
                                    <h3>강좌 상세정보</h3>
                                </div>
                                <ul class="course-list">
                                    <li><span><i
                                            class="ti-time pe-2"></i>수강 기간</span><%= getCourseResponse.getStartDate() %>
                                        ~ <%= getCourseResponse.getLastDate() %>
                                    </li>
                                    <li><span><i
                                            class="ti-files pe-2"></i>과목</span><%= getCourseResponse.getSubjectName() %>
                                    </li>
                                    <li><span><i
                                            class="ti-menu-alt pe-2"></i>가격</span><%= getCourseResponse.getPrice() %>
                                    </li>
                                    <li><span><i
                                            class="ti-user pe-2"></i>남은 자리</span><%= getCourseResponse.getRemainedSeat() %>
                                    </li>
                                    <li><span><i
                                            class="ti-medall pe-2"></i>신청 진행 여부</span><%= getCourseResponse.getRemainedSeat() <= 0 ? "신청 마" : "진행 중" %>
                                    </li>
                                    <li><span><i
                                            class="ti-stats-up pe-2"></i>개설 시간</span><%= getCourseResponse.getCreatedAt() %>
                                    </li>
                                    <li><span><i
                                            class="ti-world pe-2"></i>업데이트 시간</span><%= getCourseResponse.getModifiedAt() == null || getCourseResponse.getModifiedAt().equals("null") ? getCourseResponse.getCreatedAt() : getCourseResponse.getModifiedAt() %>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!--  end courses list right-->

            </div>
        </div>
    </section>

    <% if (getCourseResponse.getRemainedSeat() > 0) { %>
    <div style="position: fixed; bottom: 20px; right: 20px;">
        <form action="register-reservation.do" method="POST">
            <input type="hidden" name="courseId" value="<%= getCourseResponse.getId() %>"/>
            <button type="submit" class="btn btn-primary">수강 신청</button>
        </form>
    </div>
    <% } %>

    <!-- FOOTER
    ================================================== -->
    <%@include file="footer.jsp" %>
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