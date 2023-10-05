import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(1000);

        // поток для пополнения счета
        Thread depositThread = new Thread(() -> {
            Random random = new Random();
            while (true) {
                int amount = random.nextInt(100);
                account.deposit(amount);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        depositThread.start();

        // главный поток снимает деньги после накопления определенной суммы
        try {
            while (true) {
                Thread.sleep(200);
                double withdrawalAmount = 500;
                account.withdraw(withdrawalAmount);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
