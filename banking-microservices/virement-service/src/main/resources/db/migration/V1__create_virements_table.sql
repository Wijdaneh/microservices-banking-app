CREATE TABLE virements (
    id BIGSERIAL PRIMARY KEY,
    client_id BIGINT NOT NULL,
    compte_source VARCHAR(34) NOT NULL,
    compte_destination VARCHAR(34) NOT NULL,
    montant DECIMAL(15,2) NOT NULL,
    motif TEXT NOT NULL,
    type VARCHAR(20) NOT NULL,
    statut VARCHAR(20) NOT NULL,
    date_execution TIMESTAMP,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_virements_client_id ON virements(client_id);
CREATE INDEX idx_virements_statut ON virements(statut);
