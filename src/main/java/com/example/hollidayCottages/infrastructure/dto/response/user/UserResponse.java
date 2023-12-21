package com.example.hollidayCottages.infrastructure.dto.response.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Getter
public class UserResponse {
    String userId;
    String email;
    String password;
    int userTypeId;
}
