package org.secureauth.sarestapi.util;


import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.secureauth.sarestapi.data.AuthRequest;
import org.secureauth.sarestapi.data.IPEvalRequest;

import java.io.IOException;

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
public class JSONUtil {
    public JSONUtil (){}

    public static String getJSONStringFromObject(AuthRequest authRequest){
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder stringBuilder = new StringBuilder();

        try{

            stringBuilder.append(mapper.writeValueAsString(authRequest));

        }catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        }catch (IOException e) {

            e.printStackTrace();

        }

        return stringBuilder.toString();
    }

    public static String getJSONStringFromObject(IPEvalRequest ipEvalRequest){
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder stringBuilder = new StringBuilder();

        try{

            stringBuilder.append(mapper.writeValueAsString(ipEvalRequest));

        }catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        }catch (IOException e) {

            e.printStackTrace();

        }

        return stringBuilder.toString();
    }
}
