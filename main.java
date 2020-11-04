import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main {
    private static String readFileContent(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String content = "";
        StringBuilder sb = new StringBuilder();
        content = bf.readLine();
        sb.append(content.trim());
        bf.close();
        return sb.toString();
    }

    public static void main(String[] args) {
        String src_code = "";
        priority pri = new priority();
        try{
            src_code = readFileContent(args[0]);
        }
        catch(IOException e){
            System.out.println("error!");
            return;
        }
        src_code = src_code + '#';
        int i = 0;
        while(i < src_code.length()){
            int flag = pri.priority_cmp(src_code.charAt(i));
            // System.out.println(i + "符号"+src_code.charAt(i));
            if(flag == 0){
                // 不能识别
                System.out.println("E");
                return;
            }
            else if(flag == 1){
                // 规约
                pri.guiyue();
                System.out.println("R");
            }
            else if(flag == 2){
                // 失败的规约
                System.out.println("RE");
                return;
            }
            else if(flag == -1){
                // 入栈
                pri.push(src_code.charAt(i));
                System.out.println("I" + src_code.charAt(i));
                i++;
            }
            else{
                return;
            }
        }
    }
}
