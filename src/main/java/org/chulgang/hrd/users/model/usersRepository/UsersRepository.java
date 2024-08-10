package org.chulgang.hrd.users.model.usersRepository;

import org.chulgang.hrd.users.dto.UsersLoginResponse;
import org.chulgang.hrd.util.DbConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UsersRepository {

    public long signUp(String email, String username, String password, String full_name, String phone) {
        PreparedStatement pstmt =null;
        Connection con= null;
        String sql = UsersSQL.signUp;
        int flag = -1;
        long user_id = 0L;
        try {
            con = DbConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, full_name);
            pstmt.setString(5, phone);
            pstmt.executeUpdate();
            System.out.println("signUp 성공");
            System.out.println("signUp user_id : " + user_id);

            return user_id;
        } catch (SQLException e) {
            System.err.println(e + "Board write  insert SQLException");
            e.printStackTrace();
            flag = -1;
        } finally {
            DbConnection.reset();
            user_id = findSeq();
            return user_id;
        }

    }

    public UsersLoginResponse loginByEmail(String email, String password) {

        String sql = UsersSQL.loginByEmail;
        ResultSet rs;
        UsersLoginResponse dto = new UsersLoginResponse();
        String str1 = "";
        long user_seq = 0L;
        String role = "";
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            user_seq = findSeq();

            String get_role = findRoleByUserId(user_seq);
            System.out.println("레포에서 로그인 롤 : " + get_role);
            while (rs.next()) {
                dto.setId(rs.getInt(1));
                dto.setEmail(rs.getString(2));
                dto.setUsername(rs.getString(3));
                dto.setPassword(rs.getString(4));
                dto.setFull_name(rs.getString(5));
                dto.setPhone(rs.getString(6));
                dto.setRole(get_role);
                str1 = rs.getString(7);
                DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTIme = LocalDateTime.parse(str1, fomatter);
                dto.setCreate_at(dateTIme);
                dto.setModified_at(dateTIme);
            }
            System.out.println("loginByEmail 성공");
            System.out.println("loginByEmail: " + 1);

            return dto;
        } catch (SQLException e) {
            System.out.println(e + "loginByEmailSQLException");
            e.printStackTrace();
        } finally {
            DbConnection.reset();
        }
        return null;
    }

    public UsersLoginResponse loginByUsername(String username, String password) {

        String sql = UsersSQL.loginByUsername;
        ResultSet rs = null;
        UsersLoginResponse dto = new UsersLoginResponse();
        String str1 = "";
        long user_seq = 0L;
        String role = "";
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            user_seq = findSeq();
            System.out.println("레포에서 user_seq : " + user_seq);

            String get_role = findRoleByUserId(user_seq);
            System.out.println("레포에서 로그인 롤 : " + get_role);

            while (rs.next()) {
                dto.setId(rs.getLong(1));
                dto.setEmail(rs.getString(2));
                dto.setUsername(rs.getString(3));
                dto.setPassword(rs.getString(4));
                dto.setFull_name(rs.getString(5));
                dto.setPhone(rs.getString(6));
                dto.setRole(get_role);
                str1 = rs.getString(7);
                DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTIme = LocalDateTime.parse(str1, fomatter);
                dto.setCreate_at(dateTIme);
                dto.setModified_at(dateTIme);
            }
            System.out.println("loginByUsername 성공");
            System.out.println("loginByUsername: " + 1);

            return dto;
        } catch (SQLException e) {
            System.err.println(e + "loginByUsernameSQLException");
            e.printStackTrace();
        } finally {
            DbConnection.reset();
        }
        return null;
    }

    public int modifyMyPage(String email, String username, String password, String full_name, String phone) {

        String sql = UsersSQL.modifyMyPage;
        int flag = -1;

        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, full_name);
            pstmt.setString(3, phone);
            pstmt.setString(4, username);
            pstmt.executeUpdate();
            System.out.println("modifyMyPage 성공");
            System.out.println("modifyMyPage: " + 1);

            return 1;
        } catch (SQLException e) {
            System.err.println(e + "modifyMyPageSQLException");
            e.printStackTrace();
            flag = -1;
        } finally {
            DbConnection.reset();
        }
        return flag;
    }

    public long  findSeq() {
        Connection con = null;
        Statement stmt = null;
        ResultSet  rs =null;
        String sql = UsersSQL.findSeq;
        long users_no = 0L;
        try {
            con = DbConnection.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                users_no = rs.getLong(1);
                System.out.println("findSeq user_no 파인드시퀀::: " + users_no);
            }
            return users_no;
        } catch (SQLException se) {
            System.err.println(se + "getLastMemberNo 메소드에러");
            return -1;
        } finally {
            DbConnection.reset();
        }
    }

    public String findByPassword(String username, String password) {

        String sql = UsersSQL.findByPassword;
        int flag = -1;
        ResultSet rs = null;
        String get_username = "";
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                get_username = rs.getString(1);
            }
            if (get_username == null || get_username == "") {
                return null;
            }


            return get_username;
        } catch (SQLException e) {
            System.err.println(e + "findByPassword SQLException");
            e.printStackTrace();
            flag = -1;
        } finally {
            DbConnection.reset();
        }
        return null;
    }

    public String findByEmail(String email, String password) {

        String sql = UsersSQL.findByEmail;
        int flag = -1;
        ResultSet rs = null;
        String get_email = "";
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                get_email = rs.getString(1);
            }
            if (get_email == null || get_email == "") {
                return null;
            }


            return get_email;
        } catch (SQLException e) {
            System.err.println(e + "findByPassword SQLException");
            e.printStackTrace();
            flag = -1;
        } finally {
            DbConnection.reset();
        }
        return null;
    }

    public int dupplicate(String username) {

        String sql = UsersSQL.dupplicateByUserName;
        int flag = -1;

        String get_email = "";
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                get_email = rs.getString(1);
            }
            if (get_email == null || get_email == "") {
                return -1;
            } else {
                return 1;
            }
        } catch (SQLException e) {
            System.err.println(e + "findByPassword SQLException");
            e.printStackTrace();
            flag = -1;
        } finally {
            DbConnection.reset();
        }
        return 1;
    }

    public int insertUserRole(String email, String password, String role) {

        long users_id = findByEmailAndPassword(email, password);
        String sql = UsersSQL.insertUserRole;
        int flag = -1;
        ResultSet rs = null;
        String get_email = "";
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, users_id);
            pstmt.setString(2, role);
            rs = pstmt.executeQuery();

            return 1;
        } catch (SQLException e) {
            System.err.println(e + "insertUserRole SQLException");
            e.printStackTrace();
            return -1;
        } finally {
            DbConnection.reset();
        }
    }

    public long findByEmailAndPassword(String email, String password) {

        String sql = UsersSQL.findByEmailAndPassword;
        int flag = -1;
        ResultSet rs = null;
        long users_id = 0L;
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                users_id = rs.getLong(1);
            }

            return users_id;
        } catch (SQLException e) {
            System.out.println(e + "findByEmailAndPassword SQLException");
            e.printStackTrace();
            flag = -1;
        } finally {
            DbConnection.reset();
        }
        return 1;
    }

    public String findById(long id) {

        String sql = UsersSQL.findById;
        int flag = -1;
        ResultSet rs = null;
        String users_full_name = "";
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                users_full_name = rs.getString(1);
            }
            return users_full_name;
        } catch (SQLException e) {
            System.err.println(e + "selectByFullName SQLException");
            e.printStackTrace();
            flag = -1;
        } finally {
            DbConnection.reset();
        }
        return null;
    }

    public int deleteById(long id) {

        String sql = UsersSQL.deleteById;
        int flag = -1;
        ResultSet rs = null;
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            flag = 1;
            return flag;
        } catch (SQLException e) {
            System.err.println(e + "deleteById SQLException");
            e.printStackTrace();
            flag = -1;
        } finally {
            DbConnection.reset();
        }
        return flag;
    }

    public String findRoleByUserId(long id) {

        String sql = UsersSQL.findRoleByUserId;
        int flag = -1;

        String role = "";
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                role = rs.getString(1);
            }
            return role;
        } catch (SQLException e) {
            System.err.println(e + "findRoleByUserId SQLException");
            e.printStackTrace();
            flag = -1;
        } finally {
            DbConnection.reset();
        }
        return null;
    }
}

