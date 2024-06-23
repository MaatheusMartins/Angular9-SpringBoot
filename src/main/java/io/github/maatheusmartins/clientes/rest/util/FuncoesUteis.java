package io.github.maatheusmartins.clientes.rest.util;

import org.h2.util.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FuncoesUteis {

    public BigDecimal converterStringEmBigDecimal(String valor) {

        if (StringUtils.isNullOrEmpty(valor)) {
            return null;
        }
        valor = valor.replace(".", "").replace(",", ".");
        return new BigDecimal(valor);
    }
}
