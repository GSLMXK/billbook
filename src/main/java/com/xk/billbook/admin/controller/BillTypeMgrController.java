package com.xk.billbook.admin.controller;

import com.xk.billbook.admin.common.base.controller.BaseController;
import com.xk.billbook.admin.mapper.BillTypeMgrMapper;
import com.xk.billbook.admin.model.BillType;
import com.xk.billbook.admin.service.BillTypeMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 账单类型管理控制类
 * xiekuang
 * 2018/8/3
 */
@Controller
@RequestMapping("/BillType")
public class BillTypeMgrController extends BaseController<BillType> {

    @Autowired
    BillTypeMgrService billTypeMgrService;

    public List getTypeList(){

        return billTypeMgrService.findByList();
    }
}
