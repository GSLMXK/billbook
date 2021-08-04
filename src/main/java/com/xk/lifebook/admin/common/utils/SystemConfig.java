package com.xk.lifebook.admin.common.utils;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.DatabaseMetaData;
import com.xk.lifebook.admin.common.base.model.ServerInfoVo;
import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

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
    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${spring.datasource.driver-class-name}")
    public String dataDriver;
    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${spring.datasource.url}")
    public String dataUrl; /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${spring.datasource.username}")
    public String dataUser; /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${spring.datasource.password}")
    public String dataPwd;

    public String getLocation() {
        return location;
    }

    public String getSigarLocation() {
        return sigarLocation;
    }

    public ServerInfoVo getServerInfo(HttpServletRequest request){
        //服务信息
        ServerInfoVo serverInfoVo = new  ServerInfoVo();
        try {
            SigarUtils.initSigar();			//初始化动态库链接路径
//            ServerInfoDto dto = super.getExactlyDto(baseAbstractDto);
            Properties props=System.getProperties();
            Runtime runTime = Runtime.getRuntime();
            InetAddress address = InetAddress.getLocalHost();


            InetAddress.getLocalHost().getHostAddress();
            serverInfoVo.setServerIP(address.getHostAddress());
            serverInfoVo.setServerURL(
                    request.getScheme()					//请求头
                            +"://" + address.getHostAddress() 	//服务器地址
                            + ":"
                            + request.getServerPort()        	//端口号
                            + request.getContextPath());      	//项目名称
            ServletContext application = request.getSession().getServletContext();
            serverInfoVo.setServerType(application.getServerInfo());
            serverInfoVo.setServerTime(new Date());

            //操作系统
            serverInfoVo.setOsName(props.getProperty("os.name")+"  "+props.getProperty("os.arch"));
            serverInfoVo.setOsVersion(props.getProperty("os.version"));
            serverInfoVo.setUserName(props.getProperty("user.name"));
            serverInfoVo.setUserHome(props.getProperty("user.home"));
            Calendar cal = Calendar.getInstance();
            TimeZone timeZone = cal.getTimeZone();
            serverInfoVo.setOsTimeZone(timeZone.getDisplayName());

            //Cpu
            Sigar sigar = new Sigar();
//            CpuInfo cpuInfos[] = sigar.getCpuInfoList();
//            serverInfoVo.setCpuNum(cpuInfos.length);
//            CpuPerc cpuList[] = sigar.getCpuPercList();
//            List<CpuInfoVo> cpuVoList = new ArrayList<CpuInfoVo>();
//            for(int m=0;m<cpuList.length;m++){
//                CpuInfoVo cpuInfoVo = new CpuInfoVo();
//                cpuInfoVo.setCpuMhz(cpuInfos[m].getMhz());
//                String cpuIdle = String.format("%.2f",cpuList[m].getIdle()*100)+"%";
//                cpuInfoVo.setCpuLdle(cpuIdle);
//                String cpuCombined = String.format("%.2f",cpuList[m].getCombined()*100)+"%";
//                cpuInfoVo.setCpuCombined(cpuCombined);
//                cpuVoList.add(cpuInfoVo);
//            }
//            serverInfoVo.setCpuList(cpuVoList);

            //网卡信息
//            String netInfos[] = sigar.getNetInterfaceList();
//            List<NetInterfaceConfig> netList = new ArrayList<NetInterfaceConfig>();
//            for(int i=0;i<netInfos.length;i++){
//                NetInterfaceConfig netInfo = sigar.getNetInterfaceConfig(netInfos[i]);
//                if((NetFlags.LOOPBACK_ADDRESS.equals(netInfo.getAddress()))  	//127.0.0.1
//                        || (netInfo.getFlags() == 0)  							//标识为0
//                        || (NetFlags.NULL_HWADDR.equals(netInfo.getHwaddr()))	//MAC地址不存在
//                        || (NetFlags.ANY_ADDR.equals(netInfo.getAddress()))		//0.0.0.0
//                ){
//                    continue;
//                }
//                netList.add(netInfo);
//            }
//            serverInfoVo.setNetNum(netList.size());
//            serverInfoVo.setNetList(netList);

            //物理内存
//            Mem mem = sigar.getMem();
//            serverInfoVo.setMemTotal((mem.getTotal()/(1024*1024))+"M");
//            serverInfoVo.setMemUsed((mem.getUsed()/(1024*1024))+"M");
//            serverInfoVo.setMemFree((mem.getFree()/(1024*1024))+"M");

            //java配置
            serverInfoVo.setJavaPath(props.getProperty("java.home"));
            serverInfoVo.setJavaVendor(props.getProperty("java.vendor"));
            serverInfoVo.setJavaVersion(props.getProperty("java.version"));
            serverInfoVo.setJavaName(props.getProperty("java.specification.name"));
            serverInfoVo.setJvmVersion(props.getProperty("java.vm.version"));
            serverInfoVo.setJvmTotalMemory((int)(runTime.totalMemory()/(1024*1024))+"M");
            serverInfoVo.setJvmFreeMemory((int)(runTime.freeMemory()/(1024*1024))+"M");

            //数据库信息
            findDatabase(serverInfoVo);
//            dto.setServerInfoVo(serverInfoVo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverInfoVo;
    }
    //数据库连接信息
    public void findDatabase(ServerInfoVo serverInfoVo){
        try{
            serverInfoVo.setJdbcUrl(dataUrl);
            Class.forName(dataDriver);
//            Connection con = (Connection) DriverManager.getConnection(dataUrl,dataUser,dataPwd);
//            DatabaseMetaData metaData = (DatabaseMetaData) con.getMetaData();
//            serverInfoVo.setDatabaseType(metaData.getDatabaseProductName());
//            serverInfoVo.setDatabaseVersion(metaData.getDatabaseProductVersion());
//            serverInfoVo.setDatabaseDriver(metaData.getDriverName());
//            serverInfoVo.setDriverVersion(metaData.getDriverVersion());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
