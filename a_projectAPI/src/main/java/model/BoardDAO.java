package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	static final private String SQL_SELECTALL = "SELECT b.*, o.O_NAME FROM T_BOARD b JOIN T_ORDER o USING(O_NUM)";
	static final private String SQL_SELECTONE = "SELECT b.*, o.O_NAME FROM T_BOARD b JOIN T_ORDER o USING(O_NUM) WHERE B_NUM = ?";
	static final private String SQL_INSERT = "INSERT INTO T_BOARD (B_TITLE, B_CONTENT, O_NUM) VALUES(?, ?, ?)";	
	
	public ArrayList<BoardVO> selectAll(BoardVO bVO){
		conn = JDBCUtil.connect();

		ArrayList<BoardVO> datas = new ArrayList<BoardVO>();

		try {
			pstmt = conn.prepareStatement(SQL_SELECTALL);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				BoardVO data = new BoardVO();
				data.setbNum(rs.getInt("B_NUM"));
				data.setbTitle(rs.getString("B_TITLE"));
				data.setbContent(rs.getString("B_CONTENT"));
				data.setoNum(rs.getInt("O_NUM"));
				data.setoName(rs.getString("O_NAME"));
				datas.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JDBCUtil.disconnect(rs, pstmt, conn);

		return datas;
	}
	public BoardVO selectOne(BoardVO bVO){
		conn = JDBCUtil.connect();

		BoardVO data = null;
		
		try {
			pstmt =conn.prepareStatement(SQL_SELECTONE);
			pstmt.setInt(1, bVO.getbNum());
			rs=pstmt.executeQuery();

			if(rs.next()) {
				data=new BoardVO();
				data.setbNum(rs.getInt("B_NUM"));
				data.setbTitle(rs.getString("B_TITLE"));
				data.setbContent(rs.getString("B_CONTENT"));
				data.setoNum(rs.getInt("O_NUM"));
				data.setoName(rs.getString("O_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JDBCUtil.disconnect(rs, pstmt, conn);

		return data;
	}

	public boolean insert(BoardVO bVO) {
		conn=JDBCUtil.connect();

		try {
			pstmt=conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, bVO.getbTitle());
			pstmt.setString(2, bVO.getbContent());
			pstmt.setInt(3, bVO.getoNum());
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
