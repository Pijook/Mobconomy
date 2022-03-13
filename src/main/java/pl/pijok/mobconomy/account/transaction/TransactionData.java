package pl.pijok.mobconomy.account.transaction;

public class TransactionData {

    private final String accountOwner;
    private final Double balance;
    private final TransactionResult result;

    public TransactionData(String accountOwner, Double balance, TransactionResult result) {
        this.accountOwner = accountOwner;
        this.balance = balance;
        this.result = result;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public Double getBalance() {
        return balance;
    }

    public TransactionResult getResult() {
        return result;
    }
}
