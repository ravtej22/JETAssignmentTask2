package org.symfonycasts.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    public static ThreadLocal<Integer> threadCounter = new ThreadLocal<Integer>() {

        protected Integer initialValue() {
            return 0;
        }
    };

    public boolean retry(ITestResult result) {
        String retryLimit = "2";
        int counter = threadCounter.get();
        if (counter < Integer.valueOf(retryLimit)) {
            counter++;
            threadCounter.set(counter);
            System.out.println(true);
            return true;
        }
        threadCounter.set(0);
        return false;
    }
}
