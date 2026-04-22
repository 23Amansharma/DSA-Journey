class Solution 
{
    public List<String> twoEditWords(String[] queries, String[] dictionary) 
    {
        List<String> ans = new ArrayList<>();//ans array creat 
        for(String word : queries) //Loop through each query word
        {
            for(String word2 : dictionary)//Compare with every dictionary word
            {
               int  distance = 0; //count of different characters
                for(int i = 0;i<word.length();i++)//Compare characters one by one
                {
                    if(word.charAt(i) != word2.charAt(i))
                    {
                        distance++;// no match distance  count
                        
                    if(distance>2)// 2 se jyda ignore nhi count limit max 2 letter changes
                    {
                        break;
                    }
                }
            }
        if(distance<=2) // dist 2 ya 2 se km to changes allow
        {
            ans.add(word);// add query word to answer array
            break; //no need to check other dictionary words
        }
    }
}
        return ans; // finall array return kr diya 
    }
}