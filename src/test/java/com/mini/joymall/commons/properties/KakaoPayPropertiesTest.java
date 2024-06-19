package com.mini.joymall.commons.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class KakaoPayPropertiesTest {

    @Autowired
    private KakaoPayProperties kakaoPayProperties;

    @Test
    void 설정파일_정보를_가져오는데_성공() {
        assertThat(kakaoPayProperties.getHost()).isNotNull();
        assertThat(kakaoPayProperties.getCid()).isNotNull();
        assertThat(kakaoPayProperties.getSecretKey()).isNotNull();
    }
}