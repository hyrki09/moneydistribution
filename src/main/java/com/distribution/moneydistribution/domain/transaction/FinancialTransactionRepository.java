package com.distribution.moneydistribution.domain.transaction;

import com.distribution.moneydistribution.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction,Long> {

    List<FinancialTransaction> findAllByUser(User user);

}
