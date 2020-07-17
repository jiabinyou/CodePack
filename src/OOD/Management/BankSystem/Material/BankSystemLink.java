package OOD.Management.BankSystem.Material;

/**
 * LintCode 1785:
 *https://www.lintcode.com/problem/bank-system/description
 *
 */

/**
 * Description
 * Design a bank account management system that implements the following three functions:
 * 1.void deposite(int id,int amount,long timestamp)
 * 2.boolean withdraw(int id,int amount,long timestamp)
 * 3.int[] check(int id, long startTime, long endTime)
 */

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
Example
        withdraw(0,100,0) //return false
        deposite(0,100,1)
        deposite(1,250,1)
        withdraw(0,30,3) //return true
        check(0,0,2) //[0,100]
        deposite(1,5,7)
        check(1,3,9) //[250,255]
 */


public class BankSystemLink {
    private int money;
    private Map<Integer, Customer> customers;
    public BankSystemLink(){
        money = 0;
        customers = new HashMap<>();
    }
    /**
     * @param id: user account id
     * @param amount: the number of bank deposits
     * @param timestamp: the data of bank transaction
     * @return: nothing
     */
    public void deposite(int id, int amount, long timestamp) {
        Customer customer;
        if (customers.containsKey(id)) {
            customer = customers.get(id);
        } else {
            customer = new Customer();
            customers.put(id, customer);
        }
        customer.deposite(amount, timestamp);
    }

    /**
     * @param id: user account id
     * @param amount : the number of bank withdraw
     * @param timestamp: the data of bank transaction
     * @return: if user account can not withdraw the number of amount,return false. else return true
     */
    public boolean withdraw(int id, int amount , long timestamp) {
        boolean result = false;
        if (customers.containsKey(id)) {
            Customer customer = customers.get(id);
            result = customer.withdraw(amount, timestamp);
        }
        return result;
    }

    /**
     * @param id: user account id
     * @param startTime: start time
     * @param endTime: end time
     * @return: need return two numbers,the first one is start time account balance,the second is end time account balance
     */
    public int[] check(int id, long startTime, long endTime) {
        if (customers.containsKey(id)) {
            Customer customer = customers.get(id);
            return customer.check(startTime, endTime);
        }
        return new int[]{};
    }
}

class Customer {
    int balance;
    private TreeMap<Long, Integer> balanceLog;
    public Customer() {
        this.balance = 0;
        this.balanceLog = new TreeMap<Long, Integer>();
        this.balanceLog.put(0l, 0);
    }
    public void deposite(int amount, long timestamp) {
        this.balance += amount;
        if (this.balanceLog.containsKey(timestamp)) {
            int oldBalance = this.balanceLog.get(timestamp);
            this.balanceLog.put(timestamp, oldBalance+amount);
        } else {
            this.balanceLog.put(timestamp, this.balance);
        }
    }
    public boolean withdraw(int amount, long timestamp) {
        if (this.balance >= amount) {
            this.balance -= amount;
            if (this.balanceLog.containsKey(timestamp)) {
                int oldBalance = this.balanceLog.get(timestamp);
                this.balanceLog.put(timestamp, oldBalance-amount);
            } else {
                this.balanceLog.put(timestamp, this.balance);
            }
            return true;
        } else {
            return false;
        }
    }
    public int[] check(long startTime, long endTime) {
        int[] result = new int[]{0, 0};
        Map.Entry<Long, Integer> startBalance = this.balanceLog.floorEntry(startTime);
        Map.Entry<Long, Integer> endBalance = this.balanceLog.floorEntry(endTime);
        result[0] = startBalance.getValue();
        result[1] = endBalance.getValue();
        return result;
    }

}