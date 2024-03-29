package com.sys.classloder.framework;

import com.sys.classloder.businessclient.BusService;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;


/**
 * 热部署框架
 */
public class Server {

    String codePath = "D:\\IdeaProjects\\sys-test\\sys-classloader\\src\\main\\java\\com\\sys\\classloder\\businessclient\\";
    String busServiceClass="com.sys.classloder.businessclient.BusServiceImpl";
    BusService busService;
    public String doWork(String name){
        if(null != busService){
            return busService.doIt(name);
        }

        return "default";
    }

    public void init(){
        new Thread(){
            long lastTime=0;
            public void run() {
                File f = new File(codePath+"version.txt");
                while(true){
                    if(lastTime != f.lastModified()){
                        lastTime = f.lastModified();

                        ClassLoader cl = this.getClass().getClassLoader();
                        System.out.println(cl);
                        MyClassLoader myLoader = new MyClassLoader(new URL[0]);
                        try {
                            myLoader.addDir(codePath);
                            Class<BusService> clazz = (Class<BusService>) myLoader.loadClass(busServiceClass);
                            BusService busService2 = clazz.newInstance();
                            BusService temp = busService;
                            busService = busService2;
                            temp.close();//释放资源，尤其是线程，若线程不关闭的话，则类不会卸载，且会一直运行
                            ClassLoader c = temp.getClass().getClassLoader();
                            if(c instanceof URLClassLoader) ((URLClassLoader) c).close();//释放资源
                            System.out.println("busService:"+busService + "  ,classloader:"+busService.getClass().getClassLoader());
                            System.out.println("end test "+ new Date().toLocaleString());
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
        }.start();

        //myLoader.close();
    }
}