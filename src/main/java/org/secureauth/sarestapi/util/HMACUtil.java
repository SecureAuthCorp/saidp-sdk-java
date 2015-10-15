package org.secureauth.sarestapi.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

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
public class HMACUtil {

    public HMACUtil(){}

    public static byte[] encode(String secret, String data) throws Exception {
        // get the bytes of the hmac key and data string
        byte[] secretByte = new Hex().decode(secret.getBytes());
        byte[] dataBytes = data.getBytes("UTF-8");

        SecretKeySpec secretKey = new SecretKeySpec(secretByte, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] doFinal = mac.doFinal(dataBytes);
        return doFinal;
    }

    public static String getFinalHmac(String username, String secret, String data) throws Exception {
        byte[] orginHmac = encode(secret, data);

        String firstBase64 = new String(Base64.encodeBase64(orginHmac));

        String usernameN64 = username + ":" + firstBase64;

        String secodeBase64 = new String(Base64.encodeBase64(usernameN64.getBytes("UTF-8")));

        String authHeader = "Basic " + secodeBase64;

        return authHeader;
    }
}
