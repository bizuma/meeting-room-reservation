-- Customer Initial Query
insert into CUSTOMER (CUSTOMER_ID, NAME) values( -100, 'labs-1');
insert into CUSTOMER (CUSTOMER_ID, NAME) values( -101, 'labs-2');
insert into CUSTOMER (CUSTOMER_ID, NAME) values( -102, 'labs-3');

-- Meeting Room Initial Query
insert into MEETING_ROOM (MEETING_ROOM_ID, NAME) values( -200, '무궁화실');
insert into MEETING_ROOM (MEETING_ROOM_ID, NAME) values( -201, '새마을실');

-- Reservation Initial Query
insert into RESERVATION (RESERVATION_ID, DAY, HOUR, MEETING_ROOM_ID, CUSTOMER_ID) values( -300, '20180901','10', -200, -100);
insert into RESERVATION (RESERVATION_ID, DAY, HOUR, MEETING_ROOM_ID, CUSTOMER_ID) values( -301, '20180902','13', -201, -100);

insert into RESERVATION (RESERVATION_ID, DAY, HOUR, MEETING_ROOM_ID, CUSTOMER_ID) values( -302, '20181001','10', -200, -101);
insert into RESERVATION (RESERVATION_ID, DAY, HOUR, MEETING_ROOM_ID, CUSTOMER_ID) values( -303, '20181002','13', -201, -101);

-- Reservation Admin
insert into RESERVATION_ADMIN (ID, LOGIN_ID, PASSWORD, ROLE) values( -400, 'admin','$2a$10$Coz5MLZjerAONXNoli6GROvInFk0vSZaIrmnXuzbqSJ.snTIHGYrS', 'ROLE_ADMIN');
insert into RESERVATION_ADMIN (ID, LOGIN_ID, PASSWORD, ROLE) values( -401, 'guest','$2a$10$Ya/p3NxIpW9nSCNQs6sFgenMCBKPH2ZO4WJlY4FFCTaJUFOxCgUAy', 'ROLE_GUEST');
