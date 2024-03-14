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
SELECT * FROM PRODUCT;
