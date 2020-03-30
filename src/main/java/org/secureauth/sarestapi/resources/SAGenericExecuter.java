package org.secureauth.sarestapi.resources;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.secureauth.sarestapi.data.SABaseURL;
import org.secureauth.sarestapi.exception.SARestAPIException;
import org.secureauth.sarestapi.filters.SACheckRequestFilter;
import org.secureauth.sarestapi.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Optional;

public class SAGenericExecuter {

	protected Client client;
	private static Logger logger = LoggerFactory.getLogger(SAGenericExecuter.class);
	private static final String TEN_SECONDS = "10000";

	private SABaseURL saBaseURL;

	public SAGenericExecuter(SABaseURL saBaseURL){
		this.saBaseURL = saBaseURL;
	}

	//Set up our Connection
	protected void createConnection() throws SARestAPIException{
		try{
			ClientConfig config = new ClientConfig();
			SSLContext ctx;
			ctx = SSLContext.getInstance("TLS");

			TrustManager[] certs = new TrustManager[]{
					new X509TrustManager(){
						@Override
						public X509Certificate[] getAcceptedIssuers(){
							return null;
						}

						@Override
						public void checkServerTrusted(X509Certificate[] chain, String authType) {}

						@Override
						public void checkClientTrusted(X509Certificate[] chain, String authType) {}
					}
			};

			ctx.init(null, certs, new SecureRandom());


			config.register(SACheckRequestFilter.class);
			client = ClientBuilder.newBuilder()
					.withConfig(config)
					.sslContext(ctx)
					.hostnameVerifier((String s, SSLSession sslSession) -> saBaseURL.isSelfSigned())
					.build();
			int timeoutSeconds = Integer.parseInt(Optional.ofNullable(System.getProperty("rest.api.timeout")).orElse(TEN_SECONDS));
			client.property(ClientProperties.CONNECT_TIMEOUT, timeoutSeconds);
			client.property(ClientProperties.READ_TIMEOUT, timeoutSeconds);
		}catch(Exception e){
			throw new SARestAPIException("Exception occurred while attempting to associating our SSL cert to the session. Unable to create connection object.");
		}
	}

	public <T> T executeGenericGetRequest(String auth, String query, String ts, Class<T> valueType) throws SARestAPIException {
		return executeGenericGetRequest(auth, query, ts, valueType, "Generic Get Request");
	}

	public <T> T executeGenericGetRequest(String auth, String query, String ts, Class<T> responseTypeClass, String exceptionMessage) throws SARestAPIException {
		try{
			if(client == null) {
				createConnection();
			}

			WebTarget target = client.target(query);
			Response response = target.request().
					accept(MediaType.APPLICATION_JSON).
					header("Authorization", auth).
					header("X-SA-Ext-Date", ts).
					get();
			T responseObject = response.readEntity(responseTypeClass);
			response.close();
			return responseObject;
		}catch(Exception e){
			throw new SARestAPIException(exceptionMessage + " Query: " + query, e);
		}
	}

	public <T> T executeGenericPostRequest(String header, String query, Object requestType, Class<T> responseTypeClass, String ts) throws SARestAPIException {
		return executeGenericPostRequest(header, query, requestType, responseTypeClass, ts, "Generic Post Request");
	}

	public <T> T executeGenericPostRequest(String header, String query, Object requestType, Class<T> responseTypeClass, String ts, String exceptionMessage)
			throws SARestAPIException {
		try{
			if(client == null) {
				createConnection();
			}

			WebTarget target = client.target(query);
			Response response = target.request().
					accept(MediaType.APPLICATION_JSON).
					header("Authorization", header).
					header("X-SA-Ext-Date", ts).
					post(Entity.entity(JSONUtil.convertObjectToJSON(requestType),MediaType.APPLICATION_JSON));

			T responseObject = response.readEntity(responseTypeClass);
			response.close();
			return responseObject;
		}catch(Exception e){
			throw new SARestAPIException(exceptionMessage + " Query: " + query, e);
		}
	}

	public <T> T executeGenericPostRequest(String auth, String query, String ts, Class<T> valueType)throws Exception {
		return executeGenericPostRequest(auth, query, ts, valueType, "Generic Post Request");

	}

	public <T> T executeGenericPostRequest(String auth, String query, String ts, Class<T> responseTypeClass, String exceptionMessage)throws Exception {
		try{
			if(client == null) {
				createConnection();
			}

			WebTarget target = client.target(query);
			Response response = target.request().
					accept(MediaType.APPLICATION_JSON).
					header("Authorization", auth).
					header("X-SA-Ext-Date", ts).
					post(Entity.entity("",MediaType.APPLICATION_JSON));
			T responseObject = response.readEntity(responseTypeClass);
			response.close();
			return responseObject;
		}catch(Exception e){
			throw new SARestAPIException(exceptionMessage + " Query: " + query, e);
		}

	}

	public <T> T executeGenericPutRequest(String auth, String query, Object objectRequest, Class<T> responseType, String ts)
			throws SARestAPIException{
		return executeGenericPutRequest(auth, query, objectRequest, responseType, ts, "Generic Put Request");
	}

	public <T> T executeGenericPutRequest(String auth, String query, Object objectRequest, Class<T> responseTypeClass, String ts, String exceptionMessage)
			throws SARestAPIException{
		try{
			if(client == null) {
				createConnection();
			}

			WebTarget target = client.target(query);
			Response response = target.request()
					.accept(MediaType.APPLICATION_JSON)
					.header("Authorization", auth)
					.header("X-SA-Ext-Date", ts)
					.put(Entity.entity(JSONUtil.convertObjectToJSON(objectRequest),MediaType.APPLICATION_JSON));

			T responseObject = response.readEntity(responseTypeClass);
			response.close();
			return responseObject;
		}catch(Exception e){
			throw new SARestAPIException(exceptionMessage + " Query: " + query, e);
		}
	}

}
