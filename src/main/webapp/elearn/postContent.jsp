<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html lang="">

<head>
  <!-- metas -->
  <meta charset="utf-8"/>
  <meta name="author" content="Chitrakoot Web"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
  <meta name="keywords" content="Online Education Learning Template"/>
  <meta name="description" content="eLearn - Online Education Learning Template"/>
  <style>
    .comment, .reply {
      border: 1px solid #ddd;
      padding: 10px;
      margin-bottom: 10px;
      border-radius: 5px;
    }

    .comment-author, .reply-author {
      font-weight: bold;
      margin-bottom: 5px;
    }

    .comment-content, .reply-content {
      margin-bottom: 5px;
    }

    .comment-date, .reply-date {
      font-size: 0.9em;
      color: #777;
    }

    .comment-header {
      background-color: #36c6d3;
      padding: 10px;
      font-weight: bold;
      color: white;
      border-radius: 5px 5px 0 0;
    }

    .comment-author-date, .reply-author-date {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .accordion-style1 .btn-link:after {
      display: none;
    }
  </style>
  <!-- title  -->
  <title>eLearn - Online Education Learning Template</title>
  <script>function openContentJSP() {
    var openInputUrl = "post_contentForm.do";
    window.open(openInputUrl,'_self');
  }


  function openInputJSP() {
    var openInputUrl = "post_insertForm.do";
    window.open(openInputUrl,'_self');
  }

  function openInputJSP() {
    var openInputUrl = "post_insertForm.do";
    window.open(openInputUrl,'_self');
  }

  </script>

  <!-- favicon -->
  <link rel="shortcut icon" href="img/logos/favicon.png"/>
  <link rel="apple-touch-icon" href="img/logos/apple-touch-icon-57x57.png"/>
  <link rel="apple-touch-icon" sizes="72x72" href="img/logos/apple-touch-icon-72x72.png"/>
  <link rel="apple-touch-icon" sizes="114x114" href="img/logos/apple-touch-icon-114x114.png"/>

  <!-- plugins -->
  <link rel="stylesheet" href="css/plugins.css"/>

  <!-- search css -->
  <link rel="stylesheet" href="css/search.css"/>

  <!-- quform css -->
  <link rel="stylesheet" href="css/base.css">

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
  <%@include file="header.jsp"%>

  <!-- PAGE TITLE
  ================================================== -->
  <section class="page-title-section bg-img cover-background top-position1 left-overlay-dark" data-overlay-dark="9"  >
    <div class="container">
      <div class="row text-center">
        <div class="col-md-12">
          <h1>댓글</h1>
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


  <!-- FAQ
================================================== -->
  <section>
    <div class="container">
      <div class="section-heading">
        <h2 class="h1 mb-0">${refull_name} 강사님</h2>
        <span class="sub-title">Q&A</span>
      </div>


      <div class="row">
        <div class="col-md-10 mx-auto">
          <div id="accordion1" class="accordion-style1">
            <div class="card">
              <div class="card-header" id="headingOne">
                <h5 class="mb-0">
                  <button class="btn btn-link" data-bs-target="#collapseFive" aria-expanded="true" aria-controls="collapseFive">
                    ${post.subject}
                  </button>
                </h5>
              </div>
              <div id="collapseFive" class="collapse show" aria-labelledby="headingFive" data-bs-parent="#accordion1">
                <div class="card-body">
                  ${post.content}
                  <p style="margin: 0 0">
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!--권한 없을때-->
<%--      <div style="margin: 5% 10% 3% 10%;" class="row">--%>
<%--        <div class="card-body">--%>
<%--          <!-- 답글 내용 표시 부분: 리스트 형식으로 표시 -->--%>
<%--          <div class="user-comments">--%>
<%--            <div class="comment-header">--%>
<%--              답변--%>
<%--            </div>--%>
<%--            <div class="comment-section">--%>
<%--              <div class="comment">--%>
<%--                <div class="comment-author-date">--%>
<%--                  <div class="comment-author">선생님 이름</div>--%>
<%--                  <div class="comment-date">작성일</div>--%>
<%--                </div>--%>
<%--                <div class="comment-content">답글 내용.</div>--%>
<%--                <!-- 답글 부분 -->--%>
<%--                <div class="reply">--%>
<%--                  <div class="reply-author-date">--%>
<%--                    <div class="reply-author">답글 작성자 이름</div>--%>
<%--                    <div class="reply-date">작성일</div>--%>
<%--                  </div>--%>
<%--                  <div class="reply-content">질문 내용.</div>--%>
<%--                </div>--%>
<%--                <div class="reply">--%>
<%--                  <div class="reply-author-date">--%>
<%--                    <div class="reply-author">답글 작성자 이름</div>--%>
<%--                    <div class="reply-date">작성일</div>--%>
<%--                  </div>--%>
<%--                  <div class="reply-content">질문 내용.</div>--%>
<%--                </div>--%>
<%--              </div>--%>
<%--              <div class="comment">--%>
<%--                <div class="comment-author-date">--%>
<%--                  <div class="comment-author">선생님 이름</div>--%>
<%--                  <div class="comment-date">작성일</div>--%>
<%--                </div>--%>
<%--                <div class="comment-content">답글 내용.</div>--%>
<%--                <!-- 답글 부분 -->--%>
<%--                <div class="reply">--%>
<%--                  <div class="reply-author-date">--%>
<%--                    <div class="reply-author">답글 작성자 이름</div>--%>
<%--                    <div class="reply-date">작성일</div>--%>
<%--                  </div>--%>
<%--                  <div class="reply-content">질문 내용.</div>--%>
<%--                </div>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--            <!-- 답글 입력 및 작성 버튼 -->--%>
<%--            <div style="display: flex; flex-direction: column; align-items: flex-end; margin-top: 10px;">--%>
<%--              <!-- 답글 입력 칸 -->--%>
<%--              <textarea id="commentInput" class="comment" rows="4" cols="50" placeholder="답글을 입력하세요." style="width: 100%;"></textarea>--%>
<%--              <!-- 답글 작성 버튼 -->--%>
<%--              <button type="button" style="border: 1px solid; margin-top: 10px;" class="btn btn-outline-success" onclick="submitComment()">--%>
<%--                답글 작성--%>
<%--              </button>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--        </div>--%>
<%--      </div>--%>


      <%--권한 있을때--%>
            <div style="margin: 5% 10% 3% 10%;" class="row">
              <div class="card-body">
                <!-- 답글 내용 표시 부분: 리스트 형식으로 표시 -->
                <div class="user-comments">
                  <div class="comment-header">
                    답변
                  </div>
                  <div class="comment-section">
                    <div class="comment">
                      <div class="comment-author-date">
                        <div class="comment-author">홍길동</div>
                        <div class="comment-date">2024/08/02</div>
                      </div>
                      <div class="comment-content">금방 알려드리겠습니다.</div>
                      <!-- 답글 부분 -->
                      <div class="reply">
                        <div class="reply-author-date">
                          <div class="reply-author">남길동</div>
                          <div class="reply-date">2024/08/02</div>
                        </div>
                        <div class="reply-content">감사합니다.</div>
                      </div>
                      <div class="reply">
                        <div class="reply-author-date">
                          <div class="reply-author">홍길동</div>
                          <div class="reply-date">2024/08/02</div>
                        </div>
                        <div class="reply-content">네^^</div>
                      </div>
                    </div>
                    <div class="comment">
                      <div class="comment-author-date">
                        <div class="comment-author">홍길동</div>
                        <div class="comment-date">2024/08/02</div>
                      </div>
                      <div class="comment-content">오늘이라고 하네요!!!</div>
                      <!-- 답글 부분 -->
                      <div class="reply">
                        <div class="reply-author-date">
                          <div class="reply-author">남길동</div>
                          <div class="reply-date">2024/08/02</div>
                        </div>
                        <div class="reply-content">참고하겠습니다. 감사합니다^^</div>
                      </div>
                    </div>
                  </div>

                  <!-- 답글 입력 및 작성 버튼 -->
                  <c:choose>
                    <c:when test="${empty user}">

                    </c:when>

                   <c:otherwise>
                    <div style="display: flex; flex-direction: column; align-items: flex-end; margin-top: 10px;">
                      <!-- 답글 입력 칸 -->
                      <textarea id="commentInput" rows="4" cols="50" placeholder="답글을 입력하세요..." style="width: 100%;"></textarea>
                      <!-- 답글 작성 버튼 -->
                      <button type="button" style="border: 1px solid; margin-top: 10px;" class="btn btn-outline-success" onclick="submitComment()">
                        답글 작성
                      </button>
                    </div>
                    </c:otherwise>


                  </c:choose>

                </div>
              </div>
            </div>

    <c:choose>

        <c:when test="${empty user}">

        </c:when>

        <c:when test="${user_role eq 'student'}">

        </c:when>

        <c:when test="${user_role eq 'teacher'}">
          <button type="button" style="margin: 1em; float:right;" class="btn btn-outline-success">
            <a href="post_updateForm.do?post_id=${post.id}&post_subject=${post.subject}&post_content=${post.content}&refull_name=${refull_name}" class="button">수정</a>
          </button>
       </c:when>

      </c:choose>



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