package com.example.expensestracker.transactions;

import com.example.expensestracker.budget_categories.BudgetCategory;

import java.util.Date;

public class Transaction
{
    private int budgetCategoryID;
    private String productName;
    private double price;
    private Date buyDate;

    public Transaction(int budgetCategoryID, String productName, double price, Date buyDate) {
        this.budgetCategoryID = budgetCategoryID;
        this.productName = productName;
        this.price = price;
        this.buyDate = buyDate;
    }

    public int getBudgetCategoryID() {
        return budgetCategoryID;
    }

    public void setBudgetCategoryID(int budgetCategoryID) {
        this.budgetCategoryID = budgetCategoryID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "budgetCategory=" + budgetCategoryID +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", buyDate=" + buyDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        if (getBudgetCategoryID() != that.getBudgetCategoryID()) return false;
        if (Double.compare(that.getPrice(), getPrice()) != 0) return false;
        if (getProductName() != null ? !getProductName().equals(that.getProductName()) : that.getProductName() != null)
            return false;
        return getBuyDate() != null ? getBuyDate().equals(that.getBuyDate()) : that.getBuyDate() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getBudgetCategoryID();
        result = 31 * result + (getProductName() != null ? getProductName().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getBuyDate() != null ? getBuyDate().hashCode() : 0);
        return result;
    }
}
