import java.util.InputMismatchException;
import java.util.Scanner;

public class ChatBot
{
    public ChatBot(DataBase dataBase)
    {
        System.out.println("Welcome to AB bank.");
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        Account tmpAccount = null;
        int type = -1;

        while (true)
        {
            for(;true;)
            {
                // not login
                if(tmpAccount == null)
                {
                    for(;true;)
                    {
                        System.out.println("Please select your Account Type:\n 1 for User\n 2.for Manager \n 3.Exit");
                        try
                        {
                            type = scanner.nextInt();
                            if(type == 1 || type == 2 || type == 3)
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
                    if(type == 3)
                    {
                        System.exit(0);
                    }
                    else if(type == 1)
                    {
                        System.out.println("Please select your Account Type:\n 1 for Personal\n 2.for Business \n 3.Exit");
                        for(;true;)
                        {
                            try
                            {
                                type = scanner.nextInt();
                                if(type == 1 || type == 2 || type == 3)
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

                        if(type == 3)
                        {
                            break;
                        }
                        else
                        {
                            // login
                            System.out.println("Please enter your user Id:");
                            String id = scanner.next();
                            System.out.println("Please enter your password:");
                            String password = scanner.next();
                            if(type == 1)
                            {
                                tmpAccount = new AccountCustomerPersonal(id,password);
                                tmpAccount = tmpAccount.login(dataBase.cashPersonalMap, dataBase.personalAccountList);
                            }
                            else
                            {
                                tmpAccount = new AccountCustomerBusiness(id,password);
                                tmpAccount = tmpAccount.login(dataBase.cashBusincessMap, dataBase.businessAccountList);
                            }
                        }
                    }
                    else
                    {
                        // login
                        System.out.println("Please enter your manager Id:");
                        String id = scanner.next();
                        System.out.println("Please enter your password:");
                        String password = scanner.next();
                        tmpAccount = new AccountManger(id,password);
                        tmpAccount = tmpAccount.login(dataBase.cashManagerMap, dataBase.managerAccountList);
                    }
                }

                // if login success, then continue
                // else break;
                if(tmpAccount == null)
                {
                    tmpAccount = null;
                    String tryAgain = scanner.next();
                    if(tryAgain.equals("No"))
                    {
                        System.exit(0);
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    tmpAccount.print();
                }

                if(tmpAccount instanceof AccountCustomerPersonal)
                {
                    System.out.println("Do you want to check your Balance?\nEnter 'Yes' to check");
                    if(("Yes").equals(scanner.next()))
                    {
                        ((AccountCustomerPersonal) tmpAccount).getBalance();
                    }
                    break;
                }
                else if(tmpAccount instanceof AccountCustomerBusiness)
                {
                    System.out.println("Do you want to check your Balance?\nEnter 'Yes' to check");
                    if(("Yes").equals(scanner.next()))
                    {
                        ((AccountCustomerBusiness) tmpAccount).getBalance();
                    }
                    break;
                }
                else
                {
                    int choose;
                    for(;true;)
                    {
                        System.out.println("What you want to do?\n 1.add new user\n 2.delete user\n 3.search user\n 4.edit user\n 5.list all users\n 6.Exit");
                        try
                        {
                            choose= scanner.nextInt();
                            if(choose == 1 || choose == 2 || choose == 3 || choose == 4 || choose == 5 || choose == 6)
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
                        ((AccountManger) tmpAccount).addNewUser(dataBase,scanner, new Address(), new PhoneNumber());
                        dataBase.updateCashPersonalMap();
                    }
                    else if(choose == 3)
                    {
                        System.out.println("please input User ID:");
                        String userId = scanner.next();
                        ((AccountManger) tmpAccount).searchUser(userId, dataBase).print();
                    }
                    else if(choose == 4)
                    {
                        System.out.println("please input User ID:");
                        String userId = scanner.next();
                        ((AccountManger) tmpAccount).editUser(userId, dataBase, scanner);
                    }
                    else if(choose == 5)
                    {
                        ((AccountManger) tmpAccount).listUsers(dataBase);
                    }
                    else if(choose == 2)
                    {
                        System.out.println("please input User ID:");
                        String deleteId = scanner.next();
                        ((AccountManger) tmpAccount).deleteUser(deleteId,dataBase);
                    }
                    else
                    {
                        tmpAccount = null;
                        break;
                    }
                    break;
                }
            }

            // when login success, then ask for logout.
            if(tmpAccount != null)
            {
                System.out.println("Do you want to logout or continue? \nEnter Yes to logout");
                if(("Yes").equals(scanner.next()))
                {
                    tmpAccount = null;
                    type = -1;
                    System.out.println("Do you want to Exit? \nEnter Yes to Exit.");
                    if(scanner.next().equals("Yes"))
                    {
                        break;
                    }
                }
            }
        }
    }
}
