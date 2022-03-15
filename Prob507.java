package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;

public class Prob507 {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void printEmployeeList(String cityName, String deptName)throws Exception{
		
		String dburl="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection con=DriverManager.getConnection(dburl,"hr","hr");
		
		/*
		Statement stmt=con.createStatement();
		
		String query="SELECT\r\n"
				+ "L.CITY, D.DEPARTMENT_NAME, E.FIRST_NAME, E.SALARY\r\n"
				+ "FROM LOCATIONS L, DEPARTMENTS D, EMPLOYEES E\r\n"
				+ "WHERE L.LOCATION_ID=D.LOCATION_ID AND D.DEPARTMENT_ID=E.DEPARTMENT_ID\r\n"
				+ "          AND UPPER(L.CITY) LIKE UPPER('%" +cityName + "%')"
				+ "AND UPPER(D.DEPARTMENT_NAME) LIKE UPPER('%"+deptName+"%')";
		
		ResultSet rs=stmt.executeQuery(query);
		*/
		String query="SELECT\r\n"
				+ "L.CITY, D.DEPARTMENT_NAME, E.FIRST_NAME, E.SALARY\r\n"
				+ "FROM LOCATIONS L, DEPARTMENTS D, EMPLOYEES E\r\n"
				+ "WHERE L.LOCATION_ID=D.LOCATION_ID AND D.DEPARTMENT_ID=E.DEPARTMENT_ID\r\n"
				+ "          AND UPPER(L.CITY) LIKE UPPER(?)"
				+ "AND UPPER(D.DEPARTMENT_NAME) LIKE UPPER(?)";
		
		PreparedStatement pstmt=con.prepareStatement(query);
		
		pstmt.setString(1, "%"+cityName+"%");
		pstmt.setString(2, "%"+deptName+"%");
		
		ResultSet rs=pstmt.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getString(1)+" " + rs.getString(2)+" " + rs.getString(3)+ " " + rs.getInt(4));
		}
		
		rs.close();
		//stmt.close();
		con.close();
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		printEmployeeList("lon","resource");

	}

}
