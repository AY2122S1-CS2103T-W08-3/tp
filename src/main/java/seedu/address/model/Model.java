package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;
import seedu.address.model.sort.SortDescriptor;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;
    Predicate<Task> PREDICATE_SHOW_INCOMPLETE_TASKS = t -> !t.getIsDone();
    Predicate<Task> PREDICATE_SHOW_COMPLETED_TASKS = Task::getIsDone;
    Predicate<Order> PREDICATE_SHOW_INCOMPLETE_ORDERS = o -> !o.getIsComplete();
    Predicate<Order> PREDICATE_SHOW_COMPLETED_ORDERS = Order::getIsComplete;
    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    //======================================== TASK FUNCTIONALITIES =================================================
    /**
     * Returns the user prefs' TaskList file path.
     */
    Path getTaskListFilePath();

    /**
     * Sets the user prefs' TaskList file path.
     */
    void setTaskListFilePath(Path taskListFilePath);

    /**
     * Replaces taskBook data with the data in {@code taskBook}.
     */
    void setTaskBook(ReadOnlyTaskBook taskBook);

    /** Returns the TaskList */
    ReadOnlyTaskBook getTaskBook();


    /**
     * Adds the given task.
     */
    void addTask(Task task);

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task list.
     */
    boolean hasTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task list.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Deletes a task from taskBook.
     */
    void deleteTask(Task toDelete);

    /**
     * Deletes all tasks matching predicate from taskBook.
     */
    void deleteTaskIf(Predicate<Task> pred);

    void updateFilteredTaskList(Predicate<Task> predicate);

    void markTask(Task toMark, int index);

    //======================================== ORDER FUNCTIONALITIES =================================================


    /**
     * Returns the user prefs' Order books  file path.
     */
    Path getOrderPath();

    /**
     * Sets the user prefs' Order books  file path.
     */
    void setOrderBookFilePath(Path orderBookFilePath);

    /**
     * Replaces Order books data with the data in {@code salesOrderBook}.
     */
    void setOrderBook(ReadOnlyOrderBook orderBook);

    /** Returns the OrderBook */
    ReadOnlyOrderBook getOrderBook();


    /**
     * Adds the given order.
     */
    void addOrder(Order order);

    /**
     * Returns true if an order with the same identity as {@code order} exists in the order list.
     */
    boolean hasOrder(Order order);

    /**
     * Returns true if an order with the order id {@code id} exists in the order list.
     */
    boolean hasOrder(long id);

    /**
     * Replaces the given order {@code target} with {@code editedOrder}.
     * {@code target} must exist in the order list.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the order list.
     */
    void setOrder(Order target, Order editedOrder);

    /** Returns an unmodifiable view of the filtered order list */
    ObservableList<Order> getFilteredOrderList();

    void deleteOrder(Order toDelete);

    void updateFilteredOrderList(Predicate<Order> predicate);

    void markOrder(Order order, int index);

    void sortOrderList(SortDescriptor sortDescriptor);

    /** Resets the order list to its regular ordering based on id */
    void resetOrderView();

    /** Returns an unmodifiable view of the list of ClientTotalOrders */
    ObservableList<ClientTotalOrder> getClientTotalOrders();
}
