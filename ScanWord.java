import java.util.regex.Pattern;

public class ScanWord{
    private String src_code;
    private String token;
    private char ch;
    private int token_pointer = 0;
    private int src_pointer = 0;
    
    // public String getOutput(int num){
    //     if(num == 0)
    // }

    private Boolean getChar(){
        if(this.src_pointer >= this.src_code.length()){
            return false;
        }
        ch = this.src_code.charAt(this.src_pointer);
        this.src_pointer++;
        return true;
    }

    private Boolean isSpace(){
        return this.ch == ' ';
    }

    private Boolean isNewLine(){
        return this.ch == '\n' || this.ch == '\r';
    }

    private Boolean isTab(){
        return this.ch == '\t';
    }

    private Boolean isLetter(){
        return Character.isLowerCase(this.ch) || Character.isUpperCase(this.ch);
    }

    private Boolean isDigit(){
        return Character.isDigit(ch);
    }

    private void catToken(){
        this.token = this.token + this.ch;
    }

    public Boolean retract(){
        if(this.src_pointer > 0){
            this.src_pointer--;
            return true;
        }
        return false;
    }

    private String reserver(){
        Pattern Begin = Pattern.compile("begin", Pattern.CASE_INSENSITIVE);
        Pattern End = Pattern.compile("end", Pattern.CASE_INSENSITIVE);
        Pattern For = Pattern.compile("for", Pattern.CASE_INSENSITIVE);
        Pattern If = Pattern.compile("if", Pattern.CASE_INSENSITIVE);
        Pattern Then = Pattern.compile("then", Pattern.CASE_INSENSITIVE);
        Pattern Else = Pattern.compile("else", Pattern.CASE_INSENSITIVE);
        Pattern letter_or_digit = Pattern.compile("[0-9a-zA-Z]+");
        if(Begin.matcher(this.token).matches()){
            return "Begin";
        }
        else if(End.matcher(this.token).matches()){
            return "End";
        }
        else if(For.matcher(this.token).matches()){
            return "For";
        }
        else if(If.matcher(this.token).matches()){
            return "If";
        }
        else if(Then.matcher(this.token).matches()){
            return "Then";
        }
        else if(Else.matcher(this.token).matches()){
            return "Else";
        }
        else if(letter_or_digit.matcher(this.token).matches()){
            return "Ident(" + this.token + ")";
        }
        else{
            return "Unknown";
        }
    }

    private String transNum(){
        int n;
        try{
            n = Integer.parseInt(this.token);
            return "Int(" + n + ")";
        }catch(NumberFormatException e){
            return "Unknown";
        }
    }

    public String getSym(){
        /**
         * 返回值：
         * '',代表已经到末尾了
         * 其他标识符和关键字无符号直接按格式走
         */
        if(!this.getChar()){
            return "";
        }
        this.token = "";
        while(this.isSpace() || this.isNewLine() || this.isTab()){
            if(!this.getChar()){
                return "";
            }
        }
        if(isLetter()){
            while(isLetter() || isDigit()){
                catToken();
                if(!getChar()){
                    break;
                }
            }
            retract();
            return this.reserver();
        }
        else if(this.isDigit()){
            while(this.isDigit()){
                catToken();
                if(!getChar()){
                    break;
                }
            }
            retract();
            return transNum();
        }
        else if(this.ch == ':'){
            if(getChar()){
                if(this.ch == '='){
                    return "Assign";
                }
                else{
                    retract();
                    return "Colon";
                }
            }
            else{
                return "Colon";
            }
        }
        else if(this.ch == '+') return "Plus";
        else if(this.ch == '*') return "Star";
        else if(this.ch == ',') return "Comma";
        else if(this.ch == '(') return "LParenthesis";
        else if(this.ch == ')') return "RParenthesis";
        else return "Unknown";
    }

    ScanWord(String src_code){
        if(src_code == ""){
            this.token = "";
            this.token_pointer = 0;
            this.ch = '\0';
        }
        this.src_code = src_code;
        this.src_pointer = 0;
        this.token = "";
        this.token_pointer = 0;
        this.ch = src_code.charAt(0);
    }
}