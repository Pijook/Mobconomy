package pl.pijok.mobconomy.account.transaction;

public interface Transaction {

    void onTransactionFinish(TransactionData data);

}
