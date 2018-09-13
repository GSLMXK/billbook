package com.xk.billbook.admin.common.utils;

import java.io.File;

import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.annotation.Autowired;


public class SigarUtils {
    public final static Sigar sigar = initSigar();
    @Autowired
    private SystemConfig system;
    public static Sigar initSigar() {
        try {
            File classPath = new File("/static/sigarlib/");

            String path = System.getProperty("java.library.path");
            String sigarLibPath = classPath.getCanonicalPath();
            // 为防止java.library.path重复加，此处判断了一下
            if (!path.contains(sigarLibPath)) {
                if (isOSWin()) {
                    path += ";" + sigarLibPath;
                } else {
                    path += ":" + sigarLibPath;
                }
                System.setProperty("java.library.path", path);
            }
            return new Sigar();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isOSWin() {// OS 版本判断
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("win") >= 0) {
            return true;
        } else
            return false;
    }
}
