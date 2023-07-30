package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	static final private String SQL_SELECTALL = "SELECT o.*, p.* FROM T_ORDER o JOIN T_PRODUCT p USING(P_NUM)";
	static final private String SQL_SELECTONE = "SELECT o.*, p.* FROM T_ORDER o JOIN T_PRODUCT p USING(P_NUM) WHERE O_NUM = ?";
	static final private String SQL_INSERT = "INSERT INTO T_ORDER (O_NAME, O_PHONE, O_ADDRESS, O_ADDRESSDET, P_NUM) VALUES(?, ?, ?, ?, ?)";	
	
	public ArrayList<OrderVO> selectAll(OrderVO oVO){
		conn = JDBCUtil.connect();

		ArrayList<OrderVO> datas = new ArrayList<OrderVO>();

		try {
			pstmt = conn.prepareStatement(SQL_SELECTALL);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				OrderVO data = new OrderVO();
				data.setoNum(rs.getInt("O_NUM"));
				data.setoName(rs.getString("O_NAME"));
				data.setoPhone(rs.getString("O_PHONE"));
				data.setoAddress(rs.getString("O_ADDRESS"));
				data.setoAddressDet(rs.getString("O_ADDRESSDET"));
				data.setpNum(rs.getInt("P_NUM"));
				data.setpName(rs.getString("P_NAME"));
				data.setpPrice(rs.getInt("P_PRICE"));
				data.setpImage(rs.getString("P_IMAGE"));
				datas.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JDBCUtil.disconnect(rs, pstmt, conn);

		return datas;
	}
	public OrderVO selectOne(OrderVO oVO){
		conn = JDBCUtil.connect();

		OrderVO data = null;
		
		try {
			pstmt =conn.prepareStatement(SQL_SELECTONE);
			pstmt.setInt(1, oVO.getoNum());
			rs=pstmt.executeQuery();

			if(rs.next()) {
				data=new OrderVO();
				data.setoNum(rs.getInt("O_NUM"));
				data.setoName(rs.getString("O_NAME"));
				data.setoPhone(rs.getString("O_PHONE"));
				data.setoAddress(rs.getString("O_ADDRESS"));
				data.setoAddressDet(rs.getString("O_ADDRESSDET"));
				data.setpNum(rs.getInt("P_NUM"));
				data.setpName(rs.getString("P_NAME"));
				data.setpPrice(rs.getInt("P_PRICE"));
				data.setpImage(rs.getString("P_IMAGE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JDBCUtil.disconnect(rs, pstmt, conn);

		return data;
	}

	public boolean insert(OrderVO oVO) {
		conn=JDBCUtil.connect();

		try {
			pstmt=conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, oVO.getoName());
			pstmt.setString(2, oVO.getoPhone());
			pstmt.setString(3, oVO.getoAddress());
			pstmt.setString(4, oVO.getoAddressDet());
			pstmt.setInt(5, oVO.getpNum());
			int rs=pstmt.executeUpdate();
			if(rs<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		JDBCUtil.disconnect(pstmt, conn);

		return true;
	}
}
