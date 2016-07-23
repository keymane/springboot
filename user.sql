-- Table: accounts.users

-- DROP TABLE accounts.users;

CREATE TABLE accounts.users
(
  id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
  national_id integer NOT NULL,
  email character varying(128),
  first_name character varying(64),
  last_name character varying(64),
  phone_number character varying(64) NOT NULL,
  encrypted_password character varying(128),
  salt character varying(64),
  last_login timestamp without time zone,
  created_at timestamp without time zone NOT NULL DEFAULT ('now'::text)::timestamp without time zone,
  updated_at timestamp without time zone,
  is_deleted boolean NOT NULL DEFAULT false,
  CONSTRAINT "PK_USERS" PRIMARY KEY (id),
  CONSTRAINT "UNIQUE_USER_EMAIL" UNIQUE (email),
  CONSTRAINT "UNIQUE_USER_PHONE_NUMBER" UNIQUE (phone_number)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE accounts.users
  OWNER TO "bimapay-dbadmin";

-- Index: accounts.idx_users_email

-- DROP INDEX accounts.idx_users_email;

CREATE INDEX idx_users_email
  ON accounts.users
  USING btree
  (email COLLATE pg_catalog."default");

-- Index: accounts.idx_users_encrypted_password

-- DROP INDEX accounts.idx_users_encrypted_password;

CREATE INDEX idx_users_encrypted_password
  ON accounts.users
  USING btree
  (encrypted_password COLLATE pg_catalog."default");

-- Index: accounts.idx_users_id

-- DROP INDEX accounts.idx_users_id;

CREATE INDEX idx_users_id
  ON accounts.users
  USING btree
  (id);

