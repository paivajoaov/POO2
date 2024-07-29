package view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Aviao {

    private SimpleIntegerProperty id;
    private SimpleStringProperty nome;
    private SimpleIntegerProperty capacidade;
    private SimpleIntegerProperty tempoMaximoVoo;

    public Aviao(int id, String nome, int capacidade, int tempoMaximoVoo) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.capacidade = new SimpleIntegerProperty(capacidade);
        this.tempoMaximoVoo = new SimpleIntegerProperty(tempoMaximoVoo);
    }

    // Getters and Setters

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public int getCapacidade() {
        return capacidade.get();
    }

    public void setCapacidade(int capacidade) {
        this.capacidade.set(capacidade);
    }

    public int getTempoMaximoVoo() {
        return tempoMaximoVoo.get();
    }

    public void setTempoMaximoVoo(int tempoMaximoVoo) {
        this.tempoMaximoVoo.set(tempoMaximoVoo);
    }
}
