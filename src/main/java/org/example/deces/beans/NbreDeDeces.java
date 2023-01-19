package org.example.deces.beans;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NbreDeDeces implements Serializable {
    private String semaine;
    private int nb_cas_resid;
    private int nb_cas_prsnl;
}
