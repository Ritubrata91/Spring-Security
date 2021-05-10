INSERT INTO users(USERNAME, password, enabled)
	values('user','pass','true');
	
INSERT INTO users(USERNAME, password, enabled)
	values('ADMIN','pass','true');
	
INSERT INTO authorities(USERNAME, authority)
	values('user','ROLE_USER');
	
INSERT INTO authorities(USERNAME, authority)
	values('ADMIN','ROLE_ADMIN');