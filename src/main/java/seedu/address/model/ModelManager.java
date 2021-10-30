package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;
import seedu.address.model.sort.SortDescriptor;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final TaskBook taskBook;
    private final OrderBook orderBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Order> filteredOrders;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyTaskBook taskBook,
                        ReadOnlyOrderBook orderBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, taskBook, orderBook, userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.taskBook = new TaskBook(taskBook);
        this.orderBook = new OrderBook(orderBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTasks = new FilteredList<>(this.taskBook.getTaskList());
        filteredOrders = new FilteredList<>(this.orderBook.getOrderList());

    }

    public ModelManager() {
        this(new AddressBook(), new TaskBook(), new OrderBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    //=========== AddressBook ================================================================================

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }


    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Task Management ==================================================================================

    @Override
    public Path getTaskListFilePath() {
        return userPrefs.getTaskBookPath();
    }

    @Override
    public void setTaskListFilePath(Path taskListFilePath) {
        requireNonNull(taskListFilePath);
        userPrefs.getTaskListFilePath(taskListFilePath);
    }

    @Override
    public void setTaskBook(ReadOnlyTaskBook taskBook) {
        this.taskBook.resetData(taskBook);
    }

    @Override
    public ReadOnlyTaskBook getTaskBook() {
        return taskBook;
    }
    /**
     * Checks if taskBook has this task.
     */
    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskBook.hasTask(task);
    }

    /**
     * Adds a task to taskBook.
     */
    @Override
    public void addTask(Task toAdd) {
        taskBook.addTask(toAdd);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    /**
     * Deletes a task from taskBook.
     */
    @Override
    public void deleteTask(Task toDelete) {
        taskBook.deleteTask(toDelete);
    }

    /**
     * Deletes all tasks matching predicate from taskBook.
     */
    public void deleteTaskIf(Predicate<Task> pred) {
        taskBook.deleteTaskIf(pred);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);
        taskBook.setTask(target, editedTask);
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public void markTask(Task toMark, int index) {
        taskBook.markDone(toMark, index);

    }


    //=========== Order Management ==================================================================================

    @Override
    public Path getOrderPath() {
        return userPrefs.getOrderBookFilePath();
    }

    @Override
    public void setOrderBookFilePath(Path orderBookFilePath) {
        requireNonNull(orderBookFilePath);
        userPrefs.getOrderBookFilePath(orderBookFilePath);
    }

    @Override
    public void setOrderBook(ReadOnlyOrderBook orderBook) {
        this.orderBook.resetData(orderBook);
    }

    @Override
    public ReadOnlyOrderBook getOrderBook() {
        return orderBook;
    }

    /**
     * Checks if orderBook has this order.
     */
    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return orderBook.hasOrder(order);
    }

    /**
     * Checks if orderlist has an order with this id.
     */
    @Override
    public boolean hasOrder(long id) {
        return orderBook.hasOrder(id);
    }

    @Override
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);
        orderBook.setOrder(target, editedOrder);
    }

    /**
     * Adds an order to orderBook.
     */
    public void addOrder(Order toAdd) {
        orderBook.addOrder(toAdd);
        updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
    }

    /**
     * Deletes an order from orderBook.
     */
    public void deleteOrder(Order toDelete) {
        orderBook.deleteOrder(toDelete);
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return filteredOrders;
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        requireNonNull(predicate);
        filteredOrders.setPredicate(predicate);
    }

    @Override
    public void sortOrderList(SortDescriptor sortDescriptor) {
        Comparator<Order> comparator = sortDescriptor.generateComparator();
        orderBook.sortOrders(comparator);
        filteredOrders.setPredicate(PREDICATE_SHOW_ALL_ORDERS);
    }

    /**
     * Marks an order as completed
     */
    public void markOrder(Order order, int index) {
        orderBook.markOrder(order, index);
    }

    /**
     * For each person, finds orders associated with the person, and adds up the amount.
     * Creates a ClientTotalOrder for each person.
     *
     * @return an ObservableList of {@code ClientTotalOrder}.
     */
    @Override
    public ObservableList<ClientTotalOrder> getClientTotalOrders() {
        ObservableList<ClientTotalOrder> clientTotalOrders = FXCollections.observableArrayList();
        for (Person client : addressBook.getPersonList()) {
            clientTotalOrders.add(getClientTotalOrder(client));
        }
        return clientTotalOrders;
    }

    private ClientTotalOrder getClientTotalOrder(Person client) {
        String clientName = client.getName().toString();
        Predicate<Order> correctClient = (order) -> order.getCustomer().toString().equals(clientName);
        double totalOrder = orderBook.getOrderList().stream()
                .filter(correctClient)
                .mapToDouble(Order::getAmountAsDouble)
                .sum();
        return new ClientTotalOrder(clientName, totalOrder);
    }


    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && taskBook.equals(other.taskBook)
                && orderBook.equals(other.orderBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredTasks.equals(other.filteredTasks)
                && filteredOrders.equals(other.filteredOrders);
    }

    @Override
    public void resetOrderView() {
        Comparator<Order> defaultComparator = Order::compareTo;
        orderBook.sortOrders(defaultComparator);
        updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
    }

}
