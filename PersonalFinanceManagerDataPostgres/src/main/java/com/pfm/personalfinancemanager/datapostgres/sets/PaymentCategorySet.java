/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfm.personalfinancemanager.datapostgres.sets;

import com.pfm.data.data.PaymentCategoryData;
import com.pfm.data.entities.PaymentCategory;
import com.pfm.data.exceptions.PaymentCategoryAddException;
import com.pfm.data.exceptions.PaymentCategoryEditException;
import com.pfm.data.sets.IPaymentCategorySet;
import com.pfm.personalfinancemanager.datapostgres.entities.PaymentCategories;
import com.pfm.personalfinancemanager.datapostgres.sets.base.BaseSet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author Misho
 */
public class PaymentCategorySet extends BaseSet<PaymentCategories, PaymentCategory, PaymentCategoryData> implements IPaymentCategorySet {

    public PaymentCategorySet(SessionFactory factory) {
        super(factory);
    }

    @Override
    protected PaymentCategory convertEntityToDto(PaymentCategories Entity) {
        PaymentCategory category = new PaymentCategory();
        category.setActive(Entity.getPcatActive());
        category.setDescription(Entity.getPcatDescription());
        category.setId(Entity.getPcatId());
        category.setName(Entity.getPcatName());
        category.setUserId(Entity.getPcatUser());
        return category;
    }

    @Override
    protected List<PaymentCategory> convertEntititiesToDtoArray(List<PaymentCategories> EntityArray) {
        List<PaymentCategory> paymentCategoryList = new ArrayList<PaymentCategory>();
        for (PaymentCategories next : EntityArray) {
            PaymentCategory category = new PaymentCategory();
            category.setActive(next.getPcatActive());
            category.setDescription(next.getPcatDescription());
            category.setId(next.getPcatId());
            category.setName(next.getPcatName());
            category.setUserId(next.getPcatUser());
            paymentCategoryList.add(category);
        }
        return paymentCategoryList;
    }

    @Override
    protected PaymentCategories convertDtoDataToEntity(PaymentCategoryData DtoData) {
        PaymentCategories paymentEntity = new PaymentCategories();
        paymentEntity.setPcatActive(DtoData.isActive());
        paymentEntity.setPcatDescription(DtoData.getDescription());;
        paymentEntity.setPcatName(DtoData.getName());
        paymentEntity.setPcatUser(DtoData.getUserId());
        return paymentEntity;
    }

    @Override
    public UUID Add(PaymentCategoryData data) throws PaymentCategoryAddException {
        Session session = this.getSessionFactory().openSession();
        try {
            if (this.categoryExists(data.getName(),data.getUserId(), session)) {
                throw new PaymentCategoryAddException("Payment category with name \"" + data.getName() + "\" already exists.");
            }
            session.beginTransaction();
            PaymentCategories paymentCategoryEntity = convertDtoDataToEntity(data);
            Serializable id = session.save(paymentCategoryEntity);
            session.getTransaction().commit();
            session.close();
   
            return UUID.fromString(id.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void Delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Edit(UUID id, PaymentCategoryData data) throws PaymentCategoryEditException {
        Session session = this.getSessionFactory().openSession();
        try {
            //if (this.categoryExists(data.getName(),data.getUserId(), session)) {
            //    throw new PaymentCategoryEditException("Payment category with name \"" + data.getName() + "\" already exists.");
           // }
            session.beginTransaction();
            PaymentCategories paymentCategoryEntity = convertDtoDataToEntity(data);
            paymentCategoryEntity.setPcatId(id);
            session.update(paymentCategoryEntity);
            session.getTransaction().commit();
            session.close();
        } finally {
            session.close();
        }
    }

    @Override
    public List<PaymentCategory> GetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaymentCategory GetById(UUID id) {
        Session session = this.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("From PaymentCategories WHERE pcatId = :id");
        q.setParameter("id", id);
        List<PaymentCategories> resultList = q.list();
        List<PaymentCategory> paymentCategoryObjects = convertEntititiesToDtoArray(resultList);
        session.close();
        return paymentCategoryObjects.get(0);
    }

    private boolean categoryExists(String categoryName, UUID userId, Session session) {
        Query q = session.createQuery("From PaymentCategories pc where pc.pcatName = :categoryName and pc.pcatUser = :userId", PaymentCategories.class)
                .setParameter("categoryName", categoryName)
                .setParameter("userId", userId);

        boolean exists = false;
        if (q.getResultList().size() > 0) {
            exists = true;
        }
        return exists;
    }
}
