/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.models.interfaces.IBook;
import core.models.interfaces.IManager;
import core.models.interfaces.IPublisher;
import core.models.interfaces.IStand;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edangulo
 */
public class Publisher implements IPublisher {

    private final String nit;
    private final String name;
    private final String address;
    private final Manager manager;
    private final ArrayList<Book> books;
    private final ArrayList<Stand> stands;

    public Publisher(String nit, String name, String address, Manager manager) {
        this.nit = nit;
        this.name = name;
        this.address = address;
        this.manager = manager;
        this.books = new ArrayList<>();
        this.stands = new ArrayList<>();
        this.manager.setPublisher(this);
    }

    @Override
    public String getNit() {
        return nit;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public IManager getManager() {
        return manager;
    }

    @Override
    public int getStandQuantity() {
        return this.stands.size();
    }

    @Override
    public List<IBook> getBooks() {
        ArrayList<IBook> bookCopies = new ArrayList<>();
        bookCopies.addAll(this.books);
        return bookCopies;
    }

    @Override
    public List<IStand> getStands() {
        ArrayList<IStand> standCopies = new ArrayList<>();
        standCopies.addAll(this.stands);
        return standCopies;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void addStand(Stand stand) {
        this.stands.add(stand);
    }

}
