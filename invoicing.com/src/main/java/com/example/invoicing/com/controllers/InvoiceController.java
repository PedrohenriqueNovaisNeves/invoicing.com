package com.example.invoicing.com.controllers;

import com.example.invoicing.com.dtos.InvoiceRecord;
import com.example.invoicing.com.dtos.InvoiceRequest;
import com.example.invoicing.com.models.InvoiceModel;
import com.example.invoicing.com.models.UserModel;
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

    @GetMapping("/ping")
    public String ping() {
        return "ping!";
    }

    @PostMapping("/insertInvoice")
    public ResponseEntity<Object> saveInvoice(@Valid @RequestBody InvoiceRequest request){
        var invoice = new InvoiceModel();
        BeanUtils.copyProperties(request.invoiceRecord(), invoice);

        invoiceServices.saveInvoice(request.nameUser(), invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered Invoice");
    }

    @GetMapping("/listAllInvoices")
    public ResponseEntity<List<InvoiceModel>> listAllInvoices(){
        List<InvoiceModel> listInvoices = invoiceServices.listAllInvoices();
        return ResponseEntity.status(HttpStatus.OK).body(listInvoices);
    }

    @GetMapping("/oneInvoice/{id}")
    public ResponseEntity<Object> listOneInvoice(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(invoiceServices.listOneInvoice(id));
    }

    @GetMapping("/listInvoicesByPriority")
    public ResponseEntity<List<InvoiceModel>> listInvoicesByPriority(@Valid @RequestBody InvoiceModel invoiceModel){
        List<InvoiceModel> invoices = invoiceServices.listByPriority(invoiceModel.getPriority());
        return ResponseEntity.status(HttpStatus.OK).body(invoices);
    }

    @GetMapping("/listInvoicesByUser")
    public ResponseEntity<List<InvoiceModel>> listInvoicesByUser(@Valid @RequestBody UserModel userModel){
        List<InvoiceModel> invoices = invoiceServices.listInvoicesByUser(userModel.getNameUser());
        return ResponseEntity.status(HttpStatus.OK).body(invoices);
    }
}