import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManger extends Account implements UserOperation
{
    private final String accountType = "Manager Account";

    public AccountManger(String userId, String userPass)
    {
        super(userId,userPass);
    }

    public AccountManger(String userId, String userPass, String userName)
    {
        super(userId,userPass,userName);
    }
    @Override
    void print()
    {
        System.out.println("==========================");
        System.out.printf("%12s : %s%n","User Name"  , super.getUserName());
        System.out.printf("%12s : %s%n","User Id"    , super.getUserPass());
        System.out.printf("%12s : %s%n","AccountType", this.accountType);
        System.out.println("==========================");
    }
    @Override
    public void addNewUser(DataBase dataBase, Scanner scanner, Address address, PhoneNumber phoneNumber)
    {
        System.out.println("Please input User Name:");
        String name;
        for (;true;)
        {
            name = scanner.next();
            if(validateInput.isAName(name)) { break; }
            else
            {
                System.out.println("Please enter the correct name:");
            }
        }

        System.out.println("Please input User id:");
        String id = "";
        for(;true;)
        {
            id= scanner.next();
            if(dataBase.cashPersonalMap.containsKey(id)||dataBase.cashBusincessMap.containsKey(id)||dataBase.cashManagerMap.containsKey(id))
            {
                System.out.println("ID is exit, please input another one.");
            }
            else
            {
                break;
            }
        }

        System.out.println("Please input User password:");
        String password = scanner.next();

        System.out.println("Please input User balance:");
        Double balance = null;
        for(;true;)
        {
            balance = validateInput.isFloatNumber(scanner.next());
            if( balance != null)
            {
                break;
            }
            else
            {
                System.out.println("please input a float Number:");
            }
        }

        int type;
        for(;true;)
        {
            System.out.println("Please Select account type:\n 1.personal account\n 2.business account");
            try
            {
                type= scanner.nextInt();
                if(type == 1 || type == 2)
                {
                    break;
                }
                else
                {
                    System.out.println("You enter a wrong number, please try again\n");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please input a number, try again\n");
                scanner.next();
            }
        }

        if(type ==1)
        {
            System.out.println("Please input E-mail ID:");
            String email;
            for(;true;)
            {
                email = scanner.next();
                if( validateInput.isEmailID(email))
                {
                    break;
                }
                else
                {
                    System.out.println(email + " is not a Email, please try again");
                }
            }
            System.out.println("Please input phone number");
            phoneNumber.create(scanner);
            System.out.println("Please input Address");
            address.create(scanner);
            AccountCustomerPersonal newUser = new AccountCustomerPersonal(id, password, name, balance);
            newUser.setPhoneNumber(phoneNumber);
            newUser.setEmail(email);
            newUser.setAddress(address);
            dataBase.personalAccountList.add(newUser);
            newUser.print();
        }
        else
        {
            System.out.println("Please input new address:\nEnter '#' to end");
            String stringAddress;
            for ( ;true;)
            {
                stringAddress = "";
                for(;true;)
                {
                    String tmp = scanner.next();

                    if(tmp.equals("#"))
                    {
                        break;
                    }
                    else
                    {
                        stringAddress = stringAddress +"\n" + tmp;
                    }
                }
                if(validateInput.isIllegalToken(stringAddress))
                {
                    break;
                }
                else
                {
                    System.out.println("Wrong Address, please try again.");
                }
            }
            AccountCustomerBusiness newUser = new AccountCustomerBusiness(id, password, name, balance);
            newUser.setAddress(stringAddress);
            dataBase.businessAccountList.add(newUser);
            newUser.print();
        }
    }

    @Override
    public void deleteUser(String id, DataBase dataBase)
    {
        Account targetAccount = searchUser(id,dataBase);
        if(targetAccount!=null)
        {
            targetAccount.delete();
            System.out.println("success delete User: " + id);
        }
    }

    @Override
    public void editUser(String id, DataBase dataBase, Scanner scanner)
    {
        Account targetAccount = searchUser(id,dataBase);
        if(targetAccount != null)
        {
            if(targetAccount instanceof AccountCustomerPersonal)
            {
                System.out.println("Please select the which one you want to edit?\n 1.Address\n 2.phone Number\n 3.Email ID");
                int choose = -1;
                for(;true;)
                {
                    try
                    {
                        choose= scanner.nextInt();
                        if(choose == 1 || choose == 2 || choose == 3)
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("You enter a wrong number, please try again\n");
                        }
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Please input a number, try again\n");
                        scanner.next();
                    }
                }

                if(choose == 1)
                {
                    Address address = new Address();
                    address.create(scanner);
                    ((AccountCustomerPersonal)targetAccount).setAddress(address);
                }

                else if(choose == 2)
                {
                    PhoneNumber phoneNumber = new PhoneNumber();
                    phoneNumber.create(scanner);
                    ((AccountCustomerPersonal)targetAccount).setPhoneNumber(phoneNumber);
                }
                else
                {
                    System.out.println("Please input new Email Id:");
                    String email;
                    for(;true;)
                    {
                        email = scanner.next();
                        if( validateInput.isEmailID(email))
                        {
                            ((AccountCustomerPersonal)targetAccount).setEmail(email);
                            break;
                        }
                        else
                        {
                            System.out.println(email + " is not a Email, please try again");
                        }
                    }
                }
                System.out.println("User Info Update Successful\n");
                targetAccount.print();
            }
            else
            {
                System.out.println("Please input new address:\nEnter '#' to end");
                String stringAddress;
                for ( ;true;)
                {
                    stringAddress = "";
                    for(;true;)
                    {
                        String tmp = scanner.next();

                        if(tmp.equals("#"))
                        {
                            break;
                        }
                        else
                        {
                            stringAddress = stringAddress +"\n" + tmp;
                        }
                    }
                    if(validateInput.isIllegalToken(stringAddress))
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Wrong Address, please try again.");
                    }
                }

                ((AccountCustomerBusiness)targetAccount).setAddress(stringAddress);
                System.out.println("User Info Update Successful\n");
                targetAccount.print();
            }
        }
    }

    public Account searchUser(String id, DataBase dataBase)
    {
        if(dataBase.cashPersonalMap.containsKey(id))
        {
            int index = dataBase.cashPersonalMap.get(id);
            return dataBase.personalAccountList.get(index);
        }
        else if (dataBase.cashBusincessMap.containsKey(id))
        {
            int index = dataBase.cashBusincessMap.get(id);
            return dataBase.businessAccountList.get(index);
        }
        else
        {
            System.out.println("can not find user: " + id);
            return null;
        }
    }

    @Override
    public void listUsers(DataBase dataBase)
    {
        System.out.println("==Personal Account==");
        dataBase.personalAccountList.stream()
                .forEach(
                        e -> {
                            if(!((AccountCustomerPersonal)e).isDelete()){
                                e.print();
                            }
                        });
        System.out.println("==Business Account==");
        dataBase.businessAccountList.stream()
                .forEach(
                        e -> {
                            if(!((AccountCustomerBusiness)e).isDelete()){
                                e.print();
                            }
                        });
    }
}
