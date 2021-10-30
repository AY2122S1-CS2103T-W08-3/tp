package seedu.address.model.order;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.order.exceptions.DuplicateOrderException;
import seedu.address.model.order.exceptions.OrderNotFoundException;

/**
 * A list of orders supporting minimal list operations for v1.3.
 * Basic version does not support editing SalesOrders.
 */
public class OrderList implements Iterable<Order> {
    private final ObservableList<Order> internalList = FXCollections.observableArrayList();
    private final ObservableList<Order> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds an order to the OrderList.
     * @param toAdd order to add
     */
    public void add(Order toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Removes an order from the OrderList.
     * @param toRemove order to remove
     */
    public void remove(Order toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OrderNotFoundException();
        }
    }

    /**
     * Replaces the given order {@code target} in the list with {@code editedOrder}.
     * {@code target} must exist in the order list.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the order list.
     */
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OrderNotFoundException();
        }

        if (!target.equals(editedOrder) && hasOrder(editedOrder)) {
            throw new DuplicateOrderException();
        }

        internalList.set(index, editedOrder);
    }


    public void setOrders(List<Order> orders) {
        requireAllNonNull(orders);

        internalList.setAll(orders);
    }

    /**
     * Returns true if order is in the OrderList
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return internalList.stream().anyMatch(order::equals);
    }

    /**
     * Returns true if an order in the OrderList have the id
     */
    public boolean hasOrder(long id) {
        System.out.println(id);
        return internalList.stream().anyMatch(order -> order.getId() == id);
    }

    /**
     * Marks an order as complete, if it exists in the OrderList
     * @param toMark
     */
    public void markComplete(Order toMark, int index) {
        requireNonNull(toMark);
        Order target = internalList.get(index);
        if (!target.equals(toMark)) {
            throw new OrderNotFoundException();
        }
        target.setIsComplete(true);
        internalList.set(index, target);
    }

    public ObservableList<Order> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Order> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof OrderList
                && internalList.equals(((OrderList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    public void sort(Comparator<Order> comparator) {
        internalList.sort(comparator);
    }

}
