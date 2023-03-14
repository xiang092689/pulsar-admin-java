package io.github.protocol.pulsar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TenantsTest extends PulsarTest {

    @Test
    public void tenantsTest() throws PulsarAdminException {
        String tenantName = RandomUtil.randomString();
        TenantInfo initialTenantInfo = (new TenantInfo.TenantInfoBuilder())
                .adminRoles(new HashSet<>(0))
                .allowedClusters(Set.of(CLUSTER_STANDALONE)).build();
        TenantInfo updatedTenantInfo = (new TenantInfo.TenantInfoBuilder())
                .adminRoles(Set.of("test"))
                .allowedClusters(Set.of("global"))
                .build();
        pulsarAdmin.tenants().createTenant(tenantName, initialTenantInfo);
        Assertions.assertEquals(pulsarAdmin.tenants().getTenants(), List.of(tenantName, "public", "pulsar"));
        Assertions.assertEquals(pulsarAdmin.tenants().getTenantAdmin(tenantName), initialTenantInfo);
        pulsarAdmin.tenants().updateTenant(tenantName, updatedTenantInfo);
        Assertions.assertEquals(pulsarAdmin.tenants().getTenantAdmin(tenantName), updatedTenantInfo);
        pulsarAdmin.tenants().deleteTenant(tenantName, false);
        Assertions.assertEquals(pulsarAdmin.tenants().getTenants(), List.of("public", "pulsar"));
    }

}
