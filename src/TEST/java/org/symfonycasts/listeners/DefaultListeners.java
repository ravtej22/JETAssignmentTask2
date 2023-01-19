package org.symfonycasts.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.aventstack.extentreports.Status;
import org.symfonycasts.reports.ExtentTestManager;
import org.symfonycasts.utils.Log;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class DefaultListeners extends ListenerDecorator {

  public void onStart(ITestContext context) {
    Log.info("*** Test Suite " + context.getName() + " started ***");
  }

  public void onFinish(ITestContext context) {
    Log.info("*** Test Suite " + context.getName() + " ending ***");
    ExtentTestManager.endTest();
  }

  public void onTestStart(ITestResult result) {
    Log.info("Running test method " + result.getMethod().getMethodName() + "..");
    ExtentTestManager.startTest(result.getMethod().getMethodName());
  }

  public void onTestSuccess(ITestResult result) {
    Log.info("*** Executed " + result.getMethod().getMethodName() + "Test Successful");
    ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    Retry.threadCounter.set(0);
  }

  public void onTestFailure(ITestResult result) {
    Log.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
    ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable().toString());
  }

  public void onTestSkipped(ITestResult result) {
    Log.info("*** Test " + result.getMethod().getMethodName() + " skipped...");
    System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
    ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
  }

  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    Log.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
  }

  public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
    IRetryAnalyzer retryAnalyzer = null;
    if (retryAnalyzer == null) {
      annotation.setRetryAnalyzer(Retry.class);
    }
  }
}
