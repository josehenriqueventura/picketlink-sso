CREATE SEQUENCE SEQ_USERS
  	INCREMENT 1
  	MINVALUE 1
  	MAXVALUE 9223372036854775807
  	START 1
 	CACHE 1;

CREATE TABLE USERS(
  	id integer NOT NULL DEFAULT nextval('SEQ_USERS'::regclass),
 	name character varying(100) NOT NULL,
	email character varying(100) NOT NULL,	
	password character varying(20) NOT NULL,
  	CONSTRAINT users_pkey PRIMARY KEY (id )
);

CREATE UNIQUE INDEX USERS_IDX ON USERS (name);

CREATE SEQUENCE SEQ_MODULE_PERMISSION
  	INCREMENT 1
  	MINVALUE 1
  	MAXVALUE 9223372036854775807
  	START 1
 	CACHE 1;

CREATE TABLE MODULE_PERMISSION(
  	id integer NOT NULL DEFAULT nextval('SEQ_MODULE_PERMISSION'::regclass),
 	permission character varying(100) NOT NULL,
 	CONSTRAINT ROLE_PK PRIMARY KEY (id)
);

CREATE UNIQUE INDEX MODULE_PERMISSION_IDX ON MODULE_PERMISSION (permission);

CREATE SEQUENCE SEQ_USERS_PERMISSIONS
  	INCREMENT 1
  	MINVALUE 1
  	MAXVALUE 9223372036854775807
  	START 1
 	CACHE 1;

CREATE TABLE USERS_PERMISSIONS(
	id integer NOT NULL DEFAULT nextval('SEQ_USERS_PERMISSIONS'::regclass),
	users_id integer NOT NULL,
	module_permissions_id integer NOT NULL,
	CONSTRAINT USERS_PK PRIMARY KEY (id),
	CONSTRAINT USERS_PERMISSIONS_USERS_FK FOREIGN KEY (users_id)
     	REFERENCES USERS (id) MATCH SIMPLE
      	ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT USERS_PERMISSIONS_MDLP_FK FOREIGN KEY (module_permissions_id)
     	REFERENCES MODULE_PERMISSION (id) MATCH SIMPLE
      	ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO USERS (name,email,password) VALUES ('user1','user1@user.com','123');
INSERT INTO USERS (name,email,password) VALUES ('user2','user2@user.com','123');
INSERT INTO USERS (name,email,password) VALUES ('user3','user3@user.com','123');

INSERT INTO MODULE_PERMISSION (permission) VALUES ('PICKETLINK-SSO-IDP');
INSERT INTO MODULE_PERMISSION (permission) VALUES ('PICKETLINK-SSO-ONE');
INSERT INTO MODULE_PERMISSION (permission) VALUES ('PICKETLINK-SSO-TWO');

INSERT INTO USERS_PERMISSIONS (users_id,module_permissions_id) VALUES (1,1);
INSERT INTO USERS_PERMISSIONS (users_id,module_permissions_id) VALUES (1,2);
INSERT INTO USERS_PERMISSIONS (users_id,module_permissions_id) VALUES (1,3);

INSERT INTO USERS_PERMISSIONS (users_id,module_permissions_id) VALUES (2,1);
INSERT INTO USERS_PERMISSIONS (users_id,module_permissions_id) VALUES (2,2);

INSERT INTO USERS_PERMISSIONS (users_id,module_permissions_id) VALUES (3,1);


/**

Sql utilizado pelo servidor para recuperar as permissões de ususario :

select permission, 'Roles' from MODULE_PERMISSION mdl, USERS_PERMISSIONS up, USERS u where u.id = up.users_id and up.module_permissions_id = mdl.id and u.name = 'user1';

*/



