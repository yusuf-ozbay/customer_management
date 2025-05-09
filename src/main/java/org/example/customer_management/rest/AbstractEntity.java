package org.example.customer_management.rest;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

/**
 * Base model for all entity classes that uses UUIDs as ID values and provides creation timestamp
 */

@Getter
@Setter
@MappedSuperclass //bu sınıfın bir "temel sınıf" olduğunu belirtir.
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity implements Serializable {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    @GenericGenerator(name= "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "UUID")
    private String id;


    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modified;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + id + ", created=" + created + ", modified=" + modified + "]";
    }

}
