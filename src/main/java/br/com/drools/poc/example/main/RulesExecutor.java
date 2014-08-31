package br.com.drools.poc.example.main;

import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.logger.KnowledgeRuntimeLogger;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class RulesExecutor {
	
	private KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
	public RulesExecutor(RuleResource...resources) {
		super();
		for(RuleResource resource : resources) {
			knowledgeBuilder.add(ResourceFactory.newClassPathResource(resource.getResourceName()), resource.getType());
		}
		
		KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error: errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
	}
	
	public <T> T execute(T object){
		KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
		StatefulKnowledgeSession knowledgeSession = knowledgeBase.newStatefulKnowledgeSession();
		KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(knowledgeSession, "test");
		knowledgeSession.insert(object);
		knowledgeSession.fireAllRules();
		logger.close();
		return object;
	}
}
