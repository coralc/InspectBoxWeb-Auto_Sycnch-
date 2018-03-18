package com.inspectbox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.inspectbox.objetLayout.ObjetGammesNiveau;

public class GammesNiveauObjetDAO {
	public void insert(ArrayList<ObjetGammesNiveau> gammesList){
		
		Connection  con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO gammesniveauobjet (idGammes, idNiveauObjet, idClient, masque) VALUES (?,?,?,?)";
		try{
			con = JDBCConnect.establishConnection();
			pstmt = con.prepareStatement(sql);
			for (ObjetGammesNiveau gammes : gammesList){
				
			pstmt.setInt(1,gammes.getIdGammes() );
			pstmt.setInt(2, gammes.getIdNiveau());
			pstmt.setInt(3, gammes.getIdClient());
			pstmt.setBoolean(4, false);
			// execute insert SQL stetement
		//	pstmt.executeUpdate();
			pstmt.addBatch();
			}
			
			int[] updateCounts = pstmt.executeBatch();
		  System.out.println("rows affected "+updateCounts.length);
		  }
		  catch (SQLException s){
		  System.out.println("SQL statement is not executed!");
		  s.printStackTrace();
		  }finally {			  
				try {
					if (pstmt != null) {pstmt.close();	}
					} catch (SQLException e) {
					e.printStackTrace();
					}	 
				try {
					if (con != null) { con.close(); }
					} catch (SQLException e) {
					e.printStackTrace();
					}	 
		}
	}
	
	public void delete(Integer idGammes){
		
		Connection  con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE from gammesniveauobjet WHERE idgammes = ?";;
		try{
			con = JDBCConnect.establishConnection();
			pstmt = con.prepareStatement(sql);
				
			pstmt.setInt(1,idGammes );
			int count = pstmt.executeUpdate();
		  System.out.println("rows deletd "+count);
		  }
		  catch (SQLException s){
		  System.out.println("SQL statement is not executed!");
		  s.printStackTrace();
		  }finally {			  
				try {
					if (pstmt != null) {pstmt.close();	}
					} catch (SQLException e) {
					e.printStackTrace();
					}	 
				try {
					if (con != null) { con.close(); }
					} catch (SQLException e) {
					e.printStackTrace();
					}	 
		}
	}
	
	
}
