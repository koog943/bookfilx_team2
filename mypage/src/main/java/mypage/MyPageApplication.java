package mypage;

import org.apache.kafka.common.security.auth.Login;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;

import mypage.config.kafka.KafkaProcessor;
import mypage.domain.Mypage;
import mypage.domain.MypageRepository;
import mypage.infra.UserService;

@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class MyPageApplication {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext =
            SpringApplication.run(MyPageApplication.class, args);
            addUser();
            test();
    }

    private static void test() {
        MypageRepository mypageRepository = mypage.domain.Mypage.repository();
        UserService userService = new UserService(mypageRepository);

        for (int i = 1; i <= 5; i++) {
            Mypage login = new Mypage();
            login.setUserId("userId" + i);
            login.setUserMoney(i * 10000);
            if (i % 2 == 0) {
                login.setUserSubscribeStatus(false);
            } else {
                login.setUserSubscribeStatus(true);
            }
            Mypage book1 = userService.findById(1L);
            Mypage book2 = userService.findById(2L);
            Mypage book3 = userService.findById(3L);

            userService.findAll();
            // userService.findAll(book2);
            // userService.readAdd(book3);

            userService.save(login);



            System.out.println("LoginAAAAAA id: = " + login.getId());
        }


    }




    private static void addUser(){
        MypageRepository mypageRepository = mypage.domain.Mypage.repository();
        Mypage mypage = new Mypage();

        for(int i = 1; i <= 5; i++){

            mypage.setUserName("test" + i);
            mypage.setUserMoney(10000);
            mypage.setUserSubscribeStatus(false);
            mypageRepository.save(mypage);
        }

    }
}

