package org.secureauth.sarestapi.ssl;

import javax.security.auth.x500.X500Principal;
import java.security.cert.X509Certificate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class X509CertificateMockedFactory {

    public static X509Certificate createMockedSelfSignedX509Certificate(final String subject) {
        final X509Certificate x509Certificate = mock( X509Certificate.class );
        X500Principal x500Principal = new X500Principal( subject );
        when( x509Certificate.getSubjectX500Principal() ).thenReturn( x500Principal );
        when( x509Certificate.getIssuerX500Principal() ).thenReturn( x500Principal );
        return x509Certificate;
    }
}
