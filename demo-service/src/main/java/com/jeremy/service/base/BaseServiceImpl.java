package com.jeremy.service.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeremy.data.query.UserQuery;
import com.jeremy.data.utils.MyMapper;

import javax.annotation.Resource;
import java.util.List;

/**   通用service
 * @Auther: laizc
 * @Date: 2019/1/6 15:00
 * @Description:
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Resource
    private MyMapper<T> mapper;

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

    @Override
    public PageInfo<T> find(UserQuery query) {
        int count = mapper.selectCount(null);
        if (count > 0){
            PageHelper.startPage(query.getPageNo(),query.getPageSize());
            List<T> list = mapper.selectAll();
            return new PageInfo<>(list);
        }
        return null;
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

    @Override
    public int selectCount(T t) {
        return mapper.selectCount(t);
    }

    @Override
    public T findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
	public T selectOne(T t){
    	return mapper.selectOne(t);
    }
}
