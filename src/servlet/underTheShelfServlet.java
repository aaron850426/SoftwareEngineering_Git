package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.*;

/**
 * Servlet implementation class underTheShelfServlet
 */
@WebServlet("/underTheShelfServlet")
public class underTheShelfServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("underTheShelfServlet Ing");
		
		String category = request.getParameter("category");
		String []underTheShelf = request.getParameterValues("checkBox");
		
		for( int i = 0; i < underTheShelf.length; i++){
			if(category.equals("auction")){
				seller.underTheShelf_auction( Integer.parseInt(underTheShelf[i]) );
			}
			else if(category.equals("bid")){
				seller.underTheShelf_bid( Integer.parseInt(underTheShelf[i]) );
			}
		}
		
		if(category.equals("auction")){
			response.sendRedirect("auctionProductOverview_seller.jsp");
		}
		else if(category.equals("bid")){
			response.sendRedirect("biddingProductOverview_seller.jsp");
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
