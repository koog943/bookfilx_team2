package payment.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import payment.PaymentApplication;

@Entity
@Table(name = "Payment_table")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer money;
    private Boolean SubOrNot = false;

    @PostPersist
    public void onPostPersist() {
        DecreaseMoneyCommand decreaseMoneyCommand = new DecreaseMoneyCommand(this);
        decreaseMoneyCommand.publishAfterCommit();
        // PaymentCompleted paymentCompleted = new PaymentCompleted(this);
        // paymentCompleted.publishAfterCommit();
    }

    public static PaymentRepository repository() {
        PaymentRepository inventoryRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return inventoryRepository;
    }

    public void decreaseMoney(DecreaseMoneyCommand decreaseMoneyCommand) {
        //implement business logic here:

        // setStock(getStock() - Long.valueOf(decreaseStockCommand.getQty()));
        if(decreaseMoneyCommand.getMoney() < 10000) {
            
            throw new RuntimeException("Out of Money");  //돈이 적은경우 메세지를 어떻게 던져야할지 못 정함
        }

        setMoney(decreaseMoneyCommand.getMoney() - 10000); //일단 만원이라고 지정함
        setSubOrNot(true);

        // Payment payment = new Payment();
        // payment.setId(decreaseMoneyCommand.getId());
        // payment.setMoney(decreaseMoneyCommand.getMoney());
        // if(payment.getMoney() < 10000){
        //     throw new RuntimeException("Out of Money");  //돈이 적은경우 메세지를 어떻게 던져야할지 못 정함
        // }
        // payment.setMoney(payment.getMoney() - 10000);
        // payment.setSubOrNot(true);
        // repository().save(payment);
    }
}
