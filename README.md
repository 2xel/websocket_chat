# websocket_chat

--BASIC DB SETTING--

root-context
id, password setting


--BASIC TABLE EXAMPLE--

CREATE TABLE CHATMEMBER(
  USER_ID varchar2(50),
  password varchar2(50)
);
CREATE TABLE ROLES(
  USER_ID varchar2(50),
  ROLE_NAME varchar2(50)
);

INSERT INTO CHATMEMBER VALUES('jaemin','123');
INSERT INTO ROLES VALUES('jaemin', 'ROLE_USER');
