package com.distribution.moneydistribution.domain.transaction;


import com.distribution.moneydistribution.domain.user.Role;
import com.distribution.moneydistribution.domain.user.User;
import com.distribution.moneydistribution.domain.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Transactional
//@Slf4j
class FinancialTransactionRepositoryTest {

    @Autowired
    FinancialTransactionRepository financialTransactionRepository;

    @Autowired
    UserRepository userRepository;


    private final static Logger LOGGER = LoggerFactory.getLogger(FinancialTransactionRepositoryTest.class);


    @Test
    public void save(){
        // given
        FinancialTransaction financialTransaction = FinancialTransaction.builder()
                .transactionId("QWERTY240926")
                .amount(BigDecimal.valueOf(300000))
                .currency("KRW")
                .status("FT0001")
                .paymentDate(LocalDateTime.now())
                .customerPhone("010-1111-1111")
                .cardLastFourDigits("3679")
                .authorizationCode("5196159191951591223")
                .build();

        // when
        FinancialTransaction saveFinancialTransaction = financialTransactionRepository.save(financialTransaction);

        // then
        FinancialTransaction findFinancialTransaction = financialTransactionRepository.findByTransactionId(financialTransaction.getTransactionId()).orElseThrow(() -> new RuntimeException("거래내역 없음"));

        LOGGER.info("financialTransaction : {}", financialTransaction.toString());
        LOGGER.info("saveFinancialTransaction : {}", saveFinancialTransaction.toString());
        LOGGER.info("findFinancialTransaction : {}", findFinancialTransaction.toString());

        Assertions.assertThat(findFinancialTransaction).isSameAs(saveFinancialTransaction);
        Assertions.assertThat(findFinancialTransaction).isSameAs(financialTransaction);
    }

    @Test
    public void delete() throws Exception{
        // given
        FinancialTransaction financialTransaction = FinancialTransaction.builder()
                .transactionId("QWERTY240926")
                .amount(BigDecimal.valueOf(300000))
                .currency("KRW")
                .status("FT0001")
                .paymentDate(LocalDateTime.now())
                .customerPhone("010-1111-1111")
                .cardLastFourDigits("3679")
                .authorizationCode("5196159191951591223")
                .build();
        financialTransactionRepository.save(financialTransaction);

        LOGGER.info("financialTransaction {} ", financialTransaction.toString());

        // when
        financialTransactionRepository.delete(financialTransaction);

//        FinancialTransaction byTransactionId = financialTransactionRepository.findByTransactionId(financialTransaction.getTransactionId());
//        LOGGER.info("byTransactionId {} ", byTransactionId.toString());

        // then
        assertThrows(Exception.class, () -> financialTransactionRepository.findByTransactionId(financialTransaction.getTransactionId()).orElseThrow(() -> new RuntimeException("저장된 거래내역 없음")));

    }

    @Test
    public void list(){
        // given

        User user = User.builder().name("테스트김").password("1234").email("test@naver.com").age(27).phoneNum("01012345678").nickname("nick").role(Role.USER).build();

        userRepository.save(user);
        FinancialTransaction financialTransaction1 = FinancialTransaction.builder()
                .transactionId("QWERTY240926__1")
                .amount(BigDecimal.valueOf(300000))
                .currency("KRW")
                .status("FT0001")
                .paymentDate(LocalDateTime.now())
                .customerPhone(user.getPhoneNum())
                .cardLastFourDigits("3679")
                .authorizationCode("5196159191951591223")
                .user(user)
                .build();

        FinancialTransaction financialTransaction2 = FinancialTransaction.builder()
                .transactionId("QWERTY240926__2")
                .amount(BigDecimal.valueOf(300000))
                .currency("KRW")
                .status("FT0001")
                .paymentDate(LocalDateTime.now())
                .customerPhone(user.getPhoneNum())
                .cardLastFourDigits("3679")
                .authorizationCode("4389432329842611")
                .user(user)
                .build();
        
        financialTransactionRepository.save(financialTransaction1);
        financialTransactionRepository.save(financialTransaction2);



        // when
        List<FinancialTransaction> financialTransactionList = financialTransactionRepository.findAllByUser(user);

        for (FinancialTransaction financialTransaction : financialTransactionList) {
            LOGGER.info("financialTransaction : {}", financialTransaction.toString());

        }
        List<FinancialTransaction> fList = Arrays.asList(financialTransaction2 , financialTransaction1);

        Assertions.assertThat(financialTransactionList.size()).isEqualTo(2);
//        Assertions.assertThat(financialTransactionList.size()).isEqualTo(3);
        assertTrue(financialTransactionList.containsAll(fList));




    }



}