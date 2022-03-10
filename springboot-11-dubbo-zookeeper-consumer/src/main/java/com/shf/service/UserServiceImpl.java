package com.shf.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Reference
    TicketService ticket;

    @Override
    public void buyTicket() {
        String ticket = this.ticket.getTicket();
        System.out.println("在注册中心拿到===》 "+ticket);
    }
}
