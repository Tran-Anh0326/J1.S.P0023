public class Validate {
    boolean checkSelect(int n) {

        if (n >= 1 && n <= 4) {
            return true;
        } else {
            return false;
        }

    }

    boolean checkEmpty(String n) {
        return n.trim().isEmpty();
    }

    // this function check input positive
    boolean positiveCheck(double n) {

        return n > 0 ? true : false;
    }
    boolean checkYN(String yn) {
        char check = yn.charAt(0);
        return check == 'y' || check == 'Y' || check == 'n' || check == 'N' ? true : false;
    }
}
