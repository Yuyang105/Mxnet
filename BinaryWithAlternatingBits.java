class Solution {
    public boolean hasAlternatingBits(int n) {
        String cheat = Integer.toBinaryString(n);
        if (cheat.length() < 2) return true;  
        for (int i = 0; i < cheat.length() - 1; i++) {
            if (cheat.charAt(i) == cheat.charAt(i+1))
                return false;
        }
        return true;
    }
}
