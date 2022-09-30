package com.isoft.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Offer extends BaseEntity {

    private LocalDate date;
    private int value;
    private String reason;
    @Enumerated(EnumType.STRING)
    private Type type;

    public static enum Type {
        FESTIVAL, AZADI;
    }
}
