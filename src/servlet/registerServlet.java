package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("registerServlet Ing");
		
		
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		
		
		
		String errorMsg = "";
		Connection conn = null;
        try {
            //連結資料庫
            conn = db.DbConnection.getConnection();
            
        } catch ( Exception e ) {
        	errorMsg = "Get DataSource or Connection error: " + e.toString();
        	System.out.println(errorMsg);
        	request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	//stmt = conn.createStatement();
        	
        	
        	String qsql = "INSERT INTO member(Name, Account, Password , Phone, Email)"
        			+ "VALUES(?,?,?,?,?)";
        	
        	stmt = conn.prepareStatement(qsql);
        	stmt.clearParameters();
        	stmt.setString(1,name);
        	stmt.setString(2,account);
        	stmt.setString(3,password);
        	stmt.setString(4,phone);
        	stmt.setString(5,email);
        	
        	int updateRow = stmt.executeUpdate();
			
			if ( updateRow <= 0 ) {
				try {
					conn.rollback();
					errorMsg = "Insert into table fail.";
					System.out.println(errorMsg);
				}catch(Exception backerr){
					System.out.println("rollback faild!");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
			}
        	
        	
        } catch ( Exception sqle ) {
            errorMsg = "find from table error: " + sqle.toString();
            System.out.println(errorMsg);
        	request.getRequestDispatcher("register.jsp").forward(request, response);
        } finally {
            try
            {
                if ( rs != null ) rs.close();
                if ( stmt != null ) stmt.close();
                conn.close();
            } catch ( SQLException se ) {
                errorMsg = "close ResultSet or Statment or connection error: " + se.toString();
                System.out.println(errorMsg);
            	request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
        
        //request.getRequestDispatcher("signin.jsp").forward(request, response);
     // sendRedirect視為一個重新的請求,所以request傳進來的參數是不能共享的 (網址列會顯示index.jsp)
        response.sendRedirect("signin.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
