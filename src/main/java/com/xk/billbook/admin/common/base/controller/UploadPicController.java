package com.xk.billbook.admin.common.base.controller;

import com.xk.billbook.admin.common.utils.FastDFSClientWrapper;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadPicController {

    @Resource
    private FastDFSClientWrapper fwc;

    /**
     * 图片上传
     * @param file
     * @return
     *
     * @author ZHANGJL
     * @dateTime 2018-02-24 15:49:48
     */
    @PostMapping("/uploadPic/{random}")
    @ResponseBody
    public Map<String, Object> uploadPic(@RequestParam("file") MultipartFile file){
        Map<String, Object> result = new HashMap<String,Object>();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            //返回FastDFS配置好的图片路径
            String url = fwc.uploadFile(file);//正常上传
            String url1 = fwc.uploadImageAndCrtThumbImage(file);//小图
            map.put("max", url);
            map.put("min", url1);
            result.put("code",200);
            result.put("content",map);
        } catch (Exception e) {
            // TODO: handle exception
            result.put("code",400);
        }

        return result;
    }
}
