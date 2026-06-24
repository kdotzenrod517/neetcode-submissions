class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int[] pre : prerequisites){
            map.computeIfAbsent(pre[0], k -> new ArrayList<>()).add(pre[1]);
        }

        List<Integer> output = new ArrayList<>();
        Set<Integer> cycle = new HashSet<>();
        Set<Integer> visit = new HashSet<>();

        for(int i = 0; i < numCourses; i++){
            if(!dfs(i, map, cycle, visit, output)){
                return new int[0];
            }
        }

        int[] res = new int[numCourses];

        for(int i = 0; i < numCourses; i++){
            res[i] = output.get(i);
        }   

        return res;     
    }

    boolean dfs(int crs, Map<Integer, List<Integer>> map, Set<Integer> cycle, Set<Integer> visit, List<Integer> output){

        if(cycle.contains(crs)){
            return false;
        }

        if(visit.contains(crs)){
            return true;
        }

        cycle.add(crs);

        for(int p : map.getOrDefault(crs, new ArrayList<>())){
            if(!dfs(p, map, cycle, visit, output)){
                return false;
            }
        }

        visit.add(crs);
        cycle.remove(crs);
        output.add(crs);
        return true;
    }
 
}
