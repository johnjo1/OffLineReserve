package org.chulgang.hrd.users.model.usersService;

import org.chulgang.hrd.users.dto.UsersLoginResponse;
import org.chulgang.hrd.users.model.usersRepository.UsersRepository;
import org.chulgang.hrd.util.DbConnection;

public class UsersService {
    static UsersRepository usersrepository;
    public static UsersService service = new UsersService();
    UsersService(){
        usersrepository = new UsersRepository();
    }

    public long signUp(String email, String username, String password, String full_name , String phone){
        return usersrepository.signUp(email, username, password, full_name, phone);
    }
    public int modifyMyPage(String email, String username, String password, String full_name , String phone){
        return usersrepository.modifyMyPage(email, username, password, full_name, phone);
    }
    public UsersLoginResponse loginByEmail(String email, String password){
        return usersrepository.loginByEmail(email,password);
    }
    public UsersLoginResponse loginByUsername(String username, String password){
        return usersrepository.loginByUsername(username,password);
    }
    public String findByPassword(String username ,String password){
        return usersrepository.findByPassword(username,password);
    }
    public String findByEmail(String email,String password){
        return usersrepository.findByEmail(email,password);
    }
    public int dupplicate(String username){
        return usersrepository.dupplicate(username);
    }
    public int insertUserRole(String email ,String password,String role){
        return usersrepository.insertUserRole(email,password,role);
    }
    public String findById(long id){
        return usersrepository.findById(id);
    }
   /* public String findRoleByUserId(long users_id){
        return usersrepository.findRoleByUserId(users_id);
    }*/

    public int deleteById(long id){
        return usersrepository.deleteById(id);
    }

    public static UsersService getInstance(){
        DbConnection.getConnection();
        return service;
    }
}
