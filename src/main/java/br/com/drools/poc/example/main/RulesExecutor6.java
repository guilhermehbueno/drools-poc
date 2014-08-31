package br.com.drools.poc.example.main;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

public class RulesExecutor6 {
	
	private KieServices kieServices;

	public RulesExecutor6(RuleResource...resources) {
		super();
		kieServices = KieServices.Factory.get();
		KieFileSystem kfs = kieServices.newKieFileSystem();
		
		for(RuleResource resource : resources) {
			if(resource.getContent()==null){
				kfs.write("src/main/resources/KBase1/"+resource.getResourceName(), ResourceFactory.newClassPathResource(resource.getResourceName()));
			}else{
				kfs.write("src/main/resources/KBase1/"+resource.getResourceName(), resource.getContent());
			}
		}
		kieServices.newKieBuilder(kfs).buildAll();
	}

	public void execute(Object model) {
		KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		KieBase kieBase = kieContainer.getKieBase();
		KieSession newKieSession = kieBase.newKieSession();
		newKieSession.insert(model);
		newKieSession.fireAllRules();
	}
}
