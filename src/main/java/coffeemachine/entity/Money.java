package coffeemachine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Money {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "money_id_seq")
    private Long id;

    private Long moneyLocationId;

    private Long amount;
}
