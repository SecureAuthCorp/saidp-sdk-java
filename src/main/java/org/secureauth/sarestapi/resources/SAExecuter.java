package org.secureauth.sarestapi.resources;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.secureauth.sarestapi.data.*;
import org.secureauth.sarestapi.util.JSONUtil;

import javax.net.ssl.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rrowcliffe@secureauth.com
 *
 * <p>
 * Copyright 2015 SecureAuth Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */
public class SAExecuter {

    private com.sun.jersey.api.client.config.ClientConfig config = null;
    private com.sun.jersey.api.client.Client client=null;
    private static Logger logger=Logger.getLogger(SAExecuter.class.getName());

    //Set up our Connection
    private void createConnection() throws Exception{

        config = new DefaultClientConfig();

        TrustManager[] certs = new TrustManager[]{
                new X509TrustManager(){
                    @Override
                    public X509Certificate[] getAcceptedIssuers(){
                        return null;
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException{}
                }
        };

        SSLContext ctx = null;

        try{
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, certs, new SecureRandom());
        }catch(java.security.GeneralSecurityException ex){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception occurred while attempting to setup SSL security. ").toString(), ex);
        }

        //logger.log(Level.SEVERE,"Setting url connection!");
        HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());

        try{
            config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(
                    new HostnameVerifier(){
                        @Override
                        public boolean verify(String hostname, SSLSession session){return true;}
                    },ctx));


        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception occurred while attempting to associating our SSL cert to the session.").toString(), e);
        }

        try{
            client = Client.create(config);
        }catch(Exception e){
            StringBuilder bud = new StringBuilder();
            for(StackTraceElement st: e.getStackTrace()){
                bud.append(st.toString()).append("\n");
            }
            throw new Exception(new StringBuilder().append("Exception occurred while attempting to create connection object. Exception: ")
                    .append(e.getMessage()).append("\nStackTraceElements:\n").append(bud.toString()).toString());
        }

        if(client == null) throw new Exception(new StringBuilder().append("Unable to create connection object, creation attempt returned NULL.").toString());
    }

    //Get Factors for the user requested
    public <T> T executeGetRequest(String auth, String query,String ts,  Class<T> valueType)throws Exception {
        if(client == null) {
            createConnection();
        }

        WebResource service = null;
        ClientResponse response = null;
        String factors=null;
        T factorsResponse =null;
        try{

            service = client.resource(query);
            service.header("Authorization", auth);
            response = service.accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Date", ts).
                    get(ClientResponse.class);
            factors= response.getEntity(String.class);

            //System.out.println(factors);
            JAXBContext context = JAXBContext.newInstance(valueType);
            Unmarshaller un = context.createUnmarshaller();

            InputStream inStream = new ByteArrayInputStream(factors.getBytes());
            factorsResponse = new ObjectMapper().readValue(inStream, valueType);

        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception getting User Factors: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).append(".\nResponse code is ").append(response).toString(), e);
        }
        return factorsResponse;

    }

    //Validate User against Repository
    public ResponseObject executeValidateUser(String header,String query, AuthRequest authRequest,String ts)throws Exception{

        if(client == null) {
            createConnection();
        }


        WebResource service = null;
        ClientResponse response = null;
        String responseStr=null;
        ResponseObject responseObject =null;
        try{
            service = client.resource(query);

            response = service.accept(MediaType.APPLICATION_JSON).
                    type(MediaType.APPLICATION_JSON).
                    header("Authorization", header).
                    header("X-SA-Date", ts).
                    post(ClientResponse.class, JSONUtil.getJSONStringFromObject(authRequest));

            responseStr = response.getEntity(String.class);

            JAXBContext context = JAXBContext.newInstance(ResponseObject.class);
            Unmarshaller un = context.createUnmarshaller();
            InputStream inStream = new ByteArrayInputStream(responseStr.getBytes());

            responseObject = new ObjectMapper().readValue(inStream,ResponseObject.class);


        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception Validating User: \nQuery:\n\t")
                    .append(query).append("\nError: \n\t").append(responseStr).append(".\nResponse code is ").append(response).toString(), e);
        }
        return responseObject;

    }

    //Validate Users Password
    public ResponseObject executeValidateUserPassword(String auth,String query, AuthRequest authRequest,String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebResource service = null;
        ClientResponse response = null;
        String responseStr=null;
        ResponseObject responseObject =null;
        try{

            service = client.resource(query);
            response = service.accept(MediaType.APPLICATION_JSON).
                    type(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Date", ts).
                    post(ClientResponse.class, JSONUtil.getJSONStringFromObject(authRequest));
            responseStr=response.getEntity(String.class);
            JAXBContext context = JAXBContext.newInstance(ResponseObject.class);
            Unmarshaller un = context.createUnmarshaller();
            InputStream inStream = new ByteArrayInputStream(responseStr.getBytes());
            responseObject = new ObjectMapper().readValue(inStream,ResponseObject.class);


        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception Validating User Password: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).append(".\nResponse code is ").append(response).toString(), e);
        }
        return responseObject;

    }

    //Validate Users KBA
    public ResponseObject executeValidateKba(String auth,String query, AuthRequest authRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebResource service = null;
        ClientResponse response = null;
        String responseStr=null;
        ResponseObject responseObject =null;
        try{

            service = client.resource(query);
            response = service.accept(MediaType.APPLICATION_JSON).
                    type(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Date", ts).
                    post(ClientResponse.class, JSONUtil.getJSONStringFromObject(authRequest));
            responseStr= response.getEntity(String.class);
            JAXBContext context = JAXBContext.newInstance(ResponseObject.class);
            Unmarshaller un = context.createUnmarshaller();
            InputStream inStream = new ByteArrayInputStream(responseStr.getBytes());
            responseObject = new ObjectMapper().readValue(inStream,ResponseObject.class);

        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception Validating KBA: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).append(".\nResponse code is ").append(response).toString(), e);
        }
        return responseObject;

    }

    //Validate User Oath Token
    public ResponseObject executeValidateOath(String auth,String query, AuthRequest authRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebResource service = null;
        ClientResponse response = null;
        String responseStr=null;
        ResponseObject responseObject =null;
        try{

            service = client.resource(query);
            response = service.accept(MediaType.APPLICATION_JSON).
                    type(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Date", ts).
                    post(ClientResponse.class, JSONUtil.getJSONStringFromObject(authRequest));
            responseStr= response.getEntity(String.class);
            JAXBContext context = JAXBContext.newInstance(ResponseObject.class);
            Unmarshaller un = context.createUnmarshaller();
            InputStream inStream = new ByteArrayInputStream(responseStr.getBytes());
            responseObject = new ObjectMapper().readValue(inStream,ResponseObject.class);


        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception Validating OATH: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).append(".\nResponse code is ").append(response).toString(), e);
        }
        return responseObject;

    }

    //Validate OTP By Phone
    public ResponseObject executeOTPByPhone(String auth,String query, AuthRequest authRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebResource service = null;
        ClientResponse response = null;
        String responseStr=null;
        ResponseObject responseObject =null;
        try{

            service = client.resource(query);
            response = service.accept(MediaType.APPLICATION_JSON).
                    type(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Date", ts).
                    post(ClientResponse.class, JSONUtil.getJSONStringFromObject(authRequest));
            responseStr= response.getEntity(String.class);
            JAXBContext context = JAXBContext.newInstance(ResponseObject.class);
            Unmarshaller un = context.createUnmarshaller();
            InputStream inStream = new ByteArrayInputStream(responseStr.getBytes());
            responseObject = new ObjectMapper().readValue(inStream,ResponseObject.class);


        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception Delivering OTP by Phone: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).append(".\nResponse code is ").append(response).toString(), e);
        }
        return responseObject;

    }

    //Validate User OATH by SMS
    public ResponseObject executeOTPBySMS(String auth,String query, AuthRequest authRequest,String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebResource service = null;
        ClientResponse response = null;
        String responseStr=null;
        ResponseObject responseObject =null;
        try{

            service = client.resource(query);
            response = service.accept(MediaType.APPLICATION_JSON).
                    type(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Date", ts).
                    post(ClientResponse.class, JSONUtil.getJSONStringFromObject(authRequest));
            responseStr= response.getEntity(String.class);
            JAXBContext context = JAXBContext.newInstance(ResponseObject.class);
            Unmarshaller un = context.createUnmarshaller();
            InputStream inStream = new ByteArrayInputStream(responseStr.getBytes());
            responseObject = new ObjectMapper().readValue(inStream,ResponseObject.class);


        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception Delivering OTP by SMS: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).append(".\nResponse code is ").append(response).toString(), e);
        }
        return responseObject;

    }

    //Validate User OTP by Email
    public ResponseObject executeOTPByEmail(String auth,String query, AuthRequest authRequest,String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebResource service = null;
        ClientResponse response = null;
        String responseStr=null;
        ResponseObject responseObject =null;
        try{

            service = client.resource(query);
            response = service.accept(MediaType.APPLICATION_JSON).
                    type(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Date", ts).
                    post(ClientResponse.class, JSONUtil.getJSONStringFromObject(authRequest));
            responseStr= response.getEntity(String.class);
            JAXBContext context = JAXBContext.newInstance(ResponseObject.class);
            Unmarshaller un = context.createUnmarshaller();
            InputStream inStream = new ByteArrayInputStream(responseStr.getBytes());
            responseObject = new ObjectMapper().readValue(inStream,ResponseObject.class);


        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception Delivering OTP by Email: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).append(".\nResponse code is ").append(response).toString(), e);
        }
        return responseObject;

    }

    //Validate User Token by Push Notification
    public ResponseObject executePushRequest(String auth,String query, AuthRequest authRequest,String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebResource service = null;
        ClientResponse response = null;
        String responseStr=null;
        ResponseObject responseObject =null;
        try{

            service = client.resource(query);
            response = service.accept(MediaType.APPLICATION_JSON).
                    type(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Date", ts).
                    post(ClientResponse.class, JSONUtil.getJSONStringFromObject(authRequest));
            responseStr= response.getEntity(String.class);
            JAXBContext context = JAXBContext.newInstance(ResponseObject.class);
            Unmarshaller un = context.createUnmarshaller();
            InputStream inStream = new ByteArrayInputStream(responseStr.getBytes());
            responseObject = new ObjectMapper().readValue(inStream,ResponseObject.class);


        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception Delivering OTP by Push: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).append(".\nResponse code is ").append(response).toString(), e);
        }
        return responseObject;

    }

    //Validate User Token by Help Desk Call
    public ResponseObject executeOTPByHelpDesk(String auth,String query, AuthRequest authRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebResource service = null;
        ClientResponse response = null;
        String responseStr=null;
        ResponseObject responseObject =null;
        try{

            service = client.resource(query);
            response = service.accept(MediaType.APPLICATION_JSON).
                    type(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Date", ts).
                    post(ClientResponse.class, JSONUtil.getJSONStringFromObject(authRequest));
            responseStr= response.getEntity(String.class);
            JAXBContext context = JAXBContext.newInstance(ResponseObject.class);
            Unmarshaller un = context.createUnmarshaller();
            InputStream inStream = new ByteArrayInputStream(responseStr.getBytes());
            responseObject = new ObjectMapper().readValue(inStream,ResponseObject.class);


        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception Delivering OTP by HelpDesk: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).append(".\nResponse code is ").append(response).toString(), e);
        }
        return responseObject;

    }

    //Run IP Evaluation against user and IP Address
    public IPEval executeIPEval(String auth,String query, IPEvalRequest ipEvalRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebResource service = null;
        ClientResponse response = null;
        String responseStr=null;
        IPEval ipEval =null;
        try{
            service = client.resource(query);

            response = service.accept(MediaType.APPLICATION_JSON).
                    type(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Date", ts).
                    post(ClientResponse.class, JSONUtil.getJSONStringFromObject(ipEvalRequest));
            responseStr= response.getEntity(String.class);
            JAXBContext context = JAXBContext.newInstance(ResponseObject.class);
            Unmarshaller un = context.createUnmarshaller();
            InputStream inStream = new ByteArrayInputStream(responseStr.getBytes());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationConfig.Feature.AUTO_DETECT_FIELDS, true);
            ipEval = objectMapper.readValue(inStream,IPEval.class);


        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception Running IP Evaluation: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).append(".\nResponse code is ").append(response).toString(), e);
        }
        return ipEval;

    }

}
