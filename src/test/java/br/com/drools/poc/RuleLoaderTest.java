package br.com.drools.poc;

import org.testng.annotations.Test;

import br.com.drools.poc.example.main.RuleLoader;
import br.com.drools.poc.example.main.RuleResource;
import br.com.drools.poc.example.main.RulesExecutor6;
import br.com.drools.poc.model.Person;


public class RuleLoaderTest {

	@Test
	public void shouldInvokeDroolsWithExternalRules() throws Exception{
		//sb.append("import br.com.drools.poc.model.Message ").append("\n");
		//sb.append("import br.com.drools.poc.model.Person ").append("\n\n");
		
		RuleResource dsl = RuleLoader.loadDslFrom("http://localhost:3000/persistent/dsl");
		RuleResource dslr= RuleLoader.loadRuleFrom("http://localhost:3000/persistent/rule", "br.com.drools.poc.model.Person","br.com.drools.poc.model.Message");
		
		RuleResource fileDslr = new RuleResource();
		fileDslr.setResourceName("simple_rockys_rules.dslr");
		
		System.out.println("######## DSLs ############");
		System.out.println(dsl.getContent());
		
		System.out.println("\n\n######## RULES ############");
		System.out.println(dslr.getResourceName());
		System.out.println(dslr.getContent());
		
		RulesExecutor6 rulesExecutor6 = new RulesExecutor6(dsl, dslr);
		Person rocky = new Person("Rocky", "Philadelphia", 35);
		Person guilherme = new Person("Guilherme", "Philadelphia", 35);
		Person SilvioSantos = new Person("Silvio Santos", "Philadelphia", 35);
		rulesExecutor6.execute(rocky);
		rulesExecutor6.execute(guilherme);
		rulesExecutor6.execute(SilvioSantos);
		System.out.println(rocky);
	}
}
