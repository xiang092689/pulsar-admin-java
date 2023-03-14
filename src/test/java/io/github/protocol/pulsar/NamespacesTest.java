package io.github.protocol.pulsar;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NamespacesTest extends PulsarTest{

    private String tenant = RandomUtil.randomString();

    private String namespace = RandomUtil.randomString();

    @BeforeAll
    public void initTestTenant() throws PulsarAdminException {
        TenantInfo initialTenantInfo = (new TenantInfo.TenantInfoBuilder())
                .adminRoles(new HashSet<>(0))
                .allowedClusters(Set.of(CLUSTER_STANDALONE)).build();
        pulsarAdmin.tenants().createTenant(tenant, initialTenantInfo);
    }

    @Test
    public void namespaceTest() throws PulsarAdminException {
        Set<AuthAction> fullAuthenticated = Set.of(
                AuthAction.consume,
                AuthAction.produce,
                AuthAction.functions,
                AuthAction.packages,
                AuthAction.sinks,
                AuthAction.sources);
        Policies policies = Policies.builder()
                .auth_policies(AuthPolicies.builder()
                        .namespaceAuthentication(Map.of(RandomUtil.randomString(), fullAuthenticated))
                        .subscriptionAuthentication(Map.of(RandomUtil.randomString(), Set.of(RandomUtil.randomString())))
                        .topicAuthentication(Map.of(RandomUtil.randomString(), Map.of(RandomUtil.randomString(), fullAuthenticated)))
                        .build())
                .autoSubscriptionCreationOverride(
                        AutoSubscriptionCreationOverride.builder().allowAutoSubscriptionCreation(true).build())
                .autoTopicCreationOverride(
                        AutoTopicCreationOverride.builder()
                                .allowAutoTopicCreation(true)
                                .defaultNumPartitions(RandomUtil.randomInt())
                                .topicType(RandomUtil.randomString()).build())
                .backlog_quota_map(Map.of(
                        BacklogQuota.BacklogQuotaType.message_age,
                        BacklogQuota.builder()
                                .policy(BacklogQuota.RetentionPolicy.consumer_backlog_eviction)
                                .limitTime(RandomUtil.randomInt())
                                .limitSize(RandomUtil.randomLong())
                                .limit(RandomUtil.randomLong())
                                .build(),
                        BacklogQuota.BacklogQuotaType.destination_storage,
                        BacklogQuota.builder()
                                .policy(BacklogQuota.RetentionPolicy.producer_request_hold)
                                .limitTime(RandomUtil.randomInt())
                                .limitSize(RandomUtil.randomLong())
                                .limit(RandomUtil.randomLong())
                                .build()))
                .bundles(BundlesData.builder()
                        .numBundles(RandomUtil.randomInt())
                        .boundaries(List.of(RandomUtil.randomString()))
                        .build())
                .clusterDispatchRate(Map.of(CLUSTER_STANDALONE, DispatchRateImpl.builder()
                        .dispatchThrottlingRateInByte(RandomUtil.randomLong())
                        .dispatchThrottlingRateInMsg(RandomUtil.randomInt())
                        .ratePeriodInSecond(RandomUtil.randomInt())
                        .relativeToPublishRate(RandomUtil.randomBoolean())
                        .build()))
                .clusterSubscribeRate(Map.of(RandomUtil.randomString(), SubscribeRate.builder()
                        .ratePeriodInSecond(RandomUtil.randomInt())
                        .subscribeThrottlingRatePerConsumer(RandomUtil.randomInt())
                        .build()))
                .compaction_threshold(RandomUtil.randomLong())
                .deduplicationEnabled(RandomUtil.randomBoolean())
                .deduplicationSnapshotIntervalSeconds(RandomUtil.randomInt())
                .delayed_delivery_policies(DelayedDeliveryPolicies.builder()
                        .active(RandomUtil.randomBoolean())
                        .tickTime(RandomUtil.randomLong())
                        .build())
                .deleted(false)
                .encryption_required(RandomUtil.randomBoolean())
                .inactive_topic_policies(InactiveTopicPolicies.builder()
                        .inactiveTopicDeleteMode(InactiveTopicDeleteMode.delete_when_no_subscriptions)
                        .deleteWhileInactive(RandomUtil.randomBoolean())
                        .maxInactiveDurationSeconds(RandomUtil.randomInt())
                        .build())
                .is_allow_auto_update_schema(RandomUtil.randomBoolean())
                .latency_stats_sample_rate(Map.of(RandomUtil.randomString(), RandomUtil.randomInt()))
                .max_consumers_per_subscription(RandomUtil.randomInt())
                .max_consumers_per_topic(RandomUtil.randomInt())
                .max_producers_per_topic(RandomUtil.randomInt())
                .max_topics_per_namespace(RandomUtil.randomInt())
                .max_unacked_messages_per_consumer(RandomUtil.randomInt())
                .max_unacked_messages_per_subscription(RandomUtil.randomInt())
                .message_ttl_in_seconds(RandomUtil.randomInt())
                .offload_deletion_lag_ms(RandomUtil.randomLong())
                .offload_policies(OffloadPolicies.builder()
                        .fileSystemProfilePath(RandomUtil.randomString()).build())
                .build();
        pulsarAdmin.namespaces().createNamespace(tenant, namespace, policies);
    }

}
