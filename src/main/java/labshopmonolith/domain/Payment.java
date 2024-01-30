package labshopmonolith.src.main.java.labshopmonolith.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import labshopmonolith.InventoryApplication;
import lombok.Data;

@Entity
@Table(name = "Inventory_table")
@Data
public class Payment{

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
        PaymentRepository inventoryRepository = InventoryApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return inventoryRepository;
    }


    public void decreaseMoney() {
        //implement business logic here:

        // setStock(getStock() - Long.valueOf(decreaseStockCommand.getQty()));
        if(getMoney() < 10000) {
            
            throw new RuntimeException("Out of Money");  //돈이 적은경우 메세지를 어떻게 던져야할지 못 정함
        }

        setMoney(getMoney() - 10000); //일단 만원이라고 지정함
        setSubOrNot(true);

        repository().save(payment);

    }

}
