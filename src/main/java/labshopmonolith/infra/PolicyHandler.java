package labshopmonolith.src.main.java.labshopmonolith.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import labshopmonolith.config.kafka.KafkaProcessor;
import labshopmonolith.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PaymentRepository paymentRepository;

    // @StreamListener(
    //     value = KafkaProcessor.INPUT,
    //     condition = "headers['type']=='Logined'"
    // )
    // public void wheneverRoom_ReserveInfo(
    //     @Payload Logined logined
    // ) {
    //     Logined event = logined;
    //     System.out.println(
    //         "\n\n##### listener LoginInfo : " + logined + "\n\n"
    //     );

    //     Mypage mypage = new Mypage();
    //     mypage.setLogined(event);
    //     mypageRepository.save(mypage);

    // }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PaymentCompleted'"
        )
    public void whatever(@Payload PaymentCompleted paymentCompleted) {
        PaymentCompleted event = paymentCompleted;

        System.out.println(
            "\n\n##### listener PaymentCompleted : " + paymentCompleted + "\n\n"
        );

        Payment payment = new Payment();
        payment.setId(paymentCompleted.getId());
        payment.setMoney(paymentCompleted.getMoney());
        payemt.decreaseMoney();
        payment.save();

    }
}