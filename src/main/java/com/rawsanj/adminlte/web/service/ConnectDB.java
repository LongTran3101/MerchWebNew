package com.rawsanj.adminlte.web.service;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rawsanj.adminlte.web.entity.AccountMerch;
import com.rawsanj.adminlte.web.entity.User;
public class ConnectDB {
    private Connection con;
    public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public static Statement getSt() {
		return st;
	}

	public static void setSt(Statement st) {
		ConnectDB.st = st;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		ConnectDB.rs = rs;
	}

	private static Statement st;
    private static ResultSet rs;
    private static PreparedStatement statement;
    
    
    public ConnectDB(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/merchsale","root","");
        st = con.createStatement();
        
    }catch(Exception ex){
    	ex.printStackTrace();
        System.out.println("Error is found :"+ex);
    }
}
    
    public String getAdress(String key) {
    	String adr="";
    	try {
    		String sql = "select address from tbl_info where id_key ="+key+" and status = 1 and Ngay_tao <= ENDDATE";
        	rs = st.executeQuery(sql);
        	
        	
        	 while(rs.next()){
        		 adr = rs.getString("address");
                 
             }
        	 st.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return adr;
    	
    }
    public String update(String key,String adress)
    {
    	String adr="";
    	try {
    		java.util.Date date=new java.util.Date();
    		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
    		String sql = "update tbl_info set address = ? , Ngay_tao = ? where id_key ="+key +" and Ngay_tao< ?";
    		PreparedStatement ps=con.prepareStatement(sql);
    		ps.setString(1,adress);
    		ps.setDate(2,sqlDate);
    		ps.setDate(3,sqlDate);
    		ps.executeUpdate();
    		
    		ps.close();
        	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loi update");
			// TODO: handle exception
		}
    	return adr;
    }
    
    public Boolean insert(AccountMerch merch)
    {
    	
    	try {
    		try {
    			java.util.Date date=new java.util.Date();
        		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        		String sql = "INSERT INTO `account_merch`( `name`, `ip`, `day`, `sale`, `money`, `email`) VALUES (?,?,?,?,?,?) ";
        		PreparedStatement ps=con.prepareStatement(sql);
        		ps.setString(1,merch.getName());
        		ps.setString(2,merch.getIp());
        		ps.setDate(3,new java.sql.Date(merch.getDay().getTime()));
        		ps.setInt(4,merch.getSale());
        		ps.setDouble(5,merch.getMoney());
        		ps.setString(6,merch.getEmail());
        		ps.executeUpdate();
        		ps.close();
        		return true;
			}catch (Exception e) {
				
			} finally {
				con.close();
			}
    		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loi update");
			// TODO: handle exception
		}
    	return false;
    }
    public AccountMerch getByID(int ID)
    {
    	 try{
         	try {
         		
         		
         		List<AccountMerch> lst=new ArrayList<>();
                 String sql = "select * from account_merch where id = ? ";
                 statement =con.prepareStatement(sql);
                 statement.setInt(1, ID);
                 rs = statement.executeQuery();
                 
                 System.out.println("Data from online Database :");
                 while(rs.next()){
                 	AccountMerch merch = new AccountMerch();
                 	merch.setId(rs.getInt("id"));
                 	merch.setName(rs.getString("name"));
                 	merch.setIp(rs.getString("ip"));
                 	System.out.println(rs.getDate("day"));
                 	merch.setDay(rs.getDate("day"));
                 	merch.setSale(rs.getInt("sale"));
                 	merch.setMoney(rs.getDouble("money"));
                 	merch.setEmail(rs.getString("email"));
                 	lst.add(merch);
                 }
                 return lst.get(0);
 			} catch (Exception e) {
 				e.printStackTrace();
 			}finally {
 				statement.close();
 				con.close();
 			}
         	return null;
           
             
         }catch(Exception ex){
             System.out.println("Error is found :"+ex);
             return null;
         }
    }
    public List<AccountMerch> getAllAcc(){
        try{
        	try {
        		
        		
        		List<AccountMerch> lst=new ArrayList<>();
                String sql = "select name,ip,email from account_merch ";
                statement =con.prepareStatement(sql);
                rs = statement.executeQuery();
                
                System.out.println("Data from online Database :");
                while(rs.next()){
                	AccountMerch merch = new AccountMerch();
                	
                	merch.setName(rs.getString("name"));
                	merch.setIp(rs.getString("ip"));
                	
                	merch.setEmail(rs.getString("email"));
                	lst.add(merch);
                }
                return lst;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				statement.close();
				con.close();
			}
        	return null;
          
            
        }catch(Exception ex){
            System.out.println("Error is found :"+ex);
            return null;
        }
    }
    public List<AccountMerch> getSaleByDay(String daySeach){
        try{
        	try {
        		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        		
        		List<AccountMerch> lst=new ArrayList<>();
                String sql = "select * from account_merch where  ? = day  ";
                statement =con.prepareStatement(sql);
                statement.setDate(1, new java.sql.Date(df.parse(daySeach).getTime()));
                rs = statement.executeQuery();
                
                System.out.println("Data from online Database :");
                while(rs.next()){
                	AccountMerch merch = new AccountMerch();
                	merch.setId(rs.getInt("id"));
                	merch.setName(rs.getString("name"));
                	merch.setIp(rs.getString("ip"));
                	System.out.println(rs.getDate("day"));
                	merch.setDay(rs.getDate("day"));
                	merch.setSale(rs.getInt("sale"));
                	merch.setMoney(rs.getDouble("money"));
                	merch.setEmail(rs.getString("email"));
                	lst.add(merch);
                }
                return lst;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				statement.close();
				con.close();
			}
        	return null;
          
            
        }catch(Exception ex){
            System.out.println("Error is found :"+ex);
            return null;
        }
    }
//    public static void insert(List<Image> a,String contry)
//    {
//    	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//    	String date= simpleDateFormat.format(new Date());
//    	for (Image entity : a) {
//    		 String sql = "INSERT INTO `spyhours` (`id`, `value`, `count`, `time`,`contry`) VALUES (NULL, '"+entity.value+"', '"+entity.count+"', '"+date+" 00:00:00','"+contry+"')";
//    		  try {
//			st.execute(sql);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//    }
//    
    
//    public static void insert()
//    {SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//    	String date= simpleDateFormat.format(new Date());
//    	
//    		 String sql = "INSERT INTO `spyhours` (`id`, `value`, `count`, `time`,`contry`) VALUES (NULL, '1', '1', '"+date+" 00:00:00','1')";
//    		  try {
//			st.execute(sql);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//    }
}
