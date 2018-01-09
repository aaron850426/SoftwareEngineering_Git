package discount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
import product.*;

public class discount {
	public static ArrayList<discountItem> getInfoDiscount(String userId){
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
        ResultSet rs = null;
        try {
        	String mSql = "select * from discount where UserId = ? order by Status, CouponId";
        	stmt = conn.prepareStatement(mSql);
        	stmt.clearParameters();
        	stmt.setString( 1, userId );
        	
        	System.out.println( mSql );
        	
        	rs = stmt.executeQuery();
        	rs.last(); // 為了抓數量
            int rowCnt = rs.getRow();

            
            ArrayList<discountItem> result = new ArrayList<discountItem>();
            if ( rowCnt > 0 ) {
            	
            	rs.absolute(1); // 記得加
            	
                for ( int i = 0; i < rowCnt; i++ ) {
                	discountItem tmpQuery = new discountItem();
                	
                    tmpQuery.setDiscountId(rs.getInt("DiscountId"));
                    tmpQuery.setCouponId(rs.getInt("CouponId"));
                    tmpQuery.setStatus(rs.getString("Status"));
                    tmpQuery.setUserId(rs.getString("UserId"));

                    result.add( tmpQuery );
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
	
	public static couponItem getInfoCoupon(int couponId){
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
        ResultSet rs = null;
        try {
        	String mSql = "select * from coupon where CouponId = ?";
        	stmt = conn.prepareStatement(mSql);
        	stmt.clearParameters();
        	stmt.setInt( 1, couponId );
        	
        	System.out.println( mSql );
        	
        	rs = stmt.executeQuery();
            rs.first();
            
        	couponItem tmpQuery = new couponItem();     	
            tmpQuery.setCouponId(rs.getInt("CouponId"));
            tmpQuery.setName(rs.getString("Name"));
            tmpQuery.setDiscountPrice(rs.getFloat("DiscountPrice"));
            
            return tmpQuery;
            
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
	
	public static String luckyDraw( String userId ){
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
        try {
        	String mSql = "select * from coupon order by CouponId";
        	stmt = conn.prepareStatement(mSql);
        	stmt.clearParameters();
        	
        	System.out.println( mSql );
        	rs = stmt.executeQuery();
            rs.last();
            int rowCnt = rs.getRow();
            int result = 0;
            
            // 亂數取優惠券
            if ( rowCnt > 0 ) {
            	Random random = new Random();
            	result = random.nextInt(rowCnt+1);
            	
            	rs.absolute(result);
            	
            	String sql2 = "INSERT INTO discount(UserId, CouponId)"
            			+ "VALUES(?,?)";
            	stmt2 = conn.prepareStatement(sql2);
            	stmt2.clearParameters();
            	stmt2.setString(1,userId);
            	stmt2.setInt(2,rs.getInt("CouponId"));
            	System.out.println( sql2 );
            	
            	int updateRow = stmt2.executeUpdate();
    			
    			if ( updateRow <= 0 ) {
    				try {
    					conn.rollback();
    					errorMsg = "Insert into table fail.";
    					System.out.println(errorMsg);
    				}catch(Exception backerr){
    					System.out.println("rollback faild!");
    				}
    			}
    			
    			return rs.getString( "Name" );
            	
            }
            
            
            return null;
            
        } catch ( Exception sqle ) {
            errorMsg = "find from table error: " + sqle.toString();
            System.out.println(errorMsg);
        } finally {
            try
            {
                if ( rs != null ) rs.close();
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
	
	public static boolean useDiscount(int discountId) {
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
        
        try {
        	//stmt = conn.createStatement();
        	
        	
        	String msql = "update discount set Status = ? " + 
        					"where discountId = ?";
        	
        	stmt = conn.prepareStatement(msql);
        	
        	stmt.setString(1, "Used" );
        	stmt.setInt(2, discountId );
			
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
			
			return true;
        	
        	
        } catch ( Exception sqle ) {
            errorMsg = "find from table error: " + sqle.toString();
            System.out.println(errorMsg);
        } finally {
            try
            {
                if ( stmt != null ) stmt.close();
                conn.close();
            } catch ( SQLException se ) {
                errorMsg = "close ResultSet or Statment or connection error: " + se.toString();
                System.out.println(errorMsg);
            }
        }
		
		return false;
	}
}