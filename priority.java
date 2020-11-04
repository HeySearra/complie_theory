import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class priority {
    int priority_array[][] = {
        {1, -1, -1, -1, 1, 1},
        {1, 1, -1, -1, 1, 1},
        {1, 1, 0, 0, 1, 1},
        {-1, -1, -1, -1, -1, 0},
        {1, 1, 0, 0, 1, 1},
        {-1, -1, -1, -1, 0, 2},
    };
    
    Map<Character, Integer> index = new HashMap<Character, Integer>(){{
        put('+', 0);
        put('*', 1);
        put('i', 2);
        put('(', 3);
        put(')', 4);
        put('#', 5);
    }};

    List<Character> all_stack = new ArrayList<Character>();
    List<Character> token_stack = new ArrayList<Character>();

    public priority(){
        all_stack.add('#');
        token_stack.add('#');
    }

    public boolean is_valid(char a) {
        if(a != '+' && a != '*' && a != '(' && a != ')' && a != 'i' && a != '#'){
            return false;
        }
        return true;
    }

    /*
    返回0为字符非法，或优先矩阵error
    返回1为规约, 2为失败的规约
    -1为入栈
    -2为规约结束并接受结果
    */
    public int priority_cmp(char b) {
        char a = this.token_stack.get(token_stack.size() - 1);
        // System.out.println("符号："+ a);
        if(!is_valid(b)){
            return 0;
        }
        else if(this.priority_array[this.index.get(a)][this.index.get(b)] == 1){
            // todo
            switch(a){
                case '+':
                    if(all_stack.size() >= 3 && all_stack.get(all_stack.size() - 1) == 'E' && all_stack.get(all_stack.size() - 3) == 'E'){
                        return 1;
                    }
                    else{
                        return 2;
                    }
                case '*':
                    if(all_stack.size() >= 3 && all_stack.get(all_stack.size() - 1) == 'E' && all_stack.get(all_stack.size() - 3) == 'E'){
                        return 1;
                    }
                    else{
                        return 2;
                    }
                case ')':
                    if(all_stack.size() >= 3 && all_stack.get(all_stack.size() - 1) == ')' && all_stack.get(all_stack.size() - 2) == 'E' && all_stack.get(all_stack.size() - 3) == '('){
                        return 1;
                    }
                    else{
                        return 2;
                    }
                case 'i':
                    return 1;
            }
            return 1;
        }
        else if(this.priority_array[this.index.get(a)][this.index.get(b)] == -1){
            return -1;
        }
        else if(this.priority_array[this.index.get(a)][this.index.get(b)] == 0){
            return 0;
        }
        else{
            if(all_stack.size() == 2 && all_stack.get(1) == 'E'){
                return -2;
            }
            else{
                return 0;
            }
        }
    }

    public void push(char a){
        this.all_stack.add(a);
        this.token_stack.add(a);
    }

    // 保证可以规约
    public void guiyue(){
        if(token_stack.get(token_stack.size() - 1) == 'i'){
            token_stack.remove(token_stack.size() - 1);
            all_stack.set(all_stack.size() - 1, 'E');
        }
        else if(token_stack.get(token_stack.size() - 1) == ')'){
            token_stack.remove(token_stack.size() - 1);
            token_stack.remove(token_stack.size() - 1);
            all_stack.remove(all_stack.size() - 1);
            all_stack.remove(all_stack.size() - 1);
            all_stack.remove(all_stack.size() - 1);
            all_stack.add('E');
        }
        else{
            token_stack.remove(token_stack.size() - 1);
            all_stack.remove(all_stack.size() - 1);
            all_stack.remove(all_stack.size() - 1);
            all_stack.remove(all_stack.size() - 1);
            all_stack.add('E');
        }
    }
}
