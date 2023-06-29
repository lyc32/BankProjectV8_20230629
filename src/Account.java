import java.util.List;
import java.util.Map;

public abstract class Account
{
    private String userId;
    private String userPass;
    private String userName;
    private boolean delete = false;

    public Account(String userId, String userPass)
    {
        this.userId = userId;
        this.userPass = userPass;
    }

    public Account(String userId, String userPass, String userName)
    {
        this.userId = userId;
        this.userPass = userPass;
        this.userName = userName;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public void setUserPass(String userPass)
    {
        this.userPass = userPass;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserId()
    {
        return this.userId;
    }

    public String getUserPass()
    {
        return this.userPass;
    }

    public String getUserName()
    {
        return this.userName;
    }


    public void delete()
    {
        this.delete = true;
    }

    public boolean isDelete()
    {
        return this.delete;
    }

    public Account login(Map<String,Integer> cashMap,List<Account> accountList)
    {
        if(cashMap.containsKey(this.userId))
        {
            int index = cashMap.get(this.userId);
            Account tmp = accountList.get(index);
            if(tmp.getUserPass().equals(this.userPass))
            {
                if(tmp.isDelete())
                {
                    System.out.println("Your account has been suspended, please connect the Manager\nInput any key to continue");
                    return null;
                }
                return tmp;
            }
            else
            {
                System.out.println("your ID and password not match. Do you want to try again.\nEnter 'No' to Exit.");
                return null;
            }
        }
        else
        {
            System.out.println("User is not Exit, Do you want to try again.\nEnter 'No' to Exit.");
            return null;
        }
    }

    abstract void print();
}
