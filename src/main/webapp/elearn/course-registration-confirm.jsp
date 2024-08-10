<%@ page contentType="text/html;charset=utf-8" %>

<%
    boolean isSuccess = (boolean) request.getAttribute("isSuccess");
%>

<script>
    if (<%= isSuccess %>) {
        alert('강좌 등록에 성공했습니다.');
    } else {
        alert('강좌 등록에 실패했습니다. 다시 시도해 주세요.');
    }
    location.href = '/elearn/course/courses.do'
</script>