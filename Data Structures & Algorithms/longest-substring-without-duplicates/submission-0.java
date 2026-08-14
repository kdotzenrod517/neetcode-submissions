class Solution {
    public int lengthOfLongestSubstring(String s) {

        Set<Character> set = new HashSet<>();

        int l = 0;

        int result = 0;

        for(int r = 0; r < s.length(); r++){

            while(set.contains(s.charAt(r))) {
                set.remove(s.charAt(l));
                l++;
            }

            set.add(s.charAt(r));
            result = Math.max(result, r - l + 1);
        }
        
        return result;
    }
}
