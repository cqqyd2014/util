package com.cqqyd2014.common.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

public interface  BaseDAOInter<V,E> {


	
	Serializable save(E entity);
	void update(E entity);
	void delete(E entity);
	
	List<V> find(String hql);
	List<V> find(String hql,Object...params);
	long findCount(String hql);
	long findCount(String hql,Object...params);
	List<V> findByPage(String hql,int pageNo,int pageSize);
	List<V> findByPage(String hql,int pageNo,int pageSize,Object... params);
	
	
	
	
}
