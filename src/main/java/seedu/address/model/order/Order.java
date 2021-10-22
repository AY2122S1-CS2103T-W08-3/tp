package seedu.address.model.order;

import seedu.address.model.Date;

public class Order {
    private static final String idPrefix = "SO";
    private static int count = 1;

    private final Customer customer;
    private long id;
    private Amount amount;
    private Date date;
    private boolean isComplete;

    /**
     * Constructor creates an Order related to the customer, due on the given date, with the given amount.
     * isComplete flag is set to False initially, and the id is automatically assigned.
     */
    public Order(Customer customer, Date date, Amount amount) {
        this.customer = customer;
        this.date = date;
        this.amount = amount;
        this.id = Order.count;
        this.isComplete = false;

        Order.count++;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public Amount getAmount() {
        return this.amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the prefixed order id as a String.
     *
     * @return Prefixed order id
     */
    public String getDisplayId() {
        return idPrefix + this.id;
    }

    // Order string representation is temporary, change as necessary for UI.
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (isComplete) {
            builder.append("[X] ");
        } else {
            builder.append("[ ] ");
        }
        builder.append("ID: " + idPrefix)
                .append(getId())
                .append("; Customer: ")
                .append(getCustomer())
                .append("; Amount: ")
                .append(getAmount())
                .append("; Date: ")
                .append(getDate());

        return builder.toString();
    }

    // Required for OrderList to check if an Order exists, before marking it.
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return otherOrder.getCustomer().equals(getCustomer())
                && otherOrder.getDate().equals(getDate())
                && otherOrder.getAmount().equals(getAmount());

    }
}
