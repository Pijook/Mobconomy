package pl.pijok.mobconomy.account;

public class Account {

    private String owner;
    private Double balance;

    public Account(String owner, Double balance){
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void increaseBalance(Double amount){
        this.balance += amount;
    }

    public void decreaseBalance(Double amount){
        this.balance -= amount;
    }
}
