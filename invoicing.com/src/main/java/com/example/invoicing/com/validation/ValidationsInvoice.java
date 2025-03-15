package com.example.invoicing.com.validation;

import com.example.invoicing.com.models.InvoiceModel;
import com.example.invoicing.com.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidationsInvoice {

    @Autowired
    InvoiceRepository invoiceRepository;

    public boolean validationInvoice(InvoiceModel invoiceModel){
        List<InvoiceModel> allInvoices = invoiceRepository.findAll();

        List<InvoiceModel> duplicates = allInvoices.stream()
                .filter((invoiceModel1 -> invoiceModel1.getTitle().equalsIgnoreCase(invoiceModel.getTitle()) && invoiceModel1.getValue().equals(invoiceModel.getValue()) &&
                        invoiceModel1.getDescriptionInvoice().equalsIgnoreCase(invoiceModel.getDescriptionInvoice()) &&
                        invoiceModel1.getMaturity().equals(invoiceModel.getMaturity())))
                .collect(Collectors.toList());

        if(!duplicates.isEmpty()){
            return true;
        }
        return false;
    }
}
