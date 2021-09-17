package com.userproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    @NotBlank
    @Size(min = 2,max = 50)
    private String firstName;

    @Column(name="last_name")
    @NotBlank
    @Size(min = 2,max = 50)
    private String LastName;

    @Column(name="email",unique = true)
    @NotBlank
    @Email(message = "Email should be valid")
    @Size(max = 50)
    private String email;

    @Column(name="date_of_birth")
    @NotNull
    private LocalDate  dateOfBirth;

    @Column(name="created_date")
    @JsonIgnore
    @NotNull
    private LocalDate createdDate = LocalDate.now();

    @Column(name = "phone")
    @NotBlank
    @Pattern(regexp ="^(\\d{3}[- .]?){2}\\d{4}$",message = "Telefon numarası uygun değil.(Ex:500-000-000 )")
    private String phone;

    @Column(name="nationality_id")
    @NotNull
    private Long nationalityId;

    public String getEmail() {
        return email;
    }
}
