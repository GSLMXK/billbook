package com.xk.lifebook.admin.controller;

import com.xk.lifebook.admin.common.base.controller.BaseController;
import com.xk.lifebook.admin.common.base.model.PageBean;
import com.xk.lifebook.admin.model.Wish;
import com.xk.lifebook.admin.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * 心愿控制类
 * xiekuang
 * 2018/8/3
 */
@Controller
@RequestMapping("/Wish")
public class WishController extends BaseController<Wish> {
    private final String Base_URL = "admin/wish/";
//    @Override
    public String getBaseUrl() {
        return Base_URL;
    }
    @Autowired
    WishService wishService;

    @RequestMapping("/list")
    public String findAll(HttpServletRequest request, Map<String, Object> map, Integer currentPage, Integer pageSize) {
        int id = (Integer) request.getSession(true).getAttribute("userId");
        if (currentPage == null) {
            currentPage = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        String[] parmsName = {"searchContent","searchDate"};
        Map<String,Object> parms = getParms(request,parmsName);
        PageBean<Map<String,Object>> page = wishService.findByPage(currentPage, pageSize, id,parms);
        map.put("page", page);
        return Base_URL + "list";
    }
    @RequestMapping("/edit")
    @ResponseBody
    public Map<String, Object> toEditPage (HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        Wish wish = wishService.findById(Integer.parseInt(request.getParameter("id")));
        result.put("wish", wish);
        return result;
    }

    @RequestMapping("/delete/{id}")
    public String delData (Map<String, Object> map, @PathVariable int id){
        Integer result = wishService.delById(id);
        if(result!=null&&result>0){
            return "redirect:/Wish/list";
        }
        return toError();
    }
    /**'
     * Ajax 获取当月账单统计数据
     * @return
     */
    @RequestMapping("/countData")
    @ResponseBody
    public Map<String,Object> getCountData(HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        Integer id = (Integer)request.getSession(true).getAttribute("userId");
        result.put("moneyCount",wishService.getCountData(id,request.getParameter("searchDate")));
        return result;
    }

    @RequestMapping("/save")
    public String save (HttpServletRequest request,Wish wish){
        Integer result = 0;
        int userId = (Integer) request.getSession(true).getAttribute("userId");
        wish.setCreatorId(userId);
        SimpleDateFormat aDate=new SimpleDateFormat("yyyy-mm-dd");
//        if(wish.getCreateDate() == null){
//            wish.setCreateDate(getNowDateShort());
//        }
        if(wish.getId()!=null){
            result = wishService.update(wish);
        }else{
            result = wishService.insertWish(wish);
        }

        if(result!=null&&result>0){
            return "redirect:/Wish/list";
        }
        return toError();
    }

    /**'
     * Ajax 获取当月账单统计数据
     * @return
     */
    @RequestMapping("/finished")
    @ResponseBody
    public Integer finishWish(HttpServletRequest request){
        Integer result = 0;
        Integer id = Integer.valueOf(request.getParameter("id"));
        result = wishService.finishWish(id);
        return result;
    }
}
