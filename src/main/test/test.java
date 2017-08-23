import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class test {

    public static  boolean checktime(String time1,String time2){
        String str1[] = time1.split(":");
        int num1 = Integer.parseInt(str1[0]);
        int num2 = Integer.parseInt(str1[1].split("-")[1]);
        String str2[] = time2.split(":");
        int num3 = Integer.parseInt(str2[0]);
        int num4 = Integer.parseInt(str2[1].split("-")[1]);
        System.out.println(num1+"  "+num2+"  "+num3+"  "+num4);
        boolean flag = false;
        if ( (num1<num3&&num3<num2) || (num1<num4&&num4<num2) ||
        (num3<num1&&num1<num4) || (num3<num2&&num2<num4)
                || (num1 == num3&& num2 == num4)) flag =true;

        return flag;
    }

    public static void main(String[] args){
        if (checktime("11:00-12:00","9:00-11:00")){
            System.out.println(1);
        }

    }
}
