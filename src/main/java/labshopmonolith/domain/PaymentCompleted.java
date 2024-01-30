package labshopmonolith.src.main.java.labshopmonolith.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
@ToString
public class PaymentCompleted extends AbstractEvent{
    private Long id;
    private Integer money;
    private Boolean SubOrNot = false;

    public PaymentCompleted(Payment payment){
        super(payment);
    }
    public PaymentCompleted(){
        super();
    }
}
