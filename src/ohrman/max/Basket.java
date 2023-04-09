package ohrman.max;

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
     * @param item     the stock item we want to add to the basket
     * @param quantity amount of items to add to basket
     */
    public void addToBasket(StockItem item, int quantity) {
        if((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
        }
    }

    // For testing purposes
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nShopping basket " + name + " contains " + list.size() + ((list.size() == 1) ? " item " : " items") + "\n");
        double totalCost = 0.0;
        for(Map.Entry<StockItem, Integer> item : list.entrySet() ) {
            s.append(item.getKey()).append(". ").append(item.getValue()).append(" purchased\n");
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }
}
