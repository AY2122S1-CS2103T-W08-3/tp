package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderList;



/**
 * Wraps all data at the order-book level
 * Duplicates are not allowed (by .isSameOrder comparison)
 */
public class OrderBook implements ReadOnlyOrderBook {

    private final OrderList orders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        orders = new OrderList();
    }

    public OrderBook() {}

    /**
     * Creates an OrderBook using the Orders in the {@code toBeCopied}
     */
    public OrderBook(ReadOnlyOrderBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the orders list with {@code orders}.
     * {@code orders} must not contain duplicate orders.
     */
    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }


    /**
     * Resets the existing data of this {@code OrderBook} with {@code newData}.
     */
    public void resetData(ReadOnlyOrderBook newData) {
        requireNonNull(newData);

        setOrders(newData.getOrderList());

    }

    /// order-level operations

    public void addOrder(Order toAdd) {
        orders.add(toAdd);
    }

    public void deleteOrder(Order toDelete) {
        orders.remove(toDelete);
    }

    public void markOrder(Order order, int index) {
        orders.markComplete(order, index);
    }

    public void setOrder(Order target, Order editedOrder) {
        orders.setOrder(target, editedOrder);
    }

    public boolean hasOrder(Order order) {
        return orders.hasOrder(order);
    }

    public boolean hasOrder(long id) {
        return orders.hasOrder(id);
    }

    public void sortOrders(Comparator<Order> comparator) {
        orders.sort(comparator);
    }

    //// util methods

    @Override
    public String toString() {
        return orders.asUnmodifiableObservableList().size() + " orders";
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderBook // instanceof handles nulls
                && orders.equals(((OrderBook) other).orders));
    }

    @Override
    public int hashCode() {
        return orders.hashCode();
    }
}
