/*
* Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* WSO2 Inc. licenses this file to you under the Apache License,
* Version 2.0 (the "License"); you may not use this file except
* in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied. See the License for the
* specific language governing permissions and limitations
* under the License.
*
*/
package org.wso2.apim.billing.services.impl;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.wso2.apim.billing.Util;
import org.wso2.apim.billing.bean.RedirectBean;
import org.wso2.apim.billing.domain.SubsWorkflowDTO;
import org.wso2.apim.billing.domain.UserEntity;
import org.wso2.apim.billing.services.WorkflowClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WorkflowClientImpl implements WorkflowClient {
    private String apimTrustStore;
    private String apimTrustStorePassword;
    private String apimUserName;
    private String apimPassword;

    public String getApimUserName() {
        return apimUserName;
    }

    public void setApimUserName(String apimUserName) {
        this.apimUserName = apimUserName;
    }

    public String getApimPassword() {
        return apimPassword;
    }

    public void setApimPassword(String apimPassword) {
        this.apimPassword = apimPassword;
    }

    public String getApimTrustStore() {
        return apimTrustStore;
    }

    public void setApimTrustStore(String apimTrustStore) {
        this.apimTrustStore = apimTrustStore;
    }

    public String getApimTrustStorePassword() {
        return apimTrustStorePassword;
    }

    public void setApimTrustStorePassword(String apimTrustStorePassword) {
        this.apimTrustStorePassword = apimTrustStorePassword;
    }

    @Override
    public boolean activateSubscription(SubsWorkflowDTO workflowDTO) throws Exception {
        System.out.println("activateSubscription  " + workflowDTO.getCallbackUrl());
        if (workflowDTO == null) {
            System.out.println("Skipping activateSubscription ");
            return false;
        }

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("workflowReference", workflowDTO.getWorkflowRefId()));
        urlParameters.add(new BasicNameValuePair("status", "APPROVED"));
        urlParameters.add(new BasicNameValuePair("description", "DESCRIPTION"));

        List<NameValuePair> headers = new ArrayList<>();
        String encodedCredentials = encodeCredentials(apimUserName, apimPassword.toCharArray());
        headers.add(new BasicNameValuePair("Authorization", "Basic " + encodedCredentials));
        HttpResponse response = sendPOSTMessage(workflowDTO.getCallbackUrl(), headers, urlParameters);
        String msg = getResponseBody(response);
        System.out.println(msg);
        return msg.contains("\"error\" : \"false\"") && (response.getStatusLine().getStatusCode() == 200);
    }

    private HttpResponse sendPOSTMessage(String url, List<NameValuePair> headers, List<NameValuePair> urlParameters)
            throws Exception {
        CloseableHttpClient httpClient = Util.initHttpClient(this.apimTrustStore, this.apimTrustStorePassword.toCharArray());
        HttpPost post = new HttpPost(url);
        if (headers != null) {
            for (NameValuePair nameValuePair : headers) {
                post.addHeader(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        return httpClient.execute(post);
    }

    private String getResponseBody(HttpResponse response) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        String line;
        StringBuffer sb = new StringBuffer();

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        return sb.toString();
    }

    /**
     * get the base64 encoded username and password
     *
     * @param user username
     * @param pass password
     * @return encoded basic auth, as string
     */
    private String encodeCredentials(String user, char[] pass) {
        StringBuilder builder = new StringBuilder(user).append(':').append(pass);
        String cred = builder.toString();
        byte[] encodedBytes = Base64.encodeBase64(cred.getBytes());
        return new String(encodedBytes);
    }
}
