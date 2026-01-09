package com.jpay.wallet_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransferController {
    @GetMapping("/transfer")
    public String transfer() {
        return "pages/transfer";
    }
}
