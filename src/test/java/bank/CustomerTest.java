package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer cust;

    @BeforeEach
    void setup() {
        cust = new Customer(1, 123, "Rachakrit");
        cust.setAccountManager(new StubAccountManager());
    }

    //-- ทดสอบ Create
    @Test
    void testCreate() {
        Customer cust = new Customer(1, 1, "John");
        assertEquals("John", cust.getName());
    }

    @Test
    void testCreateถ้าPinเป็นค่าลบต้องเกิดException() {
        assertThrows(IllegalArgumentException.class,
                ()-> new Customer(3, -1, "John"));
    }

    @Test
    void testCreateถ้าnameมีแต่Spaceต้องเกิดException() {
        assertThrows(IllegalArgumentException.class,
                ()-> new Customer(3, 123, "   "));
    }

    @Test
    void testCreatถ้าidเป็นลบต้องเกิดException() {
        assertThrows(IllegalArgumentException.class,
                ()-> new Customer(-1, 123, "John"));
    }

    @Test
    void testCreateถ้าnameเป็นค่าว่างต้องเกิดException() {
        assertThrows(IllegalArgumentException.class,
                ()-> new Customer(1, 123, ""));
    }

    @Test
    void testCreateถ้าpinเป็นลบและnameเป็นค่าว่างต้องเกิดException() {
        assertThrows(IllegalArgumentException.class,
                ()-> new Customer(1, -1, ""));
    }
    //-- จบ Create

    @Test
    void testGetId() {
        assertEquals(1, cust.getId());
    }

    @Test
    void testGetName() {
        assertEquals("Rachakrit", cust.getName());
    }

    @Test
    void testGetAccount() {
        BankAccount account = cust.getAccount();
        assertEquals(500, account.getBalance());
    }

    //ทดสอบ setName
    @Test
    void testSetName() {
        cust.setName("Noon");
        assertEquals("Noon", cust.getName());
    }

    @Test
    void testSetNameถ้าเป็นค่าว่างต้องเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> cust.setName(""));
        assertEquals("Customer name must not be empty", exception.getMessage());
    }

    @Test
    void testSetNameถ้ามีแต่Spaceต้องเกิดException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> cust.setName(""));
        assertEquals("Customer name must not be empty", exception.getMessage());
    }
    //--จบ setName

    // ทดสอบ match
    @Test
    void testMatch() {
        assertTrue(cust.match(123));
    }

    @Test
    void testMatchเมื่อใส่ค่าลบ() {
        assertFalse(cust.match(-1));
    }

    @Test
    void testMatchเมื่อใส่pinไม่ตรง() {
        assertFalse(cust.match(999));
    }
    //--จบ match

}