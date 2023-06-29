import java.util.InputMismatchException;
import java.util.Scanner;

public class PhoneNumber
{
    private int countryNumber;
    private long phoneNumber;

    public PhoneNumber() {
    }

    public PhoneNumber(int countryNumber, long phoneNumber) {
        this.countryNumber = countryNumber;
        this.phoneNumber = phoneNumber;
    }

    public void create(Scanner scanner) {
        System.out.println("please input Country No:\n eg: US user input 1 or +1");
        for (; true; )
        {
            Integer country = validateInput.isCountryNumber(scanner.next());
            if(country != null)
            {
                countryNumber = country;
                break;
            }
            else
            {
                System.out.println("Please input correct country number, try again");
            }
        }

        System.out.println("please input the phone number:");
        for (; true; )
        {
            Long phone = validateInput.isPhoneNumber(scanner.next());
            if(phone != null)
            {
                phoneNumber = phone;
                break;
            }
            else
            {
                System.out.println("Please input correct phone number, try again");
            }
        }
    }

    public String getPhoneNumber()
    {
        return "+" + countryNumber + " " + phoneNumber;
    }
}
