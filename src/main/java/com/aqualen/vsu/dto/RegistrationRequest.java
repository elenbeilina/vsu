package com.aqualen.vsu.dto;

import com.aqualen.vsu.entity.Department;
import com.aqualen.vsu.entity.User;
import com.aqualen.vsu.enums.UserRole;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Value
@Builder
public class RegistrationRequest {
    @NotNull
    UserRole role;
    String studentBookId;
    Long departmentId;
    @NotNull
    String username;
    @NotNull
    String firstName;
    @NotNull
    String secondName;
    String description;
    String password;
    String picture;
    String vk;
    String facebook;
    String instagram;
    String twitter;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate birthday;

    public static User toEntity(RegistrationRequest from) {
        return User.builder()
                .birthday(from.birthday)
                .department(Objects.nonNull(from.departmentId) ? Department.builder().id(from.departmentId).build() : null)
                .description(from.description)
                .facebook(from.facebook)
                .firstName(from.firstName)
                .instagram(from.instagram)
                .password(from.password)
                .picture(from.picture)
                .role(from.role)
                .secondName(from.secondName)
                .twitter(from.twitter)
                .username(from.username)
                .studentBookId(from.studentBookId)
                .vk(from.vk).build();
    }
}
