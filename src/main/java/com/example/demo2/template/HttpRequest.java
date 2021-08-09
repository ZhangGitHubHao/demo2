package com.example.demo2.template;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * http请求
 *
 * @author 860120014
 * @date 2021-08-09
 */
public class HttpRequest {

    /**
     * 发送delete请求
     *
     * @param url
     * @return
     * @throws org.apache.http.client.ClientProtocolException
     * @throws java.io.IOException
     */
    public String doDelete(String url) throws IOException {

        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置BasicAuth
//        basicAuth(httpClientBuilder);
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
            if (entity != null) {
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

    public String doGet(String url) throws IOException {

        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置BasicAuth
//        basicAuth(httpClientBuilder);
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
            if (entity != null) {
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

    private CredentialsProvider basicAuth(HttpClientBuilder httpClientBuilder){
        CredentialsProvider provider = new BasicCredentialsProvider();
        // Create the authentication scope
        AuthScope scope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);
        // Create credential pair，在此处填写用户名和密钥
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("user", "secret");
        // Inject the credentials
        provider.setCredentials(scope, credentials);
        // Set the default credentials provider
        httpClientBuilder.setDefaultCredentialsProvider(provider);
        return provider;
    }
}
