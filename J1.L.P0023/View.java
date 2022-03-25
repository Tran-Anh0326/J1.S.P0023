import java.util.ArrayList;

/*
    || ----- FOR SHOP ------
    || Create fruit
    || Display list fruit
    || ViewOrder

    || ----- FOR BUYER -----
    || Shopping 
    || Display list fruits for buyer

*/
public class View {
    Utility u = new Utility();
    Controller c = new Controller();

    // Create fruit
    public void create() {
        String fruitId;
        String fruitName;
        double price;
        int quantity;
        String origin;

        boolean flag = true;
        while (flag) {
            System.out.println("------ Create Fruit ------");
            System.out.print("Enter Fruit ID: ");
            fruitId = u.getString();
            System.out.print("Enter Fruit name: ");
            fruitName = u.getString();
            System.out.print("Enter price: ");
            price = u.getDouble();
            System.out.print("Enter quantity: ");
            quantity = u.getInteger();
            System.out.print("Enter origin: ");
            origin = u.getString();

            try {
                c.createFruits(new Fruit(fruitId, fruitName, price, quantity, origin));
                System.out.println("Create fruit successful!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.print(
                    "Do you want to continue (Y/N)?\nEnter Y to continue, N to return main screen.\nChoose one: ");

            char optionYN = u.getOptionYN(); // return y||Y||n||N
            if (optionYN == 'Y' || optionYN == 'y') {

                continue;
            } else {
                flag = false;
            }

        }
        displayFruits();
    }

    // Display list fruit
    public void displayFruits() {
        ArrayList<Fruit> list = c.getListFruits();
        if (list.isEmpty()) {
            System.out.println("List fruit empty!");
        } else {
            System.out.println("ID   | Fruit name    | Price  | Quantity  | Origin  |");
            for (Fruit fruit : list) {
                System.out.println(fruit);
            }
        }
    }

    // Display list fruits for buyer
    public void displayListFruitForBuyer() {
        ArrayList<Fruit> list = c.getListFruits();
        if (list.isEmpty()) {
            System.out.println("List fruit empty!");
        } else {

            System.out.println("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ |  ");
            for (Fruit fruit : list) {
                // check shop have item or not
                if (fruit.getQuantity() != 0) {
                    System.out.printf("|     %-7d|    %-14s|   %-11s|    %-9s|\n", list.indexOf(fruit) + 1,
                            fruit.getFruitName(), fruit.getOrigin(), fruit.getPrice() + "$");
                }
            }
        }

    }

    // Shopping
    // display list fruits and allow user order in list
    public void Shopping() {

        // list: list fruit of shop
        ArrayList<Fruit> listFruits = c.getListFruits();

        // listOrderCache : store items that customer bought fruit
        ArrayList<Fruit> listOrderCache = c.getListOrdersCache();
        // getListOrdersCache() return an ArrayList be declare in controller
        System.out.println("List of Fruit: ");

        // check fruit in shop
        if (listFruits.isEmpty()) {
            System.out.println("List fruit empty!");

            // To order, customer selects Item
        } else {

            boolean flag = true;// key to exit loop shopping
            while (flag) {
                displayListFruitForBuyer();

                int item;// declare a variable for buyer choose product

                // user must enter a number > 0 && < listFruit.size()
                // loop allow user enter a number to choose item in list, condition item > 0 &&
                // item < list.size()
                while (true) {
                    System.out.print("Choose one item: ");
                    item = u.getInteger();
                    if (item <= listFruits.size() && item > 0) {
                        break;// exit loop
                    } else {
                        System.out.println("You need choose one item in list!");
                    }
                }

                System.out.print("You selected: ");
                Fruit fruitSelected = null;// contain a fruit be user choose
                // loop to find value in index(item) and display fruitName in index(item)
                for (Fruit fruit : listFruits) {
                    if (item - 1 == listFruits.indexOf(fruit)) {
                        System.out.println(fruit.getFruitName());
                        fruitSelected = fruit;
                    }
                }

                System.out.print("Please input quantity: ");
                int quantity = u.getInteger();// allow user enter a positive number
                fruitSelected.setQuantity(quantity);// User information of fruit, just change quantity become
                                                    // quantity of fruit customer selected

                // if listOrder is is empty (first add) -> put to controller
                // -> create an ArrayList to save listOrder of customer
                if (listOrderCache == null) {
                    // add fruitSelected -> listOrderCache
                    c.addOrder(listOrderCache, fruitSelected);

                    // if customer want continue order -> update
                } else if (c.findOrderByID(listOrderCache, fruitSelected) != null) {
                    c.updateOrder(listOrderCache, fruitSelected);

                    // add order in listCache
                } else {

                    c.addOrder(listOrderCache, fruitSelected);
                }

                System.out.print(
                        "Do you want to order now (Y/N)?\nEnter Y to finish order, N to continue order.\nChoose one: ");
                // user must be enter y|Y|n|N
                char optionYN = u.getOptionYN(); // return y||Y||n||N
                if (optionYN == 'Y' || optionYN == 'y') {
                    flag = false;// exit loop shopping

                } else {
                    continue;
                }

            }

            // Display listOrder of customer
            // total = Amount

            double amount = 0;
            System.out.println("| Product     | Quantity | Price | Amount |  ");
            // loop to get data in listOrder of customer and display
            for (Fruit fruit : listOrderCache) {
                System.out.printf("| %-12s| %-9d| %-6s| %-7s|\n", fruit.getFruitName(),
                        fruit.getQuantity(), fruit.getPrice() + "$",
                        fruit.getPrice() * fruit.getQuantity() + "$");// sum of amount , 1 type fruit
                amount += fruit.getPrice() * fruit.getQuantity();// sum of amount -> an Order of customer
            }
            System.out.println("Total: " + amount + "$");

            System.out.print("Input your name: ");
            String customerName = u.getString();
            // put data to controller
            c.Shopping(customerName, listOrderCache);
        }

    }

    // ViewOrder
    public void viewOrder() {
        c.viewOrder();
    }

}
