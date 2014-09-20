package br.com.drools.poc;

import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.drools.poc.model.Dsl;
import br.com.drools.poc.model.DslRule;
import br.com.drools.poc.model.ResponseDsl;
import br.com.drools.poc.model.ResponseRule;

public class RuleExecutorFromRest {


	@Test
	public void shouldLoadDsls() throws Exception {
		String url = "http://localhost:3000/persistent/dsl";

		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		String result = IOUtils.toString(new InputStreamReader(response.getEntity().getContent()));
		System.out.println(result);
		ObjectMapper mapper = new ObjectMapper();
		ResponseDsl value = mapper.readValue(result, ResponseDsl.class);
		System.out.println(value);
		Assert.assertNotNull(value);
		Assert.assertNotNull(value.getResult());
		Assert.assertTrue(value.getResult().length>0);
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
	}

	@Test
	public void shouldLoadRules() throws Exception {
		String url = "http://localhost:3000/persistent/rule";
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		String result = IOUtils.toString(new InputStreamReader(response.getEntity().getContent()));
		System.out.println(result);
		ObjectMapper mapper = new ObjectMapper();
		ResponseRule value = mapper.readValue(result, ResponseRule.class);
		System.out.println(value);
		Assert.assertNotNull(value);
		Assert.assertNotNull(value.getResult());
		Assert.assertTrue(value.getResult().length>0);
		
		StringBuilder sb = new StringBuilder();
		sb.append("expander search.dsl").append("\n");
		for (DslRule rule: value.getResult()) {
			sb.append("rule \""+rule.getName()+"_"+rule.get_id()+"\"").append("\n");
			sb.append("when").append("\n");
			sb.append(rule.getWhen()).append("\n");
			sb.append("then").append("\n");
			sb.append(rule.getThen()).append("\n");
			sb.append("end").append("\n").append("\n");
			System.out.println(rule);
		}
		System.out.println(sb.toString());
	}
}
