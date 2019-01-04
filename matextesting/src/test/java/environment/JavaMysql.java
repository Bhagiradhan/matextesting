package environment;

import java.sql.*;

public class JavaMysql {

	public static String getpassword() {
		String pa = null;
		try {
			String myUrl = "jdbc:sqlserver://192.168.2.16:1433" + ";databaseName=FinalMatrixTestingDB" + "";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			Connection conn = DriverManager.getConnection(myUrl, "Sa", "Sql@123");
			

			if (conn != null) {
				String query = "OPEN Symmetric KEy Mat_Key  DECRYPTION By Certificate Matrix_cert\r\n" + "   \r\n"
						+ "select User_name,convert(varchar(50),decryptbykey(User_password)),User_id,User_Userinfoid from tbl_master_webusers where User_name='demotl'\r\n"
						+ "\r\n" + "close Symmetric KEy Mat_Key";

				// create the java statement
				Statement st = conn.createStatement();

				// execute the query, and get a java resultset
				ResultSet rs = st.executeQuery(query);
				System.out.println(rs);
				while (rs.next()) {
					String us = rs.getString("User_name");
					pa = rs.getString("");
					System.out.println(us + "  " + pa);
					
				}

				conn.close();
				return pa;
			}
		} catch (Exception e) {
			System.out.println(e);
			return e.getMessage().toString();
		}
		return pa;
	}

}
