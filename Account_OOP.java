import java.util.HashMap;
import java.util.Map;

abstract class Account { //추상 클래스
    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

class SavingsAccount extends Account {  //Account 클래스를 상속 받아 입금 출금 메서드 구현
    private double interestRate;

    public SavingsAccount(String accountNumber, double initialBalance, double interestRate) {
        super(accountNumber, initialBalance);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void addInterest() {
        balance += balance * interestRate;
    }
}

class CheckingAccount extends Account {  //Account 클래스를 상속받아 입금 출금 메서드 구현
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        super(accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
        } else {
            System.out.println("Overdraft limit exceeded");
        }
    }
}

class Bank {  //계좌를 추가, 입금, 출금, 잔액 확인 하는 메서드를 제공
    private Map<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void deposit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found");
        }
    }

    public void printBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account " + accountNumber + " balance: " + account.getBalance());
        } else {
            System.out.println("Account not found");
        }
    }
}

public class Account_OOP {  //메인 클래스, Bank 객체 생성, 저축, 당좌 계좌 클래스의 객체를 만들어 은행에 추가
    public static void main(String[] args) {
        Bank bank = new Bank();

        // 계좌 생성
        SavingsAccount savings = new SavingsAccount("12345", 1000.0, 0.05);
        CheckingAccount checking = new CheckingAccount("67890", 500.0, 200.0);

        // 은행에 계좌 추가
        bank.addAccount(savings);
        bank.addAccount(checking);

        // 입금 및 출금
        bank.deposit("12345", 200.0);
        bank.withdraw("67890", 100.0);

        // 잔액 확인
        bank.printBalance("12345");
        bank.printBalance("67890");

        // 이자 추가
        savings.addInterest();
        bank.printBalance("12345");
    }
}
