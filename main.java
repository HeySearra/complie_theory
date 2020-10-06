import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class main {
    private static String readFileContent(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String content = "";
        StringBuilder sb = new StringBuilder();
        while(content != null){
            content = bf.readLine();
            if(content == null){
                break;
            }
            sb.append(content.trim());
            sb.append("\n");
        }
        bf.close();
        return sb.toString();
    }
    
    public static void main(String[] args) {
        String src_code;
        try{
            src_code = readFileContent(args[0]);
        }
        catch(IOException e){
            System.out.println("error!");
            return;
        }
        ScanWord sw = new ScanWord(src_code);
        String res = sw.getSym();
        while(res != "" && !res.equals("Unknown")){
            System.out.println(res);
            res = sw.getSym();
        }
        if(res.equals("Unknown")){
            System.out.println(res);
        }
    }
}
