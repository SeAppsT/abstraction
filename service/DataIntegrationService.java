package com.example.beck.service;

import com.example.beck.domain.Link;
import com.example.beck.dto.AuthDto;
import com.example.beck.dto.DataBlockDto;
import org.springframework.stereotype.Service;

@Service
public class DataIntegrationService {
    private String login;
    private String password;

    public DataIntegrationService (){}

    public void setCredentials(AuthDto authDto){
        this.login = authDto.getLogin();
        this.password = authDto.getPassword();
    }

    public void setDataBlockInfo(Link link){
        DataBlockDto dataBlockDto = this.queryForBlock(link.getLink());
        link.setName(dataBlockDto.name);
        StringBuilder str = new StringBuilder();
        str
                .append(dataBlockDto.blocks_count)
                .append(" блоков, ")
                .append(dataBlockDto.contents_count)
                .append(" контентов");
        link.setDescription(str.toString());
        link.setId(dataBlockDto.id);
    }

    public DataBlockDto queryForBlock(String link){
        AuthDto authDto = new AuthDto();
        authDto.setLogin(this.login);
        authDto.setPassword(this.password);
        return new DataBlockDto();
    }
}