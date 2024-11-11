package net.elazzioui.Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.elazzioui.model.BankAccount;

public class Mapper {
    ObjectMapper objectMapper = new ObjectMapper();

    public String toJson(BankAccount bankAccount) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bankAccount);
    }

}
