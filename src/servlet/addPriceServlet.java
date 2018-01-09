package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.*;

/**
 * Servlet implementation class addPriceServlet
 */
@WebServlet("/addPriceServlet")
public class addPriceServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("addPriceServlet Ing");
		
		String userAccount = (String)request.getSession().getAttribute("userId");
		int bidId = Integer.parseInt(request.getParameter("bidId"));
		int priceNow = Integer.parseInt(request.getParameter("priceNow"));
		int priceIncrease = Integer.parseInt(request.getParameter("priceIncrease"));
		int priceMax = Integer.parseInt(request.getParameter("priceMax"));
		
		if( seller.addPrice(bidId, userAccount, priceNow, priceIncrease) ){
			if( (priceNow+priceIncrease) >= priceMax ) {
				seller.modifyStatus(bidId);
				seller.biddingProductAddOrder( bidId );
			}
			response.sendRedirect("singleBiddingProduct.jsp?bidId=" + bidId);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
