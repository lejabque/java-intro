/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Account {
    private final Client client;
    private long amount;

    public Account(Client client) {
        this.client = client;
    }

    long getAmount() {
        return amount;
    }

    public Client getClient() {
        return client;
    }

    private void setAmount(long amount) {
        assert amount >= 0;
        this.amount = amount;
    }

    public boolean transfer(Account from, long amount) {
        if (from.amount >= amount) {
            setAmount(getAmount() + amount);
            from.setAmount(from.getAmount() - amount);
            return true;
        } else {
            return false;
        }
    }

    public void deposit(long amount) {
        this.amount += amount;
    }
}
