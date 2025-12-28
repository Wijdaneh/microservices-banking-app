package com.banking.chatbot.service;

import com.banking.chatbot.dto.ChatRequest;
import com.banking.chatbot.dto.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatbotService {
    
    private final ChatClient chatClient;
    
    public ChatResponse processMessage(ChatRequest request) {
        // Construire le prompt avec le contexte bancaire
        String systemPrompt = buildSystemPrompt(request.getClientId());
        
        String aiResponse = chatClient.prompt()
            .system(systemPrompt)
            .user(request.getMessage())
            .call()
            .content();
        
        ChatResponse response = new ChatResponse();
        response.setClientId(request.getClientId());
        response.setResponse(aiResponse);
        response.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.setSessionId(UUID.randomUUID().toString());
        
        return response;
    }
    
    private String buildSystemPrompt(Long clientId) {
        return """
            Tu es un assistant bancaire intelligent nommé "BankBot". 
            Tu aides les clients avec leurs questions bancaires.
            
            Règles importantes:
            1. Ne donne jamais d'informations personnelles ou sensibles
            2. Si on te demande de faire un virement, explique que cela doit être fait via l'interface officielle
            3. Pour les questions sur les bénéficiaires, dirige vers la section appropriée
            4. Sois poli et professionnel
            5. Réponds en français
            
            Le client ID est: %s
            
            Informations disponibles:
            - Transferts d'argent
            - Gestion des bénéficiaires
            - Consultations de solde (simulées)
            - Informations sur les produits bancaires
            
            Si tu ne sais pas quelque chose, dis-le clairement et propose de contacter le service client.
            """.formatted(clientId);
    }
    
    public ChatResponse getWelcomeMessage(Long clientId) {
        ChatResponse response = new ChatResponse();
        response.setClientId(clientId);
        response.setResponse("""
            Bonjour ! Je suis BankBot, votre assistant bancaire virtuel.
            
            Je peux vous aider avec:
            • Informations sur les virements
            • Gestion des bénéficiaires
            • Questions sur les produits bancaires
            • Conseils généraux
            
            Comment puis-je vous aider aujourd'hui ?
            """);
        response.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.setSessionId(UUID.randomUUID().toString());
        
        return response;
    }
}
