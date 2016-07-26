package ProjectUtils;

import Model.User;

import java.util.ArrayList;

/**
 * Created by domowy on 2016-07-18.
 */
public class Login {
    public static boolean entry(String login, String password,ArrayList<User> usersList ){
        boolean loginEquals = false;
        for(User user : usersList)
            if(login.equals(user.getUserName()) && password.equals(user.getPass())){
                System.out.println("Jestes zalogowany");

                loginEquals = true;
            }
            else {
                System.out.println("Bładne hasło lub login");
            }
        return loginEquals;

    }
}
