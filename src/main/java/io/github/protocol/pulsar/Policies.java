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

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Policies {

    private AuthPolicies auth_policies;

    private AutoSubscriptionCreationOverride autoSubscriptionCreationOverride;

    private AutoTopicCreationOverride autoTopicCreationOverride;

    private Map<BacklogQuota.BacklogQuotaType, BacklogQuota> backlog_quota_map;

    private BundlesData bundles;

    private Map<String, DispatchRateImpl> clusterDispatchRate;

    private Map<String, SubscribeRate> clusterSubscribeRate;

    private Long compaction_threshold;

    private Boolean deduplicationEnabled;

    private Integer deduplicationSnapshotIntervalSeconds;

    private DelayedDeliveryPolicies delayed_delivery_policies;

    private Boolean deleted;

    private Boolean encryption_required;

    private EntryFilters entryFilters;

    private InactiveTopicPolicies inactive_topic_policies;

    private Boolean is_allow_auto_update_schema;

    private Map<String, Integer> latency_stats_sample_rate;

    private Integer max_consumers_per_subscription;

    private Integer max_consumers_per_topic;

    private Integer max_producers_per_topic;

    private Integer max_subscriptions_per_topic;

    private Integer max_topics_per_namespace;

    private Integer max_unacked_messages_per_consumer;

    private Integer max_unacked_messages_per_subscription;

    private Integer message_ttl_in_seconds;

    private Long offload_deletion_lag_ms;

    private OffloadPolicies offload_policies;

    private Long offload_threshold;

    private Long offload_threshold_in_seconds;

    private PersistencePolicies persistence;

    private Map<String, String> properties;

    private Map<String, PublishRate> publishMaxMessageRate;

    private Set<String> replication_clusters;

    private Map<String, DispatchRateImpl> replicatorDispatchRate;

    private String resource_group_name;

    private RetentionPolicies retention_policies;

    private SchemaCompatibilityStrategy schema_compatibility_strategy;

    private Boolean schema_validation_enforced;

    private Map<String, DispatchRateImpl> subscriptionDispatchRate;

    private SubscriptionAuthMode subscription_auth_mode;

    private Integer subscription_expiration_time_minutes;

    private Set<String> subscription_types_enabled;

    private Map<String, DispatchRateImpl> topicDispatchRate;

}