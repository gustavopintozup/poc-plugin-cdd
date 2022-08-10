package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Resources;
import br.com.stackedu.cdd.StoreMetrics;
import spoon.Launcher;

public class TryProcessorTest {
    @Test
    public void testName() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("TrySimples.java"));

        StoreMetrics context = new StoreMetrics();
        TryProcessor processor = new TryProcessor(context);

        l.addProcessor(processor);
        l.run();

        assertEquals(1, processor.total());
    }

    @Test
    @DisplayName("Try with finally")
    public void testName2() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("TryComFinally.java"));

        StoreMetrics context = new StoreMetrics();
        TryProcessor processor = new TryProcessor(context);

        l.addProcessor(processor);
        l.run();

        assertEquals(2, processor.total());
    }

    @Test
    @DisplayName("Try with finally with no catch")
    public void testName3() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("TryComFinallySemCatch.java"));

        StoreMetrics context = new StoreMetrics();
        TryProcessor processor = new TryProcessor(context);

        l.addProcessor(processor);
        l.run();

        assertEquals(2, processor.total());
    }

    @Test
    @DisplayName("Covering all branches with a try, 3 catches and a finally")
    public void testName4() throws Exception {
        Launcher l = new Launcher();
        l.getEnvironment().setNoClasspath(true);
        l.addInputResource(
            new Resources().findFile("TryComTresCatchesEFinally.java"));

        StoreMetrics context = new StoreMetrics();
        TryProcessor p1 = new TryProcessor(context);
        CatchProcessor p2 = new CatchProcessor(context);

        l.addProcessor(p1);
        l.addProcessor(p2);
        l.run();

        assertEquals(5, p1.total() + p2.total());
    }
}