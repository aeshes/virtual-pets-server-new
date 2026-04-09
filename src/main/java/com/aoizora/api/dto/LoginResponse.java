package com.aoizora.api.dto;

public record LoginResponse(boolean success, String message, Integer userId, String login, String name) {
}
