package com.books;

import com.sun.tools.javac.code.Attribute;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestArg.class,
        TestRetrieveBooks.class,
        TestPersistence.class,
        TestSort.class
        }
)

public class TestRunner {

}
