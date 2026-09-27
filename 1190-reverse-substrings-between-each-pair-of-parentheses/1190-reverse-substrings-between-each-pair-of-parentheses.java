class Solution {
    public String reverseParentheses(String s) {
       Stack<String> stack =new Stack<>();
       String attime = "";
       for(char ch : s.toCharArray()) {
        if(ch == '(') {
        stack.push(attime);
        attime = "";
        }
       else if(ch == ')' ){
       attime = new StringBuilder(attime).reverse().toString();
       String previous = stack.pop();
       attime = previous + attime;
       }
          else attime += ch;
       }
       return attime;
    }
}