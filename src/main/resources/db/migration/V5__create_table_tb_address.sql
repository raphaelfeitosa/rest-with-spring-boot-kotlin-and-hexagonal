CREATE TABLE IF NOT EXISTS tb_address (
  id uuid NOT NULL,
  street VARCHAR NOT NULL,
  district VARCHAR NOT NULL,
  city VARCHAR NOT NULL,
  state VARCHAR NOT NULL,
  zip_code VARCHAR NOT NULL,
  number VARCHAR NOT NULL,
  client_id uuid,
        PRIMARY KEY (id),
        CONSTRAINT fk_client_address
        FOREIGN KEY (client_id)
        REFERENCES tb_clients(id)
)