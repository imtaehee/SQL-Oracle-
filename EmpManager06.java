package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

public class EmpManager06 {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void printEmployee(String city, int lo, int hi) throws SQLException{
		
		String dburl="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection con=DriverManager.getConnection(dburl,"hr","hr");
		
		/*
		Statement stmt=con.createStatement();
		String query="SELECT\r\n"
				+ "E.LAST_NAME, D.DEPARTMENT_NAME, E.SALARY\r\n"
				+ "FROM EMPLOYEES E, DEPARTMENTS D, LOCATIONS L\r\n"
				+ "WHERE E.DEPARTMENT_ID=D.DEPARTMENT_ID AND D.LOCATION_ID=L.LOCATION_ID\r\n"
				+ "          AND L.CITY='"+city+"'"
				+ "          AND E.SALARY BETWEEN "+lo+" AND "+hi;
		
		ResultSet rs=stmt.executeQuery(query);
		*/
		String query="SELECT\r\n"
				+ "E.LAST_NAME, D.DEPARTMENT_NAME, E.SALARY\r\n"
				+ "FROM EMPLOYEES E, DEPARTMENTS D, LOCATIONS L\r\n"
				+ "WHERE E.DEPARTMENT_ID=D.DEPARTMENT_ID AND D.LOCATION_ID=L.LOCATION_ID\r\n"
				+ "          AND L.CITY=?"
				+ "          AND E.SALARY BETWEEN ? AND ?";
		
		PreparedStatement pstmt=con.prepareStatement(query);
		pstmt.setString(1, city);
		pstmt.setInt(2, lo);
		pstmt.setInt(3, hi);
		
		ResultSet rs=pstmt.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getString(1)+" "+rs.getString(2));
		}
		
	}

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		new EmpManager06().printEmployee("South San Francisco", 7000, 10000);
	}

}
