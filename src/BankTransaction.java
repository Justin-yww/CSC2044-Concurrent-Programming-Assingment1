class BankAccount {
      private int balance = 1000;
      private final Object lock = new Object();
  
      public void deposit(int amount) {
            synchronized (lock) {
                  balance = balance + amount;
            }
      }
  
      public int getBalance() {
            synchronized (lock) {
                  return balance;
            }
      }
  
      public void withdraw(int amount) {
            synchronized (lock) {
                  if (balance >= amount) {
                        balance = balance - amount;
                  } else {
                        System.out.println("Insufficient balance");
                  }
            }
      }
  
      public void serviceCharge(int amount) {
            synchronized (lock) {
                  balance -= amount;
            }
      }
}
  
  public class BankTransaction {
      public static void main(String[] args) throws Exception {
            System.out.println("\n============BANK ACCOUNT TRANSACTION SYSTEM============\n");
            BankAccount account = new BankAccount();
  
            // Thread t1: Deposit
            Thread t1 = new Thread(() -> {
                  int amount = 200;
                  int txNum = 1;
                  try {
                        System.out.println("Starting Transaction " + txNum + ": Deposit RM" + amount);
                        account.deposit(amount);
                        Thread.sleep(2000);
                        System.out.println("Finished Transaction " + txNum);
                        System.out.println("Current Balance: RM" + account.getBalance());
                        System.out.println();
                  } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " interrupted");
                  }
            }, "Thread-1");
  
            // Thread t2: Withdraw
            Thread t2 = new Thread(() -> {
                  int amount = 100;
                  int txNum = 2;
                  try {
                        System.out.println("Starting Transaction " + txNum + ": Withdraw RM" + amount);
                        account.withdraw(amount);
                        Thread.sleep(2000);
                        System.out.println("Finished Transaction " + txNum);
                        System.out.println("Current Balance: RM" + account.getBalance());
                        System.out.println();
                  } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " interrupted");
                  }
            }, "Thread-2");
  
            // Thread t3: Balance Check
            Thread t3 = new Thread(() -> {
                  int txNum = 3;
                  try {
                        System.out.println("Starting Transaction " + txNum + ": Balance Check");
                        int bal = account.getBalance();
                        Thread.sleep(2000);
                        System.out.println("Finished Transaction " + txNum);
                        System.out.println("Current Balance: RM" + bal);
                        System.out.println();
                  } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " interrupted");
                  }
            }, "Thread-3");
  
            // Thread t4: Service Charge
            Thread t4 = new Thread(() -> {
                  int amount = 50;
                  int txNum = 4;
                  try {
                        System.out.println("Starting Transaction " + txNum + ": Service Charge RM" + amount);
                        account.serviceCharge(amount);
                        Thread.sleep(2000);
                        System.out.println("Finished Transaction " + txNum);
                        System.out.println("Current Balance: RM" + account.getBalance());
                        System.out.println();
                  } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " interrupted");
                  }
            }, "Thread-4");
  
            // Run sequentially: start then immediately join before starting the next
            t1.start(); t1.join();
            t2.start(); t2.join();
            t3.start(); t3.join();
            t4.start(); t4.join();
  
            System.out.println("\n======================================================\n");
      }
  }
