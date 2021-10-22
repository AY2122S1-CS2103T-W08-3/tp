package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.order.Order;

/**
 * A UI component that displays information of a {@code Order}.
 */
public class OrderCard extends UiPart<Region> {

    private static final String FXML = "OrderCard.fxml";

    public final Order order;

    @FXML
    private Label index;
    @FXML
    private Label orderId;
    @FXML
    private Label customer;
    @FXML
    private CheckBox isComplete;
    @FXML
    private Label date;
    @FXML
    private Label amount;

    /**
     * Creates a {@code OrderCard} with the given {@code Order} and index to display.
     */
    public OrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        index.setText(displayedIndex + ". ");
        orderId.setText("(" + order.getDisplayId() + ") ");
        customer.setText(order.getCustomer().toString());
        isComplete.setSelected(order.getIsComplete());
        date.setText("Deadline:  " + order.getDate().toString());
        amount.setText("$" + order.getAmount().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderCard)) {
            return false;
        }

        // state check
        OrderCard card = (OrderCard) other;
        return index.getText().equals(card.index.getText())
                && order.equals(card.order);
    }
}
