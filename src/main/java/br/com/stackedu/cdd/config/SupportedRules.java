package br.com.stackedu.cdd.config;

import java.util.Arrays;
import java.util.List;

import br.com.stackedu.cdd.icp.AnnotationProcessor;
import br.com.stackedu.cdd.icp.AnonymousClassProcessor;
import br.com.stackedu.cdd.icp.CatchProcessor;
import br.com.stackedu.cdd.icp.CondicionalProcessor;
import br.com.stackedu.cdd.icp.ContextualCouplingProcessor;
import br.com.stackedu.cdd.icp.DoWhileProcessor;
import br.com.stackedu.cdd.icp.ForEachProcessor;
import br.com.stackedu.cdd.icp.ForProcessor;
import br.com.stackedu.cdd.icp.IfProcessor;
import br.com.stackedu.cdd.icp.LambdaProcessor;
import br.com.stackedu.cdd.icp.LocalVarProcessor;
import br.com.stackedu.cdd.icp.MethodProcessor;
import br.com.stackedu.cdd.icp.SuperProcessor;
import br.com.stackedu.cdd.icp.SwitchProcessor;
import br.com.stackedu.cdd.icp.TernaryProcessor;
import br.com.stackedu.cdd.icp.ThrowProcessor;
import br.com.stackedu.cdd.icp.TryProcessor;
import br.com.stackedu.cdd.icp.WhileProcessor;
import br.com.stackedu.cdd.icp.YieldProcessor;
import br.com.stackedu.cdd.storage.StoreMetrics;
import spoon.processing.Processor;

/**
 * Supported rules
 * 
 * @author gustavopinto
 *
 */
public enum SupportedRules {

    IF_STATEMENT {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(
                    new IfProcessor(config, context),
                    new TernaryProcessor(config, context));
        }
    },
    TRY_CATCH_STATEMENT {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(
                    new TryProcessor(context),
                    new CatchProcessor(context));
        }
    },
    SWITCH_STATEMENT {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(new SwitchProcessor(context));
        }
    },
    CONDITION {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(new CondicionalProcessor(config, context));
        }
    },
    FOR_STATEMENT {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(new ForProcessor(context));
        }
    },
    FOREACH_STATEMENT {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(new ForEachProcessor(context));
        }
    },
    WHILE_STATEMENT {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(
                    new WhileProcessor(context),
                    new DoWhileProcessor(context));
        }
    },
    CONTEXT_COUPLING {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(new ContextualCouplingProcessor(config, context));
        }
    },
    METHODS_AUTOGEN {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            throw new UnsupportedOperationException(
                    "In theory, this should never happen. We have to fix this in the future");
        }
    },
    ANNOTATION {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(new AnnotationProcessor(context));
        }
    },
    LAMBDA_EXPRESSION {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(new LambdaProcessor(context));
        }
    },
    METHOD {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(new MethodProcessor(config, context));
        }
    },
    THROW_STATEMENT {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(new ThrowProcessor(context));
        }
    },
    YIELD_STATEMENT {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(new YieldProcessor(context));
        }
    },
    SUPER_EXPRESSION {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(new SuperProcessor(context));
        }
    },
    ANONYMOUS_CLASS {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(new AnonymousClassProcessor(context));
        }
    },
    LOCAL_VARIABLE {
        @Override
        public List<Processor> resolveProcessors(Config config, StoreMetrics context) {
            return Arrays.asList(new LocalVarProcessor(context));
        }
    };

    public abstract List<Processor> resolveProcessors(Config config, StoreMetrics context);
}