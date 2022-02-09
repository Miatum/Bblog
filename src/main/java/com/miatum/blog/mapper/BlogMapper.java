package com.miatum.blog.mapper;
import com.miatum.blog.entity.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.HashSet;
@Repository
public interface BlogMapper {
    // 查询所有博客
    @Select("select blog.*, type.type_name, tag.tag_name, state.state_name from blog \n" +
            "left join type on blog.type_id = type.id \n" +
            "left join tag on blog.tag_id = tag.id \n" +
            "left join state on blog.state_id = state.id")
    HashSet<Blog> selectAllBlog();
    // 查询Featured博客
    @Select("select blog.*, type.type_name, tag.tag_name, state.state_name from blog \n" +
            "left join type on blog.type_id = type.id \n" +
            "left join tag on blog.tag_id = tag.id \n" +
            "left join state on blog.state_id = state.id \n" +
            "where blog.featured = 1")
    HashSet<Blog> selectFeaturedBlog();
    // 查询公开博客
    @Select("select blog.*, type.type_name, tag.tag_name, state.state_name from blog \n" +
            "left join type on blog.type_id = type.id \n" +
            "left join tag on blog.tag_id = tag.id \n" +
            "left join state on blog.state_id = state.id \n" +
            "where blog.state_id = 2")
    HashSet<Blog> selectPublicBlog();
    // 传入分类ID查询博客
    @Select("select blog.*, type.type_name, tag.tag_name, state.state_name from blog \n" +
            "left join type on blog.type_id = type.id \n" +
            "left join tag on blog.tag_id = tag.id \n" +
            "left join state on blog.state_id = state.id \n" +
            "where blog.type_id = #{typeId}")
    HashSet<Blog> selectBlogByTypeId(int typeId);
    // 传入分类ID查询Public博客
    @Select("select blog.*, type.type_name, tag.tag_name, state.state_name from blog \n" +
            "left join type on blog.type_id = type.id\n" +
            "left join tag on blog.tag_id = tag.id\n" +
            "left join state on blog.state_id = state.id \n" +
            "where blog.type_id = #{typeId} and blog.state_id = 2")
    HashSet<Blog> selectPublicBlogByTypeId(int typeId);
    // 传入博客ID查询博客
    @Select("select blog.*, type.type_name, tag.tag_name, state.state_name from blog \n" +
            "left join type on blog.type_id = type.id\n" +
            "left join tag on blog.tag_id = tag.id\n" +
            "left join state on blog.state_id = state.id \n" +
            "where blog.id = #{id}")
    Blog selectBlogById (int id);
    // 更新博客
    @Update("update blog set state_id = #{state_id}, title = #{title},intro = #{intro},cover = #{cover}," +
            "type_id = #{type_id},tag_id = #{tag_id},author = #{author},date = #{date}," +
            "resource_path = #{resource_path}, content = #{content}, featured = #{featured} where id = #{id}")
    int updateBlog (Blog blog);
    // 传入ID删除博客
    @Delete("delete from blog where id=#{id}")
    int deleteBlog(int id);
    // 传入ID批量删除博客
    @Delete({
            "<script>",
            "delete from blog",
            "where id in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    int deleteBlogs (@Param("ids") int[] ids);
    //  新增博客
    @Insert("insert into blog values(null, #{state_id}, #{title}, #{intro}, #{cover}, #{type_id}, #{tag_id}, #{author}, #{date}, #{content}, #{resource_path}, #{featured})")
    int insertBlog(Blog blog);
    //  LAST_INSERT_ID ()
    @Select("select LAST_INSERT_ID()")
    int lastInsertId ();
}
