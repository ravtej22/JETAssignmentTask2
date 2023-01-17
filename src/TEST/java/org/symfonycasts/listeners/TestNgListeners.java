package org.symfonycasts.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.IConfigurationListener;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethodListener;
import org.testng.IRetryAnalyzer;
import org.testng.ISuiteListener;
import org.testng.ITestListener;

public interface TestNgListeners
        extends
        ITestListener,
        ISuiteListener,
        IInvokedMethodListener,
        IRetryAnalyzer,
        IAnnotationTransformer,
        IConfigurationListener,
        IExecutionListener {
}
