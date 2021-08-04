package com.xk.lifebook.admin.mapper;

import com.xk.lifebook.admin.common.base.mapper.BaseMapper;
import com.xk.lifebook.admin.model.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {
//    @Select("SELECT * FROM ${table} WHERE creator_id = #{creator_id}")
//    List<Bill> findAll(@Param("table") String table, @Param("creator_id") int creatorId);
}