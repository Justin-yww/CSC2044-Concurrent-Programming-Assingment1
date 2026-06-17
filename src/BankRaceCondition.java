class BankAccountUnsafe {
      public int balance = 1000; // Intentionally not synchronized to cause race condition

      public void deposit(int amount) {
            balance = balance + amount;
      }
}

public class BankRaceCondition {
      public static void main(String[] args) throws Exception {
            System.out.println("\n============BANK ACCOUNT TRANSACTION SYSTEM (RACE CONDITION)============\n");
            
            int depositsPerThread = 100000;
            int depositAmount = 1;
            int expectedBalance = 1000 + (2 * depositsPerThread * depositAmount);

            System.out.println("Each thread deposits RM" + depositAmount + " for " + depositsPerThread + " times.");
            System.out.println("Expected Final Balance: RM" + expectedBalance);
            System.out.println();

            for (int run = 1; run <= 5; run++) {
                  BankAccountUnsafe account = new BankAccountUnsafe();

                  Thread t1 = new Thread(() -> {
                        for (int i = 0; i < depositsPerThread; i++) {
                              account.deposit(1);
                        }
                  }, "Thread-1");

                  Thread t2 = new Thread(() -> {
                        for (int i = 0; i < depositsPerThread; i++) {
                              account.deposit(1);
                        }
                  }, "Thread-2");

                  t1.start(); t2.start();
                  t1.join(); t2.join();

                  System.out.println("Run " + run
                  + " | Expected: RM" + expectedBalance 
                  + " | Actual: RM" + account.balance 
                  + (account.balance == expectedBalance ? " [CORRECT]" : " [RACE CONDITION OCCURRED]"));
                  System.out.println();
            }

            System.out.println("\n=======================================================================\n");
      }
}
