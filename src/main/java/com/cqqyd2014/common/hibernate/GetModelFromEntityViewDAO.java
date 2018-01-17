package com.cqqyd2014.common.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.opensymphony.xwork2.ActionSupport;

public abstract class GetModelFromEntityViewDAO<T> extends ActionSupport{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5326478816396860932L;

	public String getCountByHqlSql(Session session,String hql_sql){
		
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql_sql);
		



		Long l=(Long)q.uniqueResult();
		
			return String.valueOf(l);
	}
	
	@SuppressWarnings("unchecked")
	public  <T> T getArrayListViewByHqlSql(Session session,String hql_sql,java.math.BigDecimal page_size,java.math.BigDecimal page){
		int currentPage=page.intValue();
		int pageSize=page_size.intValue();
		int offset = pageSize * (currentPage - 1);
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql_sql).setFirstResult(offset)
				.setMaxResults(pageSize);
		



		java.util.ArrayList<? extends Object> sws = (java.util.ArrayList<? extends Object>) q
				.list();
		
			return (T)getArrayListModelFromArrayListViewEntity(sws);
	}
	

	
	
	@SuppressWarnings("unchecked")
	public  <T> T getArrayListViewByHqlSql(Session session,String hql_sql){
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql_sql);
		



		java.util.ArrayList<? extends Object> sws = (java.util.ArrayList<? extends Object>) q
				.list();
		
			return (T)getArrayListModelFromArrayListViewEntity(sws);
	}

	public abstract <T> void save(Session session, T t);

	public abstract <T, S> T getModelFromViewEntity(S s);

	public <T, S> java.util.ArrayList<? extends Object> getArrayListModelFromArrayListViewEntity(
			java.util.ArrayList<? extends Object> hs) {
		java.util.ArrayList<T> ms = new java.util.ArrayList<>();
		for (int i = 0, len = hs.size(); i < len; i++) {
			@SuppressWarnings("unchecked")
			T m = getModelFromViewEntity((S) hs.get(i));
			ms.add(m);
		}
		return ms;
	}
	
}
