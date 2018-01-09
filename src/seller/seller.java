package seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
import product.*;

public class seller {
	
	
	public static boolean publishAuctionProduct(String sellerId, String title, int price, int amount, String category, String pic, String description) {
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
        	//stmt = conn.createStatement();
        	
        	
        	String qsql = "INSERT INTO auction(SellerId, Name, Price, OriginalNumber , NumberOfProduct, Category, PicturePath, Description)"
        			+ "VALUES(?,?,?,?,?,?,?,?)";
        	
        	stmt = conn.prepareStatement(qsql);
        	stmt.clearParameters();
        	stmt.setString(1,sellerId);
        	stmt.setString(2,title);
        	stmt.setInt(3,price);
        	stmt.setInt(4,amount);
        	stmt.setInt(5,amount);
        	stmt.setString(6,category);
        	stmt.setString(7,pic);
        	stmt.setString(8,description);
        	
        	int updateRow = stmt.executeUpdate();
			
			if ( updateRow <= 0 ) {
				try {
					conn.rollback();
					errorMsg = "Insert into table fail.";
					System.out.println(errorMsg);
					return false;
				}catch(Exception backerr){
					System.out.println("rollback faild!");
					return false;
				}
			}
        	
        	
        } catch ( Exception sqle ) {
            errorMsg = "find from table error: " + sqle.toString();
            System.out.println(errorMsg);
            return false;
        } finally {
            try
            {
                if ( rs != null ) rs.close();
                if ( stmt != null ) stmt.close();
                conn.close();
            } catch ( SQLException se ) {
                errorMsg = "close ResultSet or Statment or connection error: " + se.toString();
                System.out.println(errorMsg);
                return false;
            }
        }
		
		return true;
	}
	
	public static boolean publishBiddingProduct(String sellerId, String title, int priceMin, int priceMax, int priceIncrease, String endTime, String category, String pic, String description) {
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
        	//stmt = conn.createStatement();
        	
        	
        	String qsql = "INSERT INTO bid(SellerId, Name, PriceMin, PriceNow, PriceMax, PriceIncrease, EndTime, Category, PicturePath, Description)"
        			+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
        	
        	stmt = conn.prepareStatement(qsql);
        	stmt.clearParameters();
        	stmt.setString(1,sellerId);
        	stmt.setString(2,title);
        	stmt.setInt(3,priceMin);
        	stmt.setInt(4,priceMin);
        	stmt.setInt(5,priceMax);
        	stmt.setInt(6,priceIncrease);
        	stmt.setString(7,endTime);
        	stmt.setString(8,category);
        	stmt.setString(9,pic);
        	stmt.setString(10,description);
        	
        	int updateRow = stmt.executeUpdate();
			
			if ( updateRow <= 0 ) {
				try {
					conn.rollback();
					errorMsg = "Insert into table fail.";
					System.out.println(errorMsg);
					return false;
				}catch(Exception backerr){
					System.out.println("rollback faild!");
					return false;
				}
			}
        	
        	
        } catch ( Exception sqle ) {
            errorMsg = "find from table error: " + sqle.toString();
            System.out.println(errorMsg);
            return false;
        } finally {
            try
            {
                if ( rs != null ) rs.close();
                if ( stmt != null ) stmt.close();
                conn.close();
            } catch ( SQLException se ) {
                errorMsg = "close ResultSet or Statment or connection error: " + se.toString();
                System.out.println(errorMsg);
                return false;
            }
        }
		
		return true;
	}
	
	public static boolean addPrice(int bidId, String buyerId, int priceNow, int priceIncrease){
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
        	
        	
        	String msql = "update bid set PriceNow = ?, BuyerId = ? where BidId = ?";
        	
        	stmt = conn.prepareStatement(msql);
        	
        	stmt.setInt(1,priceNow+priceIncrease);
        	stmt.setString(2,buyerId);
        	stmt.setInt(3,bidId);
        	
			
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
	
	public static boolean underTheShelf_auction(int auctionId){
		String errorMsg = "";
		Connection conn = null;
        try {
            //連結資料庫
            conn = db.DbConnection.getConnection();
            //UnderTheShelf
        } catch ( Exception e ) {
        	errorMsg = "Get DataSource or Connection error: " + e.toString();
        	System.out.println(errorMsg);
        }
        
        
        PreparedStatement stmt = null;
        
        try {
        	
        	
        	String msql = "update auction set Status = ? where AuctionId = ?";
        	
        	stmt = conn.prepareStatement(msql);
        	
        	stmt.setString(1,"UnderTheShelf");
        	stmt.setInt(2,auctionId);
        	
			
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
	
	public static boolean underTheShelf_bid(int bidId){
		String errorMsg = "";
		Connection conn = null;
        try {
            //連結資料庫
            conn = db.DbConnection.getConnection();
            //UnderTheShelf
        } catch ( Exception e ) {
        	errorMsg = "Get DataSource or Connection error: " + e.toString();
        	System.out.println(errorMsg);
        }
        
        
        PreparedStatement stmt = null;
        
        try {
        	
        	
        	String msql = "update bid set Status = ? where BidId = ?";
        	
        	stmt = conn.prepareStatement(msql);
        	
        	stmt.setString(1,"UnderTheShelf");
        	stmt.setInt(2,bidId);
        	
        	System.out.println("bidId: " + bidId);
        	
			
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
	
	public static ArrayList<orderItem> getOrder(String userId) {
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
        	String mSql = "select * from orderlist where SellerId = ? order by Status desc";
        	stmt = conn.prepareStatement(mSql);
        	stmt.clearParameters();
        	stmt.setString( 1, userId );
        	
        	
        	System.out.println(mSql);
        	
        	rs = stmt.executeQuery();
        	rs.last(); // 為了抓數量
            int rowCnt = rs.getRow();
            
            ArrayList<orderItem> result = new ArrayList<orderItem>();
            if ( rowCnt > 0 ) {
            	
            	rs.absolute(1); // 記得加
            	
                for ( int i = 0; i < rowCnt; i++ ) {
                	orderItem tmpQuery = new orderItem();
                	
                	tmpQuery.setOrderId( rs.getInt( "OrderId" ) );
                	tmpQuery.setBuyerId( rs.getString( "BuyerId" ) );
                	tmpQuery.setSellerId( rs.getString( "SellerId" ) );
                	tmpQuery.setDescription( rs.getString( "Description" ) );
                	tmpQuery.setTotalPrice( rs.getInt( "TotalPrice" ) );
                	tmpQuery.setStatus( rs.getString( "Status" ) );

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
	
	public static boolean checkOrder(int orderId){
		String errorMsg = "";
		Connection conn = null;
        try {
            //連結資料庫
            conn = db.DbConnection.getConnection();
            //UnderTheShelf
        } catch ( Exception e ) {
        	errorMsg = "Get DataSource or Connection error: " + e.toString();
        	System.out.println(errorMsg);
        }
        
        
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        
        try {
        	
        	
        	String msql = "update orderList set Status = ? where OrderId = ?";
        	
        	stmt = conn.prepareStatement(msql);
        	
        	stmt.setString(1,"Move");
        	stmt.setInt(2, orderId );
        	
			
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
			
			
			String msql2 = "update checkoutauction set Status = ? where OrderId = ?";
        	
        	stmt2 = conn.prepareStatement(msql2);
        	
        	stmt2.setString(1,"Move");
        	stmt2.setInt(2, orderId );
        	
			
        	int updateRow2 = stmt2.executeUpdate();
			if ( updateRow2 < 0 ) {
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
                if ( stmt2 != null ) stmt2.close();
                conn.close();
            } catch ( SQLException se ) {
                errorMsg = "close ResultSet or Statment or connection error: " + se.toString();
                System.out.println(errorMsg);
            }
        }
        
        return false;
	}
	
	public static boolean cancelOrder(int orderId){
		String errorMsg = "";
		Connection conn = null;
        try {
            //連結資料庫
            conn = db.DbConnection.getConnection();
            //UnderTheShelf
        } catch ( Exception e ) {
        	errorMsg = "Get DataSource or Connection error: " + e.toString();
        	System.out.println(errorMsg);
        }
        
        
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        
        try {
        	
        	
        	String msql = "update orderList set Status = ? where OrderId = ?";
        	
        	stmt = conn.prepareStatement(msql);
        	
        	stmt.setString(1,"Cancel");
        	stmt.setInt(2, orderId );
        	
			
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
			
			String msql2 = "update checkoutauction set Status = ? where OrderId = ?";
        	
        	stmt2 = conn.prepareStatement(msql2);
        	
        	stmt2.setString(1,"Cancel");
        	stmt2.setInt(2, orderId );
        	
			
        	int updateRow2 = stmt2.executeUpdate();
			if ( updateRow2 < 0 ) {
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
                if ( stmt2 != null ) stmt.close();
                conn.close();
            } catch ( SQLException se ) {
                errorMsg = "close ResultSet or Statment or connection error: " + se.toString();
                System.out.println(errorMsg);
            }
        }
        
        return false;
	}
	
	// *******************還要寫移評價*******************************//
	public static boolean finishOrder(int orderId){
		String errorMsg = "";
		Connection conn = null;
        try {
            //連結資料庫
            conn = db.DbConnection.getConnection();
            //UnderTheShelf
        } catch ( Exception e ) {
        	errorMsg = "Get DataSource or Connection error: " + e.toString();
        	System.out.println(errorMsg);
        }
        
        
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        PreparedStatement stmt4 = null;
        PreparedStatement stmt5 = null;
        PreparedStatement stmt6 = null;
        PreparedStatement stmt7 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        
        try {
        	
        	// 更改訂單狀態
        	String sql1 = "update orderList set Status = ? where OrderId = ?";
        	
        	stmt = conn.prepareStatement(sql1);
        	
        	stmt.setString(1,"End");
        	stmt.setInt(2, orderId );
        	
        	System.out.println(sql1);
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
			
			String sql7 = "update checkoutauction set Status = ? where OrderId = ?";
        	
        	stmt7 = conn.prepareStatement(sql7);
        	
        	stmt7.setString(1,"End");
        	stmt7.setInt(2, orderId );
        	
        	System.out.println(sql7);
        	int updateRow7 = stmt.executeUpdate();
			if ( updateRow7 < 0 ) {
				try {
					conn.rollback();
					errorMsg = "Update table fail.";
					System.out.println(errorMsg);
				}catch(Exception backerr){
					System.out.println("rollback faild!");
				}
			}
        	
			//********** 更改拍賣商品數量 **************/
			// 抓要更改數量的拍賣商品
			String sql2 = "select * from checkoutauction where OrderId = ?";
			stmt2 = conn.prepareStatement(sql2);
			stmt2.clearParameters();
			stmt2.setInt( 1, orderId );
        	
        	
        	System.out.println(sql2);
        	
        	rs2 = stmt2.executeQuery();
        	rs2.last(); // 為了抓數量
            int rowCnt = rs2.getRow();
			
            
            if ( rowCnt > 0 ) {
            	
            	rs2.absolute(1); // 記得加
            	
                for ( int i = 0; i < rowCnt; i++ ) {
                	// 抓拍賣商品剩餘數量
                	String sql3 = "select * from auction where AuctionId = ?";
        			stmt3 = conn.prepareStatement(sql3);
        			stmt3.clearParameters();
        			stmt3.setInt( 1, rs2.getInt( "AuctionId" ) );
                	
                	
                	System.out.println(sql3);
                	
                	rs3 = stmt3.executeQuery();
                	
                	if( rs3.next() ) {
                		// 更改拍賣商品剩餘數量
                		String sql4 = "update auction set NumberOfProduct = ? where AuctionId = ?";
                    	
                    	stmt4 = conn.prepareStatement(sql4);
                    	
                    	stmt4.setInt(1, rs3.getInt("NumberOfProduct") - rs2.getInt("NumberOfProduct") );
                    	stmt4.setInt(2, rs2.getInt( "AuctionId" ) );
                    	
                    	System.out.println(sql4);
                    	int updateRow2 = stmt4.executeUpdate();
            			if ( updateRow2 < 0 ) {
            				try {
            					conn.rollback();
            					errorMsg = "Update table fail.";
            					System.out.println(errorMsg);
            				}catch(Exception backerr){
            					System.out.println("rollback faild!");
            				}
            			}
                	}
                	
                    rs2.next();
                  }
                //********** 更改拍賣商品數量End **************/
                
	              //********** 移動至評價系統 **************/
	                rs2.last(); // 為了抓數量
	                int rowCnt2 = rs2.getRow();
	                
	                if ( rowCnt > 0 ) {
	                	
	                	rs2.absolute(1); // 記得加
	                	
	                	String sellerId = rs2.getString( "SellerId" );
	                	String buyerId = rs2.getString( "BuyerId" );
	                	
	                    for ( int i = 0; i < rowCnt; i++ ) {
	                    	// 增加拍賣商品至評價系統
	                    	String sql5 = "INSERT INTO evaluation(Type, Id, BuyerId)"
	                    			+ "VALUES(?,?,?)";
	        				
	                    	stmt5 = conn.prepareStatement(sql5);
	                    	stmt5.clearParameters();
	                    	stmt5.setString(1, "Auction" );
	                    	stmt5.setString(2, Integer.toString(rs2.getInt( "AuctionId" )) );
	                    	stmt5.setString(3, buyerId );
	                    	
	                    	System.out.println(sql5);
	                    	
	                    	int updateRow3 = stmt5.executeUpdate();
	        				if ( updateRow3 <= 0 ) {
	        					try {
	        						conn.rollback();
	        						errorMsg = "Insert into table fail.";
	        						System.out.println(errorMsg);
	        					}catch(Exception backerr){
	        						System.out.println("rollback faild!");
	        					}
	        				}
	                    }
	                    
	                    // 增加賣家至評價系統
	                    String sql6 = "INSERT INTO evaluation(Type, Id, BuyerId)"
	                			+ "VALUES(?,?,?)";
	    				
	                	stmt6 = conn.prepareStatement(sql6);
	                	stmt6.clearParameters();
	                	stmt6.setString(1, "Seller" );
	                	stmt6.setString(2, sellerId );
	                	stmt6.setString(3, buyerId );
	                	
	                	System.out.println(sql6);
	                	
	                	int updateRow3 = stmt6.executeUpdate();
	    				if ( updateRow3 <= 0 ) {
	    					try {
	    						conn.rollback();
	    						errorMsg = "Insert into table fail.";
	    						System.out.println(errorMsg);
	    					}catch(Exception backerr){
	    						System.out.println("rollback faild!");
	    					}
	    				}
	                }
	              //********** 移動至評價系統End **************/
            }
			
			return true;
        	
        } catch ( Exception sqle ) {
            errorMsg = "find from table error: " + sqle.toString();
            System.out.println(errorMsg);
        } finally {
            try
            {
                if ( stmt != null ) stmt.close();
                if ( stmt2 != null ) stmt2.close();
                if ( stmt3 != null ) stmt3.close();
                if ( stmt4 != null ) stmt4.close();
                if ( stmt5 != null ) stmt5.close();
                if ( stmt6 != null ) stmt6.close();
                if ( rs2 != null ) rs2.close();
                if ( rs3 != null ) rs3.close();
                conn.close();
            } catch ( SQLException se ) {
                errorMsg = "close ResultSet or Statment or connection error: " + se.toString();
                System.out.println(errorMsg);
            }
        }
        
        return false;
	}
	
	public static boolean modifyStatus(int bidId){
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
        	
        	
        	String msql = "update bid set Status = ? where BidId = ?";
        	
        	stmt = conn.prepareStatement(msql);
        	
        	stmt.setString(1, "Result" );
        	stmt.setInt(2, bidId);
        	
			
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
	
	public static boolean biddingProductAddOrder(int bidId){
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
        	
        	String sql1 = "select * from bid where BidId = ?";
        	
        	stmt = conn.prepareStatement(sql1);
        	stmt.clearParameters();
        	stmt.setInt(1,bidId);
        	
        	System.out.println( sql1 );
        	
        	rs = stmt.executeQuery();
        	
        	
        	
        	String sql2 = "INSERT INTO orderlist(BuyerId, SellerId, Description, TotalPrice)"
        			+ "VALUES(?,?,?,?)";
        	if(rs.next()){
	        	stmt2 = conn.prepareStatement(sql2);
	        	stmt2.clearParameters();
	        	stmt2.setString(1, rs.getString( "BuyerId" ) );
	        	stmt2.setString(2, rs.getString( "SellerId" ) );
	        	stmt2.setString(3, rs.getString( "Name" ));
	        	stmt2.setInt(4, rs.getInt( "PriceNow" ) );
        	}
        	System.out.println( sql2 );
        	
        	int updateRow = stmt2.executeUpdate();
			
			if ( updateRow <= 0 ) {
				try {
					conn.rollback();
					errorMsg = "Insert into table fail.";
					System.out.println(errorMsg);
					return false;
				}catch(Exception backerr){
					System.out.println("rollback faild!");
					return false;
				}
			}
        	
        	
        } catch ( Exception sqle ) {
            errorMsg = "find from table error: " + sqle.toString();
            System.out.println(errorMsg);
            return false;
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
                return false;
            }
        }
		
		return true;
	}
	
}
