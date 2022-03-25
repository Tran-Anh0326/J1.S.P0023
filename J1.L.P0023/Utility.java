import java.util.Scanner;

public class Utility {
    Validate validate = new Validate();
    Scanner sc = new Scanner(System.in);
    // allow user input a number domain [1-]
    public int getSelect() {
        int n;
        while (true) {
            try {
                n = Integer.parseInt(sc.nextLine());// if n have character -> jump to exception
                // check input require: a positive number
                if (validate.checkSelect(n))
                    return n;
                else {
                    System.out.print("You need enter a number from 1 - 4!\nEnter again: ");

                }

            } catch (Exception e) {
                System.err.print("You need enter a number!\nEnter again: ");

            }

        }

    }
    // allow user enter a String
    public String getString() {
        String s;
        while (true) {
            try {
                s = sc.nextLine();
            if (validate.checkEmpty(s))
                System.out.print("This section cannot be left blank! Enter again: ");
            else {
                return s;
            }
            } catch (Exception e) {
                System.out.println("Get failed! Enter again: ");
            }
            
        }
    }
    public double getDouble() {
        double n;
        while (true) {
            try {
                n = Double.parseDouble(sc.nextLine());// if n have character -> jump to exception
                if (validate.positiveCheck(n))
                    return n;
                else {
                    System.out.print("You need enter a positive number!\nEnter again: ");

                }
                // check input require: a positive number

            } catch (Exception e) {
                System.err.print("You need enter a number!\nEnter again: ");

            }

        }
    }
    // allow user enter a number > 0
    public int getInteger() {
        int n;
        while (true) {
            try {
                n = Integer.parseInt(sc.nextLine());// if n have character -> jump to exception
                if (validate.positiveCheck(n))
                    return n;
                else {
                    System.out.print("You need enter a positive number!\nEnter again: ");

                }
                // check input require: a positive number

            } catch (Exception e) {
                System.err.print("You need enter a number!\nEnter again: ");

            }

        }
    }
    char getOptionYN(){
        String yn;
        while (true) {
            try {
                yn = sc.nextLine().trim();
                if (validate.checkYN(yn)) {
                    
                    return yn.charAt(0);
                } else {
                    System.out.print("You need enter y or n! Enter again: ");
                }
            } catch (Exception e) {
                System.out.print("get Option Y/N failed! Enter again: ");
            }
        }
    }
}
