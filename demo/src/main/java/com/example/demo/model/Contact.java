package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "contact_msg")
public class Contact extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy="native")
    private int contact_id;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile Number must not be blank")
    @Pattern(regexp = "[0-9]{10}",message = "Mobile number must have 10 digits")
    private String mobileNum;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid Email format")
    private String email;

    @NotBlank(message = "Subject must not be blank")
    @Size(min = 10, message = "Subject must be at least 10 characters long")
    private String subject;

    @NotBlank(message = "Message must not be blank")
    @Size(min = 30, message = "Message must be at least 30 characters long")
    private String message;

    private String status;
}
