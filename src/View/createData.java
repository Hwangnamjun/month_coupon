package View;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import DB.DBConnection;

public class createData {

	public Connection getconnection() {
		Connection conn = null;
		
		DBConnection DBc = DBConnection.getinstance();
		
		conn = DBc.getconnection();

		return conn;
	}
	
	public void closeSQL(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int checkMem(String mem)
	{
		int result = 0;
		Connection con = getconnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			String sql = "SELECT * FROM CHK_OPTION_MEM WHERE MEMBER_NO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem);
			rs = pstmt.executeQuery();
			
			if(!rs.next())
			{
				result = 1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeSQL(con, pstmt, rs);
		}
		return result;
	}
	
	public String insertCoupon(String mem, int flag) 
	{
		String result = "";
		Calendar c2 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String s_date = (sdf.format(c2.getTime())).substring(0,6) + "01";
        String e_date =(sdf.format(c2.getTime())).substring(0,6) + c2.getActualMaximum(Calendar.DAY_OF_MONTH);
        
		Connection con = getconnection();
		CallableStatement cstmt = null;
		
		try 
		{
			cstmt = con.prepareCall("{call PR_CM150_003_TMP(?,?,?,?,?,?,?)}");
			
			cstmt.setString(1, mem);//회원번호
			cstmt.setInt(2, 5);//할인율
			cstmt.setString(3, s_date);//시작일
			cstmt.setString(4, e_date);//종료일
			cstmt.setInt(5, 5);//수량
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);//성공 0 실패 9
			cstmt.registerOutParameter(7, java.sql.Types.VARCHAR);//에러 내용

			cstmt.execute();

			if(Integer.parseInt(cstmt.getString(6)) == 9)
			{
				result = "실패 : " + cstmt.getString(7);
			}
			else
			{
				result = "지급완료";
				
				InetAddress local;
				local = InetAddress.getLocalHost();
				
				String ip = local.getHostAddress();
				
				String sql = "insert into chk_option_mem values(?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mem);
				pstmt.setString(2, ip);
				int chk = pstmt.executeUpdate();
				if(chk == 1)
				{
					String sql2 = "insert into cm081 (MEMBER_NO, MEMBER_FLAG, IEMP_NO, IDATE) values(?,?,?,sysdate)";
					PreparedStatement pstmt2 = con.prepareStatement(sql2);
					pstmt2.setString(1, mem);
					pstmt2.setString(2, flag+"");
					pstmt2.setString(3, "PG");
					pstmt2.execute();
					pstmt2.close();
				}
				pstmt.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				cstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
}















