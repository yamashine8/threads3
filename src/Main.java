import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public AtomicInteger checkAtomic = new AtomicInteger(0);
    public int checkInt = 0;
    public volatile int checkVolatile = 0;
    public static void main(String[] args) throws InterruptedException {
        for (int i =1; i<=3;i++){
            System.out.println(normalInt());
            System.out.println(volInt());
            System.out.println(atmInt() + "\n");
        }


    }

    public static String normalInt() throws InterruptedException {
        final Main main = new Main();
        Thread threadInt = new Thread(() ->{
            for (int i= 1; i<= 50000; i++){
                main.checkInt++;
            }
        });
        threadInt.start();
        Thread threadInt2 = new Thread(() ->{
            for (int i= 1; i<= 50000; i++){
                main.checkInt++;
            }
        });
        threadInt2.start();
        threadInt.join();
        threadInt2.join();
        return "Результат использования int переменной: "+main.checkInt;
    }
    public static String volInt() throws InterruptedException {
        final Main main = new Main();
        Thread threadInt = new Thread(() ->{
            for (int i= 1; i<= 50000; i++){
                main.checkVolatile++;
            }
        });
        threadInt.start();
        Thread threadInt2 = new Thread(() ->{
            for (int i= 1; i<= 50000; i++){
                main.checkVolatile++;
            }
        });
        threadInt2.start();
        threadInt.join();
        threadInt2.join();
        return "Результат использования volatile int переменной: "+main.checkVolatile;
    }

    public static String atmInt() throws InterruptedException {
        final Main main = new Main();
        Thread threadInt = new Thread(() ->{
            for (int i= 1; i<= 50000; i++){
                main.checkAtomic.incrementAndGet();
            }
        });
        threadInt.start();
        Thread threadInt2 = new Thread(() ->{
            for (int i= 1; i<= 50000; i++){
                main.checkAtomic.incrementAndGet();
            }
        });
        threadInt2.start();
        threadInt.join();
        threadInt2.join();
        return "Результат использования atomic переменной: "+main.checkAtomic;
    }
}