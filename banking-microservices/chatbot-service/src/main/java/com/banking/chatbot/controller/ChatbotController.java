package com.banking.chatbot.controller;

import com.banking.chatbot.dto.ChatRequest;
import com.banking.chatbot.dto.ChatResponse;
import com.banking.chatbot.service.ChatbotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
@RequiredArgsConstructor
@Tag(name = "Chatbot", description = "API du chatbot bancaire")
public class ChatbotController {
    
    private final ChatbotService service;
    
    @PostMapping("/chat")
    @Operation(summary = "Envoyer un message au chatbot")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        ChatResponse response = service.processMessage(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/welcome/{clientId}")
    @Operation(summary = "Obtenir le message de bienvenue")
    public ResponseEntity<ChatResponse> getWelcomeMessage(@PathVariable Long clientId) {
        ChatResponse response = service.getWelcomeMessage(clientId);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/simulate/transaction")
    @Operation(summary = "Simuler une transaction (pour démonstration)")
    public ResponseEntity<ChatResponse> simulateTransaction(@RequestBody ChatRequest request) {
        // Simulation d'une réponse pour une transaction
        ChatResponse response = new ChatResponse();
        response.setClientId(request.getClientId());
        response.setResponse("""
            Simulation de transaction:
            ✅ Transaction approuvée
            
            Détails:
            • Montant: 1 500,00 EUR
            • Compte destination: FR76 3000 1000 0000 0000 0000 123
            • Date d'exécution: Immédiate
            • Frais: 0,00 EUR
            
            Cette transaction sera traitée dans les prochaines minutes.
            """);
        response.setTimestamp(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.setSessionId(java.util.UUID.randomUUID().toString());
        
        return ResponseEntity.ok(response);
    }
}
