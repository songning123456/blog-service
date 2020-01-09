package function;

import com.simple.blog.util.HttpUtil;

/**
 * @author songning
 * @date 2020/1/7
 * description
 */
public class MyThread extends Thread {

    private static int index = 0;

    @Override
    public void run() {
        try {
            String result = HttpUtil.doGet("http://122.51.193.191:8072/hello");
            System.out.println(index+ " : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1500; i++) {
            index = i;
            MyThread myThread = new MyThread();
            myThread.start();
            System.out.println(index);
        }
    }
}
