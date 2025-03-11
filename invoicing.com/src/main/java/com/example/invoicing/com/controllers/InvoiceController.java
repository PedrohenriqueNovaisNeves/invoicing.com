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
    public ResponseEntity<Object> insertInvoice(@RequestBody @Valid InvoiceRecord invoiceRecord) {
        var invoice = new InvoiceModel();
        BeanUtils.copyProperties(invoiceRecord, invoice);

        if (validationInvoice.validationInvoice(invoice)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invoice existing");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceServices.saveInvoice(invoice));
    }

    @GetMapping("/listInvoices")
    public ResponseEntity<List<InvoiceModel>> listInvoices() {
        return invoiceServices.listInvoices();
    }

    @GetMapping("/oneInvoice/{id}")
    public ResponseEntity<Object> getOneInvoice(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(invoiceServices.listOneInvoice(id));
    }

    @PutMapping("/updateInvoice/{id}")
    public ResponseEntity<Object> updateInvoice(@PathVariable(value = "id") UUID id, @RequestBody @Valid InvoiceRecord invoiceRecord) {
        return ResponseEntity.status(HttpStatus.OK).body(invoiceServices.updateInvoice(invoiceRecord, id));
    }

    @DeleteMapping("/deleteInvoice/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(invoiceServices.invoiceDelete(id));
    }

    @DeleteMapping("/deleteAllInvoices")
    public ResponseEntity<Object> deleteAllInvoices(){
        return ResponseEntity.status(HttpStatus.OK).body(invoiceServices.deleteAllInvoices());
    }
}