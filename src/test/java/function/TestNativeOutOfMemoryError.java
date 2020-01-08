package function;

import java.util.concurrent.CountDownLatch;

/**
 * @author songning
 * @date 2020/1/7
 * description
 */
public class TestNativeOutOfMemoryError {

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            System.out.println("i = " + i);
            new Thread(new HoldThread()).start();
            i++;
        }
    }

    static class HoldThread extends Thread {
        CountDownLatch cdl = new CountDownLatch(1);

        public HoldThread() {
            this.setDaemon(true);
        }

        @Override
        public void run() {
            try {
                cdl.await();
            } catch (InterruptedException ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
