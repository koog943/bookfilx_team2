package mypage.infra;



import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mypage.config.kafka.KafkaProcessor;
import mypage.domain.*;

import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    UserService mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Logined'"
    )
    public void wheneverRoom_ReserveInfo(
        @Payload Logined logined
    ) {
        Logined event = logined;
        System.out.println(
            "\n\n##### listener LoginInfo : " + logined + "\n\n"
        );

        Mypage mypage = new Mypage();
        mypage.setLogined(event);
        mypageRepository.save(mypage);

    }
}
//>>> Clean Arch / Inbound Adaptor
