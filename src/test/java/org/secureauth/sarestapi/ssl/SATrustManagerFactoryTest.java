package org.secureauth.sarestapi.ssl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.secureauth.sarestapi.data.SABaseURL;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class SATrustManagerFactoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void givenSABaseUrlSelfSignedWhenValidateSelfSignedCertificateThenNoExceptionIsThrownAndIsCertificateIsValid() throws Exception {
        // setup
        final String appliance = "127.0.0.1";
        final String port = "8080";
        final boolean ssl = true;
        final boolean selfSigned = true;
        final X509Certificate[] certificates = { X509CertificateMockedFactory.createMockedSelfSignedX509Certificate( "O=\"For test\"" ) };
        // when
        TrustManager[] trustManagers =
                SATrustManagerFactory.createTrustsManagersFor( new SABaseURL( appliance, port , ssl, selfSigned ) );
        // then
        for( TrustManager tm : trustManagers ) {
            ( ( X509TrustManager ) tm ).checkServerTrusted( certificates, "DHE_DSS" );
        }
    }

    @Test
    public void givenSABaseUrlNoSelfSignedWhenValidateASelfSignedCertificateThenExceptionIsThrownAndIsCertificateIsNotValid() throws Exception {
        // setup
        final String appliance = "127.0.0.1";
        final String port = "8080";
        final boolean ssl = true;
        final boolean selfSigned = false;

        final X509Certificate[] certificates = { X509CertificateMockedFactory.createMockedSelfSignedX509Certificate( "O=\"For test\"" ) };
        // when
        TrustManager[] trustManagers =
                SATrustManagerFactory.createTrustsManagersFor( new SABaseURL( appliance, port , ssl, selfSigned ) );
        // expect exception.
        this.thrown.expect(NullPointerException.class);
        // then
        for (TrustManager tm : trustManagers) {
            ((X509TrustManager) tm).checkServerTrusted(certificates, "DHE_DSS");
        }
    }
}
