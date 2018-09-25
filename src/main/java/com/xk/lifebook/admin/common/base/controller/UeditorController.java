/*
package com.xk.billbook.admin.common.base.controller;

import com.baidu.ueditor.ActionEnter;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class UeditorController {
    @Autowired
    private HttpServletRequest request;
    @RequestMapping("/ueditorConfig")
    public void getUEditorConfig(HttpServletResponse response){
        String rootPath = "src/main/resources/static";
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
*/
