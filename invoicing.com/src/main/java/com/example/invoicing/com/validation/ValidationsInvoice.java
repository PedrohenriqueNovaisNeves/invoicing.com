package com.example.invoicing.com.validation;

import com.example.invoicing.com.models.InvoiceModel;
import com.example.invoicing.com.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationsInvoice {

    @Autowired
    InvoiceRepository invoiceRepository;

    public boolean validationInvoice(InvoiceModel invoiceModel){
        List<InvoiceModel> allInvoices = invoiceRepository.findAll();

        List<InvoiceModel> duplicates = allInvoices.stream()
                .filter(invoiceModel1 -> invoiceModel1.getInvoiceCod().equalsIgnoreCase(invoiceModel.getInvoiceCod()))
                .collect(Collectors.toList());

        if(!duplicates.isEmpty()){
            return true;
        }
        return false;
    }

    
}
