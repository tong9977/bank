package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BankDataServiceTest {
    private Bank bank;

    @BeforeEach
    void setup() {
        bank = new Bank("SCB", new StubDataService());
        bank.addAllCustomers();
    }

    @Test
    void testFindCustomerById() {
        Customer cust = bank.findCustomerById(1);
        assertEquals("John", cust.getName());
    }

    @Test
    void testFindCustomerByIdเมื่อไม่มีidต้องreturnNull() {
        Customer cust = bank.findCustomerById(4);
        assertNull(cust);
    }

    private class StubDataService implements IDataService {
        @Override
        public Iterator<Customer> getAllObjects() {
            ArrayList<Customer> list = new ArrayList<>();
            list.add(new Customer(1, 123, "John"));
            list.add(new Customer(2, 456, "Pa"));
            return list.iterator();
        }
    }
}
