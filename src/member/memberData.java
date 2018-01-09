package member;

import java.sql.*;
import java.util.ArrayList;

import member.memberItem;

public class memberData {
	
	
	
	public static memberItem getMemberData( String userAccount ){
		String errorMsg = "";
		Connection conn = null;
		try {
		    //連結資料庫
		    conn = db.DbConnection.getConnection();
		    
		} catch ( Exception e ) {
			errorMsg = "Get DataSource or Connection error: " + e.toString();
			System.out.println(errorMsg);
		}
		
		memberItem result = new memberItem();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//stmt = conn.createStatement();
			
			String msql = "select * from member where Account = ?";
			
			stmt = conn.prepareStatement(msql);
			stmt.clearParameters();
			stmt.setString(1,userAccount);
			
			rs = stmt.executeQuery();
			
			if ( !rs.next() ) {
    			errorMsg = "No the data";
    			System.out.println(errorMsg);
    			return null;
    		}
			
			result.setAccount( rs.getString("Account") );
			result.setPassword( rs.getString("Password") );
			result.setName( rs.getString("Name") );
			result.setPhone( rs.getString("Phone") );
			result.setEmail( rs.getString("Email") );
			
			return result;
			
			
		} catch ( Exception sqle ) {
		    errorMsg = "find from table error: " + sqle.toString();
		    System.out.println(errorMsg);
		} finally {
		    try
		    {
		        if ( rs != null ) rs.close();
		        if ( stmt != null ) stmt.close();
		        conn.close();
		    } catch ( SQLException se ) {
		        errorMsg = "close ResultSet or Statment or connection error: " + se.toString();
		        System.out.println(errorMsg);
		    }
		}
		
		return null;
	}
	
	public static int[] getEvaluation_user(String userId){
		String errorMsg = "";
		Connection conn = null;
		try {
		    //連結資料庫
		    conn = db.DbConnection.getConnection();
		    
		} catch ( Exception e ) {
			errorMsg = "Get DataSource or Connection error: " + e.toString();
			System.out.println(errorMsg);
		}
		
		int []result = new int[5];
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//stmt = conn.createStatement();
			
			String msql = "select * from evaluation where Type = ? and Id = ? and Status = ? ";
			
			System.out.println(msql);
			
			stmt = conn.prepareStatement(msql);
			stmt.clearParameters();
			stmt.setString(1, "Seller" );
			stmt.setString(2, userId );
			stmt.setString(3, "Evaluated" );
			
			System.out.println(stmt);
			
			rs = stmt.executeQuery();
			rs.last(); // 為了抓數量
            int rowCnt = rs.getRow();
            
            if ( rowCnt > 0 ) {
            	
            	rs.absolute(1); // 記得加
            	
            	for ( int i = 0; i < rowCnt; i++ ) {
            		result[rs.getInt("evaluation")-1]++;
            		
            		rs.next();
            	}
            }
			
			return result;
			
			
		} catch ( Exception sqle ) {
		    errorMsg = "find from table error: " + sqle.toString();
		    System.out.println(errorMsg);
		} finally {
		    try
		    {
		        if ( rs != null ) rs.close();
		        if ( stmt != null ) stmt.close();
		        conn.close();
		    } catch ( SQLException se ) {
		        errorMsg = "close ResultSet or Statment or connection error: " + se.toString();
		        System.out.println(errorMsg);
		    }
		}
		
		return null;
	}
	
	public static int[] getEvaluation_product(String userId){
		String errorMsg = "";
		Connection conn = null;
		try {
		    //連結資料庫
		    conn = db.DbConnection.getConnection();
		    
		} catch ( Exception e ) {
			errorMsg = "Get DataSource or Connection error: " + e.toString();
			System.out.println(errorMsg);
		}
		
		
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			//stmt = conn.createStatement();
			
			String msql = "select * from auction where SellerId = ? ";
			
			System.out.println(msql);
			
			stmt = conn.prepareStatement(msql);
			stmt.clearParameters();
			stmt.setString(1, userId );
			
			rs = stmt.executeQuery();
			rs.last(); // 為了抓數量
            int rowCnt = rs.getRow();
            
            int []result = new int[5];
            if ( rowCnt > 0 ) {
            	
            	rs.absolute(1); // 記得加
            	
            	for ( int i = 0; i < rowCnt; i++ ) {
            		
                	stmt2 = null;
                	rs2 = null;
                	
                	String mSql2 = "select * from evaluation where Type = ? and Id = ? and Status = ?";
                	stmt2 = conn.prepareStatement(mSql2);
                	stmt2.clearParameters();
                	stmt2.setString( 1, "Auction" );
                	stmt2.setString( 2, Integer.toString(rs.getInt( "AuctionId" )) );
                	stmt2.setString( 3, "Evaluated " );
                	
                	System.out.println(mSql2);
                	rs2 = stmt2.executeQuery();
                	
                	rs2.last(); // 為了抓數量
                	int rowCnt2 = rs2.getRow();
                	
                	 if ( rowCnt2 > 0 ) {
                		 rs2.absolute(1); // 記得加
                		 
                		 for( int j = 0; j < rowCnt2;j++){
                			 result[rs2.getInt("evaluation")-1]++;
                			 
                			 rs2.next();
                		 }
                	 }
                	
            		rs.next();
            		
            	}
            }
			
			return result;
			
			
		} catch ( Exception sqle ) {
		    errorMsg = "find from table error: " + sqle.toString();
		    System.out.println(errorMsg);
		} finally {
		    try
		    {
		        if ( rs != null ) rs.close();
		        if ( rs2 != null ) rs2.close();
		        if ( stmt != null ) stmt.close();
		        if ( stmt2 != null ) stmt2.close();
		        conn.close();
		    } catch ( SQLException se ) {
		        errorMsg = "close ResultSet or Statment or connection error: " + se.toString();
		        System.out.println(errorMsg);
		    }
		}
		
		return null;
	}
	
	
}
