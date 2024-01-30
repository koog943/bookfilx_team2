package payment.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import payment.domain.DecreaseMoneyCommand;
import payment.domain.Payment;
import payment.domain.PaymentRepository;


@RestController
@Transactional
public class PaymentController {

    //db 조회, 저장하기 위해, 자동주입
    @Autowired
    PaymentRepository paymentRepository;

    @RequestMapping(
        value = "/pay",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Payment decreaseMoney(
        // @PathVariable(value = "id") Long id,
        @RequestBody DecreaseMoneyCommand decreaseMoneyCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /payment/decreaseStock  called #####");
        // Optional<Inventory> optionalInventory = inventoryRepository.findById(
        //     id
        // );

        // optionalInventory.orElseThrow(() -> new Exception("No Entity Found"));
        Payment payment = new Payment();
        payment.decreaseMoney(decreaseMoneyCommand);
        paymentRepository.save(payment);
        return payment;
    }

    @RequestMapping(
        value = "pay/{id}/cancel",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Payment CancelSubscribe(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /pay/cancel called #####");
        Optional<Payment> optionalPayment = paymentRepository.findById(
            id
        );

        optionalPayment.orElseThrow(() -> new Exception("No Entity Found"));
        Payment payment = optionalPayment.get();
        payment.setSubOrNot(false);
        // inventory.setId(decreaseStockCommand.getId());
        // inventory.setMoney(decreaseStockCommand.getMoney());
        // inventory.decreaseMoney();
        paymentRepository.save(payment);
        return payment;
    }
}

