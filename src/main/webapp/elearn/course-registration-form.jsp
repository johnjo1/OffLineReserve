<%@ page contentType="text/html; charset=utf-8"
         import="javax.sql.DataSource, java.sql.Connection" %>
<%@ page import="org.chulgang.hrd.course.dto.GetSubjectsResponse" %>
<%@ page import="static org.chulgang.hrd.course.util.RequestConstant.GET_SUBJECTS_ATTRIBUTE_NAME" %>
<%@ page import="org.chulgang.hrd.classroom.dto.GetClassroomsResponse" %>
<%@ page import="static org.chulgang.hrd.course.util.RequestConstant.GET_CLASSROOMS_ATTRIBUTE_NAME" %>
<%
    GetSubjectsResponse getSubjectsResponse = (GetSubjectsResponse) request.getAttribute(GET_SUBJECTS_ATTRIBUTE_NAME);
    GetClassroomsResponse getClassroomsResponse
            = (GetClassroomsResponse) request.getAttribute(GET_CLASSROOMS_ATTRIBUTE_NAME);
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
    <title>eLearn - Online Education Learning Template</title>

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
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="//code.jquery.com/jquery-latest.min.js"></script>

    <!-- Flatpickr CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">

    <!-- Flatpickr JS -->
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
</head>

<body>

<!-- PAGE LOADING
================================================== -->
<div id="preloader"></div>

<!-- MAIN WRAPPER
================================================== -->
<div class="main-wrapper">

    <%@include file="header.jsp" %>
    <!-- HEADER
    ================================================== -->

    <!-- PAGE TITLE
    ================================================== -->
    <section class="page-title-section bg-img cover-background top-position1 left-overlay-dark" data-overlay-dark="9">
        <div class="container">
            <div class="row text-center">
                <div class="col-md-12">
                    <h1>강좌 개설 예약</h1>
                </div>
                <div class="col-md-12">
                    <ul>
                        <li><a href="index.jsp">처음으로 </a></li>
                        <li><a href="#!">Contact</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <!-- QUICK CONTACT
    ================================================== -->


    <!-- CONTACT FORM
    ================================================== -->
    <section class="bg-very-light-gray py-md-8 pb-lg-0">
        <div class="container">

            <div class="col-lg-6" style="margin: 0 auto;">
                <div class="faq-form">
                    <h2 class="mb-4 text-primary">강좌 개설 예약</h2>
                    <form class="course-registration" name="f" action="/elearn/registration.do" method="post">
                        <div class="quform-elements">
                            <br class="row">

                            <div class="col-md-12">
                                <div class="quform-element form-group">
                                    <label for="subject">과목 선택</label>
                                    <div class="quform-input">
                                        <select class="form-control" id="subject" name="subjectId">
                                            <option value="">과목을 선택해 주세요</option>
                                            <%
                                                for (int i = 0; i < getSubjectsResponse.size(); i++) {
                                                    Long subjectId = getSubjectsResponse.get(i).getId(); // 과목 ID 가져오기
                                                    String subjectName = getSubjectsResponse.get(i).getName(); // 과목 이름 가져오기
                                            %>
                                            <option value="<%= subjectId %>"><%= subjectName %>
                                            </option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="quform-element form-group">
                                    <label for="courseName">강좌명</label>
                                    <div class="quform-input">

                                        <input class="form-control" id="courseName" type="text" name="courseName"
                                               placeholder="강좌명"/>
                                        <div class="quform-submit-inner">
                                            <button class="butn white my-1" id="courseName2" type="button"
                                                    onclick="duplicate()"><i
                                                    class="far fa-paper-plane icon-arrow before"></i><span
                                                    class="label">중복확인</span><i
                                                    class="far fa-paper-plane icon-arrow after"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Begin Text input element -->
                            <div class="col-md-12">
                                <div class="quform-element form-group">
                                    <label for="course-description">강좌 설명</label>
                                    <div class="quform-input">
                                        <input class="form-control" id="course-description" type="text"
                                               name="course-description"
                                               placeholder="강좌 설명"/>
                                    </div>
                                </div>
                            </div>
                            <!-- End Text input element -->

                            <!-- Begin Text input element -->
                            <div class="col-md-12">
                                <div class="quform-element form-group">
                                    <label for="price">가격</label>
                                    <div class="quform-input">
                                        <input class="form-control" id="price" type="text" name="price"
                                               placeholder="가격"/>
                                    </div>
                                </div>
                            </div>
                            <!-- End Text input element -->

                            <!-- Begin Text input element -->
                            <div class="col-md-12">
                                <div class="quform-element form-group">
                                    <label for="start-date">개강일</label>
                                    <div class="quform-input">
                                        <input class="form-control" id="start-date" type="text" name="start-date"
                                               placeholder="개강일을 선택해 주세요"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="quform-element form-group">
                                    <label for="last-date">종강일</label>
                                    <div class="quform-input">
                                        <input class="form-control" id="last-date" type="text" name="last-date"
                                               placeholder="종강일을 선택해 주세요"/>
                                    </div>
                                </div>
                            </div>
                            <!-- End Text input element -->

                            <!-- Begin Captcha element -->
                            <div class="col-md-12">
                                <div class="quform-element form-group">
                                    <label for="classroom">강의실 선택</label>
                                    <div class="quform-input">
                                        <select class="form-control" id="classroom" name="classroom">
                                            <option value="">강의실을 선택해 주세요</option>
                                            <%
                                                for (int i = 0; i < getClassroomsResponse.size(); i++) {
                                            %>
                                            <option value="<%= getClassroomsResponse.get(i).getId() %>"
                                                    data-seatcount="<%= getClassroomsResponse.get(i).getSeatCount() %>">
                                                <%= getClassroomsResponse.get(i).getName() %>
                                            </option>
                                            <%
                                                }
                                            %>
                                        </select>
                                        <input type="hidden" id="seatCount" name="seatCount" value="">
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="quform-element form-group">
                                    <label for="time-period">시간대 선택</label>
                                    <div class="quform-input">
                                        <select class="form-control" id="time-period" name="time-period">
                                            <option value="">시간대를 선택해 주세요</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <!-- End Captcha element -->

                            <!-- Begin Submit button -->
                            <div class="col-md-12">
                                <div class="quform-submit-inner">
                                    <button class="butn secondary" type="button" onclick="validate()"><i
                                            class="far fa-paper-plane icon-arrow before"></i><span
                                            class="label">전송</span><i class="far fa-paper-plane icon-arrow after"></i>
                                    </button>
                                </div>

                                <div class="quform-loading-wrap text-start"><span class="quform-loading"></span></div>
                            </div>
                            <!-- End Submit button -->
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </section>

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
<script>
    function duplicate() {
        const courseName = f.courseName.value.trim();
        if (courseName.length === 0) {
            alert('강좌명을 입력해 주세요.');
            f.courseName.value = '';
            f.courseName.focus();
            return;
        }

        $.ajax({
            type: "POST",
            dataType: 'json',
            url: "/elearn/course/validation.do",
            data: {courseName: courseName},

            success: function (data) {
                f.courseName.isDuplicateChecked = true;
                if (data.isDuplicateName === 'true') {
                    alert('존재하는 강좌명입니다.');
                    f.courseName.isDuplicated = true;
                } else {
                    alert('사용할 수 있는 강좌명입니다.');
                    f.courseName.isDuplicated = false;
                }
            },
            error: function () {
                console.log('요청에 실패했습니다. 다시 시도해 주세요.');
                f.courseName.isDuplicateChecked = false;
                f.courseName.isDuplicated = false;
            }
        });
    }

    $(document).ready(function () {
        $('#classroom').change(function () {
            var selectedSeatCount = $(this).find('option:selected').data('seatcount');
            $('#seatCount').val(selectedSeatCount);

            var selectedClassroomId = $(this).val();
            var startDate = $('#start-date').val();
            var lastDate = $('#last-date').val();

            if (selectedClassroomId) {
                $.ajax({
                    type: 'GET',
                    dataType: 'json',
                    url: '/elearn/get-periods.do',
                    data: {
                        classroomId: selectedClassroomId,
                        'start-date': startDate,
                        'last-date': lastDate
                    },
                    success: function (response) {
                        var options = '<option value="">시간대를 선택해 주세요</option>';
                        if (response.length > 0) {
                            $.each(response, function (index, timePeriod) {
                                options += '<option value="' + timePeriod.id + '">' + timePeriod.description + '</option>';
                            });
                        }
                        $('#time-period').html(options);
                    },
                    error: function () {
                        alert('시간대 목록을 가져오는 데 실패했습니다.');
                    }
                });
            } else {
                $('#time-period').html('<option value="">시간대를 선택해 주세요</option>');
            }
        });
    });
</script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var startDatePicker = flatpickr("#start-date", {
            dateFormat: "Y-m-d",
            minDate: "today",
            onChange: function (selectedDates, dateStr, instance) {
                endDatePicker.set('minDate', dateStr);
            }
        });

        var endDatePicker = flatpickr("#last-date", {
            dateFormat: "Y-m-d",
            onChange: function (selectedDates, dateStr, instance) {
                startDatePicker.set('maxDate', dateStr);
            }
        });
    });
</script>
<script>
    function validate() {
        const subject = f.subject.value.trim();
        const courseName = f.courseName.value.trim();
        const courseDescription = f['course-description'].value.trim();
        const price = f.price.value.trim();
        const startDate = f['start-date'].value.trim();
        const lastDate = f['last-date'].value.trim();
        const classroom = f.classroom.value.trim();
        const timePeriod = f['time-period'].value.trim();

        if (subject.length === 0) {
            alert('과목을 입력해 주세요.');
            f.subject.value = '';
            f.subject.focus();
            return;
        }

        if (courseName.length === 0) {
            alert('강좌명을 입력해 주세요.');
            f.courseName.value = '';
            f.courseName.focus();
            return;
        }

        if (!f.courseName.isDuplicateChecked) {
            alert('강좌명 중복 확인을 해주세요.');
            return;
        }

        if (f.courseName.isDuplicated) {
            alert('중복되는 강좌명을 입력했습니다. 다른 강좌명을 선택해 주세요.');
            return;
        }

        if (courseDescription.length === 0) {
            alert('강좌 설명을 입력해 주세요.');
            f['course-description'].value = '';
            f['course-description'].focus();
            return;
        }

        if (price.length === 0) {
            alert('가격을 입력해 주세요.');
            f.price.value = '';
            f.price.focus();
            return;
        }

        if (isNaN(price)) {
            alert('가격은 숫자만 입력할 수 있습니다.');
            f.price.value = '';
            f.price.focus();
            return;
        }

        if (startDate.length === 0) {
            alert('개강일을 선택해 주세요.');
            f['start-date'].value = '';
            f['start-date'].focus();
            return;
        }

        if (lastDate.length === 0) {
            alert('종강일을 선택해 주세요.');
            f['last-date'].value = '';
            f['last-date'].focus();
            return;
        }

        if (classroom.length === 0) {
            alert('강의실을 선택해 주세요.');
            f.classroom.value = '';
            f.classroom.focus();
            return;
        }

        if (timePeriod.length === 0) {
            alert('시간대를 선택해 주세요.');
            f['time-period'].value = '';
            f['time-period'].focus();
            return;
        }

        const seatCount = $('#seatCount').val();

        f.submit();
    }
</script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="//code.jquery.com/jquery-latest.min.js"></script>

</body>

</html>
