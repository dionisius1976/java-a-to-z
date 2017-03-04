package ru.dionisius;

import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by Dionisius on 02.03.2017.
 * Class for storing and handle orders of this book.
 */
public class Book implements Comparable{
    /**
     * This book name.
     */
    private final String name;
    /**
     * This book buy orders.
     */
    private final Map<Double, Order> buyOrders = new TreeMap<Double, Order>();
    /**
     * This book sell orders.
     */
    private final Map<Double, Order> sellOrders = new TreeMap<Double, Order>(new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return -o1.compareTo(o2);
        }

    });

    /**
     * Constructor.
     * @param name the name of this book;
     */
    public Book(final String name) {
        this.name = name;
    }

    /**
     * Getter for this book name.
     * @return this book name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Adds specified new order to this book.
     * @param order specified new order.
     */
    public void addOrder(final Order order) {
        if ("SELL".equalsIgnoreCase(order.getOperation())) {
            this.addToSellOrders(order);
        } else {
            this.addToBuyOrders(order);
        }
    }

    /**
     * Matches buy ans appropriated sell orders.
     */
    public void matchOrders() {
        Iterator<Order> itBuy;
        Iterator<Order> itSell;
        int newVolume = 0;
        Order newBuyOrder = null;
        Order newSellOrder = null;
        boolean isDone = false;
        while (!isDone) {
            itBuy =  this.buyOrders.values().iterator();
            itSell = this.sellOrders.values().iterator();
            while (itBuy.hasNext() || itSell.hasNext()) {
                Order buyOrder = itBuy.next();
                Order sellOrder = itSell.next();
                Double buyPrice = buyOrder.getPrice();
                Double sellPrice = sellOrder.getPrice();
                if (buyPrice < sellPrice) {
                    continue;
                }
                if (buyPrice > sellPrice) {
                    if (buyOrder.getVolume() > sellOrder.getVolume()) {
                        newVolume = buyOrder.getVolume() - sellOrder.getVolume();
                        newBuyOrder = new Order(buyOrder.getBookName(), buyOrder.getOperation(),
                                buyOrder.getPrice(), newVolume, buyOrder.getOrderId());
                        this.buyOrders.put(newBuyOrder.getPrice(), newBuyOrder);
                    } else if (buyOrder.getVolume() < sellOrder.getVolume()) {
                        newVolume = sellOrder.getVolume() - buyOrder.getVolume();
                        newSellOrder = new Order(buyOrder.getBookName(), buyOrder.getOperation(),
                                buyOrder.getPrice(), newVolume, buyOrder.getOrderId());
                        this.sellOrders.put(newSellOrder.getPrice(), newBuyOrder);
                    }

                }
                    this.sellOrders.remove(sellOrder.getPrice());
                    this.buyOrders.remove(buyOrder.getPrice());
                    break;
                }
                isDone = true;
            }
        }

    /**
     * Shows all sorted pair of buy and sell orders.
     */
    public void showResults() {
        Iterator<Order> itBuy =  this.buyOrders.values().iterator();
        Iterator<Order> itSell = this.sellOrders.values().iterator();
        System.out.println(this.name);
        while (itBuy.hasNext() && itSell.hasNext()) {
            System.out.printf("%s%s%s%s", itBuy.next(), " - ", itSell.next(), System.lineSeparator());
        }
    }

    /**
     * Adds specified new order to sell orders.
     * @param newOrder specified new order.
     */
    private void addToSellOrders(final Order newOrder) {
       this.addToOrders(newOrder, this.sellOrders);
    }

    /**
     * Adds specified new order to buy orders.
     * @param newOrder specified new order.
     */
    private void addToBuyOrders(final Order newOrder) {
        this.addToOrders(newOrder, this.buyOrders);
    }

    /**
     * Adds specified new order to specified orders map.
     * @param newOrder specified new order.
     * @param orders specified orders map.
     */
    private void addToOrders (final Order newOrder, Map<Double, Order> orders) {
        if (orders.containsKey(newOrder.getPrice())) {
            int newVolume  = orders.get(newOrder.getPrice()).getVolume() + newOrder.getVolume();
            orders.remove(newOrder.getPrice());
            orders.put(newOrder.getPrice(), new Order(newOrder.getBookName(), newOrder.getOperation(), newOrder.getPrice(),
                    newVolume, newOrder.getOrderId()));
        } else {
            orders.put(newOrder.getPrice(), newOrder);
        }
    }

    @Override
    public int compareTo(Object o) {
        Book b = (Book) o;
        return this.getName().compareTo(b.getName());
    }
}
