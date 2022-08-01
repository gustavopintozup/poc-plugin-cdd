package br.com.stackedu.cdd.icp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.stackedu.cdd.Resources;
import br.com.stackedu.cdd.config.DefaultUserDefinitionFactory;
import spoon.Launcher;

public class AcoplamentoContextualProcessorTest {

	@Test
	public void testName() throws Exception {
		Launcher l = new Launcher();
		l.getEnvironment().setNoClasspath(true);
		l.addInputResource(new Resources().buscaArquivo("ServicoNotas.java"));

		AcoplamentoContextualProcessor processor = new AcoplamentoContextualProcessor(
				DefaultUserDefinitionFactory.load("cdd.json"));

		l.addProcessor(processor);
		l.run();

		assertEquals(4, processor.total());
	}

	@Test
	public void testName2() throws Exception {
		Launcher l = new Launcher();
		l.getEnvironment().setNoClasspath(true);
		l.addInputResource(new Resources()
				.buscaArquivo("TodasInfosListaLearningTasksResponse.java"));

		AcoplamentoContextualProcessor processor = new AcoplamentoContextualProcessor(
				DefaultUserDefinitionFactory.load("cdd.json"));

		l.addProcessor(processor);
		l.run();

		System.out.println(processor.valores());
		assertEquals(5, processor.total());
	}
}