package org.secureauth.sarestapi.guid;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.secureauth.sarestapi.SAAccess;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class SAAccessUnitTest {

    private WireMockServer wireMockServer;
    private final String X_REQUEST_ID = "X-Request-Id";
    @Before
    public void setup() {
        this.wireMockServer = new WireMockServer(8090);
        this.wireMockServer.start();
    }

    @After
    public void teardown() {
        this.wireMockServer.stop();
    }

    @Test
    public void given_SAAccessWithNewRequestIDForEachRequest_When_PerformOutOfBandAuthStateful_Then_XRequestIDHeaderIsPresent() {
        // given
        final SAAccess saAccess = this.createSAAccessWithGUIDStrategy( UUID::randomUUID );
        final String userId = "test-user-1";
        final String factorId = "9a29542309654256a0d71f9e86095f45";
        // when
        saAccess.sendPushToAcceptReqStateful( userId, factorId, "127.0.0.1", "", "" );
        // then
        this.wireMockServer.verify(
                postRequestedFor( urlEqualTo("/Realm01/api/v1/auth" ) )
                .withHeader( X_REQUEST_ID , matching( "\\S{36}" ) )
        );
    }

    @Test
    public void given_SAAccessWithNewRequestIDForEachRequest_When_PerformFiveOutOfBandAuthStatefulRequests_Then_FiveDifferentXRequestIDHeaderValueAreGenerated() {
        // given
        final SAAccess saAccess = this.createSAAccessWithGUIDStrategy( UUID::randomUUID );
        final String userId = "test-user-1";
        final String factorId = "9a29542309654256a0d71f9e86095f45";
        final int requestsToMake = 5;
        // when
        IntStream.range( 0, requestsToMake )
                .forEach( i ->
                        saAccess.sendPushToAcceptReqStateful(userId, factorId, "127.0.0.1", "", "")
                );
        // then
        List<LoggedRequest> requestList = this.wireMockServer.findAll(
                postRequestedFor( urlEqualTo("/Realm01/api/v1/auth" ) )
                        .withHeader( X_REQUEST_ID, matching( "\\S{36}" ) )
        );
        Assert.assertEquals(
                requestsToMake,
                requestList.stream().map( r -> r.getHeader( X_REQUEST_ID ) ).collect(Collectors.toSet() ).size()
        );
    }

    private SAAccess createSAAccessWithGUIDStrategy(GUIDStrategy guidStrategy) {
        return new SAAccess(
                "localhost",
                "8090",
                false,
                true,
                "Realm01",
                "Realm01-ApplicationId",
                "Realm01-ApplicationKey",
                guidStrategy
        );
    }
}

