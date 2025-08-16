package org._majqi.PIB.model;

// Encja do automatycznego tworzenia faktur
import org._majqi.PIB.model.Reservation;
import jakarta.persistence.*;

@Entity
@Table(name = "invoice_data")
public class InvoiceData {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Reservation reservation;

    private String companyName;
    private String address;
    private String nip;
}
