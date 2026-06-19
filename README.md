# CSC2044 Assignment 1 — Bank Account Transaction System

Four Java programs that simulate how a bank account is accessed by multiple concurrent users, built to demonstrate concurrency, synchronisation, and race condition behaviour.

## Overview

This project contains four programs that isolate key concurrent programming concepts:

| Program | Task | Purpose |
|---|---|---|
| `BankTransaction_Task2.java` | Task 2 | Sequential transaction processing |
| `BankTransaction_Task3.java` | Task 3 | Concurrent transaction processing |
| `BankRaceCondition.java` | Task 5 | Race condition demonstration (unsafe) |
| `BankSynchronize.java` | Task 6 | Synchronisation solution (corrected) |

## Concepts Demonstrated

- **Multithreading** — four concurrent transaction workers (deposit, withdrawal, balance check, service charge) operating on a shared `BankAccount` object
- **Sequential vs. concurrent execution** — `start()`/`join()` patterns that control whether threads run one-at-a-time or simultaneously
- **Race conditions** — unsynchronised read-modify-write operations on a shared heap field producing non-deterministic, incorrect balances
- **Synchronisation** — `synchronized(lock)` blocks that enforce mutual exclusion over the critical section, guaranteeing correctness across all runs
- **Heap vs. stack memory** — shared mutable state (`balance`, `lock`) on the heap versus thread-private local variables (`amount`, `txNum`) on each thread's stack

## Getting Started

### Prerequisites

- Java JDK 11 or higher
- Any IDE (Integrated Development Environment)

### Steps to Download Program

1. **Clone the repository:**
```bash
git clone https://github.com/Justin-yww/CSC2044-Concurrent-Programming-Assingment1.git
cd CSC2044-Concurrent-Programming-Assingment1
```

2. **Verify the files are present:**
```bash
ls *.java
```

### Compilation

```bash
javac BankTransaction_Task2.java
javac BankTransaction_Task3.java
javac BankRaceCondition.java
javac BankSynchronize.java
```

### Running

```bash
java BankTransaction_Task2    # Sequential
java BankTransaction_Task3    # Concurrent
java BankRaceCondition        # Race condition (unsafe)
java BankSynchronized         # Synchronised (corrected)
```

## Group Members

| Name | Student ID |
|---|---|
| Lau Ching Hau | 21057799 |
| Lee Chia Khang | 21060389 |
| Lee Seng Wai | 23102627 |
| Justin Yong Wenn Weii | 18119677 |
