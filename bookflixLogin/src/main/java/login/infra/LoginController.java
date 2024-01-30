package login.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import login.domain.Login;
import login.domain.LoginRepostitory;

@RestController
@RequestMapping("/bookflix")
public class LoginController {

    @GetMapping("/login")
    public String login() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String preferred_username = getPreferred_username();

            LoginRepostitory loginRepostitory = login.domain.Login.repository();

            Login user= loginRepostitory.findByUserId(preferred_username);

            String name = jwt.getClaimAsString("name");
            name = name.replaceAll(" ","");

            if (user == null) {
                user = new Login();
                user.setUserMoney(100000);
                user.setUserName(preferred_username);
                user.setUserSubscribeStatus(false);
                loginRepostitory.save(user);
            }

            return preferred_username + " " + name;
        }
        else {
            return "Something went wrong; authentication is not provided by IAP/JWT.\n";
        }
    }

    @GetMapping("/user")
    @ResponseBody
    public Login user() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String preferred_username = getPreferred_username();

            LoginRepostitory loginRepostitory = login.domain.Login.repository();

            Login user= loginRepostitory.findByUserId(preferred_username);

            String name = jwt.getClaimAsString("name");
            name = name.replaceAll(" ","");

            if (user == null) {
                user = new Login();
                user.setUserMoney(100000);
                user.setUserName(preferred_username);
                user.setUserSubscribeStatus(false);
                loginRepostitory.save(user);
            }

            return user;
        } else {
            return null;
        }
    }

    

    private String getPreferred_username() {
        Jwt jwt = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt.getClaimAsString("preferred_username");
         
    }

}