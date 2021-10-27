package com.web2.web2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.models.auth.In;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Funcionarios")
public class Funcionarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "Nome")
    private String nome;

    @NotBlank
    @Email
    @Column(name = "Email")
    private String email;

    @NotBlank
    @Column(name = "Telefone")
    private String telefone;

    @NotBlank
    @Column(name = "Setor")
    private String setor;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdFilial")
    private Filial idFilial;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Filial getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(Filial idFilial) {
        this.idFilial = idFilial;
    }
}
