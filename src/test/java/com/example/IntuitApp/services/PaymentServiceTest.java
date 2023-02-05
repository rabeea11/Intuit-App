package com.example.IntuitApp.services;

import com.example.IntuitApp.Interfaces.IPaymentDAO;
import com.example.IntuitApp.kafka.Producer;
import com.example.IntuitApp.model.Payment;
import com.example.IntuitApp.valdiators.ValidateAmount;
import com.example.IntuitApp.valdiators.ValidateCurrency;
import com.example.IntuitApp.valdiators.ValidatePayment;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = PaymentService.class)
class PaymentServiceTest {

    @InjectMocks
    PaymentService paymentService = new PaymentService();
    @Mock
    private Producer producer;
    @Mock
    private ValidatePayment validatePayment;
    @Mock
    private ValidateAmount validateAmount;
    @Mock
    private ValidateCurrency validateCurrency;
    @Mock
    private IPaymentDAO paymentDAO;

    @Test
    void sendPaymentTestFalse() {
        Assert.assertFalse(paymentService.sendPayment(new Payment(12,null,"e8af92bd-1910-421e-8de0-cb3dcf9bf44d","4c3e304e-ce79-4f53-bb26-4e198e6c780a","8e28af1b-a3a0-43a9-96cc-57d66dd22494")));

    }
    @Test
    void isValidPaymentTestTrue() throws Exception {
       Assert.assertTrue(paymentService.isValidPayment(new Payment(12,"ILS","e8af92bd-1910-421e-8de0-cb3dcf9bf44d","4c3e304e-ce79-4f53-bb26-4e198e6c780a","8e28af1b-a3a0-43a9-96cc-57d66dd22494")));
    }

    @Test
    void isValidCurrencyTestTrue() {
        Assert.assertTrue(paymentService.isValidPayment(new Payment(12,"ILS","e8af92bd-1910-421e-8de0-cb3dcf9bf44d","4c3e304e-ce79-4f53-bb26-4e198e6c780a","8e28af1b-a3a0-43a9-96cc-57d66dd22494")));
    }

    @Test
    void isValidAmountTestTrue() {
        Assert.assertTrue(paymentService.isValidAmount(new Payment(12,"ILS","e8af92bd-1910-421e-8de0-cb3dcf9bf44d","4c3e304e-ce79-4f53-bb26-4e198e6c780a","8e28af1b-a3a0-43a9-96cc-57d66dd22494")));

    }

    @Test
    void isValidPaymentTestFalse() {
        Assert.assertFalse(paymentService.isValidPayment(new Payment(12,null,"e8af92bd-1910-421e-8de0-cb3dcf9bf44d","4c3e304e-ce79-4f53-bb26-4e198e6c780a","8e28af1b-a3a0-43a9-96cc-57d66dd22494")));

    }

    @Test
    void isValidCurrencyTestFalse() {
        Assert.assertFalse(paymentService.isValidCurrency(new Payment(12,"ABC","e8af92bd-1910-421e-8de0-cb3dcf9bf44d","4c3e304e-ce79-4f53-bb26-4e198e6c780a","8e28af1b-a3a0-43a9-96cc-57d66dd22494")));

    }

    @Test
    void isValidAmountTestFalse() {
        Assert.assertFalse(paymentService.isValidAmount(new Payment(-4,"ILS","e8af92bd-1910-421e-8de0-cb3dcf9bf44d","4c3e304e-ce79-4f53-bb26-4e198e6c780a","8e28af1b-a3a0-43a9-96cc-57d66dd22494")));

    }
}