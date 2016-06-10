/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohamed Ramadan
 */
public class Book {
    String id ,name,publisherName,publisherYear,authorName,
            categoryName,categoryDesc;
            int count , noOfRecords;
    
    
    public Book(){
    
    }

    public Book(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setPublisherYear(String publisherYear) {
        this.publisherYear = publisherYear;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }
     public void setCount(int count) {
        this.count=count;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getPublisherYear() {
        return publisherYear;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }
    public int getCount() {
        return count;
    }
    public int getNoOfRecords() {
		return noOfRecords;
	}
    	public List<Book> viewSpecificBooks(int offset,int noOfRecords , 
                Statement stmt , Book b)
	{
		String query = "select SQL_CALC_FOUND_ROWS * from book   " ;
                       
                  // + "limit " + offset + ", " + noOfRecords;
                
              if(!b.getName().equals("")||!b.getAuthorName().equals("all")
                ||!b.getCategoryName().equals("all")||!b.getPublisherYear().equals("all"))
               query+=" where  ";
                  
                  
                String and="";
                if(!b.getName().equals("")){
                    query=query+and+" bookName= "+"'"+b.getName()+"'";
                    and=" and ";        
                            }
                 if(!b.getAuthorName().equals("all")){
                    query=query+and+" autherName= "+"'"+b.getAuthorName()+"' ";
                    and=" and ";        
                            }
                 if(!b.getCategoryName().equals("all")){
                    
                    query=query+and+" category= "+"'"+b.getCategoryName()+" ' ";
                    and=" and ";        
                            }
                  if(!b.getPublisherYear().equals("all")){
                    query=query+and+" publicationYear= "+"'"+b.getPublisherYear()+" ' ";
                    and=" and ";        
                            }
                  
                
		List<Book> list = new ArrayList<Book>();
		Book book = null;
		try {
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
                                System.out.println(rs.getString("bookName"));
				book = new Book();
                                book.setCount(rs.getInt("availableCount"));
				book.setName(rs.getString("bookName"));
                                book.setPublisherName(rs.getString("publisherName"));
                                book.setPublisherYear(rs.getString("publicationYear"));
				book.setAuthorName(rs.getString("autherName"));
                                book.setCategoryName(rs.getString("category"));
				list.add(book);
			}
			rs.close();
		      rs = stmt.executeQuery("SELECT FOUND_ROWS()");
			if(rs.next())
			this.noOfRecords = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
    public ArrayList getBooksNames(Statement stmt) throws SQLException{
    
        ArrayList<String> arr=new ArrayList<>();
        String booksNames="Select bookName from book ";
         ResultSet RS=null;
         RS=stmt.executeQuery(booksNames);
          while(RS.next()){
          if(!arr.contains(RS.getString(1)))    
          arr.add(RS.getString(1));
              
          }
         
    return arr;
    }
    
     public ArrayList getBooksAuthers(Statement stmt) throws SQLException{
    
        ArrayList<String> arr=new ArrayList<>();
        String autherNames="Select autherName from book ";
         ResultSet RS=null;
         RS=stmt.executeQuery( autherNames);
          while(RS.next()){
          if(!arr.contains(RS.getString(1))) 
          arr.add(RS.getString(1));
         
          }
         
    return arr;
    }
      public ArrayList getBooksPublisihingYears
        (Statement stmt) throws SQLException{
    
        ArrayList<String> arr=new ArrayList<>();
        String publicationYears="Select publicationYear from book ";
         ResultSet RS=null;
         RS=stmt.executeQuery( publicationYears);
          while(RS.next()){
          if(!arr.contains(RS.getString(1))) 
          arr.add(RS.getString(1));
          
          }
         
    return arr;
    }
           public ArrayList getBooksCategories
        (Statement stmt) throws SQLException{
    
        ArrayList<String> arr=new ArrayList<>();
        String publicationYears="Select category from book ";
         ResultSet RS=null;
         RS=stmt.executeQuery( publicationYears);
          while(RS.next()){
           if(!arr.contains(RS.getString(1)))     
          arr.add(RS.getString(1));
         
          }
         
    return arr;
    }
    
    
    
}
