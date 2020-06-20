public class Main {

    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        r.gc();//计算内存前先垃圾回收一次
        long start = System.currentTimeMillis();//开始Time
        long startMem = r.freeMemory(); // 开始Memory
        //------------------------------


//        printFindDuplicate();
//        printMaxArea();
        printInitToRoman(140);

        //------------------------------
        long endMem =r.freeMemory(); // 末尾Memory
        long end = System.currentTimeMillis();//末尾Time
        //输出
        System.out.println("TimeCost: "+String.valueOf(end - start)+"ms");
        System.out.println("MemoryCost: "+String.valueOf((startMem- endMem)/1024)+"KB");
    }

    private static void printFindDuplicate() {
        int[] nums = {1, 2, 3, 5, 6, 7, 3, 4, 3, 9, 10};

        System.out.println("重复的数字：" + findDuplicate(nums));
    }

    private static void printMaxArea() {
        int[] height = {7, 1, 1, 1, 1, 1, 10, 8};

        int maxArea = maxArea(height);
        int maxArea1 = maxArea1(height);
        System.out.println("maxArea = [" + maxArea + "]" + " maxArea1 = [" + maxArea1 + "]");
    }

    private static void printInitToRoman(int num) {
        System.out.println("罗马数字：" + intToRoman(num));

    }

    /**
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 不能更改原数组（假设数组是只读的）。
     * 只能使用额外的 O(1) 的空间。
     * 时间复杂度小于 O(n2) 。
     * 数组中只有一个重复的数字，但它可能不止重复出现一次。
     *
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        /**
         快慢指针思想, fast 和 slow 是指针, nums[slow] 表示取指针对应的元素
         注意 nums 数组中的数字都是在 1 到 n 之间的(在数组中进行游走不会越界),
         因为有重复数字的出现, 所以这个游走必然是成环的, 环的入口就是重复的元素,
         即按照寻找链表环入口的思路来做
         **/
        int fast = 0, slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            System.out.println(nums[slow]);
            if (slow == fast) {
                fast = 0;
                while (nums[slow] != nums[fast]) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }
    }

    /**
     * 11.盛最多水的容器
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/container-with-most-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int size = height.length;
        int maxArea = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                if (area >= maxArea) {
                    maxArea = area;
                }
            }
        }

        return maxArea;
    }

    /**
     * 11.盛最多水的容器
     * 官方答案
     */
    public static int maxArea1(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }

    /**
     * 12. 整数转罗马数字
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/integer-to-roman
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param num
     * @return
     */
    public static String intToRoman(int num) {
        String a = "";
        while (num > 0) {

            if (num >= 1000) {
                int s = num / 1000;
                for (int i = 0; i < s; i++) {
                    a += "M";
                }
                num = num - s * 1000;
            } else if (num >= 900) {
                a += "CM";
                num -= 900;
            } else if (num >= 500) {
                int s = num / 500;
                for (int i = 0; i < s; i++) {
                    a += "D";
                }
                num = num - s * 500;
            } else if (num >= 400) {
                a += "CD";
                num -= 400;
            } else if (num >= 100) {
                int s = num / 100;
                for (int i = 0; i < s; i++) {
                    a += "C";
                }
                num = num - s * 100;
            } else if (num >= 90) {
                a += "XC";
                num -= 90;
            } else if (num >= 50) {
                int s = num / 50;
                for (int i = 0; i < s; i++) {
                    a += "L";
                }
                num = num - s * 50;
            } else if (num >= 40) {
                a += "XL";
                num -= 40;
            } else if (num >= 10) {
                int s = num / 10;
                for (int i = 0; i < s; i++) {
                    a += "X";
                }
                num = num - s * 10;
            } else if (num >= 9) {
                a += "IX";
                num -= 9;
            } else if (num >= 5) {
                int s = num / 5;
                for (int i = 0; i < s; i++) {
                    a += "V";
                }
                num = num - s * 5;
            } else if (num >= 4) {
                a += "IV";
                num -= 4;
            } else {
                int s = num / 1;
                for (int i = 0; i < s; i++) {
                    a += "I";
                }
                num = num - s * 1;
            }
        }
        return a;
    }


}
