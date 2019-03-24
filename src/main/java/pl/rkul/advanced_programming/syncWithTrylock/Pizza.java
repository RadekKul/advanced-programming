package pl.rkul.advanced_programming.syncWithTrylock;

public class Pizza {

    String pizzaName;
    String ingredients;

    public Pizza(String pizzaName, String ingredients) {
        this.pizzaName = pizzaName;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "pizzaName='" + pizzaName + '\'' +
                '}';
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getIngredients() {
        return ingredients;
    }
}
