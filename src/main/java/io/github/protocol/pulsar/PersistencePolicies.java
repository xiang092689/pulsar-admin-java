package io.github.protocol.pulsar;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersistencePolicies {

    private int bookkeeperEnsemble;

    private int bookkeeperWriteQuorum;

    private int bookkeeperAckQuorum;

    private double managedLedgerMaxMarkDeleteRate;

}
