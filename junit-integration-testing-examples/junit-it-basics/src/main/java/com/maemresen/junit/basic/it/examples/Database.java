package com.maemresen.junit.basic.it.examples;

public interface Database {
    void store(User user);
    User findByUsername(String username);
}
