package org.targi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Firma
    private String companyName;
    private String industry;
    private String website;

    // Rodzaj stoiska
    private String boothType;

    // Opcje dodatkowe
    private boolean chair;
    private boolean panel;
    private int cateringCount;

    // Kontakt
    private String contactName;
    private String email;
    private String phone;

    // Dane do faktury
    private String invoiceCompany;
    private String invoiceAddress;
    private String invoiceNIP;

    // Zgody
    private boolean dataConsent;
    private boolean participationConsent;
    private boolean conditionsConsent;

    // Wybrane stoisko
    @ManyToOne
    private Booth booth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBoothType() {
        return boothType;
    }

    public void setBoothType(String boothType) {
        this.boothType = boothType;
    }

    public boolean isChair() {
        return chair;
    }

    public void setChair(boolean chair) {
        this.chair = chair;
    }

    public boolean isPanel() {
        return panel;
    }

    public void setPanel(boolean panel) {
        this.panel = panel;
    }

    public int getCateringCount() {
        return cateringCount;
    }

    public void setCateringCount(int cateringCount) {
        this.cateringCount = cateringCount;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInvoiceCompany() {
        return invoiceCompany;
    }

    public void setInvoiceCompany(String invoiceCompany) {
        this.invoiceCompany = invoiceCompany;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getInvoiceNIP() {
        return invoiceNIP;
    }

    public void setInvoiceNIP(String invoiceNIP) {
        this.invoiceNIP = invoiceNIP;
    }

    public boolean isDataConsent() {
        return dataConsent;
    }

    public void setDataConsent(boolean dataConsent) {
        this.dataConsent = dataConsent;
    }

    public boolean isParticipationConsent() {
        return participationConsent;
    }

    public void setParticipationConsent(boolean participationConsent) {
        this.participationConsent = participationConsent;
    }

    public boolean isConditionsConsent() {
        return conditionsConsent;
    }

    public void setConditionsConsent(boolean conditionsConsent) {
        this.conditionsConsent = conditionsConsent;
    }

    public Booth getBooth() {
        return booth;
    }

    public void setBooth(Booth booth) {
        this.booth = booth;
    }
}