package io.pivotal.pal.data.rentaltruck.reservation.handler.creditcardverifier;

import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    public boolean validateCreditCard(String number, double amount) {
        return !number.endsWith("666");
    }
}
