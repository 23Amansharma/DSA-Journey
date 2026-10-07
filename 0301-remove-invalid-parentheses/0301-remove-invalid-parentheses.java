class Solution {
    public List<String> removeInvalidParentheses(String s) {
    List<String> output = new ArrayList<>();
    Queue<String> check = new LinkedList<>();
    Set<String> notRepeat = new HashSet<>();
    check.add(s);
    notRepeat.add(s);
    boolean found = false;
    while(!check.isEmpty()){
        String oneStringElement = check.poll();
        if(isValid(oneStringElement)){
            output.add(oneStringElement);
            found = true;
        }
        if(found) continue;//found true case me 
    // invalid case me 
    for(int i = 0;i<oneStringElement.length();i++){
        char ch = oneStringElement.charAt(i);
        if( ch !='(' && ch != ')') continue; // a,b etc in case
        // valid make
        String newString =oneStringElement.substring(0,i) + oneStringElement.substring(i+1);
        if(!notRepeat.contains(newString)){
            notRepeat.add(newString);
            check.add(newString);
        }
    }
    }
      return output;
    }
    private boolean isValid(String s){
        int depth = 0;
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i) == '(') depth++;
            else if(s.charAt(i) == ')') {
                depth--;
            if( depth < 0 ) return false;
            }
        } return depth == 0;
    }
    }
    