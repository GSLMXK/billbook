package com.xk.lifebook.admin.service;

import com.xk.lifebook.admin.common.base.mapper.BaseMapper;
import com.xk.lifebook.admin.common.base.service.BaseService;
import com.xk.lifebook.admin.mapper.ArticleMapper;
import com.xk.lifebook.admin.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleService extends BaseService<Article> {
    private final String TABLE = "lb_article";
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public BaseMapper getMapper() {
        return articleMapper;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

}
