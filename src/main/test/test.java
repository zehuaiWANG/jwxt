import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class test {

    public static void main(String[] args) throws IOException,
            RowsExceededException, WriteException {
        WritableWorkbook writeBook = Workbook.createWorkbook(new File("D://write.xls"));
        WritableSheet firstSheet = writeBook.createSheet("first", 1);// 第一个参数为工作簿的名称，第二个参数为页数
        Label label1 = new Label(1, 1, "test1");
        firstSheet.addCell(label1);
        writeBook.write();
        writeBook.close();
    }
}
