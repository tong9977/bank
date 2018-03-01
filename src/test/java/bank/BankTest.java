package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private Bank bank;
    private Customer cust;

    @BeforeEach
    void setup() {
        bank = new Bank("SCB");
        cust = new Customer(1, 123, "John");
        bank.addCustomer(cust);
    }

    // ทดสอบ Create
    @Test
    void testCreate() {
        Bank b = new Bank("TMB");
        assertEquals("TMB", b.getName());
    }

    @Test
    void testCreateถ้าnameเป็นค่าว่างเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Bank(""));
        assertEquals("Bank name can not be empty", exception.getMessage());
    }

    @Test
    void testCreateถ้าnameมีแต่Spaceต้องเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Bank("   "));
        assertEquals("Bank name can not be empty", exception.getMessage());
    }
    //-- จบ Create

    //ทดสอบ findCustomerById
    @Test
    void testFindCustomerById() {
        Customer cust = bank.findCustomerById(1);
        assertNotNull(cust);
        assertSame(cust, cust);
    }

    @Test
    void testFindCustomerByIdไม่เจอต้องreturnNull() {
        Customer cust = bank.findCustomerById(0);
        assertNull(cust);
    }
    //-- จบ findCustomerById

    //ทดสอบ addCustomer
    @Test
    void testAddCustomer() {
        Customer newCust = new Customer(2, 123, "Dow");
        bank.addCustomer(newCust);

        Customer cust = bank.findCustomerById(2);
        assertNotNull(cust);
        assertSame(newCust, cust);
    }
    //-- จบ addCustomer

    // ทดสอบ validateCustomer
    @Test
    void testValidateCustomer() {
        assertTrue(bank.validateCustomer(1, 123));
    }

    @Test
    void testValidateCustomerใส่pinไม่ตรง() {
        assertFalse(bank.validateCustomer(1, 999));
    }

    @Test
    void testValidateCustomerเมื่อไม่มีcustIdนี้ในระบบต้องreturnFalse() {
        assertFalse(bank.validateCustomer(100, 999));
    }
    // จบ validateCustomer
}