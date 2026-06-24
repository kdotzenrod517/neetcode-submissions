class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int[] pair : prerequisites){
            map.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
        }

        List<Integer> output = new ArrayList<>();
        Set<Integer> visit = new HashSet<>();
        Set<Integer> cycle = new HashSet<>();

        for(int course = 0; course < numCourses; course++){
            if(!dfs(course, map, visit, cycle, output)){
                return new int[0];
            }
        }
    
        int[] res = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            res[i] = output.get(i);
        }
        
        return res;
    }

    boolean dfs(int crs, Map<Integer, List<Integer>> map, Set<Integer> visit, Set<Integer> cycle, List<Integer> output) {

            if(cycle.contains(crs)){
                return false;
            }

            if(visit.contains(crs)){
                return true;
            }

            cycle.add(crs);

            for(int pre : map.getOrDefault(crs, Collections.emptyList())){
                if(!dfs(pre, map, visit, cycle, output)){
                    return false;
                }
            }

            cycle.remove(crs);
            visit.add(crs);
            output.add(crs);
            return true;
    }
}
