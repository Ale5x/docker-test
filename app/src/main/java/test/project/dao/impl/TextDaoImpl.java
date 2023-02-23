package test.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.project.dao.TextDao;
import test.project.entity.Text;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class TextDaoImpl implements TextDao {

    private SessionFactory sessionFactory;

    @Autowired
    public TextDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean save(Text text) {
        Session session = getSession();
        session.save(text);
//        session.flush();
        return text.getTextId() > 0;
    }

    @Override
    public boolean delete(Text text) {
        Session session = getSession();
        session.delete(text);
        return true;
    }

    @Override
    public List<Text> findAll() {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Text> criteriaQuery = criteriaBuilder.createQuery(Text.class);
        Root<Text> root = criteriaQuery.from(Text.class);
        criteriaQuery.select(root);
        return session.createQuery(criteriaQuery).getResultList();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
