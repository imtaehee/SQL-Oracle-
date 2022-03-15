package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

public class Prob5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if(args.length !=1) {
			System.out.println("부서의 아이디를 입력하세요");
			System.exit(1);
		}
		String dept_id=args[0];
		
		Connection con=null;
		//Statement stmt=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String user="hr";
			String password="hr";

		    con=DriverManager.getConnection(url,user,password);
		    /*
			stmt=con.createStatement();
			
			rs=stmt.executeQuery("SELECT\r\n"
			    + "E.EMPLOYEE_ID, D.DEPARTMENT_NAME, E.SALARY, VT.평균 AVG_SALARY\r\n"
			    + "FROM EMPLOYEES E, DEPARTMENTS D,\r\n"
			    + "(SELECT AVG(SALARY) 평균, DEPARTMENT_ID\r\n"
			    + "FROM EMPLOYEES\r\n"
			    + "WHERE DEPARTMENT_ID=' "+dept_id
			    + "'GROUP BY DEPARTMENT_ID) VT\r\n"
				+ "WHERE E.DEPARTMENT_ID=D.DEPARTMENT_ID AND D.DEPARTMENT_ID=VT.DEPARTMENT_ID\r\n"
				+ "ORDER BY E.EMPLOYEE_ID ");
			*/
		    
		    String query="SELECT\r\n"
				    + "E.EMPLOYEE_ID, D.DEPARTMENT_NAME, E.SALARY, VT.평균 AVG_SALARY\r\n"
				    + "FROM EMPLOYEES E, DEPARTMENTS D,\r\n"
				    + "(SELECT AVG(SALARY) 평균, DEPARTMENT_ID\r\n"
				    + "FROM EMPLOYEES\r\n"
				    + "WHERE DEPARTMENT_ID=?"
				    + "GROUP BY DEPARTMENT_ID) VT\r\n"
					+ "WHERE E.DEPARTMENT_ID=D.DEPARTMENT_ID AND D.DEPARTMENT_ID=VT.DEPARTMENT_ID\r\n"
					+ "ORDER BY E.EMPLOYEE_ID";
		    pstmt=con.prepareStatement(query);
		    
		    pstmt.setString(1, dept_id);
		    
		    rs=pstmt.executeQuery();
		    
			while(rs.next()) {
			 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getInt(4));
		    }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    	}finally {
			try {
				rs.close();
				//if(stmt != null) stmt.close();
				if(pstmt != null) pstmt.close();
			    if(con != null) con.close();
		    }catch(SQLException e) {
			    e.printStackTrace();
		    }
		}

	}

}
