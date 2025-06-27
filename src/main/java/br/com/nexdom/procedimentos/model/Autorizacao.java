package br.com.nexdom.procedimentos.model;

import javax.persistence.*;

@Entity
@Table(name = "autorizacao")
public class Autorizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer procedimento;
    private Integer idade;
    @Column(length = 1)
    private String sexo;
    private Boolean permitido;

    public Autorizacao() {}

    public Autorizacao(Long id, Integer idade, Boolean permitido, Integer procedimento, String sexo) {
        this.id = id;
        this.idade = idade;
        this.permitido = permitido;
        this.procedimento = procedimento;
        this.sexo = sexo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Integer procedimento) {
        this.procedimento = procedimento;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Boolean getPermitido() {
        return permitido;
    }

    public void setPermitido(Boolean permitido) {
        this.permitido = permitido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
