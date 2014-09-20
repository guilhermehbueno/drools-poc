package br.com.drools.poc.example.main;

import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.drools.poc.model.Dsl;
import br.com.drools.poc.model.DslRule;
import br.com.drools.poc.model.ResponseDsl;
import br.com.drools.poc.model.ResponseRule;

public class RuleLoader {
	public static RuleResource loadDslFrom(String endpoint) throws Exception{
		String url = endpoint;

		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		String result = IOUtils.toString(new InputStreamReader(response.getEntity().getContent()));
		System.out.println(result);
		ObjectMapper mapper = new ObjectMapper();
		ResponseDsl value = mapper.readValue(result, ResponseDsl.class);
		System.out.println(value);
		StringBuilder sb = new StringBuilder();
		Dsl[] dsls = value.getResult();
		for (Dsl dsl : dsls) {
			if(dsl.getScope().equalsIgnoreCase("when")){
				sb.append("[condition][]");
			}else{
				sb.append("[consequence][]");
			}
			sb.append(dsl.getExpression()+"="+dsl.getMapping()).append("\n");
			System.out.println(dsl);
		}
		System.out.println(sb.toString());
		RuleResource resource= new RuleResource();
		resource.setContent(sb.toString());
		resource.setResourceName("search.dsl");
		return resource;
	}
	
	public static RuleResource loadRuleFrom(String endpoint, String...imports) throws Exception{
		String url = endpoint;
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		String result = IOUtils.toString(new InputStreamReader(response.getEntity().getContent()));
		System.out.println(result);
		ObjectMapper mapper = new ObjectMapper();
		ResponseRule value = mapper.readValue(result, ResponseRule.class);
		System.out.println(value);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("package br.com.search.rules ").append("\n\n");
		for (String importString : imports) {
			sb.append("import "+importString).append(" \n");
		}
	
		
		sb.append("expander search.dsl ").append("\n\n");
		for (DslRule rule: value.getResult()) {
			sb.append("rule \""+rule.getName()+"_"+rule.get_id()+"\" ").append("\n");
			sb.append("    when ").append("\n");
			sb.append("        "+rule.getWhen()).append("\n");
			sb.append("    then ").append("\n");
			sb.append("        "+rule.getThen()).append("\n");
			sb.append("end ").append("\n").append("\n");
			System.out.println(rule);
		}
		System.out.println(sb.toString());
		RuleResource resource= new RuleResource();
		resource.setContent(sb.toString());
		resource.setResourceName("search_rules.dslr");
		return resource;
	}
}
