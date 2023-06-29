import java.util.Scanner;

public class Address
{
    private String doorNo;
    private String streetNo;
    private String direction;
    private String streetName;
    private String cityName;
    private String stateName;
    private String zipCode;

    public Address(String doorNo, String streetNo, String direction, String streetName, String cityName, String stateName, String zipCode) {
        this.doorNo = doorNo;
        this.streetNo = streetNo;
        this.direction = direction;
        this.streetName = streetName;
        this.cityName = cityName;
        this.stateName = stateName;
        this.zipCode = zipCode;
    }

    public Address()
    {
        this.doorNo = "";
        this.streetNo = "";
        this.direction = "";
        this.streetName = "";
        this.stateName = "";
        this.cityName = "";
        this.zipCode = "";
    }

    public void create(Scanner scanner)
    {
        System.out.println("please input your Street No:");
        //streetNo = scanner.next();

        for (; true; )
        {
           String tmp = scanner.next();
            if(validateInput.isIllegalToken(tmp))
            {
                streetNo = tmp;
                break;
            }
            else
            {
                System.out.println("Please input correct Street No, try again");
            }
        }

        System.out.println("please input the Street direction: W E N S");
        for (; true; )
        {
            String tmp = scanner.next();
            if( tmp.equals("W") || tmp.equals("E") || tmp.equals("N") || tmp.equals("S"))
            {
                direction = tmp;
                break;
            }
            else
            {
                System.out.println("Please input W, E, N, or S");
            }
        }

        System.out.println("please input the Street Name:");
        for (; true; )
        {
            String tmp = scanner.next();
            if(validateInput.isIllegalToken(tmp))
            {
                streetName = tmp;
                break;
            }
            else
            {
                System.out.println("Please input correct Street Name, try again");
            }
        }

        System.out.println("please input your apt No:");
        for (; true; )
        {
            String tmp = scanner.next();
            if(validateInput.isIllegalToken(tmp))
            {
                doorNo = tmp;
                break;
            }
            else
            {
                System.out.println("Please input correct Unit/Apt Number, try again");
            }
        }

        System.out.println("please input your city name:");
        for (; true; )
        {
            String tmp = scanner.next();
            if(validateInput.isIllegalToken(tmp))
            {
                cityName = tmp;
                break;
            }
            else
            {
                System.out.println("Please input correct City Name, try again");
            }
        }

        System.out.println("please input your state:");
        for (; true; )
        {
            String tmp = scanner.next();
            if(validateInput.isIllegalToken(tmp))
            {
                stateName = tmp;
                break;
            }
            else
            {
                System.out.println("Please input correct State Name, try again");
            }
        }

        System.out.println("please input your ZipCode:");
        for (;true;)
        {
            String tmp = scanner.next();
            if(validateInput.isZipCode(tmp))
            {
                zipCode = tmp;
                break;
            }
            else
            {
                System.out.println("Please input correct zipcode, try again");
            }
        }
    }

    public String getAddress()
    {
        String result = "";
        if(!streetNo.equals(""))
        {
            result = "  " + streetNo + " ";
        }

        if(!direction.equals(""))
        {
            result = result + direction + " ";
        }

        if(!streetName.equals(""))
        {
            result = result + streetName + " St, ";
        }

        if(!doorNo.equals(""))
        {
            result = result + "APT "+ doorNo + "\n  ";
        }
        if(!cityName.equals(""))
        {
            result = result + cityName;
        }

        if(!stateName.equals(""))
        {
            result = result + "," + stateName + ", ";
        }
        if(!zipCode.equals(""))
        {
            result = result + zipCode;
        }
        return result;
    }
}
