package com.distribution.moneydistribution.domain.transaction;

import com.distribution.moneydistribution.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FinancialTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;
    private BigDecimal amount;
    private String currency; // 통화 코드
    private String status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime paymentDate;
    private String customerPhone;
    private String cardLastFourDigits; // 카드 마지막 4자리
    private String authorizationCode;  // 승인 번호

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 부모엔티티에 대한 정보가 실제로 필요할때까지 정보를 가져오지 않음
    @JoinColumn(name = "user_id")
    private User user;


    @Override
    public String toString() {
        return "FinancialTransaction{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                ", paymentDate=" + paymentDate +
                ", customerPhone='" + customerPhone + '\'' +
                ", cardLastFourDigits='" + cardLastFourDigits + '\'' +
                ", authorizationCode='" + authorizationCode + '\'' +
                ", user=" + user +
                '}';
    }
}
