import java.io.*;
import java.net.URL;
import java.net.URLConnection;
public class test {
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static boolean getConnection(String username,String password){
        String sr=test.sendPost("http://gpa.sustc.edu.cn/queryscorebyitem", "username="+username+"&password="+password);
        if (sr.contains("\"status\":1000")){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        //String sr=test.sendPost("http://gpa.sustc.edu.cn/queryscorebyitem", "username=1151093&password=101871");
       // System.out.println(sr);
        if (getConnection("11510693","101871")){
            System.out.println("成功");
        }
    }
}