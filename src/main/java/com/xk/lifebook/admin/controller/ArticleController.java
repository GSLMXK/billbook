package com.xk.lifebook.admin.controller;

import com.xk.lifebook.admin.common.base.controller.BaseController;
import com.xk.lifebook.admin.common.base.service.BaseService;
import com.xk.lifebook.admin.model.Article;
import com.xk.lifebook.admin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Article")
public class ArticleController extends BaseController<Article> {
    private final String Base_URL = "admin/acticle/";
    @Autowired
    ArticleService articleService;
    @Override
    public BaseService<Article> getSevice() {
        return articleService;
    }

    @Override
    public String getBaseUrl() {
        return Base_URL;
    }

    @Override
    public String getControllerName() {
        return "Article";
    }
}
