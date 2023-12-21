package com.example.hollidayCottages.infrastructure.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    String email;
    String password;
}
