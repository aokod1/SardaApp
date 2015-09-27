package group4ie.com.sardaapp;

/**
 * Created by akodi on 9/3/2015.
 */
public class Contact
{
    int id;
    String Name,Email,User,Pass;

    public Contact(String name, String email, String usr, String pss)
    {
        Name = name;
        Email = email;
        User = usr;
        Pass = pss;
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
        return Pass;
    }

    public void setPass(String pss)
    {
        Pass = pss;
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
