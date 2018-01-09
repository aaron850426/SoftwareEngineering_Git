<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form id="hotForm" name="hotForm">
   		<!-- 存頁碼的(第1,2,3...頁) -->
   		<input type="hidden" name="page">
   		
        <!-- 商品區塊 -->
        <div class="content_container00">
        <%
        if( hotItems.size() > 0 ) {
        	for( int i = 0; i < hotItems.size(); i++) {
   				auctionItem item = hotItems.get(i);
   		%>
   				<div class="product">
        			<a href="singleAuctionProduct.jsp?auctionId=<%=item.getAuctionId()%>" title="<%=item.getName()%>">
		                <img src="./images/content-01.jpg">
		                <p>
		                    <span><%=item.getName()%></span><br>
		                    <span>NT.<%=item.getPrice()%></span><br>
		                    <span style="color: #464a4e;">已賣出數量: <%=item.getOriginalNumber() - item.getNumberOfProduct()%></span>
		                </p>
	                </a>
            	</div>
   		<%
        	}
        }
        %>
        </div>

        
		
        
	</form>