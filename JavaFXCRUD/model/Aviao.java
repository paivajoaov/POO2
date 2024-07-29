package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "aviao")
public class Aviao {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(dataType = com.j256.ormlite.field.DataType.STRING)
    private String nome;

    @DatabaseField(dataType = com.j256.ormlite.field.DataType.INTEGER)
    private int capacidade;

    @DatabaseField(dataType = com.j256.ormlite.field.DataType.INTEGER)
    private int tempoMaximoVoo;

    public Aviao() {
        // Default constructor for ORM
    }

    public Aviao(int id, String nome, int capacidade, int tempoMaximoVoo) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        this.tempoMaximoVoo = tempoMaximoVoo;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getTempoMaximoVoo() {
        return tempoMaximoVoo;
    }

    public void setTempoMaximoVoo(int tempoMaximoVoo) {
        this.tempoMaximoVoo = tempoMaximoVoo;
    }
}
