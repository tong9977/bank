package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;
    private double defaultBalance = 1000;

    @BeforeEach
    void setup() {
        account = new BankAccount(defaultBalance);
    }

    //ทดสอบ Create
    @Test
    void testCreateถ้าbalanceเป็นลบต้องเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new BankAccount(-1000));
        assertEquals("Balance must not be negative value", exception.getMessage());
    }

    @Test
    void testCreateถ้าเป็นbalanceเป็นลบทศนิยมต้องเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new BankAccount(-0.01));
        assertEquals("Balance must not be negative value", exception.getMessage());
    }

    @Test
    void testCreateด้วยbalanceเป็นศูนย์() {
        BankAccount account = new BankAccount(0);
        assertEquals(0, account.getBalance());
    }

    @Test
    void testCreateด้วยจำนวนเต็มบวก() {
        BankAccount account = new BankAccount(1000);
        assertEquals(1000, account.getBalance());
    }

    @Test
    void testCreateด้วยเลขทศนิยม() {
        BankAccount account = new BankAccount(0.01);
        assertEquals(0.01, account.getBalance());
    }
    //จบ Create

    //ทดสอบ deposit
    @Test
    void testDepositถ้าใส่ค่าเป็นลบมาต้องเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> account.deposit(-200));
        assertEquals("Amount must be greater than zero", exception.getMessage());
    }

    @Test
    void testDepositถ้าใส่ค่าเป็นเลบเลขทศนิยมมาต้องเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> account.deposit(-0.01));
        assertEquals("Amount must be greater than zero", exception.getMessage());
    }

    @Test
    void testDepositถ้าใส่ศูนย์มาต้องเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> account.deposit(0));
        assertEquals("Amount must be greater than zero", exception.getMessage());
    }

    @Test
    void testDepositใส่ค่าจำนวนเต็มบวก() {
        double amount = 200;
        account.deposit(amount);
        assertEquals(defaultBalance + amount, account.getBalance());
    }

    @Test
    void testDepositใส่ค่าเป็นทศนิยม() {
        double amount = 0.01;
        account.deposit(amount);
        assertEquals(defaultBalance + amount, account.getBalance());
    }
    //จบ deposit

    //ทดสอบ withdraw
    @Test
    void testWithdrawใส่เป็นค่าลบต้องเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(-200));
        assertEquals("Amount must be greater than zero", exception.getMessage());
    }

    @Test
    void testWithdrawใส่ค่าเป็นลบเลขทศนิยมต้องเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(-0.01));
        assertEquals("Amount must be greater than zero", exception.getMessage());
    }

    @Test
    void testWithdrawใส่เป็นศูนย์มาต้องเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(0));
        assertEquals("Amount must be greater than zero", exception.getMessage());
    }

    @Test
    void testWithdrawด้วยจำนวนเต็มบวก() {
        double amount = 200;
        account.withdraw(amount);
        assertEquals(defaultBalance - amount, account.getBalance());
    }

    @Test
    void testWithdrawดว้ยเลขทศนิยม() {
        double amount = 0.01;
        account.withdraw(amount);
        assertEquals(defaultBalance - amount, account.getBalance());
    }

    @Test
    void testWithdrawถอนหมดบัญชีต้องเหลือศูนย์() {
        account.withdraw(defaultBalance);
        assertEquals(0, account.getBalance());
    }

    @Test
    void testWithdrawถอนมากกว่าbalanceต้องเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(defaultBalance + 200));
        assertEquals("Can't withdraw more than balance", exception.getMessage());
    }

    @Test
    void testWithdrawถอนมากกว่าbalanceเกินมาแบบทศนิยมต้องเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(defaultBalance + 0.01));
        assertEquals("Can't withdraw more than balance", exception.getMessage());
    }
}