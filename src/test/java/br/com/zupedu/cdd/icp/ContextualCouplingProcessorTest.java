package br.com.zupedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.zupedu.cdd.Resources;
import br.com.zupedu.cdd.shared.UserDefinitionForTesting;
import br.com.zupedu.cdd.storage.StoreMetrics;
import spoon.Launcher;

public class ContextualCouplingProcessorTest {

	@Test
	public void testName() throws Exception {
		Launcher l = new Launcher();
		l.getEnvironment().setNoClasspath(true);
		l.addInputResource(new Resources().findFile("LiveReloadServer.java"));
		StoreMetrics context = new StoreMetrics();

		ContextualCouplingProcessor processor = new ContextualCouplingProcessor(
				UserDefinitionForTesting.load(), context);

		l.addProcessor(processor);
		l.run();

		assertEquals(0, processor.total());
	}
}