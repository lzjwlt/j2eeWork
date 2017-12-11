package ustc.lzj.interceptor;


public class Test {
    public static void main(String[] args) throws InterruptedException {
        LogInterceptor obj = new LogInterceptor();
        obj.preAction();
        System.out.println("doing somethings!");
        Thread.sleep(2000);
    }
}
