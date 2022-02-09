package com.miatum.blog.service;

import com.alibaba.fastjson.JSON;
import com.miatum.blog.entity.Tag;
import com.miatum.blog.mapper.TagMapper;
import com.miatum.blog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@Service
public class TagService {
    @Autowired
    private TagMapper tagMapper;
    @Resource
    private RedisUtil redisUtil;
    public Tag selectTagById(int id) {
        Tag tag = (Tag) redisUtil.get("tag_" + id, Tag.class);
        if (tag == null) {
            tag = tagMapper.selectTagById(id);
            redisUtil.set("tag_" + id, tag);
        }
        return tag;
    }
    public HashSet<Tag> selectAllTag() {
        HashSet<Tag> tags = new HashSet<Tag>();
        Set stag = redisUtil.smembers("AllTag");
        Iterator iterator = stag.iterator();
        while (iterator.hasNext()) {
            tags.add(JSON.parseObject(iterator.next().toString(), Tag.class));
        }
        if (tags.isEmpty()) {
            tags = tagMapper.selectAllTag();
        }
        return tags;
    }
    public int updateTag(Tag tag) {
        int result = tagMapper.updateTag(tag);
        if (result == 1) {

        }
        return result;
    }
    public int deleteTag(int id) {
        return tagMapper.deleteTag(id);
    }
    public int deleteTags(int[] ids) {
        return tagMapper.deleteTags(ids);
    }
    public int insertTag(Tag tag) {
        return tagMapper.insertTag(tag);
    }
    public int lastInsertId() {
        return tagMapper.lastInsertId();
    }
}
