package com.example.invoicing.com.controllers;

import com.example.invoicing.com.dtos.InvoiceRecord;
import com.example.invoicing.com.models.InvoiceModel;
import com.example.invoicing.com.repository.InvoiceRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;

    @GetMapping("/ping")
    public String ping(){
        return "ping!";
    }

    @PostMapping("/insertInvoice")
    public ResponseEntity<InvoiceModel> insertInvoice(@RequestBody @Valid InvoiceRecord invoiceRecord){
        var invoice = new InvoiceModel();
        BeanUtils.copyProperties(invoiceRecord, invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceRepository.save(invoice));
    }

    @GetMapping("/listInvoices")
    public ResponseEntity<List<InvoiceModel>> getAllInvoices(){
        return ResponseEntity.status(HttpStatus.OK).body(invoiceRepository.findAll());
    }

    @GetMapping("/oneInvoice/{id}")
    public ResponseEntity<Object> getOneInvoice(@PathVariable(value = "id")UUID id){
        Optional<InvoiceModel> invoice0 = invoiceRepository.findById(id);

        if(invoice0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(invoice0.get());
    }
}
