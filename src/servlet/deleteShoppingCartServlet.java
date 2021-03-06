package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class deleteShoppingCartServlet
 */
@WebServlet("/deleteShoppingCartServlet")
public class deleteShoppingCartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("deleteShoppingCartServlet Ing");
		
		String deleteUserId = request.getParameter("deleteUserId");
		int deleteShoppingCartId = Integer.parseInt(request.getParameter("deleteShoppingCartId"));
		
		
		String errorMsg;
		Connection conn = null;
        try {
            //�s����Ʈw
            conn = db.DbConnection.getConnection();
        } catch ( Exception e ) {
            errorMsg = "Get DataSource or Connection error: " + e.toString();
            System.out.println(errorMsg);
            response.sendRedirect("shoppingCart.jsp?userId=" + deleteUserId);
        }

        
        PreparedStatement stmt = null;
        
        try {
        	//stmt = conn.createStatement();
        	
        	
        	String dsql = "delete from shoppingcart where ShoppingCartId = ?";
        	
        	stmt = conn.prepareStatement(dsql);
        	stmt.clearParameters();
        	
        	stmt.setInt(1,deleteShoppingCartId);
			
        	int updateRow = stmt.executeUpdate();
			if ( updateRow < 0 ) {
				try {
					conn.rollback();
					errorMsg = "Delete table fail.";
					System.out.println(errorMsg);
				}catch(Exception backerr){
					System.out.println("rollback faild!");
				}
			}
        	
        	
        } catch ( Exception sqle ) {
        	errorMsg = "delete data error: " + sqle.toString();
            System.out.println("errorMsg="+errorMsg);
        } finally {
            try
            {
                if ( stmt != null ) stmt.close();
                conn.close();
            } catch ( SQLException se ) {
            	errorMsg = "close Statment or connection error: " + se.toString();
                System.out.println(errorMsg);
                //response.sendRedirect("member.jsp");
            }
        }
        
        response.sendRedirect("shoppingCart.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
