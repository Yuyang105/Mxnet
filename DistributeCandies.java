/** Hashset solution */
class Solution {
    public int distributeCandies(int[] candies) {
        Set<Integer> kinds = new HashSet<>();
        for (int i : candies)
            kinds.add(i);
        
        if (kinds.size() < candies.length / 2)
            return kinds.size();
        else
            return candies.length / 2;
    }
}
