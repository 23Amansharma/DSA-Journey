import java.util.ArrayList;
import java.util.List;

class Fancy {
    
    List<Long> seq;
    long add;
    long mult;
    final int MOD = 1_000_000_007;

    public Fancy() {
        seq = new ArrayList<>();
        add = 0;
        mult = 1;
    }
    
    public void append(int val) {
        // Formula: val = (storedVal * mult) + add
        // Isliye: storedVal = (val - add) / mult
        // Modulo division ke liye inverse(mult) ka use karenge
        long storedVal = (val - add % MOD + MOD) % MOD; 
        storedVal = (storedVal * power(mult, MOD - 2)) % MOD;
        seq.add(storedVal);
    }
    
    public void addAll(int inc) {
        // Global add ko update karo
        add = (add + inc) % MOD;
    }
    
    public void multAll(int m) {
        // Multiply karne par add aur mult dono update honge
        mult = (mult * m) % MOD;
        add = (add * m) % MOD;
    }
    
    public int getIndex(int idx) {
        if (idx >= seq.size()) return -1;
        
        // Stored value ko wapas real value me convert karo
        long realVal = (seq.get(idx) * mult % MOD + add) % MOD;
        return (int) realVal;
    }
    
    // Fast Exponentiation (Fermat's Little Theorem ke liye)
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */