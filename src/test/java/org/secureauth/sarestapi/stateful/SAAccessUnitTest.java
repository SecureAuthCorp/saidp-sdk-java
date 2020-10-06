package org.secureauth.sarestapi.stateful;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.PushAcceptStatus;
import org.secureauth.sarestapi.data.Response.StatefulResponseObject;

public class SAAccessUnitTest {

    private WireMockServer wireMockServer;
    private SAAccess saAccess;
    private static boolean RECORDING_MODE = false;
    @Before
    public void setup() {
        this.wireMockServer = new WireMockServer(8090);
        this.wireMockServer.start();
        if( RECORDING_MODE ) {
            this.wireMockServer.startRecording("https://myidp.com");
        }
        this.saAccess =  new SAAccess(
                "localhost",
                "8090",
                false,
                true,
                "Realm01",
                "Realm01-ApplicationId",
                "Realm01-ApplicationKey"
        );
    }

    @After
    public void teardown() {
        if( RECORDING_MODE ) {
            this.wireMockServer.stopRecording();
        } else {
            this.wireMockServer.stop();
        }
    }

    @Test
    public void given_AValidUserAndFactorId_When_PerformOutOfBandAuthStateful_Then_IngressCookieIsReturned() {
        // given
        final String userId = "test-user-1";
        final String factorId = "9a29542309654256a0d71f9e86095f45";
        // when
        StatefulResponseObject resp = this.saAccess.sendPushToAcceptReqStateful( userId, factorId, "127.0.0.1", "", "" );
        // then
        Assert.assertEquals("1570217946.933.809.995388", resp.getSessionAffinityCookie().getValue());
    }

    @Test
    public void given_APushNotificationRequestInProgress_When_QueryUsingRefIdAndCookie_Then_PushAcceptStatusIsOk() {
        // given
        final String userId = "test-user-2";
        final String factorId = "9a29542309654256a0d71f9e86095f45";
        StatefulResponseObject resp = this.saAccess.sendPushToAcceptReqStateful( userId, factorId, "127.0.0.2", "", "" );
        // when
       PushAcceptStatus pushAcceptStatus = this.saAccess.queryPushAcceptStatusStateful( resp.getReference_id(), resp.getSessionAffinityCookie() );
        // then
        Assert.assertEquals("PENDING", pushAcceptStatus.getMessage());
    }
}
