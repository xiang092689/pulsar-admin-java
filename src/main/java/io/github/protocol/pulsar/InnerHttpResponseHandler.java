/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.github.protocol.pulsar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;

public class InnerHttpResponseHandler {

    public static ObjectMapper mapper = new ObjectMapper();

    public static <T> T handleHttpResponse(HttpResponse<String> httpResponse, String msg, Class<T> response)
            throws PulsarAdminException {
        if (httpResponse.statusCode() < 200 || httpResponse.statusCode() >= 300) {
            throw new PulsarAdminException(
                    msg + ", status code: " + httpResponse.statusCode() + ", body: " + httpResponse.body(),
                    httpResponse.statusCode());
        }
        if (response.equals(Void.class)) {
            return null;
        }
        try {
            return mapper.readValue(httpResponse.body(), response);
        } catch (JsonProcessingException e) {
            throw new PulsarAdminException(e);
        }
    }

    public static void handleHttpResponse(HttpResponse<String> httpResponse, String msg) throws PulsarAdminException {
        handleHttpResponse(httpResponse, msg, Void.class);
    }

}
