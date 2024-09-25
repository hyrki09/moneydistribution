package com.distribution.moneydistribution.domain.transaction;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/finance")
public class FinancialTransactionController {


    @GetMapping("")
    public String list(){


        return "/finance/financeList";
    }
}
