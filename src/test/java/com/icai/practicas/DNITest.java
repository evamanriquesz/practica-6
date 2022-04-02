package com.icai.practicas;
import com.icai.practicas.model.DNI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class DNITest {

    @Test
    public void return_true_when_dni_is_valid(){
        DNI dni = new DNI("06028177S");
        Assertions.assertEquals(true, dni.validar());
    }

    @Test
    public void return_false_when_dni_is_not_valid(){
        DNI dni = new DNI("06028177N");
        Assertions.assertEquals(false, dni.validar());
    }

    @Test
    public void return_false_when_not_enough_digits(){
        DNI dni = new DNI("0602817S");
        Assertions.assertEquals(false, dni.validar());
    }

    @Test
    public void return_false_when_incorrect_digit_control(){
        DNI dni = new DNI("06028177A");
        Assertions.assertEquals(false, dni.validar());
    }

    @Test
    public void return_false_when_digit_control_not_in_the_list(){
        DNI dni = new DNI("06028177O");
        Assertions.assertEquals(false, dni.validar());
    }

    @Test
    public void return_false_when_invalido(){
        DNI dni = new DNI("00000000T");
        Assertions.assertEquals(false, dni.validar());
    }

}

