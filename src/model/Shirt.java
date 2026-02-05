package model;

public class Shirt extends ClothingItem {

    public Shirt(int itemId, String name, String size, double price, String brand) {
        super(itemId, name, size, price, brand);
    }

    @Override
    public String getType() {
        return "Shirt";
    }

    @Override
    public void wear() {
        System.out.println("Wearing a shirt");
    }
}
