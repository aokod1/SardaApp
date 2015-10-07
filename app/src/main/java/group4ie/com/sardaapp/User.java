package group4ie.com.sardaapp;

/**
 * Created by akodi on 9/3/2015.
 */
public class User
{
    int id;
    String Name,Email,User,Password;

    public User(String name, String email, String usr, String pss)
    {
        Name = name;
        Email = email;
        User = usr;
        Password = pss;
    }

    public User(String user,String pass)
    {
        User = user;
        Password = pass;
    }

    public void setName(String nme)
    {
        Name = nme;
    }

    public String getName()
    {
        return Name;
    }

    public void setUser(String usr)
    {
        User = usr;
    }

    public String getUser()
    {
        return User;
    }

    public String getPass()
    {
        return Password;
    }

    public void setPass(String pss)
    {
        Password = pss;
    }

    public void setEmail(String em)
    {
        Email = em;
    }

    public String getEmail()
    {
        return Email;
    }


}
