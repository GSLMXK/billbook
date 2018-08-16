package com.xk.billbook.admin.service;

import com.xk.billbook.admin.common.base.service.BaseService;
import com.xk.billbook.admin.mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class ReportService extends BaseService {
    @Autowired
    ReportMapper reportMapper;
       public List<Map<String,Object>> getPageData(){
           return reportMapper.findByParm("","","");
       }
}
