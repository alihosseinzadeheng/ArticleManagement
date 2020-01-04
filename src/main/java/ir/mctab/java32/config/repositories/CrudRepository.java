package ir.mctab.java32.config.repositories;


import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

import static ir.mctab.java32.config.HibernateUtil.getSession;

public abstract class CrudRepository<Entity, ID extends Serializable> {

    protected abstract Class<Entity> getEntityClass();

    public Entity save(Entity entity){
        getSession().getTransaction().begin();
        getSession().save(entity);
        getSession().getTransaction().commit();
        return entity;
    }
    public Entity update(Entity entity){
        getSession().beginTransaction();
        getSession().update(entity);
        getSession().getTransaction().commit();
        return entity;
    }
    public void remove(Entity entity){
        getSession().beginTransaction();
        getSession().remove(entity);
        getSession().getTransaction().commit();
    }
    public Entity findbyId(ID id){
        getSession().beginTransaction();
        Entity entity=getSession().get(getEntityClass(),id);
        getSession().getTransaction().commit();
        return entity;
    }
    public List<Entity> findAll(){
        getSession().beginTransaction();
        Query<Entity> query=getSession()
                .createQuery("from"+ getEntityClass().getName(), getEntityClass());
        List<Entity> entityList=query.list();
        getSession().getTransaction().commit();
        return entityList;
    }
    public List<Entity> findAll(int first, int max){
        getSession().beginTransaction();
        Query<Entity> query =
                getSession()
                .createQuery("from"+getEntityClass().getName(),getEntityClass());
        query.setFirstResult(first);
        query.setMaxResults(max);
        List<Entity> entityList=query.list();
        return entityList;
    }
}
