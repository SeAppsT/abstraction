package com.example.beck.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "files")
public class Media extends BaseEntity {

    @Column(nullable = false)
    @Value("${files.path}")
    protected String path;

    @Column(nullable = false)
    protected String type;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Component component;


    @PrePersist
    public void generatePath(){
        Claims claims = Jwts.claims().setSubject("filename");
        claims.put("name", this.name);
        this.path += Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "abstractionFile")
                .setClaims(claims)
                .setIssuedAt(new Date())
                .compact() + ".seapps";
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}