package com.sys.test.websocket;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class WebsocketApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(WebsocketApplication.class, args);
        createSqlFile("检验医嘱排名统计.xls",
                "check.sql",
                "INSERT INTO lepu_hospital.hos_check_item (check_item_code,check_item_name,check_note,is_limosis,status,description,remark,create_user,create_time,update_user,update_time,delete_flag)VALUES('','",
                "','',1,1,'','','',now(),'',now(),0);\r\n");
        createSqlFile("检查医嘱排名统计.xls",
                "inspect.sql",
                "INSERT INTO lepu_hospital.hos_inspect_item (inspect_item_c ode,inspect_item_name,inspect_type_code,description,is_limosis,attentions,status,remark,create_time,create_user,update_time,update_user,delete_flag)\n" +
                        "VALUES('','",
                "','','',1,'',1,'',now(),'',now(),'',0);\r\n");

    }

    public static void createSqlFile(String srcName, String targetName, String prefix, String suffix) throws IOException {

        FileInputStream fis = new FileInputStream("C:\\Users\\admin\\Desktop\\" + srcName);
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);
        File file = new File("C:" + File.separator + "Users" + File.separator + "admin" + File.separator + "Desktop" + File.separator + targetName);
        Writer output;
        output = new BufferedWriter(new FileWriter(file), 1024 * 1024 * 10);

        //获取行数
        int rowNum = sheet.getLastRowNum();
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < physicalNumberOfRows; i++) {
            HSSFRow row = sheet.getRow(i);
            HSSFCell cell = row.getCell(1);
            cell.setCellType(CellType.STRING);
            output.append(prefix + cell.getStringCellValue() + suffix);
            System.out.println(i);
        }
        output.flush();
        output.close();
    }
}
