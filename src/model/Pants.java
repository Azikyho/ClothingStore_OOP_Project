package model;

public class Pants extends ClothingItem {

    private int lengthCm;

    public Pants(int itemId, String name, String size,
                 double price, String brand, int lengthCm) {
        super(itemId, name, size, price, brand);
        this.lengthCm = lengthCm;
    }

    @Override
    public String getType() {
        return "Pants";
    }

    @Override
    public void wear() {
        System.out.println("Wearing pants, length " + lengthCm + " cm");
    }
}
