package ohrman.max;

import com.sun.jdi.Value;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new HashMap<>();
    }

    // Returns quantity if adding/overwriting stock item else 0 if null
    public int addStock(StockItem item) {
        if (item != null) {
            // inStock either will reference an existing object from our list if it exists, otherwise it will
            // reference item which is the default
            // getOrDefault(key, defaultValue)
            // key: which is the key of the element whose value has to be obtained.
            // defaultValue: which is the default value that has to be returned, if no value is mapped with the specified key.
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // If there are already stocks of this item, adjust quantity
            if (inStock != item) { // true if item was already in stock list
                item.adjustStock(inStock.quantityInStock());
            }

            list.put(item.getName(), item); // overwrite item in stock or add it
            return item.quantityInStock();
        }
        return 0;
    }

    //Returns the amount of items sold if that amount exists in stock, otherwise return 0
    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);
        // If item exist in list, its quantity is above the amount we want to sell and the amount we want to sell
        // is not negative or 0
        if ((inStock != null) && (list.get(item).quantityInStock() >= quantity) && (quantity > 0)) {
            inStock.adjustStock(-quantity);
            return quantity;
        }

        return 0;
    }

    //Pass the name of a stock item and return it as a object
    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, StockItem> items() {
        return Collections.unmodifiableMap(list); // we don't want this Map to be modified. This is faster than creating a new one
    }

    //This is a bad idea in production, we should instead implement proper debugging code.
    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            StockItem stockItem = item.getValue(); // a single item
            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

            s = s + stockItem + ". There are " + stockItem.quantityInStock() + " in stock. Value of items: ";
            s = s + String.format("%.2f",itemValue) + "\n";
            totalCost += itemValue;
        }

        return s + "Total stock value " + totalCost;
    }
}
