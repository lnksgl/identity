create table users (
    id int8 not null GENERATED ALWAYS AS IDENTITY,
    username varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
	scanUser varchar(255),
	scanDocument varchar(255),
    primary key (id)
);

create table groups (
    id int8 not null GENERATED ALWAYS AS IDENTITY,
    name varchar(255) not null,
    content text not null,
    average varchar(255) default '0',
    primary key (id)
);

create table enquiry (
	id int8 not null GENERATED ALWAYS AS IDENTITY,
	idUsers int8 not null,
	idGroups int8 not null,
	createdOn timestamp,
	constraint enquiry_users_id_fk FOREIGN KEY (idUsers) references users (id),
	constraint enquiry_groups_id_fk FOREIGN KEY (idGroups) references groups (id),
    primary key (id)
);
