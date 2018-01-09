<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="member.*" %>

<%
	String userId = (String)request.getParameter("userId");
	
	

	int evaluation_user[] = member.memberData.getEvaluation_user(userId);
	int evaluation_product[] = member.memberData.getEvaluation_product(userId);
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AS拍</title>

<%@ include file="include/css_js.jsp" %>

</head>
<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />

    <!-- 內容區 -->
    <div id="signin_content" class="content">
        <div class="topic"><%=userId + "'s Profile" %></div>
        <div class="content_container text_center">
        	 <p>賣家評價</p>
             <p>５★－－＞<%=evaluation_user[4]%></p>
             <p>４★－－＞<%=evaluation_user[3]%></p>
             <p>３★－－＞<%=evaluation_user[2]%></p>
             <p>２★－－＞<%=evaluation_user[1]%></p>
             <p>１★－－＞<%=evaluation_user[0]%></p>
             <p>商品評價</p>
             <p>５★－－＞<%=evaluation_product[4]%></p>
             <p>４★－－＞<%=evaluation_product[3]%></p>
             <p>３★－－＞<%=evaluation_product[2]%></p>
             <p>２★－－＞<%=evaluation_product[1]%></p>
             <p>１★－－＞<%=evaluation_product[0]%></p>
        </div>
    </div>





    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>