import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        File inputData = new File(args[0]);
        List<Integer> nums = new ArrayList<>();
        String line = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(inputData))) {
            while ((line = reader.readLine()) != null) {
                nums.add(Integer.parseInt(line));
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Integer [] numbers = new Integer[nums.size()];
        nums.toArray(numbers);
        System.out.println(countSteps(numbers));
    }

    private static int countSteps(Integer[] nums){
        int sum = Arrays.stream(nums).reduce(0, Integer::sum);
        double average = (double) sum / nums.length;
        Arrays.sort(nums);
        int reference = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= average) {
                reference = Math.abs(average - nums[i - 1]) > Math.abs(average - nums[i]) ? nums[i] : nums [i-1];
                break;
            }
        }
        int count = 0;
        Integer[] result = new Integer[nums.length];
        Arrays.fill(result, reference);
        while (!Arrays.equals(nums, result)) {
            for (int i = 0; i < nums.length; i++){
                while (nums[i] != reference){
                    if (nums[i] < reference) {
                        nums[i]++;
                    } else {
                        nums[i]--;
                    }
                    count++;
                }
            }
        }
        return count;
    }
}
