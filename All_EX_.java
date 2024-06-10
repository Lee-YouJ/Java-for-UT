class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // 입금 메서드
    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            // 조건문이 올바른지 확인
            System.out.println("Deposit amount must be positive");
        }
    }

    // 출금 메서드
    public synchronized void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
            } else {
                System.out.println("Insufficient funds");
            }
        } else {
            System.out.println("Withdrawal amount must be positive");
        }
    }

    public double getBalance() {
        return balance;
    }
}

class BankAccountTest {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                account.deposit(500);
                System.out.println("Balance after deposit: " + account.getBalance());
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                account.withdraw(200);
                System.out.println("Balance after withdrawal: " + account.getBalance());
            }
        });

        t1.start();
        t2.start();

        try {   //join메서드는 메인 쓰레드가 다른 쓰레드의 완료를 기다리는 지 확인
            t1.join(); // t1 쓰레드가 완료될 때까지 기다림
            t2.join(); // t2 쓰레드가 완료될 때까지 기다림
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
