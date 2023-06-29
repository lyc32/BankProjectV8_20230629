import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validateInput
{
    public static Boolean isInteger(String input)
    {
        return false;
    }

    public static Double isFloatNumber(String input)
    {
        String result = input.replace(" ", "");
        result = result.replace(",", "");
        Pattern pattern = Pattern.compile("[+|-]?[0-9]{1,}(.[0-9]{1,})?");
        Matcher matcher = pattern.matcher(result);
        if (matcher.matches())
        {
            try
            {
                return Double.valueOf(result);
            }
            catch (NumberFormatException e)
            {
                return null;
            }
        }
        System.out.println("'" + input + "' is not a float number.");
        return null;
    }
    public static boolean isAName(String input)
    {
        String[] tokenList = Pattern.compile("[ ,]").split(input);
        Pattern pattern = Pattern.compile("[A-Z|a-z]+");
        Matcher matcher;
        for(String s:tokenList)
        {
            matcher = pattern.matcher(s);
            if(!matcher.matches())
            {
                System.out.println("[Error] => " + s);
                return false;
            }
        }
        return true;
    }

    public static Integer isCountryNumber(String input)
    {
        String result = input.replace(" ", "");
        result = result.replace("+", "");
        Pattern pattern = Pattern.compile("[0-9]{1,3}");
        Matcher matcher = pattern.matcher(result);
        if (matcher.matches())
        {
            try
            {
                return Integer.valueOf(result);
            }
            catch (NumberFormatException e)
            {
                return null;
            }
        }
        System.out.println("'" + input + "' is not a Country Number.");
        return null;
    }

    public static Long isPhoneNumber(String input)
    {
        String result = input.replace(" ", "");
        result = result.replace("-", "");
        Pattern pattern = Pattern.compile("[0-9]{10,11}");
        Matcher matcher = pattern.matcher(result);
        if (matcher.matches())
        {
            try
            {
                return Long.valueOf(result);
            }
            catch (NumberFormatException e)
            {
                return null;
            }
        }
        System.out.println("'" + input + "' is not a phone number.");
        return null;
    }

    public static boolean isEmailID(String input)
    {
        Pattern pattern = Pattern.compile("[a-z|A-Z|0-9]+@[a-z|A-Z|0-9]+.[a-z|A-Z|0-9]+");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches())
        {
            return true;
        }
        return false;
    }

    public static boolean isIllegalToken(String input)
    {
        String tmp = input.replaceAll(",|:|-|\r|\n"," ");
        String[] tokenList = tmp.split(" ");
        Pattern pattern = Pattern.compile("[a-z|A-Z|0-9]+");
        Matcher matcher;
        for(String s: tokenList)
        {
            s = s.replace(" ", "");
            matcher = pattern.matcher(s);
            if ( !matcher.matches() && s.length() > 0)
            {
                System.out.println("[Error] => " + s);
                return false;
            }
        }
        return true;
    }

    public static boolean isZipCode(String input)
    {
        Pattern pattern = Pattern.compile("[0-9]{5}(-[0-9]{4})?");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches())
        {
            return true;
        }
        return false;
    }
}
