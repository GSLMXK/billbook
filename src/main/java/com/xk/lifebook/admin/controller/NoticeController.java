package com.xk.lifebook.admin.controller;

import com.xk.lifebook.admin.common.base.controller.BaseController;
import com.xk.lifebook.admin.common.base.model.PageBean;
import com.xk.lifebook.admin.model.Notice;
import com.xk.lifebook.admin.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/Notice")
public class NoticeController extends BaseController<Notice> {
    private final String Base_URL = "admin/notice/";
//    @Override
    public String getBaseUrl() {
        return Base_URL;
    }
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
        map.put("page", typePage);
        return Base_URL + "list";
    }

    @RequestMapping("/edit/{id}")
    public String toEditPage (Map<String, Object> map, @PathVariable int id){
        Notice notice = noticeService.findById(id);
        map.put("notice", notice);
        return Base_URL+"edit";
    }

    @RequestMapping("/add")
    public String toAddPage (Map<String, Object> map){
        return Base_URL+"add";
    }

    @RequestMapping("/update")
    public String updateBill (Notice notice){
        Integer result = 0;
        result = noticeService.update(notice);
        if(result!=null&&result>0){
            return "redirect:/Notice/list";
        }
        return toError();
    }
}
