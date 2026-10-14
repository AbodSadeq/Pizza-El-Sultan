package org.example;

import org.example.delivery.InternalDelivery;
import org.example.menu.Menu;
import org.example.model.*;
import org.example.notification.*;
import org.example.payment.*;
import org.example.pricing.*;
import org.example.receipt.Receipt;

public class Main {
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("         PIZZA EL SULTAN DEMONSTRATION");
        System.out.println("==============================================");

        // 1. Menu, add-ons, and products
        Menu menu = new Menu();
        AddOn extraCheese = new AddOn("Extra Cheese", 25);
        AddOn olives = new AddOn("Olives", 15);
        Pizza margherita = new Pizza("Margherita", 100, true, PizzaSize.MEDIUM);
        margherita.addAddOn(extraCheese);
        margherita.addAddOn(olives);
        Pizza chickenRanch = new Pizza("Chicken Ranch", 160, true, PizzaSize.SMALL);
        Drink cola = new Drink("Cola", 30, true);
        Drink water = new Drink("Water", 15, true);
        GarlicBread garlicBread = new GarlicBread("Garlic Bread", 50, true);
        menu.addItem(margherita);
        menu.addItem(chickenRanch);
        menu.addItem(cola);
        menu.addItem(water);
        menu.addItem(garlicBread);

        // 2. Discount rules
        OrderPricingCalculator calculator = new OrderPricingCalculator();
        DiscountRule pizzaDiscount = new PizzaDiscount(15, 300);
        DiscountRule subtotalDiscount = new SubtotalDiscount(10, 500);
        calculator.addDiscount(pizzaDiscount);
        calculator.addDiscount(subtotalDiscount);

        // 3. Takeaway order
        System.out.println();
        System.out.println("==============================================");
        System.out.println("              TAKEAWAY ORDER");
        System.out.println("==============================================");
        Customer takeawayCustomer = new Customer("Ali", "0120000000", "Amman");
        Order takeawayOrder = new Order(1, takeawayCustomer, OrderType.TAKEAWAY);
        OrderItem takeawayPizza = new OrderItem(margherita, 2);
        OrderItem takeawayCola = new OrderItem(cola, 2);
        takeawayOrder.addItem(takeawayPizza, menu);
        takeawayOrder.addItem(takeawayCola, menu);
        PriceSummary takeawaySummary = calculator.calculate(takeawayOrder);
        printPricing(takeawaySummary);

        CardPayment cardPayment = new CardPayment(takeawaySummary.getFinalTotal());
        PaymentStatus takeawayPaymentStatus = cardPayment.pay();
        takeawayOrder.setPayment(cardPayment);
        System.out.println();
        System.out.println("Payment status: " + takeawayPaymentStatus);
        System.out.println("Payment successful: " + cardPayment.isSuccessful());
        System.out.println("Order status after payment: " + takeawayOrder.getStatus());
        takeawayOrder.markReady();
        System.out.println("Order status after preparation: " + takeawayOrder.getStatus());
        NotificationChannel sms = new SmsNotification();
        sms.send(takeawayOrder);
        Receipt takeawayReceipt = new Receipt();
        takeawayReceipt.print(takeawayOrder, takeawaySummary);

        // 4. Delivery order
        System.out.println();
        System.out.println("==============================================");
        System.out.println("              DELIVERY ORDER");
        System.out.println("==============================================");
        Customer deliveryCustomer = new Customer("Omar", "0101111111", "Zarqa");
        Order deliveryOrder = new Order(2, deliveryCustomer, OrderType.DELIVERY);
        OrderItem deliveryPizza = new OrderItem(margherita, 1);
        OrderItem deliveryGarlicBread = new OrderItem(garlicBread, 2);
        OrderItem deliveryCola = new OrderItem(cola, 3);
        deliveryOrder.addItem(deliveryPizza, menu);
        deliveryOrder.addItem(deliveryGarlicBread, menu);
        deliveryOrder.addItem(deliveryCola, menu);
        PriceSummary deliverySummary = calculator.calculate(deliveryOrder);
        printPricing(deliverySummary);
        CashPayment cashPayment = new CashPayment(
                deliverySummary.getFinalTotal(), deliverySummary.getFinalTotal() + 100);
        PaymentStatus deliveryPaymentStatus = cashPayment.pay();
        deliveryOrder.setPayment(cashPayment);
        System.out.println();
        System.out.println("Payment status: " + deliveryPaymentStatus);
        System.out.println("Payment successful: " + cashPayment.isSuccessful());
        System.out.println("Change: " + cashPayment.getChange());
        System.out.println("Order status after payment: " + deliveryOrder.getStatus());
        deliveryOrder.markReady();
        System.out.println("Order status after preparation: " + deliveryOrder.getStatus());
        // Assign while the order is READY, before notification completes it.
        InternalDelivery internalDelivery = new InternalDelivery();
        System.out.println("Delivery status before assignment: " + internalDelivery.track());
        internalDelivery.assign(deliveryOrder);
        System.out.println("Delivery status after assignment: " + internalDelivery.track());
        System.out.println("Estimated delivery time: "
                + internalDelivery.estimateDeliveryTime() + " minutes");

        NotificationChannel whatsapp = new WhatsAppNotification();
        whatsapp.send(deliveryOrder);
        Receipt deliveryReceipt = new Receipt();
        deliveryReceipt.print(deliveryOrder, deliverySummary);

        // 5. Failed payment scenario
        System.out.println();
        System.out.println("==============================================");
        System.out.println("           FAILED PAYMENT SCENARIO");
        System.out.println("==============================================");
        Customer failedCustomer = new Customer("Khaled", "0152222222", "Irbid");
        Order failedOrder = new Order(3, failedCustomer, OrderType.TAKEAWAY);
        OrderItem failedPizza = new OrderItem(margherita, 1);
        failedOrder.addItem(failedPizza, menu);
        PriceSummary failedSummary = calculator.calculate(failedOrder);
        printPricing(failedSummary);
        CashPayment failedCashPayment = new CashPayment(failedSummary.getFinalTotal(), 10);
        PaymentStatus failedPaymentStatus = failedCashPayment.pay();
        failedOrder.setPayment(failedCashPayment);
        System.out.println();
        System.out.println("Payment status: " + failedPaymentStatus);
        System.out.println("Payment successful: " + failedCashPayment.isSuccessful());
        System.out.println("Order status after failed payment: " + failedOrder.getStatus());
        Receipt failedReceipt = new Receipt();
        failedReceipt.print(failedOrder, failedSummary);

        // 6. Edge case 1: invalid quantity
        System.out.println();
        System.out.println("==============================================");
        System.out.println("              EDGE CASE 1");
        System.out.println("==============================================");
        try {
            OrderItem invalidQuantityItem = new OrderItem(cola, 0);
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid quantity handled: " + exception.getMessage());
        }

        // 7. Edge case 2: an item in the menu that is unavailable
        System.out.println();
        System.out.println("==============================================");
        System.out.println("              EDGE CASE 2");
        System.out.println("==============================================");
        Drink unavailableDrink = new Drink("Unavailable Drink", 40, false);
        menu.addItem(unavailableDrink);
        Order unavailableOrder = new Order(4, takeawayCustomer, OrderType.TAKEAWAY);
        OrderItem unavailableItem = new OrderItem(unavailableDrink, 1);
        unavailableOrder.addItem(unavailableItem, menu);
        if (unavailableOrder.getItems().isEmpty()) {
            System.out.println("Unavailable product in Menu was not added.");
        } else {
            System.out.println("Error: unavailable product was added.");
        }

        // 8. Edge case 3: product not in the menu
        System.out.println();
        System.out.println("==============================================");
        System.out.println("              EDGE CASE 3");
        System.out.println("==============================================");
        Pizza pizzaNotInMenu = new Pizza("Pizza Not In Menu", 200, true, PizzaSize.SMALL);
        Order notInMenuOrder = new Order(5, takeawayCustomer, OrderType.TAKEAWAY);
        OrderItem notInMenuItem = new OrderItem(pizzaNotInMenu, 1);
        notInMenuOrder.addItem(notInMenuItem, menu);
        if (notInMenuOrder.getItems().isEmpty()) {
            System.out.println("Product not found in Menu was not added.");
        } else {
            System.out.println("Error: product not found in Menu was added.");
        }
        System.out.println();
        System.out.println("==============================================");
        System.out.println("          DEMONSTRATION COMPLETED");
        System.out.println("==============================================");
    }

    private static void printPricing(PriceSummary summary) {
        System.out.println();
        System.out.println("Pricing summary:");
        System.out.println("Subtotal: " + summary.getSubtotal());
        System.out.println("Discount: " + summary.getDiscount());
        System.out.println("Fulfillment charge: " + summary.getFulfillmentCharge());
        System.out.println("Final total: " + summary.getFinalTotal());
    }
}
