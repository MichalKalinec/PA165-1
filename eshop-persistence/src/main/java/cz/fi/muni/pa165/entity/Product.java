package cz.fi.muni.pa165.entity;

import cz.fi.muni.pa165.enums.Color;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ESHOP_PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private Color color;
    
    @Temporal(TemporalType.DATE)
    @Column(unique = true)
    private Date addedDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.color);
        hash = 89 * hash + Objects.hashCode(this.addedDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Product)) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (this.getColor() != other.getColor()) {
            return false;
        }
        if (!Objects.equals(this.getAddedDate(), other.getAddedDate())) {
            return false;
        }
        return true;
    }
    
    
}
