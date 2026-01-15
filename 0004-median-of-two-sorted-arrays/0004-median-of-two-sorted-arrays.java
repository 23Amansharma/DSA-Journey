class Solution {
    public double findMedianSortedArrays(int[] arr1, int[] arr2) {
        int  a = arr1.length;
        int b = arr2.length;
        int merge[] = new int[a+b];
        int i =0,j=0,k=0;
        while(i<a && j<b){
            if(arr1[i] < arr2[j]){
                merge[k++]=arr1[i++];
            }else{
                merge[k++]=arr2[j++];
            }
        }
          while (i <a) {
            merge[k++] = arr1[i++];
        }
        while (j < b) {
            merge[k++] = arr2[j++];
        }

        int total = a + b;

        if (total % 2 == 1) {
            return merge[total / 2];
        } else {
            int mid1 = total / 2;
            int mid2 = mid1 - 1;
            return (merge[mid1] + merge[mid2]) / 2.0;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        int m = sc.nextInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[m];
        for(int i = 0;i<n;i++){
            arr1[i] = sc.nextInt();
        }
           for(int j = 0;j<m;j++){
            arr2[j] = sc.nextInt();
        }
        Solution merge = new Solution();
        double output = merge.findMedianSortedArrays(arr1,arr2);
        System.out.println(output);
        sc.close();
    }
}