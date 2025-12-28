CREATE TABLE beneficiaires (
    id BIGSERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    rib VARCHAR(27) NOT NULL UNIQUE,
    type VARCHAR(20) NOT NULL,
    client_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_beneficiaires_client_id ON beneficiaires(client_id);
CREATE INDEX idx_beneficiaires_rib ON beneficiaires(rib);
