package org.secureauth.sarestapi.ssl;

import org.secureauth.sarestapi.data.SABaseURL;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class SATrustManagerFactory {

    private static final String PKIX_ALGORITHM = "PKIX";

    public static TrustManager[] createTrustsManagersFor(SABaseURL saBaseURL) throws Exception {
        if( saBaseURL.isSelfSigned() ) {
            // disabled impl.
            return new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }
                    }
            };
        }
        // The default implementation for SSLContext :
        // A factory for X509ExtendedTrustManager objects that validate certificate chains according
        // to the rules defined by the IETF PKIX working group in RFC 5280 or its successor.
        // "Internet X.509 Public Key Infrastructure Certificate
        // and Certificate Revocation List (CRL) Profile"
        // https://tools.ietf.org/html/rfc5280https://tools.ietf.org/html/rfc5280
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance( PKIX_ALGORITHM );
        trustManagerFactory.init( ( KeyStore )null );
        return trustManagerFactory.getTrustManagers();
    }
}
