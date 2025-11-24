package core.models.factory;

import core.models.Publisher;
import core.models.Stand;

/**
 * Maneja asociaciones entre stands y editoriales
 */
public class PublisherAssignmentFactory {

    public void link(Stand stand, Publisher publisher) {
        if (!stand.getPublishers().contains(publisher)) {
            stand.addPublisher(publisher);
        }
        if (!publisher.getStands().contains(stand)) {
            publisher.addStand(stand);
        }
    }
}

