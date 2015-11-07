package cm.grant;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String[] foodNames = {"Sampler Plate", "Mozzarella Sticks", "Hot Wings", "Side Salad", "French Fries", "Mixed Fruit"};
    private static final int[] foodPrices = {580, 335, 355, 420, 275, 215};
    private static final int N = foodNames.length;

    public static void main(String[] args) {
        List<List<String>> orders = getOrders(1505);
        for (List<String> order : orders) {
            System.out.println(order);
        }
    }

    private static List<List<String>> getOrders(int pennies) {
        return getOrders(pennies, new ArrayList<String>(), 0);
    }

    private static List<List<String>> getOrders(int pennies, List<String> order, int index) {
        if (index >= N) {
            List<List<String>> orders = new ArrayList<>();
            if (pennies == 0) {
                orders.add(order);
            }
            return orders;
        }

        int foodCost = foodPrices[index];
        int q = pennies / foodCost;
        List<List<String>> validOrders = new ArrayList<>();
        do {
            ArrayList<String> newOrder = new ArrayList<>(order);
            for (int i = 0; i < q; ++i) {
                newOrder.add(foodNames[index]);
            }
            List<List<String>> orders = getOrders(pennies - q * foodCost, newOrder, index + 1);
            for (List<String> o : orders) {
                validOrders.add(o);
            }
        } while (index < N - 1 && --q >= 0);
        return validOrders;
    }
}
