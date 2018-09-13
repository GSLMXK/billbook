package com.xk.billbook.admin.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConfig {
    /**
     * 文件保存路径
     */
    @Value("${file.location}")
    public String location;
    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${sigar.location}")
    public String sigarLocation;

    public String getLocation() {
        return location;
    }

    public String getSigarLocation() {
        return sigarLocation;
    }
}
