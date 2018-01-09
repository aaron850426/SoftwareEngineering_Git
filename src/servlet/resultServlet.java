package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.seller;

/**
 * Servlet implementation class resultServlet
 */
@WebServlet("/resultServlet")
public class resultServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("resultServlet Ing");
		
		String []result = request.getParameterValues("checkBox");
		
		for( int i = 0; i < result.length; i++){
			seller.modifyStatus( Integer.parseInt( result[i] ) );
			seller.biddingProductAddOrder( Integer.parseInt( result[i] ) );
		}
		
		response.sendRedirect("biddingProductOverview_seller.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
