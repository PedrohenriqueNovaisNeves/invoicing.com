package com.example.invoicing.com.services;

import com.example.invoicing.com.dtos.InvoiceRecord;
import com.example.invoicing.com.models.InvoiceModel;
import com.example.invoicing.com.repository.InvoiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceServices {

    @Autowired
    InvoiceRepository invoiceRepository;

    public ResponseEntity<InvoiceModel> saveInvoice(InvoiceModel invoiceModel){
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

    public ResponseEntity<Object> updateInvoice(InvoiceRecord invoiceRecord, UUID id){
        Optional<InvoiceModel> invoice = invoiceRepository.findById(id);

        if(invoice.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invoice not found");
        }

        var invoice1 = invoice.get();
        BeanUtils.copyProperties(invoiceRecord, invoice1);
        return ResponseEntity.status(HttpStatus.OK).body(invoiceRepository.save(invoice1));
    }

    public ResponseEntity<Object> invoiceDelete(UUID id){
        Optional<InvoiceModel> invoice = invoiceRepository.findById(id);

        if(invoice.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invoice not found");
        }

        invoiceRepository.delete(invoice.get());
        return ResponseEntity.status(HttpStatus.OK).body("Invoice delete successfully");
    }

    public ResponseEntity<Object> deleteAllInvoices(){
        invoiceRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All invoices delete successfully");
    }


}