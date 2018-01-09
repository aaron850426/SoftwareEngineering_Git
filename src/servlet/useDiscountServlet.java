package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import discount.*;

/**
 * Servlet implementation class useDiscountServlet
 */
@WebServlet("/useDiscountServlet")
public class useDiscountServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("useDiscountServlet Ing");
		
		String userId = request.getParameter("userId");
		int discountId = Integer.parseInt( request.getParameter("discountId") );
		
		discount.useDiscount(discountId);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
