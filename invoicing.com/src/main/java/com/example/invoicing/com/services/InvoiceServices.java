package com.example.invoicing.com.services;

import com.example.invoicing.com.models.InvoiceModel;
import com.example.invoicing.com.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceServices {

    @Autowired
    InvoiceRepository invoiceRepository;

    public ResponseEntity<InvoiceModel> saveUser(InvoiceModel invoiceModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceRepository.save(invoiceModel));
    }

    public ResponseEntity<List<InvoiceModel>> listInvoices(){
        return ResponseEntity.status(HttpStatus.OK).body(invoiceRepository.findAll());
    }

    public ResponseEntity<Object> listOneInvoice(UUID id){
        Optional<InvoiceModel> invoice = invoiceRepository.findById(id);

        if(invoice.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invoice not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(invoice.get());
    }


}
