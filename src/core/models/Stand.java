/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.models.interfaces.IPublisher;
import core.models.interfaces.IStand;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edangulo
 */
public class Stand implements IStand {

    private final long id;
    private final double price;
    private final ArrayList<Publisher> publishers;

    public Stand(long id, double price) {
        this.id = id;
        this.price = price;
        this.publishers = new ArrayList<>();
    }

    public void addPublisher(Publisher publisher) {
        this.publishers.add(publisher);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public List<IPublisher> getPublishers() {
        ArrayList<IPublisher> publisherCopies = new ArrayList<>();
        publisherCopies.addAll(this.publishers);
        return publisherCopies;
    }

    @Override
    public int getPublisherQuantity() {
        return this.publishers.size();
    }

}
