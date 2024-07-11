package org.example.hexlet.dto.users;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hexlet.dto.BasePage;

import java.util.List;
import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuildUserPage extends BasePage {
    private Long id;
    private String name;
    private String email;
    private Map<String, List<ValidationError<Object>>> errors;
}
