package com.sys.test.designmode.eventlistener;

public class User {
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username) {
        System.out.println("event bus test" + username);
        this.username = username;
    }

    public User() {
    }
}
