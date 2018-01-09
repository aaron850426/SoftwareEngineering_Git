<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<%@ include file="import/index_main_java.jsp" %>

	<title>AS拍</title>

	<%@ include file="include/css_js.jsp" %>

</head>
<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />
	
    <!-- 廣告輪播 -->
    <jsp:include page="include/banner.jsp" flush="true" />
    

    <!-- 內容區 -->
    <div id="index_content" class="content">

        <div class="topic">熱銷商品</div>
        <div class="content_container">
            <%@include file="import/index_hot.jsp" %>

        </div>


    </div>





    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>