package com.example.demo2;

import com.alibaba.fastjson.JSON;
import com.example.demo2.model.Client;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.ShellRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class Demo2ApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) throws IOException, InterruptedException {
//		String res = doDelete("http://52.80.200.7:8081/api/v4/clients/test-7553adffa999431b97ae2687b6d08316");
		String res2 = doGet("http://52.80.200.7:8081/api/v4/nodes/emqx@10.2.12.196/clients?_page=1&_limit=30000");
		JSONObject json = JSONObject.fromObject(res2);
		JSONArray jsonArray = JSONArray.fromObject(json.get("data"));
		List<Client> clientList = JSON.parseArray(jsonArray.toString(), Client.class);
		for (Client client:clientList) {
			doDelete("http://52.80.200.7:8081/api/v4/clients/"+client.getClientid());
//			System.out.println(client.getClientid());
//			Thread.sleep(200);
		}

	}

	/**
	 * 发送delete请求
	 *
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String doDelete(String url) throws IOException {

		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// 设置BasicAuth
		CredentialsProvider provider = new BasicCredentialsProvider();
		// Create the authentication scope
		AuthScope scope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);
		// Create credential pair，在此处填写用户名和密码
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("appTest", "MzAwMzU3NDA3Mzk0NzM5NTgwOTYxOTk0NDM0NzMzNjcwNDA");
		// Inject the credentials
		provider.setCredentials(scope, credentials);
		// Set the default credentials provider
		httpClientBuilder.setDefaultCredentialsProvider(provider);
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		String result = "";
		HttpDelete httpDelete = null;
		HttpResponse httpResponse = null;
		HttpEntity entity = null;
		httpDelete = new HttpDelete(url);
		try {
			httpResponse = closeableHttpClient.execute(httpDelete);
			entity = httpResponse.getEntity();
			if( entity != null ){
				result = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 关闭连接
		closeableHttpClient.close();
		return result;
	}

	public static String doGet(String url) throws IOException {

		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// 设置BasicAuth
		CredentialsProvider provider = new BasicCredentialsProvider();
		// Create the authentication scope
		AuthScope scope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);
		// Create credential pair，在此处填写用户名和密码
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("appTest", "MzAwMzU3NDA3Mzk0NzM5NTgwOTYxOTk0NDM0NzMzNjcwNDA");
		// Inject the credentials
		provider.setCredentials(scope, credentials);
		// Set the default credentials provider
		httpClientBuilder.setDefaultCredentialsProvider(provider);
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		String result = "";
		HttpGet httpGet = null;
		HttpResponse httpResponse = null;
		HttpEntity entity = null;
		httpGet = new HttpGet(url);
		try {
			httpResponse = closeableHttpClient.execute(httpGet);
			entity = httpResponse.getEntity();
			if( entity != null ){
				result = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 关闭连接
		closeableHttpClient.close();
		return result;
	}
}
