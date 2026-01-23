import java.util.LinkedList;
import java.util.Queue;

class RecentCounter {

    // Queue banaya request time store karne ke liye
    Queue<Integer> q;

    // Constructor: object bante hi empty queue ban jayega
    public RecentCounter() {
        q = new LinkedList<>();
    }
    
    public int ping(int t) {

        // Step 1: new request ka time queue me daal diya
        q.add(t);

        // Step 2: jo request 3000 ms se purani hai usko hata diya
        // t - 3000 se chhota time valid nahi hai
        while (q.peek() < t - 3000) {
            q.poll(); // front se remove
        }

        // Step 3: queue ka size hi answer hai
        return q.size();
    }
}
