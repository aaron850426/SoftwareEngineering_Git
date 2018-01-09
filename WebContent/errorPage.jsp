<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>錯誤與例外處理頁面</title>
</head>
<body>
	錯誤碼： <%=request.getAttribute("javax.servlet.error.status_code")%> <br>
    訊息： <%=request.getAttribute("javax.servlet.error.message")%> <br>
    例外： <%=request.getAttribute("javax.servlet.error.exception_type")%> <br>
</body>
</html>
