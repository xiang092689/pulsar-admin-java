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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TenantsImpl implements Tenants {

    private final InnerHttpClient httpClient;

    public TenantsImpl(InnerHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public void createTenant(String tenant, TenantInfo tenantInfo) throws PulsarAdminException {
        try {
            InnerHttpResponseHandler.handleHttpResponse(
                    httpClient.put(
                            String.format("%s/%s", UrlConst.TENANTS, tenant), tenantInfo),
                    "failed to create tenant " + tenant);
        } catch (IOException | InterruptedException e) {
            throw new PulsarAdminException(e);
        }
    }

    @Override
    public void deleteTenant(String tenant, boolean force) throws PulsarAdminException {
        try {
            InnerHttpResponseHandler.handleHttpResponse(
                    httpClient.delete(String.format("%s/%s", UrlConst.TENANTS, tenant, "force", force)),
                    "failed to delete tenant " + tenant);
        } catch (IOException | InterruptedException e) {
            throw new PulsarAdminException(e);
        }
    }

    @Override
    public void updateTenant(String tenant, TenantInfo tenantInfo) throws PulsarAdminException {
        try {
            InnerHttpResponseHandler.handleHttpResponse(
                    httpClient.post(
                            String.format("%s/%s", UrlConst.TENANTS, tenant), tenantInfo),
                    "failed to update tenant " + tenant);
        } catch (IOException | InterruptedException e) {
            throw new PulsarAdminException(e);
        }
    }

    @Override
    public TenantInfo getTenantAdmin(String tenant) throws PulsarAdminException {
        try {
            return InnerHttpResponseHandler.handleHttpResponse(
                    httpClient.get(
                            String.format("%s/%s", UrlConst.TENANTS, tenant)),
                    "failed to get tenant " + tenant,
                    TenantInfo.class);
        } catch (IOException | InterruptedException e) {
            throw new PulsarAdminException(e);
        }
    }

    @Override
    public List<String> getTenants() throws PulsarAdminException {
        try {
            return InnerHttpResponseHandler.handleHttpResponse(
                    httpClient.get(UrlConst.TENANTS),
                    "failed to get list of tenants",
                    ArrayList.class);
        } catch (IOException | InterruptedException e) {
            throw new PulsarAdminException(e);
        }
    }
}
