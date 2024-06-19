-- seller
insert into seller(email, password, name, store_name, phone_number, created_date, updated_date)
values ('apple@apple.com', '1234', '팀 쿡', 'apple', '010-1111-2222', now(), now());

-- customer

insert into customer(email, password, name, phone_number, created_date, updated_date)
values ('a@a.com', '1234', 'acustomer', '010-1111-2222', now(), now());

insert into customer(email, password, name, phone_number, created_date, updated_date)
values ('b@b.com', '1234', 'bcustomer', '010-1111-3333', now(), now());

-- customer_address
insert into customer_address(customer_id, receipt_name, receipt_phone_number, region, city, town, street, zip_code,
                             detail, created_date, updated_date)
values (1, '저스틴 비버', '010-9999-99999', '대한민국', '서울시', '강남구', '도산대로2길', '001-001', '1301동 101호', now(), now());

insert into customer_address(customer_id, receipt_name, receipt_phone_number, region, city, town, street, zip_code,
                             detail, created_date, updated_date)
values (2, '카일리 제너', '010-8888-8888', '대한민국', '서울시', '강남구', '도산대로3길', '001-001', '1301동 101호', now(), now());

-- review

insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 1, '와 이거 무조건 사셈;;', 5, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 1, '와 이거 무조건 사셈;;', 5, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 2, '와 이거 무조건 사셈;;', 5, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 2, '와 이거 무조건 사셈;;', 5, now(), now());

-- product
insert into product(seller_id, name, description, image_url, created_date, updated_date)
values (1, '아이폰 실리콘 케이스', '아이폰 실리콘 케이스 설명', '아이폰 실리콘 케이스 이미지', now(), now());

insert into product(seller_id, name, description, image_url, created_date, updated_date)
values (1, '아이폰 가죽 케이스', '아이폰 가죽 케이스 설명', '아이폰 가죽 케이스 이미지', now(), now());

insert into product(seller_id, name, description, image_url, created_date, updated_date)
values (1, '아이폰 방탄 케이스', '아이폰 방탄 케이스 설명', '아이폰 방탄 케이스 이미지', now(), now());

-- productreviewsummary
insert into product_review_summary(product_id, average_review_rating, total_review_count, created_date, updated_date)
values (1, 3, 12, now(), now());

insert into product_review_summary(product_id, average_review_rating, total_review_count, created_date, updated_date)
values (1, 3, 12, now(), now());

insert into product_review_summary(product_id, average_review_rating, total_review_count, created_date, updated_date)
values (1, 2.6, 10, now(), now());

-- productoption

insert into product_option(product_id, name, created_date, updated_date)
values (1, '아이폰13 pro', now(), now());

insert into product_option(product_id, name, created_date, updated_date)
values (1, '아이폰12 pro', now(), now());

-- category

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
insert into category(parent_id, depth, name, created_date, updated_date)
values (1, 1, '휴대폰기종', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (1, 1, '소재', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (1, 1, '색상', now(), now());

-- depth 2
insert into category(parent_id, depth, name, created_date, updated_date)
values (23, 2, '아이폰 14 pro max', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (23, 2, '아이폰 14 pro', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (23, 2, '아이폰 14', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (23, 2, '아이폰 13 pro max', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (23, 2, '아이폰 13 pro', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (23, 2, '아이폰 13', now(), now());

insert into category(parent_id, depth, name, created_date, updated_date)
values (24, 2, '가죽', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (24, 2, '젤리/실리콘', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (24, 2, '하드/플라스틱', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (24, 2, '메탈/미러', now(), now());

insert into category(parent_id, depth, name, created_date, updated_date)
values (25, 2, '블랙', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (25, 2, '네이비', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (25, 2, '그레이', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (25, 2, '실버', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (25, 2, '레드', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (25, 2, '오렌지', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (25, 2, '옐로우', now(), now());
insert into category(parent_id, depth, name, created_date, updated_date)
values (25, 2, '다크 그린', now(), now());

-- depth 3

insert into category(parent_id, depth, name, created_date, updated_date)
values (32, 3, '소', now(), now());

-- depth 4

insert into category(parent_id, depth, name, created_date, updated_date)
values (44, 4, '한우', now(), now());


-- depth 1
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 1, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 1, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 1, now(), now());

-- depth 2 기종
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 26, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 27, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 28, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 29, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 30, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 31, now(), now());

insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 26, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 27, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 28, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 29, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 30, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 31, now(), now());

insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 26, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 27, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 28, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 29, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 30, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 31, now(), now());

-- depth 2 소재
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 32, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 33, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 34, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 35, now(), now());

insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 32, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 33, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 34, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 35, now(), now());

insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 32, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 33, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 34, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 35, now(), now());

-- depth 2 컬러

insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 36, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 37, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 38, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 39, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 40, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 41, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 42, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (1, 43, now(), now());

insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 36, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 37, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 38, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 39, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 40, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 41, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 42, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (2, 43, now(), now());

insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 36, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 37, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 38, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 39, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 40, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 41, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 42, now(), now());
insert into product_category(product_id, category_id, created_date, updated_date)
values (3, 43, now(), now());

-- review

insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 1, '비추;;;', 1, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 1, '별로에요 ㅋㅋ', 1, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 1, '낫배드...', 2, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 1, '우와 가성비 짱!', 4, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 1, '꼭 사세요!!', 5, now(), now());

insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 1, '비추;;;', 1, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 1, '별로에요 ㅋㅋ', 1, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 1, '낫배드...', 2, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 1, '우와 가성비 짱!', 4, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 1, '꼭 사세요!!', 5, now(), now());

insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 2, '비추;;;', 1, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 2, '별로에요 ㅋㅋ', 1, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 2, '낫배드...', 2, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 2, '우와 가성비 짱!', 4, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 2, '꼭 사세요!!', 5, now(), now());

insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 2, '비추;;;', 1, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 2, '별로에요 ㅋㅋ', 1, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 2, '낫배드...', 2, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 2, '우와 가성비 짱!', 4, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 2, '꼭 사세요!!', 5, now(), now());

insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 3, '비추;;;', 1, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 3, '별로에요 ㅋㅋ', 1, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 3, '낫배드...', 2, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 3, '우와 가성비 짱!', 4, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (1, 3, '꼭 사세요!!', 5, now(), now());

insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 3, '비추;;;', 1, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 3, '별로에요 ㅋㅋ', 1, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 3, '낫배드...', 2, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 3, '우와 가성비 짱!', 4, now(), now());
insert into review(customer_id, product_id, content, rating, created_date, updated_date)
values (2, 3, '꼭 사세요!!', 5, now(), now());

-- sales_group
insert into sales_group(created_date, updated_date)
values (now(), now());
insert into sales_group(created_date, updated_date)
values (now(), now());
insert into sales_group(created_date, updated_date)
values (now(), now());

-- sales_product
insert into sales_product (sales_group_id, product_option_id, sales_price, sales_stock, sales_status, created_date,
                           updated_date)
values (1, 1, 10000, 100, 'on_sales', now(), now());
insert into sales_product (sales_group_id, product_option_id, sales_price, sales_stock, sales_status, created_date,
                           updated_date)
values (1, 2, 15000, 200, 'pending', now(), now());

-- order
insert into `order`(customer_address_id, order_date, created_date, updated_date)
values (1, now(), now(), now());

-- order_history
insert into order_history(order_id, order_status, created_date)
values ( 1,  'pending', now());

-- order_item
insert into `order_item`(order_id, sales_product_id, quantity, price_per_item, created_date, updated_date)
values (1, 1, 1, 10000, now(), now());

insert into `order_item`(order_id, sales_product_id, quantity, price_per_item, created_date, updated_date)
values (1, 2, 2, 15000, now(), now());