/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Raymond
 */
public class AccountService {
    String username;
    String password;
    User user;
    
    public User login(String username, String password){
        
        this.username = username;
        this.password = password;
        
        if(username.equals("abe") || username.equals("barb")){
            if(password.equals("password")){
                user = new User(this.username, null);
                return user;
            }
        }
        return user;
    }
}
