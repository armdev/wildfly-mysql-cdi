
package com.project.web.data;

import com.project.web.entities.Contact;
import com.project.web.facades.ContactRepository;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
public class ContactListProducer {

    @Inject
    private ContactRepository contactRepository;

    private List<Contact> contacts;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<Contact> getContacts() {
        return contacts;
    }

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Contact member) {
        retrieveAllMembersOrderedByName();
    }

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        contacts = contactRepository.findAll();
    }
}
