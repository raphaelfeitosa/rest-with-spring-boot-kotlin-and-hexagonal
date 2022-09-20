CREATE TABLE IF NOT EXISTS client (
  id uuid NOT NULL,
  name VARCHAR NOT NULL,
  gender VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  document_number VARCHAR NOT NULL,
  document_type VARCHAR NOT NULL,
  salary NUMERIC NOT NULL,
  street VARCHAR NOT NULL,
  district VARCHAR NOT NULL,
  city VARCHAR NOT NULL,
  state VARCHAR NOT NULL,
  zip_code VARCHAR NOT NULL,
  number VARCHAR NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
)