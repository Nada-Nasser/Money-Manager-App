package com.example.expensestracker.budget_categories;

public class BudgetCategory
{
    private String name;
    private double maxBudget;
    private double currentBudget;

    public BudgetCategory(String name, double maxBudget) {
        this.name = name;
        this.maxBudget = maxBudget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(double maxBudget) {
        this.maxBudget = maxBudget;
    }

    public double getCurrentBudget() {
        return currentBudget;
    }

    public void setCurrentBudget(double currentBudget) {
        this.currentBudget = currentBudget;
    }

    @Override
    public String toString() {
        return "BudgetCategory{" +
                "name='" + name + '\'' +
                ", maxBudget=" + maxBudget +
                ", currentBudget=" + currentBudget +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BudgetCategory)) return false;

        BudgetCategory that = (BudgetCategory) o;

        if (Double.compare(that.getMaxBudget(), getMaxBudget()) != 0) return false;
        if (Double.compare(that.getCurrentBudget(), getCurrentBudget()) != 0) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        temp = Double.doubleToLongBits(getMaxBudget());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getCurrentBudget());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
