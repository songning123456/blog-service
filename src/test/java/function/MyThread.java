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
        String image = "http://10.65.3.68:10010/GetPhoto?path=file:///mnt/image/haiyan-vehicles/suzhou/suzhou1109to1115/15692.jpg";
        String url1 = "http://localhost:8092/haiyan-server/image/compress?url=" + image;
        String url2 = "http://localhost:8092/haiyan-server/image/original-image?url=" + image + "&waterMarkText=";
        String url3 = "http://localhost:8092/haiyan-server/image/compress-with-rect?url=" + image + "&position=";
        if (index % 3 == 0) {
            HttpUtil.doGet(url1);
        } else if (index % 3 == 1) {
            HttpUtil.doGet(url2);
        } else {
            HttpUtil.doGet(url3);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            index = i;
            MyThread myThread = new MyThread();
            myThread.start();
            System.out.println(i);
        }
    }
}
