package bank;

import java.util.HashMap;
import java.util.Map;

public class StubAccountManager implements IAccountManager {

    private Map<Integer, BankAccount> accounts;

    StubAccountManager() {
        accounts = new HashMap<>();
        this.accounts.put(1, new BankAccount(500));
        this.accounts.put(2, new BankAccount(1000));
        this.accounts.put(3, new BankAccount(0));
    }

    @Override
    public BankAccount getAccount(int accountId) {
        return this.accounts.get(accountId);
    }
}
