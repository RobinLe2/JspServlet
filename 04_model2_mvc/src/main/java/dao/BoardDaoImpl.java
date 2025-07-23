package dao;

import model.dto.BoardDTO;
import model.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDaoImpl implements BoardDao {
    private BoardDaoImpl() {}

    private static BoardDao dao = new BoardDaoImpl();

    public static BoardDao getInstance() {
        return dao;
    }

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    //----- 연결
    @Override
    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_jdbc?characterEncoding=UTF-8&serverTimezone=UTC",
                    "goodee", "goodee");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    //---- 자원해제
    @Override
    public void close() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---- 조회(목록)
    @Override
    public List<BoardDTO> getBoards() {
        List<BoardDTO> boards = new ArrayList<>();
        try {
            con = getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT b.bid, u.uid, u.nickname, b.title, b.content, b.created_at, b.modified_at ");
            sb.append("FROM tbl_board b ");
            sb.append("JOIN tbl_user u ON b.uid = u.uid ");
            sb.append("ORDER BY bid DESC ");
            sb.append("LIMIT 0, 10");
            sql = sb.toString();

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int bid = rs.getInt("bid");
                int uid = rs.getInt("uid");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String nickname = rs.getString("nickname");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp modifiedAt = rs.getTimestamp("modified_at");

                BoardDTO board = new BoardDTO();
                board.setBid(bid);
                board.setTitle(title);
                board.setContent(content);
                board.setCreatedAt(createdAt);
                board.setModifiedAt(modifiedAt);

                UserDTO user = new UserDTO();
                user.setUid(uid);
                user.setNickname(nickname);
                board.setUser(user);

                boards.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return boards;
    }

    //---- 조회(단일 항목)
    @Override
    public BoardDTO getBoardById(int bid) {
        BoardDTO board = null;
        try {
            con = getConnection();

            StringBuilder sb = new StringBuilder();
            sb.append("SELECT b.bid, u.uid, u.nickname, b.title, b.content, b.created_at, b.modified_at ");
            sb.append("FROM tbl_board b ");
            sb.append("JOIN tbl_user u ON b.uid = u.uid ");
            sb.append("WHERE b.bid = ?");
            sql = sb.toString();

            ps = con.prepareStatement(sql);
            ps.setInt(1, bid);
            rs = ps.executeQuery();

            if (rs.next()) {
                board = new BoardDTO();
                board.setBid(rs.getInt("bid"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setCreatedAt(rs.getTimestamp("created_at"));
                board.setModifiedAt(rs.getTimestamp("modified_at"));

                UserDTO user = new UserDTO();
                user.setUid(rs.getInt("uid"));
                user.setNickname(rs.getString("nickname"));
                board.setUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return board;
    }

    //---- 삽입
    @Override
    public int insertBoard(BoardDTO board) {
        int count = 0;
        try {
            con = getConnection();
            sql = "INSERT INTO tbl_board (uid, title, content) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, board.getUser().getUid());
            ps.setString(2, board.getTitle());
            ps.setString(3, board.getContent());
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return count;
    }

    //---- 삭제
    @Override
    public int deleteBoard(int bid) {
        int count = 0;
        try {
            con = getConnection();
            sql = "DELETE FROM tbl_board WHERE bid = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, bid);
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return count;
    }

    //---- 수정
    @Override
    public int updateBoard(BoardDTO board) {
        int count = 0;
        try {
            con = getConnection();
            sql = "UPDATE tbl_board SET title = ?, content = ?, modified_at = NOW() WHERE bid = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, board.getUser().getUid());
            ps.setString(2, board.getTitle());
            ps.setInt(3, board.getBid());
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return count;
    }
}
