package com.gestionterrain.service;

import com.gestionterrain.entity.Client;
import com.gestionterrain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
    
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
    
    public void save(Client client) {
        clientRepository.save(client);
    }
    
    public void update(Client client) {
        clientRepository.save(client);
    }
    
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
    
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email).orElse(null);
    }
}
