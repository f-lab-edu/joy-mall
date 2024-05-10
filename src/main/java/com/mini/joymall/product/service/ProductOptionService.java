package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.ProductOption;
import com.mini.joymall.product.domain.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductOptionService {
    private final ProductOptionRepository productOptionRepository;

    public ProductOption decreaseStock(Long id, int selectQuantity) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        productOption.decreaseStock(selectQuantity);
        productOptionRepository.save(productOption);
        return productOption;
    }
}
