package com.xxl.job.dao;

import com.xxl.job.page.Pagination;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by norris
 */
public interface BaseDao<T> {

    public boolean save(T t);

    public boolean saveSelective(T t);

    public boolean delete(Serializable id);

    public boolean update(T t);

    public boolean updateSelective(T t);

    public T get(Serializable id);

    public List<T> queryList(Map params);

    public Pagination<T> queryList(Map params, int offset, int max);

    public Long queryCount(Map params);
}
