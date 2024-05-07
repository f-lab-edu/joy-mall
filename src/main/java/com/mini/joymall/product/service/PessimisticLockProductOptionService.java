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
public class PessimisticLockProductOptionService {
    private final ProductOptionRepository productOptionRepository;

    public ProductOption decreaseStock(Long id, int selectQuantity) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        int updatedStock = productOption.decreaseStock(selectQuantity);
        productOptionRepository.updateStockQuantityById(id, updatedStock);
        return productOption;
    }
}
