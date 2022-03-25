public class Main {
    public static void main(String[] args) {
        Utility u = new Utility();
        View view = new View();
        while (true) {
            System.out.print("FRUIT SHOP SYSTEM\n"+
        "1.	Create Fruit\n"+
        "2.	View orders\n"+
        "3.	Shopping (for buyer)\n"+
        "4.	Exit\n"+
        "Choose one: ");
        int select = u.getSelect();
        switch (select) {
            case 1:
                view.create();
                break;
            case 2:
                view.viewOrder();
                break;
            case 3:
                 view.Shopping();
                 break;
            default:
                System.exit(0);
                break;
        }
        }
        
    }

}
