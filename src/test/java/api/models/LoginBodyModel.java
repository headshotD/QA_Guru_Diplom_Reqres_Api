package api.models;

import lombok.Data;

@Data
public class LoginBodyModel {
    String email, password;
}