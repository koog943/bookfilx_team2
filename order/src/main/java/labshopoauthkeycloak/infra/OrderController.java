package labshopoauthkeycloak.infra;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.lang.ProcessBuilder.Redirect;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/logout")
    public RedirectView logout() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000/bookflix/signIn");
        return redirectView; 
    }

    @GetMapping("/placeOrder")
    public String placeAnOrder() {
        String preferred_username = getPreferred_username();        
        return "<center> Hi~ " + preferred_username + 
                        ". Welcome to 12stMall.<br/><br/> We've Good Products, You can place an Order.";
    }

    @GetMapping("/redirect")
    public RedirectView redirect() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000/bookflix/signIn");
        return redirectView;
    }

    @GetMapping("/manageOrder")
    public String orderManage() {
        String preferred_username = getPreferred_username();        
        return "<center> Hi~ " + preferred_username + 
                        ". Welcome to 12stMall.<br/><br/> You can manage Order Admin Jobs.";
    }

    @GetMapping("/getUser")
    @PermitAll
    public String getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return String.format("You are [%s] with e-mail address [%s]. name[$s]%n",
                jwt.getSubject(), jwt.getClaimAsString("email"), jwt.getClaimAsString("preferred_username"));
        }
        else {
            return "Something went wrong; authentication is not provided by IAP/JWT.\n";
        }
    }

    private String getPreferred_username() {
        Jwt jwt = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt.getClaimAsString("preferred_username");
         
    }
}
