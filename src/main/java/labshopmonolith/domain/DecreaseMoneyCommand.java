package labshopmonolith.src.main.java.labshopmonolith.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
@ToString
public class DecreaseMoneyCommand extends AbstractEvent{

    private Long id;
    private Integer money;
    
    public DecreaseMoneyCommand(Payment payment){
        this.id = payment.getId();
        this.money = payment.getMoney();
    }
    public DecreaseMoneyCommand(){
        super();
    }
}

//./kafka-console-consumer --bootstrap-server localhost:9092 --topic labshopcompensation --from-beginning
