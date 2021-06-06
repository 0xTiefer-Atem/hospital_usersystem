package org.usersystem.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;

/**
 * @Author WangQian
 * @Date 2021/5/22 下午 9:36
 */
public class Test {
    public static void main(String[] args) {
        countChartNum();

    }


    public static void countChartNum() {
        int[] nums = new int[26];

        File file = new File("D:\\test\\src\\test_1.txt");
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                s = s.replace(" ", "");
                char[] ch = s.toCharArray();
                for (int i = 0; i < ch.length; i++) {
                    nums[ch[i]-97]++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static void getImage(){
        File file = new File("D:\\test\\src");
        String[] filenames = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                System.out.println(dir+"/"+name);
                return name.endsWith("jpg");
            }
        });
        for (String string : filenames) {
            System.out.println(string);
        }
    }
}
