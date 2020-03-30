package org.secureauth.sarestapi.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.secureauth.sarestapi.data.DFP.DFP;
import org.secureauth.sarestapi.data.Requests.DFPValidateRequest;
import org.secureauth.sarestapi.resources.SAExecuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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


public class JSONUtil {

    private static Logger logger = LoggerFactory.getLogger(JSONUtil.class);
    public JSONUtil(){}

     public static String convertObjectToJSON(Object object){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        return json;
    }


    public static DFPValidateRequest getObjectFromJSONString(String dfpJsonString){
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder stringBuilder = new StringBuilder();
        DFPValidateRequest dfpValidateRequest = new DFPValidateRequest();

        try{
            dfpValidateRequest = mapper.readValue(dfpJsonString,DFPValidateRequest.class);

        } catch(IOException e){
            logger.error(e.getMessage());
        }

        return dfpValidateRequest;
    }

    public static DFP getDFPFromJSONString(String dfpJsonString){
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder stringBuilder = new StringBuilder();
        DFP dfp = new DFP();

        try{
            dfp = mapper.readValue(dfpJsonString,DFP.class);

        } catch(IOException e){
            logger.error(e.getMessage());
        }

        return dfp;
    }
}
