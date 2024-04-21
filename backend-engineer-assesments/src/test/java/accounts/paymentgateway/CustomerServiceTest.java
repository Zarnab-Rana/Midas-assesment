package accounts.paymentgateway;

import static org.mockito.Mockito.when;

import com.midas.app.models.Account;
import com.midas.app.patmentgateway.service.CustomerService;
import com.midas.app.patmentgateway.service.StripeGateway;
import com.midas.generated.model.CreateAccountDto;
import com.midas.generated.model.UpdateAccountDto;
import com.stripe.Stripe;
import com.stripe.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

  @InjectMocks private CustomerService customerService;

  @Mock private Customer customer;

  @Mock private StripeGateway stripeGateway;

  @Value("${stripe.api-key}")
  private static String apiKey;

  @BeforeAll
  public static void setApiKey() {
    Stripe.apiKey =
        "sk_test_51P6xaV08KmsPhPtGDzJgDqcIphG7em0UbavceNbepuhcjDp7rupeU5GnFp9vraTM1TBnVKJLsFRqM9LtvzNS0skZ00FmFHceHY";
  }

  @Test
  public void createCustomerAtStripeWithSuccess() {
    CreateAccountDto createAccountDto = new CreateAccountDto();
    createAccountDto.setFirstName("abc");
    createAccountDto.setLastName("xyz");
    createAccountDto.setEmail("xyz@gmail.com");

    Customer customer1 = new Customer();
    customer1.setName("abc xyz");
    when(stripeGateway.createCustomer(createAccountDto)).thenReturn(customer1);
    Assertions.assertEquals(customerService.createCustomer(createAccountDto).getName(), "abc xyz");
  }

  @Test
  public void updateCustomerAtStripeWithSuccess() {
    UpdateAccountDto updateAccountDto = new UpdateAccountDto();
    updateAccountDto.setFirstName("abc");
    updateAccountDto.setLastName("xyz");
    updateAccountDto.setEmail("xyz@gmail.com");

    Account account = new Account();
    account.setId(Long.valueOf(1));
    account.setFirstName("abc");
    account.setLastName("xyz");
    account.setEmail("xyz@gmail.com");

    Customer customer1 = new Customer();
    customer1.setName("abc xyz");
    when(stripeGateway.updateCustomer(account, updateAccountDto)).thenReturn(customer1);
    Assertions.assertEquals(
        customerService.updateCustomer(account, updateAccountDto).getName(), "abc xyz");
  }
}
