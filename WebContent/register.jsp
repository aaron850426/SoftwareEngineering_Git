<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AS拍</title>

	<%@ include file="include/css_js.jsp" %>

</head>

<!-- 要記得寫jstl的error回傳(insert fail , 值重複) -->

<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />
	
	
	<script>
		function check(form) {
			if( password.value != password_check.value) {
				alert("密碼不一致");
				return;
			}else{
				form.action = "registerServlet";
				form.submit();
                
			}
		}
	</script>
	
    <!-- 內容區 -->
    <div id="register_content" class="content">
        <div class="topic">註冊</div>
        <div class="content_container">
            <form id="registerForm" name="registerForm" class="center" onsubmit="check(this)">
                <div class="form-group col-md-6">
                    <label class="col-form-label" for="name">姓名</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="Input your name" required>
                </div>
                <div class="form-group col-md-6">
                    <label class="col-form-label" for="account">帳號</label>
                    <input type="text" class="form-control" onkeyup="value=value.replace(/[\W]/g,'') " name="account" id="account" placeholder="Input your account" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="password">密碼</label>
                    <input type="password" class="form-control" onkeyup="value=value.replace(/[\W]/g,'') " name="password" id="password" placeholder="Password" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="password_check">確認密碼</label>
                    <input type="password" class="form-control" onkeyup="value=value.replace(/[\W]/g,'') " id="password_check" placeholder="Password" required>
                </div>
                <div class="form-group col-md-6">
                    <label class="col-form-label" for="phone">電話</label>
                    <input type="text" class="form-control" onkeyup="value=value.replace(/[^\d]/g,'') " name="phone" id="phone" placeholder="Input your phone" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="email">信箱</label>
                    <input type="email" class="form-control" name="email" id="email1" aria-describedby="emailHelp" placeholder="Enter email" required>
                </div>
                
                <div class="col-md-6 text_center">
                    <button type="submit" class="btn btn-primary ">確認送出</button>
                </div>
            </form>

        </div>




    </div>





    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>