package cn.jxj4869.blog.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {


    public static int getRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min =" + min + ">" + "max=" + max);
        }
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(max - min + 1);
        return min + randomNumber;
    }

    public static String getRandomName(int min, int max) {
        StringBuffer stringBuffer = new StringBuffer();
        int len = getRandomNumber(min, max);
        for (int i = 0; i < len; i++) {
            stringBuffer.append((char)getRandomNumber('a','z'));
        }
        return stringBuffer.toString();
    }

    public static int getRandomAge(int min, int max) {
        return getRandomNumber(min, max);
    }


    public static List generateNameList(int n) {
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nameList.add(getRandomName(2, 10));
        }
        return nameList;
    }


    /**
     * 固定在类路径下的/img/avatar中有九张图片，命名规则为1.jpg 2.jpg 等依次类推
     * @return
     */
    public static String getAvatar(){
//        String projectPath = System.getProperty("user.dir")+"/src/main/resources/static/img/avatars";
//        projectPath.replace("\\","\\\\");
//        File file=new File(projectPath);

//        String[] list = file.list();
        int idx = Util.getRandomNumber(1, 9);
//        for (String s : list) {
//            System.out.println(s);
//        }
//        System.out.println(file.isDirectory());
//        System.out.println(file.exists());
        String avatars="/img/avatars/"+idx+".jpg";
        System.out.println(avatars);
//        System.out.println(projectPath);
        return avatars;
    }


}
