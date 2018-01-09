<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AS拍</title>

	<%@ include file="include/css_js.jsp" %>
	
	<script>
		function check(form) {
			if( password_old.value == password_new.value) {
				alert("不可設定相同密碼");
				return;
			}else{
				form.action = "memberModifyServlet";
				form.submit();
                
			}
		}
	</script>
	
</head>

<%@page import="java.sql.*" %>
<%@page import="member.*" %>
<%
	String account = (String)session.getAttribute("userId");

	memberItem member = memberData.getMemberData(account);
	
	
%>

<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />

    <!-- 內容區 -->
    <div id="register_content" class="content">
    <div class="topic">會員中心</div>
    <div class="content_container">
        <form id="memberForm" name="memberForm" class="center" onsubmit="check(this)">
			<div class="form-group col-md-6">
                <label class="col-form-label" for="name">姓名</label>
                <input type="text" class="form-control" name="name" id="name" value="<%=member.getName() %>" required>
            </div>
            <div class="form-group col-md-6">
                <label class="col-form-label" for="account">帳號</label>
                <input type="text" class="form-control" onkeyup="value=value.replace(/[\W]/g,'') " name="account" id="account" value="<%=member.getAccount() %>" disabled>
            </div>
            <div class="form-group col-md-6">
                <label for="password_old">舊密碼</label>
                <input type="password" class="form-control" onkeyup="value=value.replace(/[\W]/g,'') " name="password_old" id="password_old" required>
            </div>
            <div class="form-group col-md-6">
                <label for="password_new">新密碼</label>
                <input type="password" class="form-control" onkeyup="value=value.replace(/[\W]/g,'') " name="password_new" id="password_new"required>
            </div>
            <div class="form-group col-md-6">
                    <label class="col-form-label" for="phone">電話</label>
                    <input type="text" class="form-control" onkeyup="value=value.replace(/[^\d]/g,'') " name="phone" id="phone" value="<%=member.getPhone() %>" required>
                </div>
            <div class="form-group col-md-6">
                    <label for="email">信箱</label>
                    <input type="email" class="form-control" name="email" id="email1" aria-describedby="emailHelp" value="<%=member.getEmail() %>" required>
                </div>
            <div class="col-md-6 text_center">
                <button type="submit" class="btn btn-primary ">確認修改</button>
            </div>
        </form>

    </div>
</div>





    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>