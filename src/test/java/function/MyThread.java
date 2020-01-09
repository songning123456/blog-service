package function;

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
            Thread.sleep(2000);
        } catch (InterruptedException e) {
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
