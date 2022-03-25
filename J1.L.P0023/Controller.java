
import java.util.ArrayList;
import java.util.Hashtable;
/*
    || ----- FOR SHOP ------
    || Create fruit
    || Get list fruits
    || Find fruit by id
    || Find order by id
    || show view order

    
    || ----- FOR BUYER -----
    || Shopping 
    || Create order
    || Update order
    || Get list orders
*/

public class Controller {
    ArrayList<Fruit> listFruits = new ArrayList<>();
    Hashtable<String, ArrayList<Fruit>> hTable = new Hashtable<String, ArrayList<Fruit>>();

    // find fruit by id
    // exist -> that fruit || no exist -> null
    Fruit findFruitByID(String id) {
        if (listFruits.isEmpty()) {
            return null;
        }
        // loop to check duplicate id
        for (Fruit fruit : listFruits) {
            if (fruit.getFruitId().equals(id))
                return fruit;
        }
        return null;
    }

    // Find order by id
    // exist -> order || no exist -> null
    Fruit findOrderByID(ArrayList<Fruit> cache, Fruit f) {
        // if listOrder of customer is empty -> return null
        if (cache.isEmpty()) {
            return null;
        }
        // loop check fruit existed or not in listOrder of customer
        for (Fruit fruit : cache) {
            if (fruit.getFruitId().equals(f.getFruitId()))
                return f;
        }

        return null;
    }

    // Create fruit
    // check duplicate fruit by id
    public void createFruits(Fruit e) throws Exception {
        String id = e.getFruitId();
        // check exist of fruit -> if existed -> print error
        if (findFruitByID(id) != null)
            throw new Exception("Duplicate ID!");
        listFruits.add(e);
    }

    // Get list fruits
    public ArrayList<Fruit> getListFruits() {
        return listFruits;
    }

    // Shopping
    // add a order and a customer to hashTable
    // rule of listOrder is save Order of customer
    public void Shopping(String name, ArrayList<Fruit> listOderCache) {        
        hTable.put(name, listOderCache);
    }

    // Update order
    // increase quantity of fruit user selected
    public void updateOrder(ArrayList<Fruit> listOrderCache, Fruit f) {
        // loop in listOrderCache, if FruitID in list = FruitID input -> update quantity
        for (Fruit fruit : listOrderCache) {
            
            if (fruit.getFruitId().equalsIgnoreCase(f.getFruitId())) {
                // set new quantity = old quantity + new quantity in input
                fruit.setQuantity(fruit.getQuantity() + f.getQuantity());

            }
        }
    }

    // Create order
    // add order of customer in listOrderCache
    public void addOrder(ArrayList<Fruit> listOrdersCache, Fruit f) {

        listOrdersCache.add(f);

    }

    // Get list orders
    // declare ArrayList to save Order of a customer
    public ArrayList<Fruit> getListOrdersCache() {
        ArrayList<Fruit> listOrdersCache = new ArrayList<>();
        return listOrdersCache;
    }

    // show view order
    void viewOrder() {
        
        if (hTable.isEmpty()) {
            System.out.println("No orders yet!");
        } else {
            // traverse all key in hashTable
            for (String name : hTable.keySet()) {
                System.out.println("Customer: " + name);
                // get listOrder of a customer and assign to list1
                ArrayList<Fruit> list1 = new ArrayList<>();
                list1 = (hTable.get(name));
                
                double amount = 0;
                System.out.println("| Product     | Quantity | Price | Amount |  ");
                // loop to display ListOrder of a customer
                for (Fruit fruit : list1) {
                    System.out.printf("| %-12s| %-9d| %-6s| %-7s|\n", fruit.getFruitName(),
                            fruit.getQuantity(), fruit.getPrice() + "$",
                            fruit.getPrice() * fruit.getQuantity() + "$");
                    amount += fruit.getPrice() * fruit.getQuantity();
                }
                System.out.println("Total: " + amount + "$");

            }
        }

    }
}
