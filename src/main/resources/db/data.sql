insert into category(depth, name, created_date, updated_date) values (0, '휴대폰/통신', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '자동차 용품', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '시공/리모델링', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '여성패션', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '패션잡화', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '디지털 용품', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '신발', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '홈인테리어', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '남성패션', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '가전', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '가구', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '장난감/놀이', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '스포츠/레저', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '뷰티/건강', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '문구/사무용품', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '가방/소품', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '반려동물', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '유아용품', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '공구/도구', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '쥬얼리/시계', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '속옷/잠옷', now(), now());
insert into category(depth, name, created_date, updated_date) values (0, '홈캠/도어록', now(), now());

-- seller
insert into seller(email, password, name, store_name, phone_number, created_date, updated_date)
values ('apple@apple.com', '1234', '팀 쿡', 'apple', '010-1111-2222', now(), now());

-- product
insert into product(seller_id, name, description, price, stock_quantity, image_url, created_date, updated_date)
values (1, '아이폰 실리콘 컬러 휴대폰 케이스', '[설명] 아이폰 실리콘 컬러 휴대폰 케이스', 8000, 100, '', now(), now());

insert into product(seller_id, name, description, price, stock_quantity, image_url, created_date, updated_date)
values (1, '아이폰 반투명 풀 커버 케이스', '[설명] 아이폰 반투명 풀 커버 케이스', 9400, 100, '', now(), now());

insert into product(seller_id, name, description, price, stock_quantity, image_url, created_date, updated_date)
values (1, 'iphone 실리콘 케이스', '[설명] iphone 실리콘 케이스', 5500, 100, '', now(), now());