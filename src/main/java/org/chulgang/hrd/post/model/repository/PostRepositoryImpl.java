package org.chulgang.hrd.post.model.repository;

import org.chulgang.hrd.post.domain.Post;
import org.chulgang.hrd.post.model.sql.PostSQL;
import org.chulgang.hrd.users.dto.UsersLoginResponse;
import org.chulgang.hrd.util.DbConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static org.chulgang.hrd.post.model.sql.PostSQL.*;

public class PostRepositoryImpl implements PostRepository {

    public ArrayList<Post> posts() {
        ArrayList<Post> posts = new ArrayList<>();
        DbConnection.getConnection();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = PostSQL.Posts;
        Post viewpost = null;

        try {
            con = DbConnection.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println("하이");

            while (rs.next()) {
                long id = rs.getInt(1);
                long writer_id = rs.getLong(2);
                System.out.println("writer_id: " + writer_id);
                String subject = rs.getString(3);
                String content = rs.getString(4);
                int view = rs.getInt(5);
                viewpost = new Post(id, writer_id, subject, content, view);

                posts.add(viewpost);
            }
            return posts;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ㅎㅇㅎㅇ");
            return null;
        }
    }

    public ArrayList<Post> posts(String full_name) {
        ArrayList<Post> posts = new ArrayList<>();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = PostSQL.viewPosts;

        Post viewpost = null;

        try {
            con = DbConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, full_name);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                long id = rs.getInt(1);
                long writer_id = rs.getLong(2);
                String subject = rs.getString(3);
                String content = rs.getString(4);
                long view_count = rs.getLong(5);

                viewpost = new Post(id, writer_id, subject, content, view_count);
                posts.add(viewpost);
            }
            return posts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DbConnection.reset();
        }
    }

    public void insert_posts(Post post) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = insertPost;

        try {
            con = DbConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            System.out.println("getWriter_id" + post.getWriter_id());
            System.out.println("getSubject" + post.getSubject());
            System.out.println("getSubject" + post.getContent());
            System.out.println("getViewCount" + post.getView_count());


            pstmt.setLong(1, post.getWriter_id());
            pstmt.setString(2, post.getSubject());
            pstmt.setString(3, post.getContent());
            pstmt.setLong(4, post.getView_count());
            pstmt.setString(5, post.getFull_name());

            int i = pstmt.executeUpdate();
            System.out.println("Rows affected: " + i);
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DbConnection.reset();
        }
    }

    @Override
    public ArrayList<UsersLoginResponse> list_postsS() {
        ArrayList<UsersLoginResponse> list_posts = new ArrayList<>();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = PostSQL.viewPostList;

        try {
            con = DbConnection.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                UsersLoginResponse viewpost = new UsersLoginResponse();
                String full_name = rs.getString(1);
                viewpost.setFull_name(full_name);
                list_posts.add(viewpost);
            }
            return list_posts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DbConnection.reset();
        }
    }

    //    public void incrementViewCount(long postId) throws SQLException {
//
//        Connection con = null;
//        String query = "UPDATE post SET view_count = view_count + 1 WHERE id = ?";
//        try {
//            con= DbConnection.getConnection();
//            PreparedStatement stmt = con.prepareStatement(query);
//            stmt.setLong(1, postId);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            DbConnection.reset();
//        }
//    }
//
//    public long getViewCount(long postId) throws SQLException {
//
//        Connection con = null;
//        String query = "SELECT view_count FROM post WHERE id = ?";
//
//        try {
//            con= DbConnection.getConnection();
//            PreparedStatement stmt = con.prepareStatement(query);
//
//            stmt.setLong(1, postId);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                return rs.getInt("view_count");
//            } else {
//                return 0; // Default if post not found
//            }
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            DbConnection.reset();
//        }
//        return -1;
//    }
//    //게시글 삭제 메소드 추가함
    @Override
    public void deletePost(long postId) {

        Connection con = null;
        String query = deletePost;
        PreparedStatement pstmt =null;
        try {
            con= DbConnection.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setLong(1, postId);
            pstmt.executeUpdate();
            System.out.println("삭제성공");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbConnection.reset();
        }
    }
    //게시글 제목 및 내용 가져오는 메소드
    @Override
    public Post getSubjectAndContent(long postId) {
        Connection con = null;
        String query = selectPosts;
        System.out.println("query::" + query);
        ResultSet rs = null;
        PreparedStatement pstmt =null;

        System.out.println("postId  getSubjectAndContent::" + postId);
        try {
            con= DbConnection.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setLong(1, postId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setSubject(rs.getString(1));
                post.setContent(rs.getString(2));
                return post;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbConnection.reset();
        }
        return null;
    }

    public void update_posts(Post post){
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = updatePost;

        try {
            con = DbConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, post.getSubject());
            pstmt.setString(2, post.getContent());
            pstmt.setLong(3, post.getId());

            int i = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DbConnection.reset();
        }
    }
}


//
//
//
//    public static void select() {
//
//        Connection con = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        String sql = "select * from  dept";
//        int flag = -1;
//
//        try {
//            con = DbConnection.getConnection();
//            stmt = con.createStatement();
//
//            rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                int a = rs.getInt(1);
//                String b = rs.getString(2);
//                String c = rs.getString(3);
//                System.out.println(rs.getInt(1));
//            }
//            System.out.println("insert_users성공");
//
//        } catch (SQLException e) {
//            System.err.println(e + "Board write  insert SQLException");
//            e.printStackTrace();
//
//        }
//    }

