package com.tranngocqui.ditusmartfoodbackend.test;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> mapValueIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int part = target - nums[i];
            if (mapValueIndex.containsKey(part)) {
                return new int[]{i, mapValueIndex.get(part)};
            }
            mapValueIndex.put(nums[i], i);
        }
        return new int[]{};
    }
}
