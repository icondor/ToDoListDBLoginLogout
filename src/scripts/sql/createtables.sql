CREATE TABLE users
(
  id bigserial NOT NULL,
  username character(40),
  userpassword character(40),
  CONSTRAINT idpk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tasklistionel
(
  id bigserial NOT NULL,
  taskname character(50),
  isdone boolean,
  fkiduser bigint NOT NULL,
  CONSTRAINT pktasklist PRIMARY KEY (id),
  CONSTRAINT fkuserconstr FOREIGN KEY (fkiduser)
  REFERENCES users (id) MATCH FULL
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);

INSERT INTO users(
 username, userpassword)
VALUES ('ion','password1');
INSERT INTO users(
  username, userpassword)
VALUES ('maria','password1');
