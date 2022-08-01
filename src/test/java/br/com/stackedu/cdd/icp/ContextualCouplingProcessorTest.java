package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Resources;
import br.com.stackedu.cdd.StoreMetrics;
import br.com.stackedu.cdd.config.DefaultUserDefinitionFactory;
import spoon.Launcher;

public class ContextualCouplingProcessorTest {

	@Test
	public void testName() throws Exception {
		Launcher l = new Launcher();
		l.getEnvironment().setNoClasspath(true);
		l.addInputResource(new Resources().findFile("ServicoNotas.java"));
		StoreMetrics context = new StoreMetrics();

		ContextualCouplingProcessor processor = new ContextualCouplingProcessor(
				DefaultUserDefinitionFactory.load("cdd.json"), context);

		l.addProcessor(processor);
		l.run();

		assertEquals(4, processor.total());
	}

	@Test
	public void testName2() throws Exception {
		Launcher l = new Launcher();
		l.getEnvironment().setNoClasspath(true);
		l.addInputResource(new Resources()
				.findFile("TodasInfosListaLearningTasksResponse.java"));

		StoreMetrics context = new StoreMetrics();
		ContextualCouplingProcessor processor = new ContextualCouplingProcessor(
				DefaultUserDefinitionFactory.load("cdd.json"), context);

		l.addProcessor(processor);
		l.run();

		System.out.println(processor.values());
		assertEquals(5, processor.total());
	}
}