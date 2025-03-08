package com.example.invoicing.com.repository;

import com.example.invoicing.com.models.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel, UUID> {
}
