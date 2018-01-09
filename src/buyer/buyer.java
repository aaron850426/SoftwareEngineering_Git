package buyer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
import product.*;

public class buyer {
	
	public static ArrayList<shoppingCartItem> getInfoShoppingCart(String userId){
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
        	String mSql = "select * from shoppingCart where UserId = ? order by SellerId";
        	stmt = conn.prepareStatement(mSql);
        	stmt.clearParameters();
        	stmt.setString( 1, userId );
        	
        	
        	System.out.println(mSql);
        	
        	rs = stmt.executeQuery();
        	rs.last(); // 為了抓數量
            int rowCnt = rs.getRow();
            
            ArrayList<shoppingCartItem> result = new ArrayList<shoppingCartItem>();
            if ( rowCnt > 0 ) {
            	
            	rs.absolute(1); // 記得加
            	
                for ( int i = 0; i < rowCnt; i++ ) {
                	shoppingCartItem tmpQuery = new shoppingCartItem();
                	
                    tmpQuery.setShoppingCartId( rs.getInt( "ShoppingCartId" ) );
                    tmpQuery.setUserId( rs.getString( "UserId" ) );
                    tmpQuery.setAuctionId( rs.getInt( "AuctionId" ) );
                    tmpQuery.setAuctionName( rs.getString( "AuctionName" ) );
                    tmpQuery.setNumberOfProduct( rs.getInt( "NumberOfProduct" ) );
                    tmpQuery.setPrice( rs.getInt( "Price" ) );
                    tmpQuery.setTotal( rs.getInt( "Total" ) );
                    tmpQuery.setSellerId( rs.getString( "SellerId" ) );

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
	
	public static boolean addShoppingCart(String userId, int auctionId, String auctionName, int numberOfProduct, int price, String sellerId){
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
        	
        	
        	String qsql = "INSERT INTO shoppingCart(UserId, AuctionId, AuctionName , NumberOfProduct, Price, Total, SellerId)"
        			+ "VALUES(?,?,?,?,?,?,?)";
        	
        	stmt = conn.prepareStatement(qsql);
        	stmt.clearParameters();
        	stmt.setString(1,userId);
        	stmt.setInt(2,auctionId);
        	stmt.setString(3,auctionName);
        	stmt.setInt(4,numberOfProduct);
        	stmt.setInt(5,price);
        	stmt.setInt(6,numberOfProduct * price);
        	stmt.setString(7,sellerId);
        	
        	int updateRow = stmt.executeUpdate();
			
			if ( updateRow <= 0 ) {
				try {
					conn.rollback();
					errorMsg = "Insert into table fail.";
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
                if ( rs != null ) rs.close();
                if ( stmt != null ) stmt.close();
                conn.close();
            } catch ( SQLException se ) {
                errorMsg = "close ResultSet or Statment or connection error: " + se.toString();
                System.out.println(errorMsg);
            }
        }
        
        return false;
	}
	
	
	public static ArrayList<auctionItem> searchAuctions(String keyword, String productCategory, int startRow, int endRow, String hot) {
		
		
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
        	
        	ArrayList<String> paraList = new ArrayList<String>();
        	StringBuffer sSql = new StringBuffer();
        	
        	sSql.append("select * from auction" +  " where 1 = 1");
        	
        	if( keyword != null && !keyword.equals("null") && !keyword.equals("") ){
        		sSql.append( " and Name like ? " );
                paraList.add( "%" + keyword + "%");
        	}
        	if( productCategory != null && !productCategory.equals("null") && !productCategory.equals("") ){
        		sSql.append( " and Category = ?" );
                paraList.add(productCategory);
        	}
        	if( hot != null && !hot.equals("null") && !hot.equals("") ){
        		sSql.append( " and hot = ?" );
                paraList.add("True");
        	}
        	
        	System.out.println(sSql);
        	
        	stmt = conn.prepareStatement(sSql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	stmt.clearParameters();
        	
        	for(int i=0;i<paraList.size();i++){
            	stmt.setString(i+1,paraList.get(i) );
            }
        	rs = stmt.executeQuery();
        	rs.last();
            int rowCnt = rs.getRow();
        	
            ArrayList<auctionItem> result = new ArrayList<auctionItem>();
            //List<biddingItem> result_bid = new ArrayList<biddingItem>();
            
            if ( rowCnt > 0 ) {
                if ( endRow > rowCnt )
                    endRow = rowCnt;
                rs.absolute(startRow);
                
                for ( int i = startRow; i <= endRow; i++ ) {
                    auctionItem tmpQuery = new auctionItem();
                    
                    tmpQuery.setAuctionId( rs.getInt( "AuctionId" ) );
                    tmpQuery.setCategory( rs.getString( "Category" ) );
                    tmpQuery.setName( rs.getString( "Name" ) );
                    tmpQuery.setDescription( rs.getString( "Description" ) );
                    tmpQuery.setSellerId( rs.getString( "SellerId" ) );
                    tmpQuery.setPrice( rs.getInt( "Price" ) );
                    tmpQuery.setOriginalNumber( rs.getInt( "OriginalNumber" ) );
                    tmpQuery.setNumberOfProduct( rs.getInt( "NumberOfProduct" ) );
                    tmpQuery.setStatus( rs.getString( "Status" ) );
                    tmpQuery.setPicturePath( rs.getString( "PicturePath" ) );
                    tmpQuery.setHot( rs.getString( "Hot" ) );

                    //tmpQuery.setTotrow(rowCnt);

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
	
	public static ArrayList<evaluationItem> getInfoEvaluationSeller(String userId){
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
        	String mSql = "select * from evaluation where  Type = ? and BuyerId = ? order by Status desc";
        	stmt = conn.prepareStatement(mSql);
        	stmt.clearParameters();
        	stmt.setString( 1, "Seller" );
        	stmt.setString( 2, userId );
        	
        	
        	System.out.println(mSql);
        	
        	rs = stmt.executeQuery();
        	rs.last(); // 為了抓數量
            int rowCnt = rs.getRow();
            
            ArrayList<evaluationItem> result = new ArrayList<evaluationItem>();
            if ( rowCnt > 0 ) {
            	
            	rs.absolute(1); // 記得加
            	
                for ( int i = 0; i < rowCnt; i++ ) {
                	evaluationItem tmpQuery = new evaluationItem();
                	
                	tmpQuery.setEvaluationId( rs.getInt( "EvaluationId" ) );
                	tmpQuery.setType( rs.getString( "Type" ) );
                	tmpQuery.setId( rs.getString( "Id" ) );
                	tmpQuery.setBuyerId( rs.getString( "BuyerId" ) );
                	tmpQuery.setEvaluation(rs.getInt( "Evaluation" ) );
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
	
	public static ArrayList<evaluationItem> getInfoEvaluationProduct(String userId){
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
        	String mSql = "select * from evaluation where  Type = ? and BuyerId = ? order by Status desc";
        	stmt = conn.prepareStatement(mSql);
        	stmt.clearParameters();
        	stmt.setString( 1, "Auction" );
        	stmt.setString( 2, userId );
        	
        	
        	System.out.println(mSql);
        	
        	rs = stmt.executeQuery();
        	rs.last(); // 為了抓數量
            int rowCnt = rs.getRow();
            
            
            ArrayList<evaluationItem> result = new ArrayList<evaluationItem>();
            if ( rowCnt > 0 ) {
            	
            	rs.absolute(1); // 記得加
            	
                for ( int i = 0; i < rowCnt; i++ ) {
                	evaluationItem tmpQuery = new evaluationItem();
                	
                	// 抓商品名稱
                	stmt2 = null;
                	rs2 = null;
                	
                	String mSql2 = "select * from auction where AuctionId = ?";
                	stmt2 = conn.prepareStatement(mSql2);
                	stmt2.clearParameters();
                	stmt2.setInt( 1, Integer.parseInt(rs.getString( "Id" )) );
                	
                	System.out.println(mSql2);
                	rs2 = stmt2.executeQuery();
                	
                	rs2.last(); // 為了抓數量
                	rs2.absolute(1); // 記得加
                	
                	// 抓商品名稱 end
                	
                	tmpQuery.setEvaluationId( rs.getInt( "EvaluationId" ) );
                	tmpQuery.setType( rs.getString( "Type" ) );
                	tmpQuery.setId( rs.getString( "Id" ) );
                	tmpQuery.setBuyerId( rs.getString( "BuyerId" ) );
                	tmpQuery.setEvaluation(rs.getInt( "Evaluation" ) );
                	tmpQuery.setStatus( rs.getString( "Status" ) );
                	tmpQuery.setProductName( rs2.getString( "Name" ) );
                	

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
	
	public static boolean evalution(int evaluationId, int evaluation){
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
        	
        	
        	String msql = "update evaluation set Evaluation = ?, Status = ? " + 
        					" where EvaluationId = ?";
        	
        	stmt = conn.prepareStatement(msql);
        	
        	stmt.setInt(1, evaluation );
        	stmt.setString(2, "Evaluated " );
        	stmt.setInt(3, evaluationId );
        	
			
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
        	String mSql = "select * from orderlist where BuyerId = ? order by Status desc";
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
	
	
public static boolean addOrder(ArrayList<shoppingCartItem> shoppingCartItem, int totalPrice) {
		
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
        	for(int i = 0; i < shoppingCartItem.size(); i++){     		
        		/******/
        		
        		shoppingCartItem itemCurrent = shoppingCartItem.get(i);
        		String tempName = itemCurrent.getAuctionName() + "*" + Integer.toString(itemCurrent.getNumberOfProduct() );
        		//int totalPrice = itemCurrent.getTotal();
        		
        		int j;
        		for(j = i + 1; j < shoppingCartItem.size(); j++) {
     				if(itemCurrent.getSellerId().compareTo(shoppingCartItem.get(j).getSellerId()) != 0) break;
        			tempName += "," + shoppingCartItem.get(j).getAuctionName() + "*" + Integer.toString(shoppingCartItem.get(j).getNumberOfProduct());      			
        			//totalPrice += shoppingCartItem.get(j).getTotal();
        			
        		}
        		
        		i = j - 1;
        		
        		String qsql = "INSERT INTO orderList(BuyerId, SellerId, Description, TotalPrice, Status)"
            			+ "VALUES(?,?,?,?,?)";
				
            	stmt = conn.prepareStatement(qsql);
            	stmt.clearParameters();
            	stmt.setString(1,itemCurrent.getUserId());
            	stmt.setString(2,itemCurrent.getSellerId());
            	stmt.setString(3,tempName);
            	stmt.setInt(4,totalPrice);
            	stmt.setString(5, "Ing");  // Ing, Move, End, Cancel
            	
            	System.out.println(qsql);
            	
            	int updateRow = stmt.executeUpdate();
				if ( updateRow <= 0 ) {
					try {
						conn.rollback();
						errorMsg = "Insert into table fail.";
						System.out.println(errorMsg);
					}catch(Exception backerr){
						System.out.println("rollback faild!");
					}
				} else {
					return true;
				}
 		     	
        	}
        	
        	
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
        
        return false;
	}

	public static boolean addChechOutAuction(ArrayList<shoppingCartItem> shoppingCartItem) {
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
        PreparedStatement stmt2 = null;
        
        
        try {
        	for(int i = 0; i < shoppingCartItem.size(); i++){
        		
        		shoppingCartItem item = shoppingCartItem.get(i);
        				
        		String mSql = "select * from orderlist where BuyerId = ? AND SellerId = ?";
            	stmt2 = conn.prepareStatement(mSql);
            	stmt2.clearParameters();      		
            	stmt2.setString( 1, item.getUserId() );
            	stmt2.setString( 2, item.getSellerId() );
            	
            	System.out.println(mSql);
            	
            	rs = stmt2.executeQuery();		
            	rs.first();
            	
        		String qsql = "INSERT INTO checkoutauction(BuyerId, SellerId, AuctionId, Price, NumberOfProduct, OrderId, Status, Name)"
            			+ "VALUES(?,?,?,?,?,?,?,?)";

            	stmt = conn.prepareStatement(qsql);
            	stmt.clearParameters();
            	stmt.setString(1,item.getUserId());
            	stmt.setString(2,item.getSellerId());
            	stmt.setInt(3,item.getAuctionId());
            	stmt.setInt(4,item.getPrice());
            	stmt.setInt(5,item.getNumberOfProduct());
            	stmt.setInt(6,rs.getInt("OrderId"));
            	stmt.setString(7, rs.getString("Status"));  // Ing, Move, End, Cancel
            	stmt.setString(8, item.getAuctionName());
            	
            	System.out.println(qsql);
            	
            	int updateRow = stmt.executeUpdate();
				if ( updateRow <= 0 ) {
					try {
						conn.rollback();
						errorMsg = "Insert into table fail.";
						System.out.println(errorMsg);
					}catch(Exception backerr){
						System.out.println("rollback faild!");
					}
				}else{
					return true;
				}      	
        	}
        	
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
        
        return false;
	}

	public static boolean deleteShoppingCart(ArrayList<shoppingCartItem> shoppingCartItem) {
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
        	for( int i = 0; i < shoppingCartItem.size(); i++) {
        	
	        	shoppingCartItem item = shoppingCartItem.get(0);        	
	        	String qsql = "delete from shoppingcart where UserId = ?";
	        	stmt = conn.prepareStatement(qsql);
	        	stmt.clearParameters();
	        	stmt.setString( 1, item.getUserId() );
	        	
	        	int updateRow = stmt.executeUpdate();			
				if ( updateRow <= 0 ) {
					try {
						conn.rollback();
						errorMsg = "Insert into table fail.";
						System.out.println(errorMsg);
					}catch(Exception backerr){
						System.out.println("rollback faild!");
					}
				}
        	}
        	
        	return true;

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
        return false;
	}
	
	
	public static ArrayList<evaluationItem> getProductEvaluation(int auctionId){
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
        	String mSql = "select * from evaluation where Id = ?";
        	stmt = conn.prepareStatement(mSql);
        	stmt.clearParameters();
        	stmt.setInt( 1, auctionId );
        	
        	
        	System.out.println(mSql);
        	
        	rs = stmt.executeQuery();
        	rs.last(); // 為了抓數量
            int rowCnt = rs.getRow();
            
            ArrayList<evaluationItem> result = new ArrayList<evaluationItem>();
            if ( rowCnt > 0 ) {
            	
            	rs.absolute(1); // 記得加
            	
                for ( int i = 0; i < rowCnt; i++ ) {
                	evaluationItem tmpQuery = new evaluationItem();
                	
                	tmpQuery.setEvaluationId( rs.getInt( "EvaluationId" ) );
                	tmpQuery.setType( rs.getString( "Type" ) );
                	tmpQuery.setId( rs.getString( "Id" ) );
                	tmpQuery.setBuyerId( rs.getString( "BuyerId" ) );
                	tmpQuery.setEvaluation( rs.getInt( "Evaluation" ) );
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
	
	// 把商品更改成熱銷商品(目前訂為銷售超過10個之商品)
	public static boolean updateHot(int num) {
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
        	
        	
        	String msql = "update auction set Hot = ? " + 
        					" where NumberOfProduct <= OriginalNumber - ?";
        	
        	stmt = conn.prepareStatement(msql);
        	
        	stmt.setString(1, "True" );
        	stmt.setInt(2, num );
        	
			System.out.println( msql );
        	
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
