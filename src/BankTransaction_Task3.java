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
  
public class BankTransaction_Task3 {
      public static void main(String[] args) throws Exception {
            System.out.println("\n============BANK ACCOUNT TRANSACTION SYSTEM============\n");
            BankAccount account = new BankAccount();
      
            // Thread t1: Deposit
            Thread t1 = new Thread(() -> {
                  int amount = 200;
                  try {
                        System.out.println(Thread.currentThread().getName() + " started Deposit RM" + amount);
                        account.deposit(amount);
                        Thread.sleep(3000); 
                        System.out.println(Thread.currentThread().getName() + " finished Deposit RM" + amount);
                  } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " interrupted");
                  }
            }, "Thread-1");
      
            // Thread t2: Withdraw
            Thread t2 = new Thread(() -> {
                  int amount = 100;
                  try {
                        System.out.println(Thread.currentThread().getName() + " started Withdraw RM" + amount);
                        account.withdraw(amount);
                        Thread.sleep(5000); 
                        System.out.println(Thread.currentThread().getName() + " finished Withdraw RM" + amount);
                  } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " interrupted");
                  }
            }, "Thread-2");
      
            // Thread t3: Balance Check
            Thread t3 = new Thread(() -> {
                  try {
                        System.out.println(Thread.currentThread().getName() + " started Balance Check");
                        account.getBalance();
                        Thread.sleep(1000); 
                        System.out.println(Thread.currentThread().getName() + " finished Balance Check");
                  } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " interrupted");
                  }
            }, "Thread-3");
      
            // Thread t4: Service Charge
            Thread t4 = new Thread(() -> {
                  int amount = 50;
                  try {
                        System.out.println(Thread.currentThread().getName() + " started Service Charge RM" + amount);
                        account.serviceCharge(amount);
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + " finished Service Charge RM" + amount);
                  } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " interrupted");
                  }
            }, "Thread-4");
      
            // Run concurrently: Start together and join to wait for all to finish
            t1.start(); t2.start(); t3.start(); t4.start();
            t1.join(); t2.join(); t3.join(); t4.join();
      
            System.out.println("\n======================================================\n");
      }
}