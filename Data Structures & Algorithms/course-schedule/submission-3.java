class Solution {

    Map<Integer, List<Integer>> preMap = new HashMap<>();
    Set<Integer> visiting = new HashSet<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        for(int i = 0; i < numCourses; i++){
            preMap.put(i, new ArrayList<>());
        }

        for(int[] preReq : prerequisites){
            preMap.get(preReq[0]).add(preReq[1]);
        }

        for(int c = 0; c < numCourses; c++){
            if(!dfs(c)){
                return false;
            }
        }

        return true;
    }


    boolean dfs(int crs){
        if (visiting.contains(crs)){
            return false;
        }

        if(preMap.get(crs).isEmpty()){
            return true;
        }

        visiting.add(crs);

        for(int pre : preMap.get(crs)){
            if(!dfs(pre)){
                return false;
            }
        }

        visiting.remove(crs);
        preMap.put(crs, new ArrayList<>());

        return true;
    }


}
