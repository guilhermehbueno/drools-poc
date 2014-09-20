package br.com.drools.poc.stateless;

import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatelessKnowledgeSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.drools.poc.model.Applicant;

public class StatelessExample {
	
	@Test
	public void shouldExecuteRuleWhenObjectIsApplicant(){
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add( ResourceFactory.newClassPathResource( "stateless/licenseApplication.drl", getClass()), ResourceType.DRL );
		if ( kbuilder.hasErrors() ) {
		    System.err.println( kbuilder.getErrors().toString() );
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
		
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		Applicant applicant = new Applicant( "Mr John Smith", 19 );
		Assert.assertFalse( applicant.isValid() );
		ksession.execute( applicant );
		Assert.assertTrue( applicant.isValid() );
		System.out.println(applicant.getName());
		
		applicant = new Applicant( "Mr John Smith", 17 );
		applicant.setValid(true);
		Assert.assertTrue( applicant.isValid());
		ksession.execute( applicant );
		Assert.assertFalse( applicant.isValid());
		System.out.println(applicant.getName());
	}
}
