package pl.pijok.mobconomy.account.transaction;

public class TransactionData {

    private final String accountOwner;
    private final Double balance;
    private final Boolean transactionSuccessful;

    public TransactionData(String accountOwner, Double balance, Boolean transactionSuccessful) {
        this.accountOwner = accountOwner;
        this.balance = balance;
        this.transactionSuccessful = transactionSuccessful;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public Double getBalance() {
        return balance;
    }

    public Boolean isTransactionSuccessful() {
        return transactionSuccessful;
    }
}
