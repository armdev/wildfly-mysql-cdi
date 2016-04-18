package com.project.web.facades;

import com.project.web.entities.Contact;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class ContactRepository {

   // @Inject
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public Contact findById(Long id) {
        return em.find(Contact.class, id);
    }

    public List<Contact> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteria = cb.createQuery(Contact.class);
        Root<Contact> contact = criteria.from(Contact.class);

        criteria.select(contact).orderBy(cb.desc(contact.get("id")));
        return em.createQuery(criteria).getResultList();
    }
}
