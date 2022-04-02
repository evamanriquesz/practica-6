# Practica 6: Testing de una aplicacion de Spring Boot

## Objetivo de la práctica

Dado un desarrollo de Spring Boot, es necesario añadir tests a las siguientes clases:

- DNI & Telefono (Unit Tests) (Cada clase tiene un metodo y varias casuisticas para probar)
- ProcessController (E2E Tests) (2 endpoints)


## Pruebas realizadas

### CLASE DNI : DNITest
* return_true_when_dni_is_valid : devuelve true si introducimos un dni correcto
* return_false_when_dni_is_not_valid : devuelve false si introducimos un dni incorrecto
* return_false_when_not_enough_digits : devuelve false si los introducimos menos de 9 digitos
* return_false_when_incorrect_digit_control : devuelve false si no es un número de dni real, en el que las cifras se correspondan con la letra
* return_false_when_digit_control_not_in_the_list : devuelve false si la letra del dni no se encuentra en la lista de digit_control
* return_false_when_invalido : devuelve false si es uno de los dni que están en la lista de invalidos

### CLASE TELEFONO : TelefonoTest
* return_true_when_telefono_is_valid : devuelve true si el telefono es correcto
* return_false_when_telefono_is_too_short : devuelve false si el telefono tiene menos de 9 cifras
* return_false_when_telefono_has_letters : devuelve false si el telefono tiene letras

### CLASE PROCESSCONTROLLER : ProcessControllerTest
* process_entries_correct_then_ok : devuelve true si nombre, dni y telefono son válidos
* process_entries_bad_dni_then_ko : devuelve false si el dni no es válido
* process_entries_bad_telephone_then_ko : devuelve false si el teléfono no es válido
* process_entries_correct_then_ok_legacy : devuelve true si el nombre, dni y telefono son válidos 
* process_entries_bad_dni_then_ko_legacy : devuelve false si el dni no es válido
* process_entries_bad_telephone_then_ko_legacy : devuelve false si el teléfono no es válido

### COVERAGE 
He ejecutado los test para averiguar cuánto código cubren, y se cubre el 91% de las clases, y el 88% de las líneas de código. 
He intentado probado a realizar los informes de cobertura con JaCoCo pero me daba errores, por lo que he borrado todo lo referente a ello.
