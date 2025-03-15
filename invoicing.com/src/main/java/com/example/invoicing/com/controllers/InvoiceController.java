package com.example.invoicing.com.controllers;

import com.example.invoicing.com.dtos.InvoiceRecord;
import com.example.invoicing.com.models.InvoiceModel;
import com.example.invoicing.com.services.InvoiceServices;
import com.example.invoicing.com.validation.ValidationsInvoice;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceServices invoiceServices;

    @Autowired
    ValidationsInvoice validationInvoice;

    @GetMapping("/ping")
    public String ping() {
        return "ping!";
    }

    @PostMapping("/insertInvoice")
    public ResponseEntity<Object> saveInvoice(@Valid @RequestBody InvoiceRecord invoiceRecord){
        var invoice = new InvoiceModel();
        BeanUtils.copyProperties(invoiceRecord, invoice);

        if(!validationInvoice.validationInvoice(invoice)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invoice existing");
        }

        invoiceServices.saveInvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered Invoice");
    }

    @GetMapping("/listAllInvoices")
    public ResponseEntity<List<InvoiceModel>> listAllInvoices(){
        List<InvoiceModel> listInvoices = invoiceServices.listAllInvoices();
        return ResponseEntity.status(HttpStatus.OK).body(listInvoices);
    }
}