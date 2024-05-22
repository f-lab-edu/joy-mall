package com.mini.joymall.payment.domain.repository;

import com.mini.joymall.payment.domain.entity.KakaoPayHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KakaoPayHistoryRepository extends CrudRepository<KakaoPayHistory, Long> {
    Optional<KakaoPayHistory> findByPartnerOrderId(String partnerOrderId);
}
