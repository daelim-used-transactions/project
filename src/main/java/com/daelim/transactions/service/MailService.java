package com.daelim.transactions.service;

import java.util.Map;

public interface MailService {
    public Map<String, Object> idSearch(String email, String id);

    public String sendNumber(String email);
}
