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

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.List;

public class NamespacesImpl implements Namespaces {

    private final InnerHttpClient httpClient;

    public NamespacesImpl(InnerHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public List<String> getTenantNamespaces(String tenant) throws PulsarAdminException {
        try {
            HttpResponse<String> response = httpClient.get(String.format("%s/%s", UrlConst.NAMESPACES, tenant));
            if (response.statusCode() != 200) {
                throw new PulsarAdminException(
                        String.format("failed to get namespaces of tenant %s, status code %s, body : %s"
                                , tenant, response.statusCode(), response.body()));
            }
            return JacksonService.toList(response.body(), new TypeReference<List<String>>() {
            });
        } catch (IOException | InterruptedException e) {
            throw new PulsarAdminException(e);
        }
    }

    @Override
    public List<String> getTopics(String tenant, String namespace, Mode mode, boolean includeSystemTopic)
            throws PulsarAdminException {
        try {
            HttpResponse<String> response = httpClient.get(
                    String.format("%s/%s/%s/topics", UrlConst.NAMESPACES, tenant, namespace),
                    "mode", String.valueOf(mode),
                    "includeSystemTopic", String.valueOf(includeSystemTopic));
            if (response.statusCode() != 200) {
                throw new PulsarAdminException(
                        String.format("failed to get topics of namespace %s/%s, status code %s, body : %s",
                                tenant, namespace, response.statusCode(), response.body()));
            }
            return JacksonService.toList(response.body(), new TypeReference<List<String>>() {
            });
        } catch (IOException | InterruptedException e) {
            throw new PulsarAdminException(e);
        }
    }

    @Override
    public Policies getPolicies(String tenant, String namespace) throws PulsarAdminException {
        try {
            HttpResponse<String> response = httpClient.get(
                    String.format("%s/%s/%s", UrlConst.NAMESPACES, tenant, namespace));
            if (response.statusCode() != 200) {
                throw new PulsarAdminException(
                        String.format("failed to get policy of namespace %s/%s, status code %s, body : %s",
                                tenant, namespace, response.statusCode(), response.body()));
            }
            return JacksonService.toObject(response.body(), Policies.class);
        } catch (IOException | InterruptedException e) {
            throw new PulsarAdminException(e);
        }
    }

    @Override
    public void createNamespace(String tenant, String namespace, Policies policies) throws PulsarAdminException {
        try {
            HttpResponse<String> response = httpClient.put(
                    String.format("%s/%s/%s", UrlConst.NAMESPACES, tenant, namespace));
            if (response.statusCode() != 204) {
                throw new PulsarAdminException(
                        String.format("failed to create namespace %s/%s, status code %s, body : %s"
                                , tenant, namespace, response.statusCode(), response.body()));
            }
        } catch (IOException | InterruptedException e) {
            throw new PulsarAdminException(e);
        }
    }

    @Override
    public void deleteNamespace(String tenant, String namespace, boolean force, boolean authoritative)
            throws PulsarAdminException {
        try {
            HttpResponse<String> response = httpClient.delete(
                    String.format("%s/%s/%s", UrlConst.NAMESPACES, tenant, namespace),
                    "force", String.valueOf(force),
                    "authoritative", String.valueOf(authoritative));
            if (response.statusCode() != 204) {
                throw new PulsarAdminException(
                        String.format("failed to get topics of namespace %s/%s, status code %s, body : %s"
                                , tenant, namespace, response.statusCode(), response.body()));
            }
        } catch (IOException | InterruptedException e) {
            throw new PulsarAdminException(e);
        }
    }
}
