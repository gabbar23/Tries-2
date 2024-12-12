

Time Complexity (TC): O(n + n log n)
Space Complexity (SC): O(n)

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Map for val - > Freq
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }


        // List for freq-> val
        ArrayList<Integer> list = new ArrayList<>(nums.length+1);
        for(int i=0;i<nums.length+1;i++)list.add(0);
        for (int i : map.keySet()) {
            // // if(list.get(map.get(i))==null)list.add(i,new ArrayList<>());
          
            list.add(map.get(i),i);
            // list.add(index, element);
        }
        int n=list.size();
        // System.out.println(list);
        

        //result list ---> holding k elements
        ArrayList<Integer>result=new ArrayList<>();
        for(int i=n-1;i>=0 && result.size()<=k;i--){
            if(list.get(i)!=0)result.add(list.get(i));
        }

        int[] res=new int[k];
        for(int i=0;i<k;i++){
            res[i]=result.get(i);
        }
        return res;
    }
}
