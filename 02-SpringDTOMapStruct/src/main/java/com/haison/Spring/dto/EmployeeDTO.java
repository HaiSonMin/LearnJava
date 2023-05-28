package com.haison.Spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema( // Swagger
        description = "EmployeeDOT Model Information"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @Schema( // Swagger
            description = "ID employee"
    )
    private long id;

    @Schema( // Swagger
            description = "FirstName Employee"
    )
    @NotEmpty(message = "The field FirstName cannot be empty!")
    private String firstName;

    @Schema( // Swagger
            description = "LastName Employee"
    )
    @NotEmpty(message = "The field LastName cannot be empty!")

    private String lastName;

    @Schema( // Swagger
            description = "Email Employee"
    )
    @NotEmpty(message = "The field Email cannot be empty!")
    @Email(message = "Invalid Email!")
    private String emailAddress;

}
