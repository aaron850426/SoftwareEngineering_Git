<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.*" %>
<%@ page import="buyer.*" %>
<%@ page import="java.util.*" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AS拍</title>

<%@ include file="include/css_js.jsp" %>
<%
	String userId = (String)session.getAttribute("userId");
	
	ArrayList<evaluationItem> evaluationItems_Seller = buyer.getInfoEvaluationSeller(userId);
	System.out.println("evaluationItems_Seller: " + evaluationItems_Seller.size());
	ArrayList<evaluationItem> evaluationItems_Product = buyer.getInfoEvaluationProduct(userId);
	//System.out.println("evaluationItems_Product: " + evaluationItems_Product.size());
%>
	<script>

        function checkForm1(form)
        {
            // 判斷checkbox有沒有被勾選
            if($("form[name='evaluationForm_seller'] input[name='check']:checked").length == 0){
                alert("Please choice the CheckBox.")
                document.location.href="evaluation.jsp";
            }
            else{
                form.action = "evaluationServlet";
                form.submit();
            }

        }
        function checkForm2(form)
        {
            // 判斷checkbox有沒有被勾選
            if($("form[name='evaluationForm_product'] input[name='check']:checked").length == 0){
                alert("Please choice the CheckBox.")
                document.location.href="evaluation.jsp";
            }
            else{
            	form.action = "evaluationServlet";
                form.submit();
            }

        }

    </script>
	
</head>
<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />

    <!-- 內容區 -->
    <div id="evaluation_content" class="content">
	    <div class="topic">評價系統</div>
	    <div class="content_container">
	            <nav class="nav nav-tabs" id="myTab" role="tablist">
	                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">評價賣家</a>
	                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">評價商品</a>
	            </nav>
	            <div class="tab-content" id="nav-tabContent" style="margin-top: 1rem;">
	                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
	                    <!-------- 評價賣家 -------->
	                    <form id="evaluationForm_seller" name="evaluationForm_seller" class="center" onsubmit="checkForm1(this);">
	                    
	                    	<input type="hidden" name="category" value="seller">
	                    
	                        <div class="content_list center">
	                            <!-- 標題class用list_head，商品用list_list -->
	                            <div class="css_tr list_head">
	                                <div class="css_hd list_number"></div>
	                                <div class="css_hd list_total">選擇評價</div>
	                                <div class="css_hd list_title">賣家暱稱</div>
	                                <div class="css_hd list_status">狀態</div>
	                                <!-- 已評價、未評價 -->
	                            </div>
	                            <%for( int i = 0; i < evaluationItems_Seller.size(); i++) {
	                            	evaluationItem item = evaluationItems_Seller.get(i);
	                            %>
	                            <div class="css_tr list_list">
	                                <div class="css_hd list_number text_center vertical_middle">
	                                    <!-- value放評價編號 -->
	                                    <%if(item.getStatus().equals("UnEvaluated")){ %>
	                                   	 <input name='check' type="checkbox" value="<%=item.getEvaluationId()%>,<%=i%>">
	                                    <%} %>
	                                </div>
	                                <div class="css_hd list_total">
	                                	<%if(item.getStatus().equals("UnEvaluated")){ %>
		                                    <select name="evaluation">
		                                        <option value="1">1</option>
		                                        <option value="2">2</option>
		                                        <option value="3">3</option>
		                                        <option value="4">4</option>
		                                        <option value="5">5</option>
		                                     </select>
	                                     <%}else{
	                                    	 out.println(item.getEvaluation());
	                                     	}
	                                     %>
	                                </div>
	                                <div class="css_hd list_title"><a href="memberProfile.jsp?userId=<%=item.getId() %>" title="<%=item.getId() %>"><%=item.getId() %></a></div>
	                                <div class="css_hd list_status">
	                                <%
	                                	if(item.getStatus().equals("UnEvaluated")) 
	                                		out.println("未評價");
	                                	else
	                                		out.println("已評價");
	                                %>
	                                </div>
	                            </div>
	                            <%} %>
	                            
	                        </div>
	                        <div class="content_settlement center text_center">
	                            <div class="col-md-6 center">
	                                <button type="submit" class="btn btn-primary ">確認送出</button>
	                            </div>
	                        </div>
	                    </form>
	                </div>
	                <!-------- 評價商品 -------->
	                <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
	                    <form id="evaluationForm_product" name="evaluationForm_product" class="center" onsubmit="checkForm2(this);">
	                    
	                    	<input type="hidden" name="category" value="product">
	                    
	                        <div class="content_list center">
	                            <!-- 標題class用list_head，商品用list_list -->
	                            <div class="css_tr list_head">
	                                <div class="css_hd list_number"></div>
	                                <div class="css_hd list_total">選擇評價</div>
	                                <div class="css_hd list_title">商品名稱</div>
	                                <div class="css_hd list_status">狀態</div>
	                                <!-- 已評價、未評價 -->
	                            </div>
	                            <%for( int i = 0; i < evaluationItems_Product.size(); i++) {
	                            	evaluationItem item = evaluationItems_Product.get(i);
	                            %>
	                            <div class="css_tr list_list">
	                                <div class="css_hd list_number text_center vertical_middle">
	                                    <!-- value放評價編號 -->
	                                    <%if(item.getStatus().equals("UnEvaluated")){ %>
	                                    <input name='check' type="checkbox" value="<%=item.getEvaluationId()%>,<%=i%>">
	                                    <%} %>
	                                </div>
	                                <div class="css_hd list_total">
	                                    <%if(item.getStatus().equals("UnEvaluated")){ %>
		                                    <select name="evaluation">
		                                        <option value="1">1</option>
		                                        <option value="2">2</option>
		                                        <option value="3">3</option>
		                                        <option value="4">4</option>
		                                        <option value="5">5</option>
		                                     </select>
	                                     <%}else{
	                                    	 out.println(item.getEvaluation());
	                                     	}
	                                     %>
	                                </div>
	                                <div class="css_hd list_title"><a href="singleAuctionProduct.jsp?auctionId=<%=item.getId() %>" title="<%=item.getProductName() %>"><%=item.getProductName() %></a></div>
	                                <div class="css_hd list_status">
	                                <%
	                                	if(item.getStatus().equals("UnEvaluated")) 
	                                		out.println("未評價");
	                                	else
	                                		out.println("已評價");
	                                %>
	                                </div>
	                            </div>
	                            <%} %>
	                            
	                        </div>
	                        <div class="content_settlement center text_center">
	                            <div class="col-md-6 center">
	                                <button type="submit" class="btn btn-primary ">確認送出</button>
	                            </div>
	                        </div>
	                    </form>
	                </div>
	            </div>
	
	    </div>
	
	
	
	
	</div>





    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>