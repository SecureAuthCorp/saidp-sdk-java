package org.secureauth.sarestapi.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author rrowcliffe@secureauth.com
 *
Copyright (c) 2015, SecureAuth
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

public class HMACUtil {

    public HMACUtil(){}

    public static byte[] encode(String secret, String data) throws Exception {
        // get the bytes of the hmac key and data string
        byte[] secretByte = new Hex(StandardCharsets.UTF_8).decode(secret.getBytes(StandardCharsets.UTF_8));
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

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

        String secodeBase64 = new String(Base64.encodeBase64(usernameN64.getBytes(StandardCharsets.UTF_8)));

        String authHeader = "Basic " + secodeBase64;

        return authHeader;
    }
}
