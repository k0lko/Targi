package org.targi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Booth {
    @Id
    @GeneratedValue
    private Long id;

    private String label;
    private int x, y;
    private boolean reserved;

    @ManyToOne
    private Person reservedBy;

    // Konstruktor bezargumentowy (wymagany przez JPA)
    public Booth() {
    }

    // Konstruktor z label, x, y
    public Booth(String label, int x, int y) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.reserved = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Person getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(Person reservedBy) {
        this.reservedBy = reservedBy;
    }
}
