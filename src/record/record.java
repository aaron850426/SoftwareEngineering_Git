package record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import product.*;

public class record {
		
	// Search auction by seller id
	public static ArrayList<auctionItem> searchAuctionBySellerId(String sellerId){
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
        	
        	sSql.append("select * from auction" +  " where SellerId = ? order by Status ");
        	paraList.add(sellerId);
        	//System.out.println(sSql);
        	
        	stmt = conn.prepareStatement(sSql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	stmt.clearParameters();
        	
        	for(int i=0;i<paraList.size();i++){
            	stmt.setString(i + 1, paraList.get(i) );
            }
        	rs = stmt.executeQuery();
        	rs.last();
            int count = rs.getRow();
            rs.absolute(1);
        	
            ArrayList<auctionItem> result = new ArrayList<auctionItem>();
            
            //System.out.printf("%d%n", count);
            for ( int i = 1; i <= count; i++ ) {
            	
            	//System.out.printf("%d %d %n", i, count);
            	 
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
            return result; 
            
        } catch ( Exception sqle ) {
            errorMsg = "find from table error: " + sqle.toString();
            System.out.println(errorMsg);
        } finally {
            try {
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
	
	// Search bidding by seller id
	public static ArrayList<biddingItem> searchBiddingBySellerId(String sellerId){
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
        	
        	sSql.append("select * from bid" +  " where SellerId = ? order by Status ");
        	paraList.add(sellerId);
        	
        	stmt = conn.prepareStatement(sSql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	stmt.clearParameters();
        	
        	for(int i=0;i<paraList.size();i++){
            	stmt.setString(i + 1, paraList.get(i) );
            }
        	rs = stmt.executeQuery();
        	rs.last();
            int count = rs.getRow();
            rs.absolute(1);
        	
            ArrayList<biddingItem> result = new ArrayList<biddingItem>();
            
            System.out.printf("%d%n", count);
            for ( int i = 1; i <= count; i++ ) {
            	
            	//System.out.printf("%d %d %n", i, count);
            	 
            	biddingItem tmpQuery = new biddingItem();
                
                tmpQuery.setBidId( rs.getInt( "BidId" ) );
                tmpQuery.setCategory( rs.getString( "Category" ) );
                tmpQuery.setName( rs.getString( "Name" ) );
                tmpQuery.setDescription( rs.getString( "Description" ) );
                tmpQuery.setSellerId( rs.getString( "SellerId" ) );
                tmpQuery.setPriceMin( rs.getInt( "PriceMin" ) );
                tmpQuery.setPriceMax( rs.getInt( "PriceMax" ) );
                tmpQuery.setPriceIncrease( rs.getInt( "PriceIncrease" ) );
                tmpQuery.setPriceNow( rs.getInt( "PriceNow" ) );
                tmpQuery.setEndTime( rs.getString( "EndTime" ) );
                tmpQuery.setStatus( rs.getString( "Status" ) );
                tmpQuery.setBuyerId( rs.getString( "BuyerId" ) );
                tmpQuery.setPicturePath( rs.getString( "PicturePath" ) );

                //tmpQuery.setTotrow(rowCnt);

                result.add( tmpQuery );
                rs.next();
            }
            return result; 
            
        } catch ( Exception sqle ) {
            errorMsg = "find from table error: " + sqle.toString();
            System.out.println(errorMsg);
        } finally {
            try {
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
	
	
	public static ArrayList<checkoutAuctionItem> searchAuctionByBuyerId(String buyerId){
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
        	
        	sSql.append("select * from checkoutauction" +  " where BuyerId = ? order by Status ");
        	paraList.add(buyerId);
        	//System.out.println(sSql);
        	
        	stmt = conn.prepareStatement(sSql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	stmt.clearParameters();
        	
        	for(int i=0;i<paraList.size();i++){
            	stmt.setString(i + 1, paraList.get(i) );
            }
        	rs = stmt.executeQuery();
        	rs.last();
            int count = rs.getRow();
            rs.absolute(1);
        	
            ArrayList<checkoutAuctionItem> result = new ArrayList<checkoutAuctionItem>();
            
            //System.out.printf("%d%n", count);
            for ( int i = 1; i <= count; i++ ) {
            	
            	//System.out.printf("%d %d %n", i, count);
            	 
            	checkoutAuctionItem tmpQuery = new checkoutAuctionItem();
                
            	tmpQuery.setCheckoutAuctionId( rs.getInt( "CheckoutAuctionId" ) );
            	tmpQuery.setBuyerId( rs.getString( "BuyerId" ) );
            	tmpQuery.setAuctionId( rs.getInt( "AuctionId" ) );
            	tmpQuery.setPrice( rs.getInt( "Price" ) );
            	tmpQuery.setNumberOfProduct( rs.getInt( "NumberOfProduct" ) );
            	tmpQuery.setOrderId( rs.getInt( "OrderId" ) );
            	tmpQuery.setStatus( rs.getString( "Status" ) );
            	tmpQuery.setPicturePath( rs.getString( "PicturePath" ) );
            	tmpQuery.setSellerId( rs.getString( "SellerId" ) );
            	tmpQuery.setName( rs.getString( "Name" ) );
            	


                result.add( tmpQuery );
                rs.next();
            }
            return result; 
            
        } catch ( Exception sqle ) {
            errorMsg = "find from table error: " + sqle.toString();
            System.out.println(errorMsg);
        } finally {
            try {
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
	
	public static ArrayList<biddingItem> searchBiddingByBuyerId(String buyerId){
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
        	
        	sSql.append("select * from bid" +  " where BuyerId = ? order by Status ");
        	paraList.add(buyerId);
        	
        	stmt = conn.prepareStatement(sSql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	stmt.clearParameters();
        	
        	for(int i=0;i<paraList.size();i++){
            	stmt.setString(i + 1, paraList.get(i) );
            }
        	rs = stmt.executeQuery();
        	rs.last();
            int count = rs.getRow();
            rs.absolute(1);
        	
            ArrayList<biddingItem> result = new ArrayList<biddingItem>();
            
            System.out.printf("%d%n", count);
            for ( int i = 1; i <= count; i++ ) {
            	
            	//System.out.printf("%d %d %n", i, count);
            	 
            	biddingItem tmpQuery = new biddingItem();
                
                tmpQuery.setBidId( rs.getInt( "BidId" ) );
                tmpQuery.setCategory( rs.getString( "Category" ) );
                tmpQuery.setName( rs.getString( "Name" ) );
                tmpQuery.setDescription( rs.getString( "Description" ) );
                tmpQuery.setSellerId( rs.getString( "SellerId" ) );
                tmpQuery.setPriceMin( rs.getInt( "PriceMin" ) );
                tmpQuery.setPriceMax( rs.getInt( "PriceMax" ) );
                tmpQuery.setPriceIncrease( rs.getInt( "PriceIncrease" ) );
                tmpQuery.setPriceNow( rs.getInt( "PriceNow" ) );
                tmpQuery.setEndTime( rs.getString( "EndTime" ) );
                tmpQuery.setStatus( rs.getString( "Status" ) );
                tmpQuery.setBuyerId( rs.getString( "BuyerId" ) );
                tmpQuery.setPicturePath( rs.getString( "PicturePath" ) );

                //tmpQuery.setTotrow(rowCnt);

                result.add( tmpQuery );
                rs.next();
            }
            return result; 
            
        } catch ( Exception sqle ) {
            errorMsg = "find from table error: " + sqle.toString();
            System.out.println(errorMsg);
        } finally {
            try {
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
	
}
