package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	static final private String SQL_SELECTALL = "SELECT * FROM T_PRODUCT";
	static final private String SQL_SELECTONE = "SELECT * FROM T_PRODUCT WHERE P_NUM = ?";
	static final private String SQL_INSERT = "INSERT INTO T_PRODUCT (P_NAME, P_PRICE, P_IMAGE) VALUES(?, ?, ?)";	
	
	public ArrayList<ProductVO> selectAll(ProductVO pVO){
		conn = JDBCUtil.connect();

		ArrayList<ProductVO> datas = new ArrayList<ProductVO>();

		try {
			pstmt = conn.prepareStatement(SQL_SELECTALL);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				ProductVO data = new ProductVO();
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
	public ProductVO selectOne(ProductVO pVO){
		conn = JDBCUtil.connect();

		ProductVO data = null;
		
		try {
			pstmt =conn.prepareStatement(SQL_SELECTONE);
			pstmt.setInt(1, pVO.getpNum());
			rs=pstmt.executeQuery();

			if(rs.next()) {
				data=new ProductVO();
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

	public boolean insert(ProductVO pVO) {
		conn=JDBCUtil.connect();

		try {
			pstmt=conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, pVO.getpName());
			pstmt.setInt(2, pVO.getpPrice());
			pstmt.setString(3, pVO.getpImage());
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
