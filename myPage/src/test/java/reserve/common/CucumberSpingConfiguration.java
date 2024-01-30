package reserve.common;

import io.cucumber.spring.CucumberContextConfiguration;
import mypage.MyPageApplication;

import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { MyPageApplication.class })
public class CucumberSpingConfiguration {}
