package com.springbootredis.server;

import com.springbootredis.util.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: laizc
 * @Date: 2019/1/6 15:00
 * @Description:
 */
public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private MyMapper<T> mapper;

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

    @Override
    public int save(T t) {
        return mapper.insert(t);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return mapper.updateByPrimaryKey(t);
    }

    @Override
    public int deleteByPrimaryKey(int t) {
        return mapper.deleteByPrimaryKey(t);
    }
}
