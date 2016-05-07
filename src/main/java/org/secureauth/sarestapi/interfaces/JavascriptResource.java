package org.secureauth.sarestapi.interfaces;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.JSObjectResponse;

/**
 * Created by rrowcliffe on 5/7/16.
 */
public interface JavascriptResource {

    JSObjectResponse getDFPJavaScriptSrc(SAAccess saAccess);
}
