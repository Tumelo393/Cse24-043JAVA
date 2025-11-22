package controller;

public class LoginController {
    // demo credentials: admin / password
    public boolean authenticate(String username, String password){
        return "admin".equals(username) && "password".equals(password);
    }
}
