package com.inspectbox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.primefaces.model.TreeNode;

import com.inspectbox.objetLayout.ObjetGammes;
import com.inspectbox.objetLayout.ObjetGammesNiveau;

public class GammesNiveauDAO{

	public void insert(ArrayList<ObjetGammesNiveau> gammesList){
		
		Connection  con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO gammesniveau (idGammes, idNiveau, idClient, masque) VALUES (?,?,?,?)";
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
		String sql = "DELETE from gammesniveau WHERE idgammes = ?";
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
	
	public ArrayList<ObjetGammesNiveau> fetchGammesNiveauList(Integer idGammes, Integer idClient){
		Connection  con = null;
		PreparedStatement pstmt = null;
		ArrayList<ObjetGammesNiveau> gammesList=  new ArrayList<ObjetGammesNiveau>();
		ObjetGammesNiveau gammesNiveau = null;
		String sql = "SELECT idniveau from gammesniveau WHERE idGammes=? and idclient=?";
		try{
			con = JDBCConnect.establishConnection();
			pstmt = con.prepareStatement(sql);
			System.out.println("step1");
			pstmt.setInt(1, idGammes.intValue());
			pstmt.setInt(2, idClient);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("step2");
			while (rs.next()) {
				gammesNiveau = new ObjetGammesNiveau();
				gammesNiveau.setIdClient(idClient);
				gammesNiveau.setIdGammes(idGammes);
				gammesNiveau.setIdNiveau(rs.getInt("idniveau"));
				gammesList.add(gammesNiveau);
			}
		  } catch (SQLException s){
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

		return gammesList;
	}
	
	public static void main(String[] args){
		System.out.println("before call");
	//	ArrayList<ObjetGammesNiveau> gammesList =  new GammesNiveauDAO().fetchGammesNiveauList(44, 1);
	//	System.out.println("before call list size "+gammesList.size());
		
		Calendar today = Calendar.getInstance();  
		// Subtract 1 day  
		today.add(Calendar.DATE, -1);  
		
		
		// Make an SQL Date out of that  
		java.util.Date yesterday = new java.sql.Date(today.getTimeInMillis()); 
		System.out.println("yesterday "+yesterday);
				   
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");  
		String s = df.format(yesterday); 
		System.out.println("yesterday "+s);
	}
}
