package br.unip.dsd.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by dirceu on 9/2/15.
 */
@Entity
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBairro;

    private String nomeDoBairro;

    @ManyToOne
    @JoinColumn(name = "id")
    private Cidade cidade;

    public Bairro(){}

    public Bairro(Long idBairro, String nomeDoBairro, Cidade cidade) {
        this.idBairro = idBairro;
        this.nomeDoBairro = nomeDoBairro;
        this.cidade = cidade;
    }

    public Long getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Long idBairro) {
        this.idBairro = idBairro;
    }

    public String getNomeDoBairro() {
        return nomeDoBairro;
    }

    public void setNomeDoBairro(String nomeDoBairro) {
        this.nomeDoBairro = nomeDoBairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bairro)) return false;

        Bairro bairro = (Bairro) o;

        if (cidade != null ? !cidade.equals(bairro.cidade) : bairro.cidade != null) return false;
        if (idBairro != null ? !idBairro.equals(bairro.idBairro) : bairro.idBairro != null) return false;
        if (nomeDoBairro != null ? !nomeDoBairro.equals(bairro.nomeDoBairro) : bairro.nomeDoBairro != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBairro != null ? idBairro.hashCode() : 0;
        result = 31 * result + (nomeDoBairro != null ? nomeDoBairro.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        return result;
    }
}
