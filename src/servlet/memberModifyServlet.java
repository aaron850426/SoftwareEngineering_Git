package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class memberModifyServlet
 */
@WebServlet("/memberModifyServlet")
public class memberModifyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("memberModifyServlet Ing");
		
		
		String name = request.getParameter("name");
		String account = (String)request.getSession().getAttribute("userId");
		String password_old = request.getParameter("password_old");
		String password_new = request.getParameter("password_new");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		
		String errorMsg = "";
		Connection conn = null;
        try {
            //�s����Ʈw
            conn = db.DbConnection.getConnection();
            
        } catch ( Exception e ) {
        	errorMsg = "Get DataSource or Connection error: " + e.toString();
        	System.out.println(errorMsg);
        	response.sendRedirect("member.jsp");
        }
        
        
        PreparedStatement stmt = null;
        
        try {
        	//stmt = conn.createStatement();
        	
        	
        	String msql = "update member set Password = ?, Name = ?, Phone = ?, Email = ?" + 
        					"where Account = ? and Password = ?";
        	
        	stmt = conn.prepareStatement(msql);
        	
        	stmt.setString(1,password_new);
        	stmt.setString(2,name);
        	stmt.setString(3,phone);
        	stmt.setString(4,email);
        	stmt.setString(5,account);
        	stmt.setString(6,password_old);
			
        	int updateRow = stmt.executeUpdate();
			if ( updateRow < 0 ) {
				try {
					conn.rollback();
					errorMsg = "Update table fail.";
					System.out.println(errorMsg);
				}catch(Exception backerr){
					System.out.println("rollback faild!");
				}
			}
        	
        	
        } catch ( Exception sqle ) {
            errorMsg = "find from table error: " + sqle.toString();
            System.out.println(errorMsg);
            response.sendRedirect("member.jsp");
        } finally {
            try
            {
                if ( stmt != null ) stmt.close();
                conn.close();
            } catch ( SQLException se ) {
                errorMsg = "close ResultSet or Statment or connection error: " + se.toString();
                System.out.println(errorMsg);
                response.sendRedirect("member.jsp");
            }
        }
        
        response.sendRedirect("member.jsp");
        
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
