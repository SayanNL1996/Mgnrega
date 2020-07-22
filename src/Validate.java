import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    Scanner scanner = new Scanner(System.in);
    private String dateFormat;

    public String validateEmail() {
        String email = null;
        boolean result = false;
        while (result != true) {
            System.out.println("Enter Email:");
            email = scanner.nextLine();
            result = isValid(email);
        }
        return email;
    }

    public boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public String validatePin() {
        String pincode = null;
        boolean result = false;
        while (result != true) {
            System.out.println("Enter Pincode:");
            pincode = scanner.nextLine();
            result = pin(pincode);
        }
        return pincode;
    }

    public boolean pin(String pincode) {
        String regex
                = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$";
        Pattern p = Pattern.compile(regex);
        if (pincode == null) {
            return false;
        }
        Matcher m = p.matcher(pincode);
        return m.matches();

    }

    public int validateAge() {
        int age = 0;
        boolean result = false;
        while (result != true) {
            System.out.println("Enter age:");
            age = scanner.nextInt();
            result = age(age);
        }
        return age;
    }

    public boolean age(int age) {
        if (age > 18 && age < 60) {
            return true;
        }
        return false;
    }

    public String validateString() {
        String res = null;
        boolean result = false;
        while (result != true) {
            System.out.println("Enter name:");
            res = scanner.nextLine();
            result = valstring(res);
        }
        return res;
    }

    public boolean valstring(String res) {
        return res.matches("[A-Z][a-z]*");
    }

    public String validatePAssword() {
        String password = null;
        boolean result = false;
        while (result != true) {
            System.out.println("Enter Password(Must be atleast 7 digit long):");
            password = scanner.nextLine();
            result = valpass(password);
        }
        return password;
    }

    public boolean valpass(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{7,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public String validateDate() {
        String d = null;
        boolean result = false;
        while (result != true) {
            System.out.println("Enter date in DD/MM/YYYY:");
            d = scanner.nextLine();
            result = valdate(d);
        }
        return d;
    }

    public boolean valdate(String d) {
        String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(d);
        return matcher.matches();

    }

}
