CREATE TABLE public.app_user (
                                 id bigserial NOT NULL,
                                 idate timestamp NOT NULL,
                                 udate timestamp NULL,
                                 status int4 NOT NULL DEFAULT 0,
                                 name varchar(140) NOT NULL,
                                 password varchar(26) NULL,
                                 UNIQUE(name),
                                 CONSTRAINT pk_app_user PRIMARY KEY (id)
);