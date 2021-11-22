package com.example.demo2;

import com.alibaba.fastjson.JSON;
import com.example.demo2.model.Client;
import com.example.demo2.template.schedule.sample2.CronTaskRegistrar;
import com.example.demo2.template.schedule.sample2.SchedulingRunnable;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class Demo2ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	CronTaskRegistrar cronTaskRegistrar;

	@Test
	public void testTask() throws InterruptedException {
		SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskNoParams", null);
		cronTaskRegistrar.addCronTask(task, "0/10 * * * * ?");
		Thread.sleep(15000);
		remove();
		// 便于观察
		Thread.sleep(3000000);
	}

	void remove(){
		SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskNoParams", null);
		cronTaskRegistrar.removeCronTask(task);
	}

	@Test
	public void testHaveParamsTask() throws InterruptedException {
		SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskWithParams", "haha", 23);
		cronTaskRegistrar.addCronTask(task, "0/10 * * * * ?");

		// 便于观察
		Thread.sleep(3000000);
	}
}
