package com.alodiga.hsm.access.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCon {
	private static String url="jdbc:mysql://localhost:3306/minibodegon?useUnicode=true&characterEncoding=utf-8";
	private static String user="root";
	private static String pS="1nt3r4xt3l3ph0nyDB";
	private static Connection connection=null;
	private DBCon(){
	}
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		if(connection==null){
			connection=DriverManager.getConnection(url,user,pS);
		}
		connection.setAutoCommit(false);
		return connection;
	}

	public static void free(ResultSet rs,PreparedStatement pst,Connection conn){
		try{
			if(rs!=null){
				rs.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(pst!=null){
					pst.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try{
					if(conn!=null){
						conn.close();
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
