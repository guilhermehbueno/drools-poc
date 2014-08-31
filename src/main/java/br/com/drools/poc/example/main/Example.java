package br.com.drools.poc.example.main;

import org.kie.api.io.ResourceType;
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

import br.com.drools.poc.model.Person;

public class Example {
	public static final void main(String[] args) {
		try {
			// 1 - load the rules in to a knowledge builder
			KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			
			knowledgeBuilder.add(ResourceFactory.newClassPathResource("say_something.dsl"), ResourceType.DSL);
			knowledgeBuilder.add(ResourceFactory.newClassPathResource("rockys_rules.dslr"), ResourceType.DSLR);
			//knowledgeBuilder.add(ResourceFactory.newClassPathResource("rockys_rules2.dslr"), ResourceType.DSLR);
			
			KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
			if (errors.size() > 0) {
				for (KnowledgeBuilderError error: errors) {
					System.err.println(error);
				}
				throw new IllegalArgumentException("Could not parse knowledge.");
			}
			// 2 - create a knowledge base using a knowledge builder
			KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
			knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
			
			// 3 - create a stateful knowledge session
			StatefulKnowledgeSession knowledgeSession = knowledgeBase.newStatefulKnowledgeSession();
			
			// create a logger for the knowledge session
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(knowledgeSession, "test");
			
			// 4 - create and assert some facts
			Person rocky = new Person("Rocky Balboa", "Philadelphia", 35);
			
			knowledgeSession.insert(rocky);
		
			// 5 - fire the rules
			knowledgeSession.fireAllRules();
			
			logger.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
