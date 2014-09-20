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

import com.walmart.search.rules.model.Dsl;
import com.walmart.search.rules.model.DslRule;
import com.walmart.search.rules.model.ResponseDsl;
import com.walmart.search.rules.model.ResponseRule;

public class RuleLoaderFromRuleEditorTest {

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
		Dsl[] dsls = value.getResult();
		for (Dsl dsl : dsls) {
			System.out.println(dsl);
		}
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
		
		for (DslRule rule: value.getResult()) {
			System.out.println(rule);
		}
	}
}
