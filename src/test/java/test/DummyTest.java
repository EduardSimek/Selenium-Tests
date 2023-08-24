
package test;

import categories.ReleaseTest;
import categories.SmokeTest;
import org.junit.experimental.categories.Category;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DummyTest {

    private static int numberOfFailedTests;
    private int failed;

    @BeforeClass
    public static void setUpClass(){
        System.out.println("setup class");
        numberOfFailedTests = 0;
    }

    @BeforeMethod
    public void setUp(){
        System.out.println("setup");
    }

    @Test
    public void testA(){
        System.out.println("A");
        System.out.println("Static: " +numberOfFailedTests);
        failed++;
        System.out.println(failed);
    }

    @Category({SmokeTest.class, ReleaseTest.class})
    @Test
    public void testB(){
        System.out.println("B");
        numberOfFailedTests++;
        System.out.println("Static " +numberOfFailedTests);
        failed = 10;
        System.out.println(failed);
    }

    @Test
    public void testC(){

    }

    @AfterClass
    public void tearDown(){

    }
}

