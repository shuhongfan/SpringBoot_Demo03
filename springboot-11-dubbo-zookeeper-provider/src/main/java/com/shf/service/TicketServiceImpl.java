package com.shf.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service
public class TicketServiceImpl implements TicketService{
    @Override
    public String getTicket() {
        return "hello dubbo";
    }
}
