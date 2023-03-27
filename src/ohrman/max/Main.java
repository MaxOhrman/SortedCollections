package ohrman.max;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.5, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.5, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.5, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        for (String s: stockList.items().keySet()) {
            System.out.printf(s);
        }

        //Test cases
        Basket maxBasket = new Basket("Max");
        sellItem(maxBasket, "car", 1);
        System.out.println(maxBasket);

        sellItem(maxBasket, "car", 1);
        System.out.println(maxBasket);

        sellItem(maxBasket, "car", 1);
        sellItem(maxBasket, "spanner", 5);
        System.out.println(maxBasket);

        sellItem(maxBasket, "juice", 4);
        sellItem(maxBasket, "cup", 12);
        sellItem(maxBasket, "bread", 1);
        System.out.println(maxBasket);

        System.out.println(stockList);


    }

    /**
     *
     * @param basket - basket in which item is placed if exists
     * @param item - the item we want to sell
     * @param quantity - the amount of items
     * @return - returns the quantity sold, can be 0.
     */
    public static int sellItem(Basket basket, String item, int quantity) {
        //retrieve the item from stock list first
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.sellStock(item, quantity) !=0) {
            basket.addToBasket(stockItem, quantity);
            return quantity;
        }
        return 0;
    }
}
