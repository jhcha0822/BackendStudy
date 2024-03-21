create table product(
product_idx number primary key
, product_name varchar2(30)
, price number DEFAULT 0
, brand varchar2(25)
, filename varchar2(17)
, subcategory_idx number
, constraint fk_subcategory_product foreign key(subcategory_idx)
references subcategory(subcategory_idx)
);
CREATE SEQUENCE seq_topcategory
INCREMENT BY 1
START WITH 1;
CREATE SEQUENCE seq_subcategory
INCREMENT BY 1
START WITH 1;
CREATE SEQUENCE seq_product
INCREMENT BY 1
START WITH 1;

INSERT INTO topcategory(TOPCATEGORY_IDX, TOPNAME) values(seq_topcategory.nextval,'상의');
INSERT INTO topcategory(TOPCATEGORY_IDX, TOPNAME) values(seq_topcategory.nextval,'하의');
INSERT INTO topcategory(TOPCATEGORY_IDX, TOPNAME) values(seq_topcategory.nextval,'액세서리');

SELECT * FROM TOPCATEGORY;

INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '가디건', 1);
INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '니트', 1);
INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '점퍼', 1);
INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '청바지', 2);
INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '스커트', 2);
INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '반지', 3);
INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '목걸이', 3);

SELECT * FROM SUBCATEGORY;

SELECT * FROM PRODUCT;
insert into product(product_idx, product_name, price, brand, filename, subcategory_idx)values(seq_product.nextval, 'test', 30000, 'test', '1710405218371.jpg', 4);
DELETE product; -- DBeaver는 오토커밋. (JBDC가 오토커밋, DBeaver는 JBDC 기반)

------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 공지 게시판 DDL
CREATE TABLE notice(
	notice_idx NUMBER PRIMARY KEY
	, title varchar2(100)
	, writer varchar2(20)
	, content clob
	, regdate DATE DEFAULT sysdate
	, hit NUMBER DEFAULT 0
);

CREATE SEQUENCE seq_notice
INCREMENT BY 1
START WITH 1;

SELECT * FROM notice;

-- 24.03.18
CREATE TABLE gallery(
	gallery_idx NUMBER PRIMARY KEY
	, title varchar2(100)
	, writer varchar2(40)
	, content clob
	, regdate DATE DEFAULT sysdate
	, hit NUMBER DEFAULT 0
	, filename varchar2(17)
);

CREATE SEQUENCE seq_gallery
INCREMENT BY 1
START WITH 1;

SELECT * FROM gallery;

DELETE gallery;

-- 24.03.20
CREATE TABLE member(
	member_idx NUMBER PRIMARY KEY
	, id varchar2(20)
	, pass varchar2(64)
	, name varchar2(30)
	, email varchar2(50)
	, receive NUMBER
	, regdate DATE DEFAULT sysdate
);

CREATE SEQUENCE seq_member
INCREMENT BY 1
START WITH 1;

CREATE TABLE skill(
	skill_idx NUMBER PRIMARY KEY
	, skill_name varchar2(20)
	, member_idx NUMBER
	, CONSTRAINT fk_member_skill FOREIGN KEY(member_idx)
	REFERENCES member(member_idx)
);

CREATE SEQUENCE seq_skill
INCREMENT BY 1
START WITH 1;

SELECT * FROM MEMBER;

DROP TABLE skill;
DROP TABLE MEMBER;

create table member(
member_idx number primary key
 , id varchar2(20)
 , pass varchar2(64)
 , name varchar2(30)
 , email varchar2(50)
 , receive number
 , regdate  date  default sysdate
);

create  table  skill(
   skill_idx  number  primary key 
 , skill_name varchar2(20) 
 , member_idx  number 
 , constraint  fk_member_skill foreign key(member_idx)
   references  member(member_idx)
);

SELECT * FROM MEMBER;