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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OffloadPolicies {

    private String fileSystemProfilePath;

    private String fileSystemURI;

    private String gcsManagedLedgerOffloadBucket;

    private Integer gcsManagedLedgerOffloadMaxBlockSizeInBytes;

    private String gcsManagedLedgerOffloadRegion;

    private String gcsManagedLedgerOffloadServiceAccountKeyFile;

    private String managedLedgerOffloadBucket;

    private Long managedLedgerOffloadDeletionLagInMillis;

    private String managedLedgerOffloadDriver;

    private Integer managedLedgerOffloadMaxBlockSizeInBytes;

    private Integer managedLedgerOffloadMaxThreads;

    private Integer managedLedgerOffloadPrefetchRounds;

    private Integer managedLedgerOffloadReadBufferSizeInBytes;

    private String managedLedgerOffloadRegion;

    private String managedLedgerOffloadServiceEndpoint;

    private Long managedLedgerOffloadThresholdInBytes;

    private Long managedLedgerOffloadThresholdInSeconds;

    private OffloadedReadPriority managedLedgerOffloadedReadPriority;

    private String offloadersDirectory;

    private String s3ManagedLedgerOffloadBucket;

    private String s3ManagedLedgerOffloadCredentialId;

    private String s3ManagedLedgerOffloadCredentialSecret;

    private Integer s3ManagedLedgerOffloadMaxBlockSizeInBytes;

    private Integer s3ManagedLedgerOffloadReadBufferSizeInBytes;

    private String s3ManagedLedgerOffloadRegion;

    private String s3ManagedLedgerOffloadRole;

    private String s3ManagedLedgerOffloadRoleSessionName;

    private String s3ManagedLedgerOffloadServiceEndpoint;

}
