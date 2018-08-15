package com.xk.billbook.admin.controller;

import com.xk.billbook.admin.common.base.controller.BaseController;
import com.xk.billbook.admin.common.base.model.PageBean;
import com.xk.billbook.admin.model.Notice;
import com.xk.billbook.admin.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Notice")
public class NoticeController extends BaseController<Notice> {
    private final String Base_URL = "admin/notice/";
    @Autowired
    NoticeService noticeService;

    @RequestMapping("/list")
    public String findAll(Map<String, Object> map, Integer currentPage, Integer pageSize) {
        int id = 1;
        if (currentPage == null) {
            currentPage = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        PageBean<Notice> typePage = noticeService.findByPage(currentPage, pageSize, id);
//        List<Map<String,Object>> billList = billmgrService.findAll(id);
        map.put("noticePage", typePage);
        return Base_URL + "list";
    }
}
