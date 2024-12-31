package com.example.invoicing.com.services;

import com.example.invoicing.com.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServices {

    @Autowired
    InvoiceRepository invoiceRepository;



}
