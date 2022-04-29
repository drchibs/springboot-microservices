package com.chibs.Fraud;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FraudCheckHistoryService.class})
@ExtendWith(SpringExtension.class)
class FraudCheckHistoryServiceTest {
    @MockBean
    private FraudCheckHistoryRepository fraudCheckHistoryRepository;

    @Autowired
    private FraudCheckHistoryService fraudCheckHistoryService;

    /**
     * Method under test: {@link FraudCheckHistoryService#isFraudulentCustomer(Long)}
     */
    @Test
    void testIsFraudulentCustomer() {
        FraudCheckHistory fraudCheckHistory = new FraudCheckHistory();
        fraudCheckHistory.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        fraudCheckHistory.setCustomerId(123L);
        fraudCheckHistory.setId(123L);
        fraudCheckHistory.setIsFraudster(true);
        fraudCheckHistory.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.fraudCheckHistoryRepository.save((FraudCheckHistory) any())).thenReturn(fraudCheckHistory);
        assertFalse(this.fraudCheckHistoryService.isFraudulentCustomer(123L));
        verify(this.fraudCheckHistoryRepository).save((FraudCheckHistory) any());
    }
}

