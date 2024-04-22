insert into category(depth, name, created_date, updated_date)
values (0, '휴대폰/통신', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '자동차 용품', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '시공/리모델링', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '여성패션', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '패션잡화', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '디지털 용품', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '신발', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '홈인테리어', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '남성패션', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '가전', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '가구', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '장난감/놀이', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '스포츠/레저', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '뷰티/건강', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '문구/사무용품', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '가방/소품', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '반려동물', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '유아용품', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '공구/도구', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '쥬얼리/시계', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '속옷/잠옷', now(), now());
insert into category(depth, name, created_date, updated_date)
values (0, '홈캠/도어록', now(), now());

-- depth 1
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (1, 1, '휴대폰기종', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (1, 1, '소재', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (1, 1, '색상', now(), now());

-- depth 2
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (23, 2, '아이폰 14 Pro Max', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (23, 2, '아이폰 14 Pro', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (23, 2, '아이폰 14', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (23, 2, '아이폰 13 Pro Max', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (23, 2, '아이폰 13 Pro', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (23, 2, '아이폰 13', now(), now());

insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (24, 2, '가죽', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (24, 2, '젤리/실리콘', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (24, 2, '하드/플라스틱', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (24, 2, '메탈/미러', now(), now());

insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (25, 2, '블랙', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (25, 2, '네이비', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (25, 2, '그레이', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (25, 2, '실버', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (25, 2, '레드', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (25, 2, '오렌지', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (25, 2, '옐로우', now(), now());
insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (25, 2, '다크 그린', now(), now());

-- depth 3

insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (32, 3, '소', now(), now());

-- depth 4

insert into CATEGORY(parent_id, depth, name, created_date, updated_date)
values (44, 4, '한우', now(), now());

-- seller
insert into seller(email, password, name, store_name, phone_number, created_date, updated_date)
values ('apple@apple.com', '1234', '팀 쿡', 'apple', '010-1111-2222', now(), now());

-- Customer

insert into CUSTOMER(EMAIL, PASSWORD, NAME, PHONE_NUMBER, CREATED_DATE, UPDATED_DATE)
VALUES ('a@a.com', '1234', 'aCustomer', '010-1111-2222', now(), now());

insert into CUSTOMER(EMAIL, PASSWORD, NAME, PHONE_NUMBER, CREATED_DATE, UPDATED_DATE)
VALUES ('b@b.com', '1234', 'bCustomer', '010-1111-3333', now(), now());

-- Review

insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 1, '와 이거 무조건 사셈;;', 5, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 1, '와 이거 무조건 사셈;;', 5, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 2, '와 이거 무조건 사셈;;', 5, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 2, '와 이거 무조건 사셈;;', 5, now(), now());

-- ProductOption

insert into PRODUCT_OPTION(PRODUCT_ID, NAME, PRICE, STOCK_QUANTITY, CREATED_DATE, UPDATED_DATE)
VALUES (1, '아이폰13 pro', 8000, 100, now(), now());

insert into PRODUCT_OPTION(PRODUCT_ID, NAME, PRICE, STOCK_QUANTITY, CREATED_DATE, UPDATED_DATE)
VALUES (1, '아이폰12 pro', 9000, 100, now(), now());
