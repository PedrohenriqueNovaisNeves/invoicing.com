package com.example.invoicing.com.services;

import com.example.invoicing.com.models.InvoiceModel;
import com.example.invoicing.com.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceServices {

    @Autowired
    InvoiceRepository invoiceRepository;

    public InvoiceModel saveInvoice(InvoiceModel invoiceModel){
        return invoiceRepository.save(invoiceModel);
    }

    public List<InvoiceModel> listAllInvoices(){
        return invoiceRepository.findAll();
    }

    public Object listOneInvoice(UUID id){
        return invoiceRepository.findById(id);
    }

    public InvoiceModel updateInvoice(UUID id, InvoiceModel invoiceModel){
        Optional<InvoiceModel> invoice = invoiceRepository.findById(id);

        if(invoice.isEmpty()){
            throw new RuntimeException("Invoice not found");
        }

        var newInvoice = invoice.get();

        newInvoice.setTitle(invoiceModel.getTitle());
        newInvoice.setValue(invoiceModel.getValue());
        newInvoice.setDescriptionInvoice(invoiceModel.getDescriptionInvoice());
        newInvoice.setMaturity(invoiceModel.getMaturity());
        newInvoice.setPaid(invoiceModel.isPaid());
        newInvoice.setPriority(invoiceModel.getPriority());

        return invoiceRepository.save(newInvoice);
    }

    public void updateInvoiceStatus(UUID id, InvoiceModel invoiceModel){
        Optional<InvoiceModel> invoice = invoiceRepository.findById(id);

        if(invoice.isEmpty()){
            throw new RuntimeException("invoice not found");
        }

        var newInvoice = invoice.get();

        newInvoice.setPaid(invoiceModel.isPaid());
    }

    public void deleteOneInvoice(UUID id){
        Optional<InvoiceModel> invoice = invoiceRepository.findById(id);

        if(invoice.isEmpty()){
            throw new RuntimeException("Invoice not found");
        }

        invoiceRepository.delete(invoice.get());
    }

    public void deleteAllInvoices(){
        invoiceRepository.deleteAll();
    }
}