package ru.dionisius;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Dionisius on 28.02.2017.
 * This class handle all orders in all books.
 */
public class Processor {

    /**
     * Store of all order books.
     */
    Map<String, Book> books = new HashMap<String, Book>();
    /**
     * Initial store of all unsorted orders.
     */
    Map<Integer, Order> unsortedOrders = new HashMap<Integer, Order>();

    /**
     * Starts parsing and handling all orders from specified file.
     * @param fileName
     */
    public void init(final String fileName) {
        new Parser().parse(fileName);
        this.sortOrders();
        this.mathOrders();
    }

    /**
     * Handle an order from specified reader.
     * @param streamReader specified reader.
     */
    private void handleEntry(final XMLStreamReader streamReader) {
        if ("AddOrder".equals(streamReader.getLocalName())) {
            this.addToUnsorted(streamReader);
        }
        if ("DeleteOrder".equals(streamReader.getLocalName())) {
            this.deleteFromUnsorted(streamReader);
        }
    }

    /**
     * Matches orders in all available books.
     */
    public void mathOrders() {
        for (Book book : this.books.values()) {
            book.matchOrders();
        }
    }

    /**
     * Shows matching results for all available books.
     */
    public void showResults() {
        Set<Book> books = new TreeSet<>( this.books.values());
        for (Book book : books) {
            book.showResults();
            System.out.println();
        }
    }

    /**
     * Adds an order from specified reader to unsorted storage.
     * @param streamReader specified reader.
     */
    private void addToUnsorted(final XMLStreamReader streamReader) {
        String bookName = String.valueOf(streamReader.getAttributeValue(0));
        String operation = String.valueOf(streamReader.getAttributeValue(1));
        Double price = Double.valueOf(streamReader.getAttributeValue(2));
        int volume = Integer.valueOf(streamReader.getAttributeValue(3));
        int orderId = Integer.valueOf(streamReader.getAttributeValue(4));
        this.unsortedOrders.put(orderId, new Order(bookName, operation, price, volume, orderId));
    }
    /**
     * Deletes an order from specified reader to unsorted storage.
     * @param streamReader specified reader.
     */
    private void deleteFromUnsorted(final XMLStreamReader streamReader) {
        int orderId = Integer.valueOf(streamReader.getAttributeValue(1));
        this.unsortedOrders.remove(orderId);
    }

    /**
     * Sorts all order from unsorted storage to appropriated books.
     */
    private void sortOrders() {
        for (Map.Entry<Integer, Order> entry : this.unsortedOrders.entrySet()) {
            String bookName = entry.getValue().getBookName();
            if (this.books.containsKey(bookName)) {
                this.books.get(bookName).addOrder(entry.getValue());
            } else {
                Book book = new Book(entry.getValue().getBookName());
                book.addOrder(entry.getValue());
                this.books.put(book.getName(), book);
            }
        }
        this.unsortedOrders.clear();
    }

    /**
     * Class for parsing specified XML file.
     */
    private class Parser {

        /**
         * Parses specified XML file.
         * @param fileName specified XML file.
         */
        public void parse(final String fileName){
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in;
            XMLStreamReader streamReader;
            try {
                in = new FileInputStream(fileName);
                streamReader = inputFactory.createXMLStreamReader(in);
                while (streamReader.hasNext()) {
                    streamReader.next();
                    if (streamReader.isStartElement()) {
                        handleEntry(streamReader);
                    }
                    streamReader.close();
                }
            } catch (FileNotFoundException | XMLStreamException e) {
                e.printStackTrace();
            }
        }
    }
}
