package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Category;
import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.entity.ProductCategory;
import com.mini.joymall.product.domain.entity.ProductOption;
import com.mini.joymall.seller.domain.entity.Seller;
import com.mini.joymall.seller.domain.repository.SellerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Test
    void 상품_리스트_상품명_검색과_페이징_처리() {
        // given
        Seller sellerA = new Seller("a@a.com", "1234", "a", "aStore", "010-1234-5678", LocalDateTime.now(), LocalDateTime.now());
        Seller sellerB = new Seller("b@b.com", "1234", "b", "bStore", "010-5678-5678", LocalDateTime.now(), LocalDateTime.now());

        Product product1 = new Product(null, "맥북1", "맥북1", "맥북");
        Product product2 = new Product(null, "맥북2", "맥북2","맥북");
        Product product3 = new Product(null, "맥북3", "맥북3","맥북");
        Product product4 = new Product(null, "맥북4", "맥북4", "맥북");

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
        Page<Product> products = productRepository.findByNameContainingIgnoreCase("맥북", pageable);

        // then
        assertThat(products.getTotalElements()).isEqualTo(4);
        assertThat(products.getNumberOfElements()).isEqualTo(2);
        assertThat(products.getContent().size()).isEqualTo(2);
        assertThat(products.getNumber()).isEqualTo(1);
        assertThat(products.hasNext()).isFalse();
        assertThat(products.hasPrevious()).isTrue();
    }

    @Test
    void 상품과_함께_카테고리를_저장한다() {
        // given
        sellerRepository.deleteAll();
        Seller sellerA = new Seller("a@a.com", "1234", "a", "aStore", "010-1234-5678", LocalDateTime.now(), LocalDateTime.now());
        Seller sellerB = new Seller("b@b.com", "1234", "b", "bStore", "010-5678-5678", LocalDateTime.now(), LocalDateTime.now());

        Product product1 = new Product(null, "맥북1", "맥북1", "맥북");
        Product product2 = new Product(null, "맥북2", "맥북2","맥북");
        Product product3 = new Product(null, "맥북3", "맥북3","맥북");
        Product product4 = new Product(null, "맥북4", "맥북4", "맥북");

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

        Product product = new Product(1L, "맥북", "맥북", "맥북");
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
        Product savedProduct1 = productRepository.save(new Product(1L, "fruitProduct", "fruitProduct", ""));
        ProductOption savedProductOption1 = productOptionRepository.save(new ProductOption(savedProduct1.getId(), "option1", 1000, 100));

        // when
        Product findProduct = productRepository.findById(savedProduct1.getId()).orElseThrow(NoSuchElementException::new);
        List<ProductOption> productOptions = findProduct.getProductOptions().stream().toList();

        // then
        assertThat(productOptions.get(0).getId()).isEqualTo(savedProductOption1.getId());
        assertThat(productOptions.get(0).getProductId()).isEqualTo(savedProductOption1.getProductId());
    }
}