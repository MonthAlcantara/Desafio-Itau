package io.github.monthalcantara.cadastroclientesapi.validation.annotations;


import io.github.monthalcantara.cadastroclientesapi.validation.validators.MaiorIdadeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MaiorIdadeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaiorIdade {

    String message() default "{io.github.monthalcantara.cadastroclientesapi.validation.annotations.MaiorIdade}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int maiorQue() default 18;

}
