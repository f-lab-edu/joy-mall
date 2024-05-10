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

-- Product
insert into PRODUCT(SELLER_ID, NAME, DESCRIPTION, IMAGE_URL, CREATED_DATE, UPDATED_DATE)
VALUES (1, '아이폰 실리콘 케이스', '아이폰 실리콘 케이스 설명', '아이폰 실리콘 케이스 이미지', now(), now());

insert into PRODUCT(SELLER_ID, NAME, DESCRIPTION, IMAGE_URL, CREATED_DATE, UPDATED_DATE)
VALUES (1, '아이폰 가죽 케이스', '아이폰 가죽 케이스 설명', '아이폰 가죽 케이스 이미지', now(), now());

insert into PRODUCT(SELLER_ID, NAME, DESCRIPTION, IMAGE_URL, CREATED_DATE, UPDATED_DATE)
VALUES (1, '아이폰 방탄 케이스', '아이폰 방탄 케이스 설명', '아이폰 방탄 케이스 이미지', now(), now());

-- ProductReviewSummary
insert into PRODUCT_REVIEW_SUMMARY(PRODUCT_ID, AVERAGE_REVIEW_RATING, TOTAL_REVIEW_COUNT, CREATED_DATE, UPDATED_DATE)
VALUES (1, 3, 12, now(), now());

insert into PRODUCT_REVIEW_SUMMARY(PRODUCT_ID, AVERAGE_REVIEW_RATING, TOTAL_REVIEW_COUNT, CREATED_DATE, UPDATED_DATE)
VALUES (1, 3, 12, now(), now());

insert into PRODUCT_REVIEW_SUMMARY(PRODUCT_ID, AVERAGE_REVIEW_RATING, TOTAL_REVIEW_COUNT, CREATED_DATE, UPDATED_DATE)
VALUES (1, 2.6, 10, now(), now());

-- ProductOption

insert into PRODUCT_OPTION(PRODUCT_ID, NAME, PRICE, STOCK_QUANTITY, CREATED_DATE, UPDATED_DATE)
VALUES (1, '아이폰13 pro', 8000, 100, now(), now());

insert into PRODUCT_OPTION(PRODUCT_ID, NAME, PRICE, STOCK_QUANTITY, CREATED_DATE, UPDATED_DATE)
VALUES (1, '아이폰12 pro', 9000, 100, now(), now());

-- Category

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


-- depth 1
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 1, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 1, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 1, now(), now());

-- depth 2 기종
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 26, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 27, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 28, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 29, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 30, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 31, now(), now());

insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 26, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 27, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 28, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 29, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 30, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 31, now(), now());

insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 26, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 27, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 28, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 29, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 30, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 31, now(), now());

-- depth 2 소재
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 32, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 33, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 34, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 35, now(), now());

insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 32, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 33, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 34, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 35, now(), now());

insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 32, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 33, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 34, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 35, now(), now());

-- depth 2 컬러

insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 36, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 37, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 38, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 39, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 40, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 41, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 42, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (1, 43, now(), now());

insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 36, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 37, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 38, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 39, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 40, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 41, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 42, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (2, 43, now(), now());

insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 36, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 37, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 38, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 39, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 40, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 41, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 42, now(), now());
insert into PRODUCT_CATEGORY(PRODUCT_ID, CATEGORY_ID, CREATED_DATE, UPDATED_DATE)
VALUES (3, 43, now(), now());

-- REVIEW

insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 1, '비추;;;', 1, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 1, '별로에요 ㅋㅋ', 1, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 1, '낫배드...', 2, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 1, '우와 가성비 짱!', 4, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 1, '꼭 사세요!!', 5, now(), now());

insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 1, '비추;;;', 1, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 1, '별로에요 ㅋㅋ', 1, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 1, '낫배드...', 2, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 1, '우와 가성비 짱!', 4, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 1, '꼭 사세요!!', 5, now(), now());

insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 2, '비추;;;', 1, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 2, '별로에요 ㅋㅋ', 1, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 2, '낫배드...', 2, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 2, '우와 가성비 짱!', 4, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 2, '꼭 사세요!!', 5, now(), now());

insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 2, '비추;;;', 1, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 2, '별로에요 ㅋㅋ', 1, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 2, '낫배드...', 2, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 2, '우와 가성비 짱!', 4, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 2, '꼭 사세요!!', 5, now(), now());

insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 3, '비추;;;', 1, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 3, '별로에요 ㅋㅋ', 1, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 3, '낫배드...', 2, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 3, '우와 가성비 짱!', 4, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (1, 3, '꼭 사세요!!', 5, now(), now());

insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 3, '비추;;;', 1, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 3, '별로에요 ㅋㅋ', 1, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 3, '낫배드...', 2, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 3, '우와 가성비 짱!', 4, now(), now());
insert into REVIEW(CUSTOMER_ID, PRODUCT_ID, CONTENT, RATING, CREATED_DATE, UPDATED_DATE)
VALUES (2, 3, '꼭 사세요!!', 5, now(), now());