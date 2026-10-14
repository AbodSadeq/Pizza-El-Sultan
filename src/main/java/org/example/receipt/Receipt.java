package org.example.receipt;

import org.example.model.*;

public class Receipt {

    public void print(Order order, PriceSummary priceSummary) {

        if (order.getStatus() == OrderStatus.CANCELED) {
            return;
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("           PIZZA EL SULTAN");
        System.out.println("              RECEIPT");
        System.out.println("========================================");

        System.out.printf("Order ID: %-28d%n", order.getId());
        System.out.printf("Customer: %-27s%n", order.getCustomer().getName());
        System.out.printf("Order Type: %-25s%n", order.getOrderType());

        System.out.println("----------------------------------------");
        System.out.printf("%-18s %-8s %-10s%n",
                "Item", "Quantity", "Total");
        System.out.println("----------------------------------------");

        for (OrderItem item : order.getItems()) {
            System.out.printf(
                    "%-18s %-8d %-10d EGP%n",
                    item.getItem().getName(),
                    item.getQuantity(),
                    item.calculateTotal()
            );
        }

        System.out.println("----------------------------------------");

        System.out.printf(
                "%-28s %10d EGP%n",
                "Subtotal:",
                priceSummary.getSubtotal()
        );

        System.out.printf(
                "%-28s %10d EGP%n",
                "Discount:",
                priceSummary.getDiscount()
        );

        if (order.getOrderType() == OrderType.DELIVERY) {
            System.out.printf(
                    "%-28s %10d EGP%n",
                    "Delivery:",
                    priceSummary.getFulfillmentCharge()
            );
        }

        System.out.println("----------------------------------------");

        System.out.printf(
                "%-28s %10d EGP%n",
                "TOTAL:",
                priceSummary.getFinalTotal()
        );

        System.out.println("========================================");
        System.out.println("       Thank you for ordering!");
        System.out.println("========================================");
        System.out.println();
    }
}
