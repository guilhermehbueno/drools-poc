package br.com.drools.poc;

import org.junit.Test;
import org.kie.api.io.ResourceType;

import br.com.drools.poc.example.main.RuleResource;
import br.com.drools.poc.example.main.RulesExecutor;
import br.com.drools.poc.model.Person;

public class RuleExecutorTest {
	
	@Test
	public void shouldSayHello(){
		RuleResource dsl = new RuleResource();
		dsl.setResourceName("say_something.dsl");
		dsl.setType(ResourceType.DSL);
		
		RuleResource dslr = new RuleResource();
		dslr.setResourceName("rockys_rules.dslr");
		dslr.setType(ResourceType.DSLR);
		
		RulesExecutor executor = new RulesExecutor(dsl, dslr);
		Person rocky = new Person("Rocky Balboa", "Philadelphia", 35);
		executor.execute(rocky);
	}
}
