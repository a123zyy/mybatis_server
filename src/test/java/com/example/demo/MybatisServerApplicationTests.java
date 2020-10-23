package com.example.demo;

import com.example.bean.HobbyInfo;
import com.example.bean.PostInfo;
import com.example.dao.PostInfoMapper;
import com.example.pojo.PostInfoDto;
import com.example.service.PostInfoService;
import com.example.until.GlobalUntil;
import com.example.until.JwtTokenUtil;
import com.example.until.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class MybatisServerApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PostInfoMapper postInfoMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String REDIS_BLOG_POST_COUNT_LIKE="blog:post:like_count";



    @Test
    void contextLoads() {
        System.out.println(getRandomHan());
    }

    @Test
    void contextLoads2() {
       List<PostInfo> list = postInfoMapper.findAllByYear();
       for (int i=0;i>list.size();i++){
           System.out.println(list.get(i));
       }

    }

    private final static int DATA = 0x9fa5 - 0x4e00 + 1;

    public char getRandomHan() {
        Random ran = new Random();
        return (char) (0x4e00 + ran.nextInt(DATA));
    }

    @Test
    void contextLoads11() {
        //int[] nums = new int[]{-10,-3,0,5,9};
        testIterator();

       // System.out.println();

//        //System.out.println(arraysToTree(nums));
//        Integer integer = (Integer) redisTemplate.opsForValue().get("ket2");
//        System.out.println(integer + 1);

    }

    @Test
    void contextLoads12() {
//        twoArray();

        List<String> list = new ArrayList<>();
        list.add("呜呜呜");
        list.add("hhhh");
        list.add("呱呱呱");
        Stream.Builder<String> streamBuilder = Stream.builder();
        list.stream();
        System.out.println(streamBuilder.build().collect(Collectors.joining(",")));

        //double[] nums = new double[]{3.5,5,3,3.5,2.2,1.9,2};
//        Arrays.sort(nums);
//        Arrays.binarySearch(nums,);
        //threeSumClosest();
//        System.out.println((int) );
//        for (double a:nums){
//            System.out.println(a);
//        }

    }


    //    public int rob(int[] nums) {
//        int n = nums.length;
//        if (n <= 1) return n == 0 ? 0 : nums[0];
//        int[] memo = new int[n];
//        memo[0] = nums[0];
//        memo[1] = Math.max(nums[0], nums[1]);
//        for (int i = 2; i < n; i++)
//            memo[i] = Math.max(memo[i - 1], nums[i] + memo[i - 2]);
//        return memo[n - 1];
//    }
    public int findRepeatNumber(int[] nums) {
        int count = 0;
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; i < nums.length; i++) {
                if (nums[j] > nums[i]) {

                }
            }
        }


        return count;

    }

    //找出能被5和6整除
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of lines:");
        int n = scanner.nextInt();
        int i = 1, m = 1;
        int s = 2, k = 1;
        for (m = 1; m <= n; m++) {
            for (k = 1; k <= (n - m); k++)
                System.out.print(" "); //循环输出n-m空格
            for (i = m; i >= 1; i--)
                System.out.print(i);   //输出i,i-1,..1
            for (s = 2; s <= m; s++)
                System.out.print(s);   //输出2,3,...,m
            for (k = 1; k <= n - m; k++)
                System.out.print(" "); //输出n-m个空格
            System.out.print("\n");    //换行
        }
    }
    public static ArrayList<BinaryTree> temps;// 构造二叉树临时数据

    static class BinaryTree {
        public int data;// value
        public BinaryTree left = null;// 左子树
        public BinaryTree right = null;// 右子数
    }

    // 前序遍历打印二叉树
    public static void prOrderTree(BinaryTree node) {
        if (node == null) {
            return;
        }
        System.out.print("\t" + node.data);
        // 左子树
        BinaryTree left = node.left;
        // 打印节点信息
        if (left != null) {
            prOrderTree(left);
        }
        // 右子树
        BinaryTree right = node.right;
        if (right != null) {
            prOrderTree(right);
        }

    }
    // 中序打印遍历
    public static void inOrderTree(BinaryTree node) {
        if (node == null) {
            return;
        }
        // 左子树
        BinaryTree left = node.left;
        if (left != null) {
            inOrderTree(left);
        }
        // 打印节点信息
        System.out.print("\t" + node.data);
        // 右子树
        BinaryTree right = node.right;
        if (right != null) {
            inOrderTree(right);
        }
    }
    // 数组转二叉树
    public static BinaryTree arraysToTree(int arrays[]) {
        if (arrays.length == 0) {
            return null;
        }
        int length = arrays.length;

        // 生成二叉树节点
        temps = new ArrayList<BinaryTree>(length);
        for (int i = 0; i < length; i++) {
            BinaryTree node = new BinaryTree();
            node.data = arrays[i];
            temps.add(node);

        }
        // 构建二叉树 根节点n[i] 左子树n[i*2+1] 右子树 n[i*2+2]
        for (int i = 0; i < length / 2 - 1; i++) {
            BinaryTree node = temps.get(i);
            node.left = temps.get(2 * i + 1);
            node.right = temps.get(2 * i + 2);

        }
        // 处理最后一个节点
        int lastNode = length / 2 - 1;
        BinaryTree node = temps.get(lastNode);
        // 添加左子树
        node.left = temps.get(lastNode * 2 + 1);

        if (length % 2 != 0) {
            // 当为偶数时，添加右子树
            node.right = temps.get(lastNode * 2 + 2);
        }

        BinaryTree root = temps.get(0);
        temps.clear();
        temps = null;
        return root;

    }


    //给出年月日判断星期几
   // Week = (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400+1) mod 7

    public String dayOfTheWeek(int day, int month, int year) {
//       int week = (day+2*month+3*(month+1)/5+year+year/4-year/100+year/400+1)%7;


            if(month==1||month==2) {
                month+=12;
                year--;
            }
            int week=(day+2*month+3*(month+1)/5+year+year/4-year/100+year/400+1)%7;
        System.out.println(week);

        switch(week) {
            case 1 :
                return "Monday";
            case 2 :
                return "Tuesday";
            case 3 :
                return "Wednesday";
            case 4 :
                return "Thursday";
            case 5 :
                return "Friday";
            case 6 :
                return "Saturday";
            case 7 :
                return "Sunday";
            default :
                return "0";
        }

    }
     public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
//    求二叉树是是否是平衡二叉树
//    判断点 左右子树深度不一致时就是不平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if(root == null){return true;}
        if(!isBalanced(root.left) || !isBalanced(root.right))return false;
        if (Math.abs(maxDepth(root.left)- maxDepth(root.right)) >1) return false;
        return true;

    }
    public int maxDepth(TreeNode root) {
        int ln = maxDepth(root.left);
        int rn = maxDepth(root.right);
        return ln>rn? ln+1:rn+1;
    }
    public int minDepth(TreeNode root) {
        if (root == null){return 0;}
        int ln = minDepth(root.left);
        int rn = minDepth(root.right);
        double[] aaa;
        int list[];
        return ln>rn? rn+1:ln+1;


    }
    public double matNum(int[] myList){
        double max =myList[0];
        for (int i =1;i<myList.length;i++){
            if (myList[i] >max){ max =myList[i];}
        }
        return max;
    }

    public double minNum(int[] myList){
        double min =myList[0];
        for (int i =1;i<myList.length;i++){
            if (myList[i] <min){ min =myList[i];}
        }
        return min;
    }
    //数组从右向左拍
    public int[] ritoleft(int[] myList){
       int temp = myList[0];
        for (int i =0;i<myList.length;i++){
            if (i == (myList.length - 1)){
                break;
            }
           myList[i] = myList[i+1];
        }
        myList[myList.length-1] = temp;
        return myList;
    }
    //数组从左向右拍
    public int[] lefttori(int[] myList){
        int aa[] =null;
        System.arraycopy(myList,0,aa,0,myList.length);
        int pointer = myList.length -1;
        int temp = myList[pointer];
        for (int i =pointer;i > 0;i--){
            myList[i] = myList[i-1];
        }
        myList[0] = temp;

        return myList;
    }
    //二分法排序
    public int getSubscript(int[] num,int key){
        int low = 0;
        int high = num.length-1;
        while (high>=low){
            int mid = (low+high)/2;
            if (num[mid] > key){
                high = mid -1;
            } else if (num[mid] == key){
                return mid;
            }  else {
                low = mid +1;
            }
        }
        return -1;
    }
    //插入排序
    public double[] insertSort(double[] list){
        for (int i= 1;i<list.length;i++){
            double current = list[i];
            int k;
            for (k =i-1;k>=0 && list[k] > current;k--){
                list[k +1] =list[k];
            }
            list[k+1] =current;
        }
        return list;
    }
    //
    public void threeSumClosest() {
        int[] list =new int[100];
       int[] count= new int[10];
       for (int i =0;i<list.length;i++){
           list[i] =(int) Math.floor((Math.random()*9)+0);
           System.out.print(list[i]+"\t\t");
           int num = list[i];
           count[num]++;
       }
        System.out.println("------------------------");
        for (int i =0;i<count.length;i++){
            System.out.println(i+"出现----"+count[i]+"次");
        }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       return l1;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public void testIterator(){
//        Set set =new HashSet();
//        set.add("1");
//        set.add("2");
//        set.add("3");
//        set.add("4");
//        set.add("5");
//        set.add("6");
//        Iterator<String> iterator = set.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next().toLowerCase()+"");
//        }
        //向后遍历链表
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        System.out.println(linkedList.size());
        for (int i =linkedList.size()-1 ;i >=0;i--){
            System.out.println(linkedList.get(i));
        }
//        Collections.binarySearch()
    }
    //二维数组
    public final void twoArray(){
        int [][] mat =new int[10][10];
        for (int row =0;row<mat.length;row++){
            for (int clunm =0;clunm<mat[row].length;clunm++){
                mat[row][row] = (int) (Math.random()*100);
            }
        }
        for (int row = 0;row < mat.length;row ++ ){
            for (int column = 0; column<mat[row].length;column++){
                System.out.print(mat[row][column]+" ");
            }
            System.out.println();
        }
    }
    @Test
    void getuser(){
//       String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjEsImNyZWF0ZWQiOjE2MDIzMjI3MDE1ODUsImV4cCI6MTYwMjkyNzUwMX0.5cIaw5ZgSnakUM9PFqCcQHg_Ad5OcnbHHGSVU05Tr7QPUKYYZt2LL3n6xhHztJ8Ic502Yec2zjijOR11Ydy5Gg";
//        System.out.println(jwtTokenUtil.getUseridFromToken(token));
        int aa =4;
             redisTemplate.opsForValue().set(REDIS_BLOG_POST_COUNT_LIKE+1, 4-1);



    }

    @Test
    void getuser1(){
//       String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjEsImNyZWF0ZWQiOjE2MDIzMjI3MDE1ODUsImV4cCI6MTYwMjkyNzUwMX0.5cIaw5ZgSnakUM9PFqCcQHg_Ad5OcnbHHGSVU05Tr7QPUKYYZt2LL3n6xhHztJ8Ic502Yec2zjijOR11Ydy5Gg";
//        System.out.println(jwtTokenUtil.getUseridFromToken(token));

        Integer gitcount = (Integer) redisTemplate.opsForValue().get(REDIS_BLOG_POST_COUNT_LIKE+1);
        System.out.println(gitcount);



    }





}
















