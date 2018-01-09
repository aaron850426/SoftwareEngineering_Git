package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.*;
import buyer.*;

/**
 * Servlet implementation class cancelOrderServlet
 */
@WebServlet("/cancelOrderServlet")
public class cancelOrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("cancelOrderServlet Ing");
		
		String category = request.getParameter("category");
		String []cancelOrder = request.getParameterValues("checkbox");
		
		for( int i = 0; i < cancelOrder.length; i++){
			if(category.equals("seller")){
				seller.cancelOrder( Integer.parseInt(cancelOrder[i]) );
			}
			else if(category.equals("buyer")){
				buyer.cancelOrder( Integer.parseInt(cancelOrder[i]) );
			}
		}
		
		
		
		if(category.equals("seller")){
			response.sendRedirect("order_seller.jsp");
		}
		else if(category.equals("buyer")){
			response.sendRedirect("order_buyer.jsp");
		}
		else{
			response.sendRedirect("index.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
