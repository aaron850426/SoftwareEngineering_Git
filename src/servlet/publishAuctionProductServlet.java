package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.*;

/**
 * Servlet implementation class publishAuctionProductServlet
 */
@WebServlet("/publishAuctionProductServlet")
public class publishAuctionProductServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("publishAuctionProductServlet Ing");
		
		String sellerId = (String)request.getSession().getAttribute("userId");
		String title = request.getParameter("title");
		int price = Integer.parseInt(request.getParameter("price"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String category = request.getParameter("category");
		String pic = request.getParameter("pic");
		String description = request.getParameter("description");
		
		if( seller.publishAuctionProduct(sellerId, title, price, amount, category, pic, description) ) {
			response.sendRedirect("auctionProductOverview_seller.jsp");
		}
		else {
			response.sendRedirect("publishAuctionProduct.jsp");
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
