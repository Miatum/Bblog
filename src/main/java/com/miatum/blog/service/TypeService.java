package com.miatum.blog.service;

import com.alibaba.fastjson.JSON;
import com.miatum.blog.entity.Type;
import com.miatum.blog.mapper.TypeMapper;
import com.miatum.blog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Resource
    private RedisUtil redisUtil;
    public Type selectTypeById (int id) {
        return typeMapper.selectTypeById(id);
    }
    public HashSet<Type> selectAllType () {
        HashSet<Type> types = new HashSet<Type>();
        Set stype = redisUtil.smembers("AllType");
        Iterator iterator = stype.iterator();
        while (iterator.hasNext()) {
            types.add(JSON.parseObject(iterator.next().toString(), Type.class));
        }
        if (types.isEmpty()) {
            types = typeMapper.selectAllType();
            for (Type type : types) {
                redisUtil.set("type_" + type.getId(), type);
            }
            redisUtil.sadd("AllType", types.toArray());
        }
        return types;
    }
    public int updateType (Type type) {
        // 操作数据库
        int result = typeMapper.updateType(type);
        // 同步缓存
        if (result == 1) {
            Type preType = (Type) redisUtil.get("type_" + type.getId(), Type.class);
            redisUtil.set("type_" + type.getId(), type);
            if (redisUtil.srem("AllType", preType) == 1) {
                redisUtil.sadd("AllType", type);
            }
        }
        return result;
    }
    public int deleteType (int id) {
        Type type = (Type) redisUtil.get("type_" + id, Type.class);
        int result = typeMapper.deleteType(id);
        if (result == 1) {
            redisUtil.del("type_" + id);
            redisUtil.srem("AllType", type);
        }
        return result;
    }
    public int deleteTypes (int[] ids) {
        int result = typeMapper.deleteTypes(ids);
        if (result > 0) {
            for (int i = 0; i < ids.length; i++) {
                Type type = typeMapper.selectTypeById(ids[i]);
                // 散列缓存
                redisUtil.del("type_" + ids[i]);
                // 集合缓存
                redisUtil.srem("AllType", typeMapper.selectTypeById(ids[i]));
            }
        }
        return result;
    }
    public int insertType (Type type) {
        return typeMapper.insertType(type);
    }
    public int lastInsertId () {
        return typeMapper.lastInsertId();
    }
}
