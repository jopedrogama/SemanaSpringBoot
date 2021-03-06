package com.api.logisticaapi.Domain.Models;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.time.OffsetDateTime;
import javax.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.api.logisticaapi.Domain.ValidationGroups;
import com.api.logisticaapi.Domain.Exceptions.DomainException;
import javax.persistence.OneToMany;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {

    // hibernate converts the camel case lettering to underscores by default. so you
    // either change your columns in your table to reflect that or change hibernate
    // naming strategy. @StackOverflow

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.CustomerId.class)
    @NotNull
    @ManyToOne
    private Customer customer;

    @Column()
    @Embedded // Abstract information from another class but in the same table
    private Recipient recipient;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<DeliveryEvent> deliveryEvents = new ArrayList<>();

    @Column()
    private BigDecimal delivery_price;

    @Column()
    @Enumerated(EnumType.STRING)
    @JsonProperty(access = Access.READ_ONLY)
    private DeliveryStatus delivery_status;

    @Column()
    @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime order_date;

    @Column()
    @JsonInclude(Include.NON_NULL)
    @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime delivery_date;

    public DeliveryEvent addDeliveryEvent(String description) {
        DeliveryEvent deliveryEvent = new DeliveryEvent();
        deliveryEvent.setDelivery(this);
        deliveryEvent.setEvent_date_time(OffsetDateTime.now());
        deliveryEvent.setDescription(description);

        this.getDeliveryEvents().add(deliveryEvent);
        return deliveryEvent;
    }

    public void finishDelivery() {
        if (!DeliveryStatus.PENDING.equals(getDelivery_status())) {
            throw new DomainException("This delivery is not Pending, so it can not be finished!");
        }
        setDelivery_status(DeliveryStatus.DELIVERIED);
        setDelivery_date(OffsetDateTime.now());

    }

    public void cancelDelivery() {
        if (!DeliveryStatus.PENDING.equals(getDelivery_status())) {
            throw new DomainException("This delivery is not Pending, so it can not be canceled!");
        }
        setDelivery_status(DeliveryStatus.CANCELLED);
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ". Customer ID:[" + this.customer.getId() + "]. Delivery Price: "
                + this.delivery_price
                + ". Recipient: [" + this.recipient + "]. Deliverystatus" + this.delivery_status + ". OrderDate:"
                + this.order_date + ". DeliveryDate: " + this.delivery_date;
    }
}
