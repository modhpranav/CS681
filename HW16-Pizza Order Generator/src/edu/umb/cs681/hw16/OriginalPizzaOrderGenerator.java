package edu.umb.cs681.hw16;

public class OriginalPizzaOrderGenerator {
    private String[] toppings = {"Bell Pepper", "Mushrooms", "Onions", "Pineapple"};
    private int numToppings = 0;

    public void addTopping(String topping) {
        toppings[numToppings] = topping;
        numToppings++;
    }

    public String generateOrder() {
        String order = "";
        for (int i = 0; i < numToppings; i++) {
            order += toppings[i] + " ";
        }
        System.out.println("Toppings are: " + order);
        return order;
    }
    public static void main(String[] args) {

        OriginalPizzaOrderGenerator generatorOriginal = new OriginalPizzaOrderGenerator();
        generatorOriginal.addTopping("Bell Pepper");
        generatorOriginal.addTopping("Mushrooms");
        generatorOriginal.addTopping("Onions");
        generatorOriginal.addTopping("Pineapple");
        generatorOriginal.generateOrder();
    }
}
