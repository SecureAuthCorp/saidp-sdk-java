package org.secureauth.sarestapi.main;

import com.google.common.collect.Maps;
import org.secureauth.sarestapi.ISAAccess;
import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.SAAuth;
import org.secureauth.sarestapi.data.SABaseURL;
import org.secureauth.sarestapi.main.api.DiagnosticApi;
import org.secureauth.sarestapi.main.api.NoImplementedYetApi;
import org.secureauth.sarestapi.main.api.Parameters;
import org.secureauth.sarestapi.main.api.fingerprint.DFPApi;
import org.secureauth.sarestapi.main.api.users.UsersProfileApi;
import org.secureauth.sarestapi.main.api.users.UsersProfileQPApi;
import org.secureauth.sarestapi.resources.SAExecuter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    private static final String REALM = "idp.realm";
    private static final String API_APPLICATION_ID = "idp.realm.api.application.id";
    private static final String API_APPLICATION_KEY = "idp.realm.api.application.key";
    private static final String HOST = "idp.host";
    private static final String PORT = "idp.port";
    private static final String SSL = "idp.ssl";
    private static final String PARAMETER_VALUE_DELIMITER = "=";
    private static final String PARAMETER_KEY_SERVICE = "service";
    private Main() {}
    private static Map<String, DiagnosticApi> DIAGNOSTIC_APIS = Maps.newHashMap();
    static {
        DIAGNOSTIC_APIS.put( "v1.dfp.score", new DFPApi() );
        DIAGNOSTIC_APIS.put( "v1.users.profile", new UsersProfileApi());
        DIAGNOSTIC_APIS.put( "v1.users.profile.qp", new UsersProfileQPApi() );
    }

    public static void main(String [] args) throws InterruptedException, ExecutionException {
        final Parameters params = new Parameters( args, PARAMETER_VALUE_DELIMITER );
        final String serviceName = params.getMandatory( PARAMETER_KEY_SERVICE );

        Properties commonConfig = null;
        final String propertiesFilePath = params.getOrDefault(
                "properties",
                "configuration/configuration.properties"
        );
        try {
            commonConfig = readCommonConfig( propertiesFilePath );
        } catch (Exception e) {
            System.err.println("Error parsing configuration from : " + propertiesFilePath );
            e.printStackTrace( System.err );
            return;
        }
        final SABaseURL saBaseURL = new SABaseURL(
                commonConfig.getProperty( HOST, "" ),
                commonConfig.getProperty( PORT, "" ),
                Boolean.parseBoolean( commonConfig.getProperty( SSL, "true" ) ),
                true
        );
        String apiApplicationId = commonConfig.getProperty( API_APPLICATION_ID, "" );
        String apiApplicationKey = commonConfig.getProperty( API_APPLICATION_KEY, "" );
        String realm = commonConfig.getProperty( REALM, "" );
        final ISAAccess saAccess = new SAAccess(
                saBaseURL,
                new SAAuth( apiApplicationId, apiApplicationKey, realm ),
                new SAExecuter( saBaseURL )
        );
        final DiagnosticApi diagnosticApi = DIAGNOSTIC_APIS.getOrDefault( serviceName, new NoImplementedYetApi(serviceName) );
        // request for help.
        if( ! params.getOrDefault( "help", "" ).isEmpty() ) {
            System.out.println( "------------------------------" );
            System.out.println( serviceName + " usage : " );
            System.out.println( "------------------------------" );
            System.out.println( diagnosticApi.printHelp() );
            System.out.println( "------------------------------" );
            return;
        }
        // request for run.
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<Object> future = executor.submit(() -> diagnosticApi.run( params, saAccess ));
            System.out.print("Running..");
            while ( !future.isDone() ) {
                System.out.print( "." );
                Thread.sleep(300);
            }
            System.out.println( "Done!" );
            System.out.println( future.get() );
        } catch (ExecutionException exc) {
            System.err.println( exc.getMessage() );
        } catch (Exception exc) {
            exc.printStackTrace( System.err );
        } finally {
            executor.shutdown();
        }
    }

    private static Properties readCommonConfig(final String filePath) throws IOException{
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = Main.class.getClassLoader().getResourceAsStream( filePath );
            properties.load( inputStream );
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            inputStream.close();
        }
        return properties;
    }
}
