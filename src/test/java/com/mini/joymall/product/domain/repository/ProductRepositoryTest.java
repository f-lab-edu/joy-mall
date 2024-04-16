package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Category;
import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.entity.ProductCategory;
import com.mini.joymall.seller.domain.entity.Seller;
import com.mini.joymall.seller.domain.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Test
    @Transactional
    void 상품조회시_판매자정보를_가져온다() {
        // given
        sellerRepository.deleteAll();
        Seller sellerA = new Seller(null, "a@a.com", "1234", "a", "aStore", "010-1234-5678", LocalDateTime.now(), LocalDateTime.now());
        Seller sellerB = new Seller(null, "b@b.com", "1234", "b", "bStore", "010-5678-5678", LocalDateTime.now(), LocalDateTime.now());

        Product product1 = new Product("아이폰1", "아이폰1", 100.0, 100, "아이폰");
        Product product2 = new Product("아이폰2", "아이폰2", 200.0, 100, "아이폰");
        Product product3 = new Product("아이폰3", "아이폰3", 300.0, 100, "아이폰");
        Product product4 = new Product("아이폰4", "아이폰4", 400.0, 100, "아이폰");

        sellerA.addProduct(product1);
        sellerA.addProduct(product2);
        sellerB.addProduct(product3);
        sellerB.addProduct(product4);
        sellerRepository.save(sellerA);
        sellerRepository.save(sellerB);

        // when
        List<Product> products = productRepository.findAllWithOrder();

        // then
        String emailOfSellerA = products.get(0).getSeller().getEmail();
        String emailOfSellerB = products.get(2).getSeller().getEmail();

        assertThat(emailOfSellerA).isEqualTo("a@a.com");
        assertThat(emailOfSellerB).isEqualTo("b@b.com");

        for (Product product : products) {
            System.out.println("product = " + product);
        }
    }

    @Test
    void 상품_리스트_상품명_검색과_페이징_처리() {
        // given
        sellerRepository.deleteAll();
        Seller sellerA = new Seller(null, "a@a.com", "1234", "a", "aStore", "010-1234-5678", LocalDateTime.now(), LocalDateTime.now());
        Seller sellerB = new Seller(null, "b@b.com", "1234", "b", "bStore", "010-5678-5678", LocalDateTime.now(), LocalDateTime.now());

        Product product1 = new Product("아이폰1", "아이폰1", 100.0, 100, "아이폰");
        Product product2 = new Product("아이폰2", "아이폰2", 200.0, 100, "아이폰");
        Product product3 = new Product("아이폰3", "아이폰3", 300.0, 100, "아이폰");
        Product product4 = new Product("아이폰4", "아이폰4", 400.0, 100, "아이폰");

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
        Page<Product> products = productRepository.findByNameContainingIgnoreCase("아이폰", pageable);

        // then
        assertThat(products.getTotalElements()).isEqualTo(4);
        assertThat(products.getNumberOfElements()).isEqualTo(2);
        assertThat(products.getSize()).isEqualTo(2);
        assertThat(products.getNumber()).isEqualTo(1);
        assertThat(products.hasNext()).isFalse();
        assertThat(products.hasPrevious()).isTrue();
    }

    @Test
    @Transactional
    void 상품과_함께_카테고리를_저장한다() {
        // given
        sellerRepository.deleteAll();
        Seller sellerA = new Seller(null, "a@a.com", "1234", "a", "aStore", "010-1234-5678", LocalDateTime.now(), LocalDateTime.now());
        Seller sellerB = new Seller(null, "b@b.com", "1234", "b", "bStore", "010-5678-5678", LocalDateTime.now(), LocalDateTime.now());

        Product product1 = new Product("아이폰1", "아이폰1", 100.0, 100, "아이폰");
        Product product2 = new Product("아이폰2", "아이폰2", 200.0, 100, "아이폰");
        Product product3 = new Product("아이폰3", "아이폰3", 300.0, 100, "아이폰");
        Product product4 = new Product("아이폰4", "아이폰4", 400.0, 100, "아이폰");

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

        Product product = new Product(null, 1L, "아이폰", "아이폰", 100.0, 100, "아이폰", LocalDateTime.now(), LocalDateTime.now());
        product.addCategory(savedCategory1);
        product.addCategory(savedCategory2);
        productRepository.save(product);

        List<Product> products = productRepository.findAll();
        Set<ProductCategory> productCategories = products.get(products.size() - 1).getProductCategories();
        assertThat(productCategories.size()).isEqualTo(2);
    }
}