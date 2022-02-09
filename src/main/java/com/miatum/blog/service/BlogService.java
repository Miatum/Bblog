package com.miatum.blog.service;
import com.alibaba.fastjson.JSON;
import com.miatum.blog.entity.Blog;
import com.miatum.blog.entity.ResponseEnum;
import com.miatum.blog.mapper.BlogMapper;
import com.miatum.blog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class BlogService {
    @Value("${com.miatum.blog.picServer}")
    private String picServer;
    private ResponseEnum responseEnum;
    @Autowired
    private BlogMapper blogMapper;
    @Resource
    private RedisUtil redisUtil;
    public HashSet<Blog> selectAllBlog() {
        HashSet<Blog> blogs = new HashSet<Blog>();
        Set sblogs = redisUtil.smembers("AllBlog");
        Iterator iterator = sblogs.iterator();
        while(iterator.hasNext()) {
            blogs.add(JSON.parseObject(iterator.next().toString(), Blog.class));
        }
        if (blogs.isEmpty()) {
            blogs = blogMapper.selectAllBlog();
            // 将每篇博客写入缓存
            for (Blog blog : blogs) {
                redisUtil.set("blog_" + blog.getId(), blog);
            }
            redisUtil.sadd("AllBlog", blogs.toArray());
        }
        return blogs;
    }
    public HashSet<Blog> selectFeaturedBlog() {
        HashSet<Blog> blogs = new HashSet<Blog>();
        Set sblogs = redisUtil.smembers("FeaturedBlog");
        Iterator iterator = sblogs.iterator();
        while (iterator.hasNext()) {
            blogs.add(JSON.parseObject(iterator.next().toString(), Blog.class));
        }
        if (blogs.isEmpty()) {
            blogs = blogMapper.selectFeaturedBlog();
            redisUtil.sadd("FeaturedBlog", blogs.toArray());
        }
        return blogs;
    }
    public HashSet<Blog> selectPublicBlog() {
        HashSet<Blog> blogs = new HashSet<Blog>();
        Set sblogs = redisUtil.smembers("PublicBlog");
        Iterator iterator = sblogs.iterator();
        while (iterator.hasNext()) {
            blogs.add(JSON.parseObject(iterator.next().toString(), Blog.class));
        }
        if (blogs.isEmpty()) {
            blogs = blogMapper.selectPublicBlog();
            redisUtil.sadd("PublicBlog", blogs.toArray());
        }
        return blogs;
    }
    public HashSet<Blog> selectBlogByTypeId(int typeId) {
        HashSet<Blog> blogs = new HashSet<Blog>();
        Set sblogs = redisUtil.smembers("TypeBlog" + typeId);
        Iterator iterator = sblogs.iterator();
        while (iterator.hasNext()) {
            blogs.add(JSON.parseObject(iterator.next().toString(), Blog.class));
        }
        if (blogs.isEmpty()) {
            blogs = blogMapper.selectBlogByTypeId(typeId);
            redisUtil.sadd("TypeBlog_" + typeId, blogs.toArray());
        }
        return blogs;
    }
    public HashSet<Blog> selectPublicBlogByTypeId(int typeId) {
        HashSet<Blog> blogs = new HashSet<Blog>();
        Set sblogs = redisUtil.smembers("PublicTypeBlog_" + typeId);
        Iterator iterator = sblogs.iterator();
        while (iterator.hasNext()) {
            blogs.add(JSON.parseObject(iterator.next().toString(), Blog.class));
        }
        if (blogs.isEmpty()) {
            blogs = blogMapper.selectPublicBlogByTypeId(typeId);
            redisUtil.sadd("PublicTypeBlog_" + typeId, blogs.toArray());
        }
        return blogs;
    }
    public Blog selectBlogById(int id) {
        Blog blog = (Blog) redisUtil.get("blog_" + id, Blog.class);
        if (blog == null) {
            blog = blogMapper.selectBlogById(id);
            redisUtil.set("blog_" + id, blog);
        }
        return blog;
    }
    public int updateBlog(Blog blog) {
        int result = blogMapper.updateBlog(blog);
        if (result == 1) {
            Blog preBlog = (Blog) redisUtil.get("blog_" + blog.getId(), Blog.class);
            redisUtil.set("blog_" + blog.getId(), blog);
            if (redisUtil.srem("AllBlog", preBlog) == 1) {
                redisUtil.sadd("AllBlog", blog);
            }
            if (redisUtil.srem("FeaturedBlog", preBlog) == 1) {
                redisUtil.sadd("FeaturedBlog", blog);
            }
            if (redisUtil.srem("TypeBlog_" + blog.getType_id(), preBlog) == 1) {
                redisUtil.sadd("TypeBlog_" + blog.getType_id(), blog);
            }
            if (redisUtil.srem("PublicBlog", preBlog) == 1) {
                redisUtil.sadd("PublicBlog", blog);
            }
            if (redisUtil.srem("PublicTypeBlog_" + blog.getType_id(), preBlog) == 1) {
                redisUtil.sadd("PublicTypeBlog_" + blog.getType_id(), blog);
            }
        }
        return result;
    }
    public int deleteBlog(int id) {
        int result = blogMapper.deleteBlog(id);
        // 数据库删除博客后更新缓存
        if (result == 1) {
            Blog blog = (Blog)redisUtil.get("blog_" + id, Blog.class);
            // 删除散列缓存
            redisUtil.del("blog_" + id);
            // 删除结合缓存
            redisUtil.srem("AllBlog", blog);
            redisUtil.srem("FeaturedBlog", blog);
            redisUtil.srem("TypeBlog_" + blog.getType_id(), blog);
            redisUtil.srem("PublicBlog", blog);
            redisUtil.srem("PublicTypeBlog_" + blog.getType_id(), blog);
        }
        return result;
    }
    public int deleteBlogs(int[] ids) {
        // 删除数据库数据
        int result = blogMapper.deleteBlogs(ids);
        // 删除缓存数据
        if (result == ids.length) {
            for (int i = 0; i < ids.length; i++) {
                Blog blog = (Blog)redisUtil.get("blog_" + ids[i], Blog.class);
                // 删除散列缓存
                redisUtil.del("blog_" + ids[i]);
                // 删除集合缓存
                redisUtil.srem("AllBlog", blog);
                redisUtil.srem("FeaturedBlog", blog);
                redisUtil.srem("TypeBlog_" + blog.getType_id(), blog);
                redisUtil.srem("PublicBlog", blog);
                redisUtil.srem("PublicTypeBlog_" + blog.getType_id(), blog);
            }
        }
        return result;
    }
    // 新增博客
    public int insertBlog(Blog blog) {
        int result = blogMapper.insertBlog(blog);
        if (result == 1) {
            redisUtil.set("blog_" + blog.getId(), blog);
            redisUtil.sadd("AllBlog", blog);
            redisUtil.sadd("TypeBlog_" + blog.getType_id(), blog);
            if (blog.getFeatured() == 1) {
                redisUtil.sadd("FeaturedBlog", blog);
            }
            if (blog.getState_id() == 2) {
                redisUtil.sadd("PublicBlog", blog);
                redisUtil.sadd("PublicTypeBlog_" + blog.getType_id(), blog);
            }
        }
        return result;
    }
    // 上一篇新增博客的ID
    public int lastInsertId() {
        return blogMapper.lastInsertId();
    }

    // 更新图片服务器地址
    public ResponseEnum updatePicServer (String newPicServer) {
        HashSet<Blog> blogs = blogMapper.selectAllBlog();
        for (Blog blog : blogs) {
            // 替换封面路径
            String oldCover = blog.getCover();
            String newCover = oldCover.replace(picServer, newPicServer);
            blog.setCover(newCover);
            // 替换内容图片路径
            String oldContent = blog.getContent();
            String newContent = oldContent.replace(picServer, newPicServer);
            blog.setContent(newContent);
            // 更新缓存和数据库
            updateBlog(blog);
        }
        return responseEnum.SUCCESS;
    }
}
