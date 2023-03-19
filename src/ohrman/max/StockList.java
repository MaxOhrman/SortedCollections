package ohrman.max;

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
                item.adjustStock(inStock.getQuantityStock());
            }

            list.put(item.getName(), item); // overwrite item in stock or add it
            return item.getQuantityStock();
        }
        return 0;
    }

    //Returns the amount of items sold if that amount exists in stock, otherwise return 0
    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);
        // If item exist in list, its quantity is above the amount we want to sell and the amount we want to sell
        // is not negative or 0
        if ((inStock != null) && (list.get(item).getQuantityStock() >= quantity) && (quantity > 0)) {
            inStock.adjustStock(-quantity);
            return quantity;
        }

        return 0;
    }


}
