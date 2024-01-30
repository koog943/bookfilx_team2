package payment.domain;

import java.time.LocalDate;
import java.util.*;

import lombok.Data;
import lombok.ToString;
import payment.infra.AbstractEvent;

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
