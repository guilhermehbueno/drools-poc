package br.com.drools.poc;


import java.io.FileReader;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.testng.Assert;

import br.com.drools.poc.example.main.RuleResource;
import br.com.drools.poc.example.main.RulesExecutor6;
import br.com.drools.poc.model.Person;

public class RuleExecutor6Test {
	
	//@Test
	public void test(){
		RuleResource dsl = new RuleResource();
		dsl.setResourceName("say_something.dsl");
		
		RuleResource dslr = new RuleResource();
		dslr.setResourceName("rockys_rules.dslr");
		
		RulesExecutor6 rulesExecutor6 = new RulesExecutor6(dsl, dslr);
		Person rocky = new Person("Rocky Balboa", "Philadelphia", 35);
		rulesExecutor6.execute(rocky);
		System.out.println(rocky);
	}
	
	//@Test
	public void testSimpleDRLs(){
		RuleResource drl = new RuleResource();
		drl.setResourceName("simple.drl");
		drl.setType(ResourceType.DRL);
		
		RulesExecutor6 rulesExecutor6 = new RulesExecutor6(drl);
		Person rocky = new Person("Rocky Balboa", "Philadelphia", 35);
		rulesExecutor6.execute(rocky);
	}
	
	//@Test
	public void shouldLoadRuleFromString() throws Exception{		
		String content = IOUtils.toString(new FileReader("src/main/resources/simple.drl"));
		RuleResource drl = new RuleResource();
		drl.setResourceName("new-simple.drl");
		drl.setType(ResourceType.DRL);
		drl.setContent(content);
		RulesExecutor6 rulesExecutor6 = new RulesExecutor6(drl);
		Person rocky = new Person("Rocky Balboa", "Philadelphia", 35);
		rulesExecutor6.execute(rocky);
		Assert.assertNotNull(content);
	}
	
	@Test
	public void shouldLoadRuleAndDslFromString() throws Exception{
		String dslContent = IOUtils.toString(new FileReader("src/main/resources/search.dsl"));
		RuleResource dsl = new RuleResource();
		dsl.setResourceName("search.dsl");
		System.out.println(dslContent);
		dsl.setContent(dslContent);
		
		String content = IOUtils.toString(new FileReader("src/main/resources/simple_rockys_rules.dslr"));
		System.out.println(content);
		RuleResource dslr = new RuleResource();
		dslr.setResourceName("simple_rockys_rules.dslr");
		dslr.setContent(content);
		RulesExecutor6 rulesExecutor6 = new RulesExecutor6(dsl, dslr);
		Person rocky = new Person("Rocky", "Philadelphia", 35);
		rulesExecutor6.execute(rocky);
		Assert.assertNotNull(content);
	}
}
