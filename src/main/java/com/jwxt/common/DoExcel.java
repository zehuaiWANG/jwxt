package com.jwxt.common;

import com.jwxt.dao.StudentinfoMapper;
import com.jwxt.pojo.Studentinfo;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DoExcel {
    @Autowired
    private StudentinfoMapper studentinfoMapper;
    private String filePath;
    private List list = new ArrayList();

    public DoExcel(){}
    public DoExcel(String filePath){
        this.filePath = filePath;
    }

    private void readExcel() throws IOException, BiffException {
        InputStream stream = new FileInputStream(filePath);
        Workbook rwb = Workbook.getWorkbook(stream);
        Sheet sheet = rwb.getSheet(0);
        for(int i=0; i<sheet.getRows(); i++){
            String[] str = new String[sheet.getColumns()];
            Cell cell = null;
            for(int j=0; j<sheet.getColumns(); j++){
                cell = sheet.getCell(j,i);
                str[j] = cell.getContents();
            }
            list.add(str);
        }
    }
    private void outData(){
        for(int i=0;i<list.size();i++){
            String[] str = (String[])list.get(i);
            for(int j=0;j<str.length;j++){
                System.out.print(str[j]+'\t');
            }
            System.out.println();
        }
    }

    public void downloadExecl(String path) throws IOException, RowsExceededException, WriteException {
        WritableWorkbook writeBook = Workbook.createWorkbook(new File(path));
        WritableSheet firstSheet = writeBook.createSheet("first", 1);// 第一个参数为工作簿的名称，第二个参数为页数
        List<Studentinfo> list =studentinfoMapper.findall();
        for (int i = 0; i < list.size(); i++ ){
            Label label1 = new Label(0, i, String.valueOf(list.get(i).getStudentId()));
            firstSheet.addCell(label1);
            Label label2 = new Label(1, i, list.get(i).getClassId());
            firstSheet.addCell(label2);
            Label label3 = new Label(2, i, list.get(i).getClassName());
            firstSheet.addCell(label3);
            Label label4 = new Label(3, i, list.get(i).getClassTime());
            firstSheet.addCell(label4);
        }
        writeBook.write();
        writeBook.close();
    }
}
