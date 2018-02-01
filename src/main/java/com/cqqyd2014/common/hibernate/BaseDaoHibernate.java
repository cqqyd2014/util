package com.cqqyd2014.common.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;



public abstract class BaseDaoHibernate<V,E,M> implements BaseDAOInter<V,E> {
	
	public abstract M getModelFromEntity(V v);
	public  abstract E getEntityFromModel(M m);
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	@Override
	public Serializable save(E entity) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().save(entity);
	}
	public Serializable saveModel(M model){
		return save(getEntityFromModel(model));
	}

	@Override
	public void update(E entity) {
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
		
	}
	public void updateModel(M model){
		update(getEntityFromModel(model));
	}

	@Override
	public void delete(E entity) {
		getSessionFactory().getCurrentSession().delete(entity);
		
	}
	public void deleteModel(M model){
		delete(getEntityFromModel(model));
	}



	private java.util.ArrayList<M> getArrayListModelFromArrayListEntity(
			java.util.List<V> hs) {
		java.util.ArrayList<M> ms = new java.util.ArrayList<>();
		for (int i = 0, len = hs.size(); i < len; i++) {

			M m = getModelFromEntity((V) hs.get(i));
			ms.add(m);
		}
		return ms;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<V> findByPage(String hql,int pageNo,int pageSize){
		return getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
		
	}
	public List<M> findModelByPage(String hql,int pageNo,int pageSize){
		return getArrayListModelFromArrayListEntity(findByPage(hql,pageNo,pageSize));
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<V> findByPage(String hql,int pageNo,int pageSize,Object... params){
		@SuppressWarnings("rawtypes")
		Query query=getSessionFactory().getCurrentSession().createQuery(hql);
		for (int i=0,len=params.length;i<len;i++){
			query.setParameter(i, params[i]);
			
		}
		return query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
	}
	public List<M> findModelByPage(String hql,int pageNo,int pageSize,Object... params){
		return getArrayListModelFromArrayListEntity(findByPage(hql,pageNo,pageSize,params));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V> find(String hql) {
		// TODO Auto-generated method stub
		@SuppressWarnings("rawtypes")
		Query query=getSessionFactory().getCurrentSession().createQuery(hql);
		
		return (List<V>)query.list();
	}
	public List<M> findModel(String hql){
		return getArrayListModelFromArrayListEntity(find(hql));
	}

	@Override
	public long findCount(String hql) {
		List<?> l=find("select count(*) from ("+hql+") view");
		if (l!=null && l.size()==1){
			return (Long)l.get(0);
		}
		return 0;
	}

	@Override
	public long findCount(String hql, Object... params) {
		// TODO Auto-generated method stub
		List<?> l=find("select count(*) from ("+hql+") view",params);
		if (l!=null && l.size()==1){
			return (Long)l.get(0);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V> find(String hql, Object... params) {
		// TODO Auto-generated method stub
		
			@SuppressWarnings("rawtypes")
			Query query=getSessionFactory().getCurrentSession().createQuery(hql);
			for (int i=0,len=params.length;i<len;i++){
				query.setParameter(i, params[i]);
			}
			return (List<V>)query.list();
		
	}
	public List<M> findModel(String hql, Object... params){
		return getArrayListModelFromArrayListEntity(find(hql,params));
	}


}
