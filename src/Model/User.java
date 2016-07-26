package Model;



import java.io.Serializable;

/**
 * Created by domowy on 2016-07-18.
 */
public class User implements Serializable {

    private String name;
    private String surname;
    private String email;
    private String userName;
    private String pass;
    private int age;

    public User(){
        this.name = "name";
        this.surname = "surname";
        this.email = "emial";
        this.userName = "user name";
        this.pass = "pass";
        this.age = 15;
    }


    public User(String name, String surname,String email,String userName, String pass , int age){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userName = userName;
        this.pass = pass;
        this.age = age;
    }

    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getEmail(){
        return email;
    }
    public String getUserName(){
        return userName;
    }
    public String getPass(){
        return pass;
    }
    public Integer getAge(){
        return age;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public void setAge(Integer age){
        this.age= age;
    }

    public static User createUserFromRegistration(String name, String surname,String email,String userName, String pass , int age){
        User u = new User();
        u.setName(name);
        u.setSurname(surname);
        u.setEmail(email);
        u.setUserName(userName);
        u.setPass(pass);
        u.setAge(age);
        return u;
    }
    public String toString(){
        return  " name " + getName() + " surname " + getSurname() + " emial " + getEmail()   + " login " + getUserName() + " password " + getPass() + " age " + getAge();
    }
}
