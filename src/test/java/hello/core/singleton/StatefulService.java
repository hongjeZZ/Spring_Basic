package hello.core.singleton;

public class StatefulService {
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

    /*
    private int price; //상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
     */
}
