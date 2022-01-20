create table delivery(
	id bigint not null auto_increment,
	customer_id bigint not null, 
	delivery_price decimal (10, 2) not null,
	delivery_status varchar(20) not null,
	order_date datetime not null,
	delivery_date datetime,
	
	-- Embeeded recipient infos
	recipient_name varchar(60) not null,
	recipient_street varchar(255) not null,
	recipient_building_number varchar(255) not null,
	recipient_address_complement varchar(155) not null, 
	recipient_district varchar(30) not null,
	recipient_cep varchar(8) not null,
	recipient_city varchar(255) not null,
	recipient_state varchar(2) not null,
	
	primary key (id),
	foreign key (customer_id) references customer(id)
);