package mypage;

import org.apache.kafka.common.security.auth.Login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import java.util.logging.Logger;
import mypage.config.kafka.KafkaProcessor;
import mypage.domain.Mypage;
import mypage.domain.MypageRepository;
import mypage.infra.UserService;

@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class MyPageApplication {

    public static ApplicationContext applicationContext;

    private static final Logger logger = Logger.getLogger(MyPageApplication.class.getName());
    public static void main(String[] args) {
        applicationContext =
            SpringApplication.run(MyPageApplication.class, args);
            addUser();
            // test();
    }

    // private static void test() {
    //     MypageRepository mypageRepository = mypage.domain.Mypage.repository();
    //     UserService userService = new UserService(mypageRepository);

    //     try {

    //         for (int i = 1; i <= 5; i++) {
    //             Mypage mypage = new Mypage();
    //             mypage.setUserId("userId" + i);
    //             mypage.setUserName("test" + i);
    //             mypage.setUserMoney(10000);
    //             mypage.setUserSubscribeStatus(i % 2 == 1); // 홀수면 true, 짝수면 false
    //             // 새로운 사용자를 추가
    //             mypageRepository.save(mypage);
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace(); // 예외를 콘솔에 출력
    //     }

    // }





    private static void addUser(){
        MypageRepository mypageRepository = mypage.domain.Mypage.repository();
        UserService userService = new UserService(mypageRepository);


        try {

            for(int i = 1; i <= 5; i++){
                Mypage mypage = new Mypage();
                mypage.setUserId("userId" + i);
                mypage.setUserName("test" + i);
                mypage.setUserMoney(i * 10000);
                mypage.setUserSubscribeStatus(false);
                userService.save(mypage);
            }

        } catch (Exception e) {

            logger.severe(
                "ㅊㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ아ㅓ니ㅓ랸ㅇ노ㅑㅗㅇ뉘ㅜ타ㅣ냐아ㅣㅣㅣㅣㅣ아ㅟㅏㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ너이냐ㅣㅗㅑ오ㅑㅐㅗ내ㅑㅙㅑ올인마ㅗ래ㅑ왜ㅑ냐ㅓ야ㅐ너어내ㅓㅑㅐㅗ애농노ㅜ레누ㅗ야ㅏㅗ네ㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅗ루냐ㅗㅜㅑ오ㅔㅑ넹ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ" + 
                e.getMessage());
            e.printStackTrace(); // 예외를 콘솔에 출력
          
           


                


        }

        

    }
}

