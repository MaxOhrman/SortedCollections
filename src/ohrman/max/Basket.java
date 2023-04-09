package ohrman.max;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }

    /**
     * Method adds item of quantity to our Map called list
     *
     * @param item the stock item we want to add to the basket
     * @param quantity amount of items to add to basket
     * @return either the amount added or if item is null or quantity <=0 return 0
     */
    public int addToBasket(StockItem item, int quantity) {
        if((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    /**
     * Return our list in unmodifiable form
     *
     * @return Unmodifiable Map List
     */
    public Map<StockItem, Integer> items() {
        return Collections.unmodifiableMap(list);
    }

    // For testing purposes
    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + list.size() + ((list.size() == 1) ? " item " : " items") + "\n";
        double totalCost = 0.0;
        for(Map.Entry<StockItem, Integer> item : list.entrySet() ) {
            s = s + item.getKey() + ". " + item.getValue() + " purchased\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }
}
