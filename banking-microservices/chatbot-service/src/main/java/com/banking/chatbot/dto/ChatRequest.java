package com.banking.chatbot.dto;

import lombok.Data;

@Data
public class ChatRequest {
    private String message;
    private Long clientId;
}
