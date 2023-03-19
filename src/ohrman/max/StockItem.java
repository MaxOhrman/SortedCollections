package ohrman.max;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityStock;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;
    }

    //Overload constructor to optionally set a quantityStock when creating the instance
    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int quantityInStock() {
        return quantityStock;
    }

    public void setPrice(double price) {
        if (price > 0.0) {
            this.price = price;
        }
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityStock + quantity;
        if (newQuantity >= 0) {
            this.quantityStock = newQuantity;
        }
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");
        if (obj == this) {
            return true;
        }

        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        // The reason we add a number to the hash is for example a completely different object
        // also checks for name in its equals and hashcode methods they could end up with the same
        // hash code without being equal
        return this.name.hashCode() + 31;
    }

    /*
     Returns negative integer = this object is less than
     Returns zero = this object is equal to
     Returns positive integer = this object is greater than
     the specified object.
     */
    @Override
    public int compareTo(StockItem o) {
        System.out.println("Entering StockItem.compareTo");
        //If the object o is the same object as in memory as this object
        if (this == o) {
            return 0;
        }

        //Not equal, not null. Lets compare
        if (o != null) {
            return this.name.compareTo(o.getName());
        }

        //o is null, throw exception
        throw new NullPointerException();
    }

    //For testing purposes
    @Override
    public String toString() {
        return this.name + this.price;
    }
}
