package com.ga.feedparser;

import com.ga.feedparser.impl.BaseElementTest;
import com.ga.feedparser.impl.BaseItemTest;
import com.ga.feedparser.impl.DefaultFeedParserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * JUnit 4 test suite to run all tests.
 */
@RunWith(Suite.class)
@SuiteClasses({
    BaseElementTest.class,
    BaseItemTest.class,
    DefaultFeedParserTest.class,
    FeedUtilsTest.class
})
public class AllTestSuite {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTestSuite.class.getName());
        //$JUnit-BEGIN$

        //$JUnit-END$
        return suite;
    }

}
