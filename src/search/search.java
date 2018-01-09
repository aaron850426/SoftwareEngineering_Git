package search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
import product.*;

public class search {
	
	
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
                paraList.add( hot );
        	}
        	
        	sSql.append( " and Status <> ?" );
        	paraList.add("UnderTheShelf");
        	
        	sSql.append( " order by Hot desc, Status" );
        	
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
	
// 用來寫頁數	
public static int searchAuctionsForPage(String keyword, String productCategory, String hot) {
		
		
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
        		sSql.append( " and Hot = ?" );
                paraList.add( hot );
        	}
        	sSql.append( " and Status <> ?" );
        	paraList.add("UnderTheShelf");
        	
        	sSql.append( " order by Hot desc, Status" );
        	
        	System.out.println(sSql);
        	
        	stmt = conn.prepareStatement(sSql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	stmt.clearParameters();
        	
        	for(int i=0;i<paraList.size();i++){
            	stmt.setString(i+1,paraList.get(i) );
            }
        	rs = stmt.executeQuery();
        	rs.last();
            int rowCnt = rs.getRow();
            
            return rowCnt;
			
        	
        	
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
        
        return 0;
	}
	
	public static auctionItem searchSingleAuction(int auctionId){
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
        	
        	
        	String msql = "select * from auction where AuctionId = ?";
        	
        	stmt = conn.prepareStatement(msql);
        	stmt.clearParameters();
        	stmt.setInt(1,auctionId);
        	
        	rs = stmt.executeQuery();
        	
        	auctionItem item = new auctionItem();
        	
        	if(rs.next()){
        		item.setAuctionId( rs.getInt( "AuctionId" ) );
        		item.setCategory( rs.getString( "Category" ) );
        		item.setName( rs.getString( "Name" ) );
        		item.setDescription( rs.getString( "Description" ) );
        		item.setSellerId( rs.getString( "SellerId" ) );
        		item.setPrice( rs.getInt( "Price" ) );
        		item.setOriginalNumber( rs.getInt( "OriginalNumber" ) );
        		item.setNumberOfProduct( rs.getInt( "NumberOfProduct" ) );
        		item.setStatus( rs.getString( "Status" ) );
        		item.setPicturePath( rs.getString( "PicturePath" ) );
        		item.setHot( rs.getString( "Hot" ) );
        		
        		return item;
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
		
		return null;
	}
	
	public static ArrayList<biddingItem> searchBids(String keyword, String productCategory, int startRow, int endRow) {
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
        	
        	sSql.append("select * from bid" +  " where 1 = 1");
        	
        	if( keyword != null && !keyword.equals("null") && !keyword.equals("") ){
        		sSql.append( " and Name like ? " );
                paraList.add( "%" + keyword + "%");
        	}
        	if( productCategory != null && !productCategory.equals("null") && !productCategory.equals("") ){
        		sSql.append( " and Category = ?" );
                paraList.add(productCategory);
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
        	
            ArrayList<biddingItem> result = new ArrayList<biddingItem>();
            //List<biddingItem> result_bid = new ArrayList<biddingItem>();
            
            if ( rowCnt > 0 ) {
                if ( endRow > rowCnt )
                    endRow = rowCnt;
                rs.absolute(startRow);
                
                for ( int i = startRow; i <= endRow; i++ ) {
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
	
	public static int searchBidsForPage(String keyword, String productCategory) {
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
        	
        	sSql.append("select * from bid" +  " where 1 = 1");
        	
        	if( keyword != null && !keyword.equals("null") && !keyword.equals("") ){
        		sSql.append( " and Name like ? " );
                paraList.add( "%" + keyword + "%");
        	}
        	if( productCategory != null && !productCategory.equals("null") && !productCategory.equals("") ){
        		sSql.append( " and Category = ?" );
                paraList.add(productCategory);
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
            
            return rowCnt;
        	
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
        
        return 0;
	}
	
	public static biddingItem searchSingleBid(int bidId){
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
        	
        	
        	String msql = "select * from bid where BidId = ?";
        	
        	stmt = conn.prepareStatement(msql);
        	stmt.clearParameters();
        	stmt.setInt(1,bidId);
        	
        	rs = stmt.executeQuery();
        	
        	biddingItem item = new biddingItem();
        	
        	if(rs.next()){
        		
        		item.setBidId( rs.getInt( "BidId" ) );
        		item.setCategory( rs.getString( "Category" ) );
        		item.setName( rs.getString( "Name" ) );
        		item.setDescription( rs.getString( "Description" ) );
        		item.setSellerId( rs.getString( "SellerId" ) );
        		item.setPriceMin( rs.getInt( "PriceMin" ) );
        		item.setPriceMax( rs.getInt( "PriceMax" ) );
        		item.setPriceIncrease( rs.getInt( "PriceIncrease" ) );
        		item.setPriceNow( rs.getInt( "PriceNow" ) );
                item.setEndTime( rs.getString( "EndTime" ) );
                item.setStatus( rs.getString( "Status" ) );
                item.setBuyerId( rs.getString( "BuyerId" ) );
                item.setPicturePath( rs.getString( "PicturePath" ) );
        		
        		return item;
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
		
		return null;
	}
	
}
