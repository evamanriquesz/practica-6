package com.icai.practicas;

import com.icai.practicas.model.DNI;
import com.icai.practicas.model.Telefono;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TelefonoTest {

    @Test
    public void return_true_when_telefono_is_valid(){
        Telefono telefono = new Telefono("630720672");
        Assertions.assertEquals(true, telefono.validar());
    }

    @Test
    public void return_false_when_telefono_is_too_short(){
        Telefono telefono = new Telefono("63072067");
        Assertions.assertEquals(false, telefono.validar());
    }

    @Test
    public void return_false_when_telefono_has_letters(){
        Telefono telefono = new Telefono("630720f72");
        Assertions.assertEquals(false, telefono.validar());
    }

}
