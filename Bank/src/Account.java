import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

class Account {
    private final int balance;
    private int sc;

    public Account(int initialBalance) {
        this.balance = initialBalance;
        this.sc = 0;
    }

    public synchronized void deposit(int amount) {
        sc += amount;
        System.out.println("Пополнение на " + amount + ". Баланс: " + sc);
        notifyAll();
    }

    public synchronized void withdraw(double amount) throws InterruptedException {
        while (sc < balance) {
            System.out.println("Недостаточно средств. Ожидаем пополнения.");
            wait();
        }
        sc -= amount;
        System.out.println("Снятие " + amount + ". Баланс: " + sc);
    }

    public synchronized double getBalance() {
        return balance;
    }
    public synchronized int getSc() {
        return sc;
    }
}

