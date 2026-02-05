package model;

public abstract class ClothingItem {

    protected int itemId;
    protected String name;
    protected String size;
    protected double price;
    protected String brand;

    public ClothingItem(int itemId, String name, String size,
                        double price, String brand) {
        this.itemId = itemId;
        this.name = name;
        this.size = size;
        this.price = price;
        this.brand = brand;
    }

    public abstract String getType();
    public abstract void wear();
}
