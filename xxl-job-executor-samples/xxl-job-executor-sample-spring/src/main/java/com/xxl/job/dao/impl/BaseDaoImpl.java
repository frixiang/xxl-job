package com.xxl.job.dao.impl;

import com.xxl.job.dao.BaseDao;
import com.xxl.job.enums.DatasourceEnum;
import com.xxl.job.page.Pagination;
import org.apache.ibatis.session.SqlSession;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by djb
 * on 2014/11/10.
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {

    protected Class<T> clazz;

    public BaseDaoImpl() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    @Resource(name = "sqlSession_tms")
    protected SqlSession sqlSession_tms;




    protected DatasourceEnum getSqlSessionType() {
        return DatasourceEnum.BRAND;
    }

    public SqlSession getSqlSession(DatasourceEnum sqlSessionType) {
        return sqlSession_tms;
    }

    public SqlSession getSqlSession() {
        return getSqlSession(getSqlSessionType());
    }

    /**
     * edit by baiqi
     * 增加写入结果判断
     *
     * @param t
     * @return
     */
    @Override
    public boolean save(T t) {
        int result = getSqlSession().insert(this.clazz.getName() + "Mapper.insert", t);
        if (result > 0) {
            return true;
        }

        return false;
    }

    /**
     * edit by djb
     * 新增T中非null的字段
     *
     * @param t 实体
     * @return true
     */
    @Override
    public boolean saveSelective(T t) {
        int result = getSqlSession().insert(this.clazz.getName() + "Mapper.insertSelective", t);
        if (result > 0)
            return true;

        return false;
    }

    /**
     * edit by baiqi
     *
     * @date 2014-11-27
     * 增加返回结果判断
     */
    @Override
    public boolean delete(Serializable id) {
        int result = getSqlSession().delete(this.clazz.getName() + "Mapper.deleteByPrimaryKey", id);
        if (result > 0)
            return true;

        return false;
    }

    /**
     * edit by baiqi
     *
     * @param t
     * @date 2014-11-27
     * 增加返回结果判断
     */
    @Override
    public boolean update(T t) {
        int result = getSqlSession().update(this.clazz.getName() + "Mapper.updateByPrimaryKey", t);
        if (result > 0)
            return true;

        return false;
    }

    /**
     * 仅更新T中非null的字段
     * @param t
     * @return
     */
    @Override
    public boolean updateSelective(T t) {
        int result = getSqlSession().update(this.clazz.getName() + "Mapper.updateByPrimaryKeySelective", t);
        if (result > 0)
            return true;

        return false;
    }

    @Override
    public T get(Serializable id) {
        return (T) getSqlSession().selectOne(this.clazz.getName() + "Mapper.selectByPrimaryKey", id);
    }

    @Override
    public List<T> queryList(Map params) {
        return getSqlSession().selectList(this.clazz.getName() + "Mapper.queryList", params);
    }


    @Override
    public Pagination<T> queryList(Map params, int offset, int max) {
        params.put("offset", offset);
        params.put("max", max);

        List<T> result = queryList(params);
        Long count = queryCount(params);

        return new Pagination<T>(result, count.intValue());
    }

    @Override
    public Long queryCount(Map params) {
        return getSqlSession().selectOne(this.clazz.getName() + "Mapper.queryCount", params);
    }

}
