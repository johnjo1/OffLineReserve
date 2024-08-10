package org.chulgang.hrd.users.model.usersRepository;

public class UsersSQL {
    static final String findAll = "select * from users";

    static final String signUp = "insert into users values(users_seq.nextval,?,?,?,?,?,sysdate,sysdate)";
    static final String modifyMyPage = "update  users set email = ? , full_name = ? , phone = ?, modified_at = sysdate where username = ?";
    static final String loginByEmail ="select * from users where email = ? and password = ?";
    static final String loginByUsername ="select * from users where username =? and password = ?";
    static final String findByPassword = "select username from users where username = ? and password = ?";
    static final String findByEmail = "select username from users where email = ? and password = ?";
    static final String dupplicateByUserName ="select username from users where username = ?";
    static final String insertUserRole = "insert into user_role values(USER_ROLE_SEQ.nextval,? ,? )";
    static final String findByEmailAndPassword = "select id from users where email = ? and password = ?";
    public static final String findById = "select full_name from users where id = ?";
    public static final String deleteById = "delete from users where id = ?";
 // 다른 엔티티있어서 필요없음   public static final String findByUsersId = "select current_amount from wallet_history where id = ?";
    public static final String findRoleByUserId ="select role from user_role r join users u on r.user_id = u.id where u.id = ?";
    public static final String findSeq ="SELECT MAX(ROWNUM) FROM USERS";




}
