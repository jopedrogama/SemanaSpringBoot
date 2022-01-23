create table delivery_event (
	
	id bigint not null auto_increment,
	delivery_id bigint not null,
	description text not null,
	event_date_time datetime not null,
	
	primary key(id),
	foreign key (delivery_id) references delivery(id)
);