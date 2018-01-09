package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buyer.*;

/**
 * Servlet implementation class addShoppingCartServlet
 */
@WebServlet("/addShoppingCartServlet")
public class addShoppingCartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("addShoppingCartServlet Ing");
		
		String userId = (String)request.getSession().getAttribute("userId");
		int auctionId = Integer.parseInt(request.getParameter("auctionId"));
		String auctionName = request.getParameter("auctionName");
		int productPrice = Integer.parseInt(request.getParameter("productPrice"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String sellerId = request.getParameter("sellerId");
		
		if( buyer.addShoppingCart(userId, auctionId, auctionName, amount, productPrice, sellerId) ){
			response.sendRedirect("singleAuctionProduct.jsp?auctionId=" + auctionId);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
