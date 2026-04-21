import java.util.HashMap;
class Solution {
    int parent[];// store value of each index
    public  int find(int element){
        // find group of element
        if(parent[element] != element) 
        {
          
            parent[element] = find(parent[element]);
        }
        return parent[element];
        //root search 
    }
    //after find group merge(union)
    public void union(int a,int b){
        int grpA = find(a);
        int grpB = find(b);
        if(grpA != grpB){
            parent[grpA] = grpB;
            //if a and b element are  in same group then element merge in same group 
        }
    }
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
       int n = source.length;
       //dsu apply
      parent = new int[n];
       for(int i = 0;i<n;i++){
        parent[i] = i;
       }
       //built connected component using allowedspace array
       //union find 
       for(int [] swap : allowedSwaps){
        union(swap[0],swap[1]); // connected component bn gye 
       }
       //group wise frequency map 
       HashMap<Integer,HashMap<Integer,Integer>> GroupMap = new HashMap<>();
       // value count in group 
       for(int i = 0;i<n;i++){
        int root = find(i);
        GroupMap.putIfAbsent(root,new HashMap<>());
        HashMap<Integer,Integer> freqMap = GroupMap.get(root);
        freqMap.put(source[i],freqMap.getOrDefault(source[i],0)+1);
        //store value in each group 
       } 
       // if mismatch target found
       int result = 0;
       for(int i = 0;i<n;i++){
        int root = find(i);
        HashMap<Integer,Integer>freqMap = GroupMap.get(root);
        int value = target[i];
        if(freqMap.getOrDefault(value,0)>0){
            freqMap.put(value,freqMap.get(value)-1);
        }
        else result++;
       }
       // if value in group then use otherwise mismatch
       return result;
    
    }
}