package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public Biblioteca() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    //O atributo "livros" não deve possuir getters e setters, segundo os testes.
    //A lista de "livros" ao criar o objeto não pode ser nula, segundo os testes.

    public void adicionarLivro(Livro livro) {
        if (livro == null) {
            throw new ArgumentoInvalidoException("ERRO: o livro não pode ser nulo");
        }
        //Mesmo que não pede no readme, é necessário usar o ".trim()" para garantir que não há apenas espaços em branco no título e autor.
        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            throw new ArgumentoInvalidoException("ERRO: o título não pode ser nulo ou vazio.");
        }
        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty()) {
            throw new ArgumentoInvalidoException("ERRO: o autor não pode ser nulo ou vazio.");
        }
        if (livro.getDataPublicacao() == null) {
            throw new ArgumentoInvalidoException("ERRO: data não pode ser nula.");
        }
        livros.add(livro);
    }

    public void removerLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ArgumentoInvalidoException("ERRO: livro não encontrado.");
        }
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                livros.remove(i);
                return; //Interrompe o loop assim que o livro for removido.
                // Sem o return, o loop continuaria desnecessariamente verificando os outros livros,
                // e a exceção seria lançada mesmo após a remoção bem-sucedida.

            }
        }
        throw new LivroNaoEncontradoException("ERRO: livro não encontrado.");
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ArgumentoInvalidoException("ERRO: livro não encontrado.");
        }
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        throw new LivroNaoEncontradoException("ERRO: o livro não foi encontrado.");
    }

    public Integer contarLivros() {
        return livros.size();
    }

    public List<Livro> obterLivrosAteAno(Integer ano) {
        List<Livro> livrosDoAno = new ArrayList<>();
        for (Livro livro : livros) {
            Integer anoPublicado = livro.getDataPublicacao().getYear();
            if (anoPublicado <= ano) {
                livrosDoAno.add(livro);
            }
        }
        return livrosDoAno;
    }

    public List<Livro> retornarTopCincoLivros() {
        List<Livro> listaCincoLivros = new ArrayList<>();

        if (livros.isEmpty()) {
            return listaCincoLivros;
        }

        for (int i = 0; i < livros.size(); i++) {
            Livro livroAtual = livros.get(i);

            listaCincoLivros.add(livroAtual);
        }

        listaCincoLivros.sort((livro1, livro2) -> Double.compare(livro2.calcularMediaAvaliacoes(), livro1.calcularMediaAvaliacoes()));

        if (listaCincoLivros.size() > 5) {
            return listaCincoLivros.subList(0, 5);
        }
        return listaCincoLivros;
    }

}
