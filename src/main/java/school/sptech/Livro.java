package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private List<Avaliacao> avaliacoes; //Para armazenar todas as avaliações de um único livro.

    public Livro(String titulo, String autor, LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.avaliacoes = new ArrayList<>();
    }

    public Livro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public void adicionarAvaliacao(String descricao, Double qtdEstrelas){
        if (descricao == null || descricao.isEmpty() || descricao.isBlank()){
            throw new ArgumentoInvalidoException("ERRO: há algum erro de gramática na descrição, ou está nula.");
        } else if (qtdEstrelas == null || qtdEstrelas < 0 || qtdEstrelas > 5) {
            throw new ArgumentoInvalidoException("ERRO: a quantidade de estrelas deve ser somente entre 0 e 5.");
        }
        Avaliacao avaliacao = new Avaliacao(descricao, qtdEstrelas);
        avaliacoes.add(avaliacao);
    }

    public Double calcularMediaAvaliacoes(){
        if (avaliacoes.isEmpty()){
            return 0.0;
        }
        Double soma = 0.0;
        for (Avaliacao avaliacao : avaliacoes) {
            soma += avaliacao.getQtdEstrelas();
        }
        return soma / avaliacoes.size();
    }

    @Override public String toString(){
        return "Este livro:" + titulo +
                "Com autoria de " + autor +
                "Publicado em " + dataPublicacao +
                "Contém as seguintes avaliações:" + avaliacoes;
    }
}
