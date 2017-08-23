package com.jwxt.controller.protal;

import com.jwxt.common.DoExcel;
import com.jwxt.dao.StudentinfoMapper;
import com.jwxt.pojo.Studentinfo;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@Scope("prototype")
@RequestMapping("/downloadFile")
public class DownLoad
{
    @Autowired
    private StudentinfoMapper studentinfoMapper;
    @RequestMapping(value ="download.do",method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException, WriteException {
        String path=request.getServletContext().getRealPath("/WEB-INF/")+"write.xls";
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
        File file=new File(path);
        HttpHeaders headers = new HttpHeaders();
        String fileName=new String("write.xls".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
}
