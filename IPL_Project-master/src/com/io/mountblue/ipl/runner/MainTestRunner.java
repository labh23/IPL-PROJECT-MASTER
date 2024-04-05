package com.io.mountblue.ipl.runner;

import com.io.mountblue.ipl.test.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class MainTestRunner {
    public static void main(String[] args) {
        Result result= JUnitCore.
                runClasses(MainTest1.class, MainTest2.class, MainTest3.class, MainTest4.class, MainTest5.class);

        for (Failure failure:result.getFailures()){
            System.out.println(failure.toString());
        }

        System.out.println("Result was successful "+result.wasSuccessful());
    }
}
