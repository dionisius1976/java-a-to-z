package ru.dionisius;

/**
 * Created by Dionisius on 24.02.2017.
 * Order class.
 */
public class Order {
    /**
     * Order's book name.
     */
    private final String bookName;
    /**
     * The type of this order.
     */
    private final String operation;
    /**
     * The price this order.
     */
    private final Double price;
    /**
     * The volume of this order.
     */
    private final int volume;
    /**
     * This order id.
     */
    private final int orderId;

    /**
     * Constructor.
     * @param bookName this order's book name.
     * @param operation this order's type.
     * @param price this order's price.
     * @param volume this order's volume.
     * @param orderId this order's id.
     */
    public Order(String bookName, String operation, Double price, int volume, int orderId) {
        this.bookName = bookName;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.orderId = orderId;
    }

    /**
     * Getter for this order's book name.
     * @return order's book name.
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * Getter for this order's operation type.
     * @return this order's ioperation type
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Getter for this order's price.
     * @return this order's price.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Getter for this order's volume.
     * @return this order's volume.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Getter for this order's id.
     * @return this order's id.
     */
    public int getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return String.format("%d%s%s", this.volume, "@", this.price);
    }
}
