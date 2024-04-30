package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Category;
import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.entity.ProductCategory;
import com.mini.joymall.product.domain.entity.ProductOption;
import com.mini.joymall.product.dto.response.ProductReviewSummaryResponse;
import com.mini.joymall.seller.domain.entity.Seller;
import com.mini.joymall.seller.domain.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductOptionRepository productOptionRepository;

    @Test
    void 상품_리스트_상품명_검색과_페이징_처리() {
        // given
        sellerRepository.deleteAll();
        Seller sellerA = new Seller("a@a.com", "1234", "a", "aStore", "010-1234-5678", LocalDateTime.now(), LocalDateTime.now());
        Seller sellerB = new Seller("b@b.com", "1234", "b", "bStore", "010-5678-5678", LocalDateTime.now(), LocalDateTime.now());

        Product product1 = new Product(null, "아이폰1", "아이폰1", "아이폰");
        Product product2 = new Product(null, "아이폰2", "아이폰2","아이폰");
        Product product3 = new Product(null, "아이폰3", "아이폰3","아이폰");
        Product product4 = new Product(null, "아이폰4", "아이폰4", "아이폰");

        sellerA.addProduct(product1);
        sellerA.addProduct(product2);
        sellerB.addProduct(product3);
        sellerB.addProduct(product4);
        sellerRepository.save(sellerA);
        sellerRepository.save(sellerB);

        String sortBy = "DESC";
        Sort sort = Sort.by(Sort.Direction.fromString(sortBy), "id");
        Pageable pageable = PageRequest.of(1, 2, sort);

        // when
        List<ProductReviewSummaryResponse> products = productRepository.findByNameContainingIgnoreCase("아이폰", pageable.getPageSize(), pageable.getPageNumber());
        Page<ProductReviewSummaryResponse> page = new PageImpl<>(products, pageable, products.size());

        // then
        assertThat(page.getTotalElements()).isEqualTo(4);
        assertThat(page.getNumberOfElements()).isEqualTo(2);
        assertThat(page.getSize()).isEqualTo(2);
        assertThat(page.getNumber()).isEqualTo(1);
        assertThat(page.hasNext()).isFalse();
        assertThat(page.hasPrevious()).isTrue();
    }

    @Test
    void 상품과_함께_카테고리를_저장한다() {
        // given
        sellerRepository.deleteAll();
        Seller sellerA = new Seller("a@a.com", "1234", "a", "aStore", "010-1234-5678", LocalDateTime.now(), LocalDateTime.now());
        Seller sellerB = new Seller("b@b.com", "1234", "b", "bStore", "010-5678-5678", LocalDateTime.now(), LocalDateTime.now());

        Product product1 = new Product(null, "아이폰1", "아이폰1", "아이폰");
        Product product2 = new Product(null, "아이폰2", "아이폰2","아이폰");
        Product product3 = new Product(null, "아이폰3", "아이폰3","아이폰");
        Product product4 = new Product(null, "아이폰4", "아이폰4", "아이폰");

        sellerA.addProduct(product1);
        sellerA.addProduct(product2);
        sellerB.addProduct(product3);
        sellerB.addProduct(product4);
        sellerRepository.save(sellerA);
        sellerRepository.save(sellerB);

        Category category1 = new Category(null, 0, "애플 기기");
        Category category2 = new Category(category1.getId(), 1, "테블릿");
        Category savedCategory1 = categoryRepository.save(category1);
        Category savedCategory2 = categoryRepository.save(category2);

        Product product = new Product(1L, "아이폰", "아이폰", "아이폰", LocalDateTime.now(), LocalDateTime.now());
        product.addCategory(savedCategory1);
        product.addCategory(savedCategory2);
        Product savedProduct = productRepository.save(product);

        Product findProduct = productRepository.findById(savedProduct.getId()).orElse(null);
        Set<ProductCategory> productCategories = findProduct.getProductCategories();
        assertThat(productCategories.size()).isEqualTo(2);
    }

    @Test
    void 상품을_옵션과_함께_정상적으로_출력() {
        // given
        Product product = new Product(1L, "product1", "product1", "");
        Product savedProduct1 = productRepository.save(product);

        ProductOption productOption1 = new ProductOption(savedProduct1.getId(), "option1", 1000, 100);
        ProductOption savedProductOption1 = productOptionRepository.save(productOption1);

        // when
        Product product1 = productRepository.findById(savedProduct1.getId()).orElse(null);
        List<ProductOption> productOptions = product1.getProductOptions().stream().toList();

        // then
        assertThat(productOptions.get(0)).isEqualTo(savedProductOption1);
    }
}