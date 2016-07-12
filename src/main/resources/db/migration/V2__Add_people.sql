-- sql script with placeholder value to be injected via Flyway properties. Insert persons
insert into PERSON (ID, NAME) values (1, '${name}');
insert into PERSON (ID, NAME) values (2, 'Mr. Foo');
insert into PERSON (ID, NAME) values (3, 'Ms. Bar');