package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import model.AviaoRepositorio;
import view.Aviao;

public class AppController implements Initializable {
    @FXML
    private TableView<Aviao> tabela;
    @FXML
    private TableColumn<Aviao, Integer> idCol;
    @FXML
    private TableColumn<Aviao, String> nomeCol;
    @FXML
    private TableColumn<Aviao, Integer> capacidadeCol;
    @FXML
    private TableColumn<Aviao, Integer> tempoMaximoVooCol;
    @FXML
    private TextField idField;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField capacidadeField;
    @FXML
    private TextField tempoMaximoVooField;
    @FXML
    private Button adicionarButton;
    @FXML
    private Button atualizarButton;
    @FXML
    private Button deletarButton;    
    @FXML
    private Button cancelarButton;    
    @FXML
    private Button salvarButton;
    
    private AviaoRepositorio aviaoRepo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        aviaoRepo = new AviaoRepositorio(new model.Database("app.sqlite"));
        configureTable();
        tabela.setItems(loadAllAviões());
        tabela.getSelectionModel().selectedItemProperty().addListener(
            (observableValue, oldSelection, newSelection) -> handleAviaoSelected(newSelection));
    }

    private void configureTable() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        capacidadeCol.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        tempoMaximoVooCol.setCellValueFactory(new PropertyValueFactory<>("tempoMaximoVoo"));
    }
    
    private ObservableList<Aviao> loadAllAviões() {
        ObservableList<Aviao> lista = FXCollections.observableArrayList();
        List<model.Aviao> listaFromDatabase = aviaoRepo.loadAll();
        for (model.Aviao aviao : listaFromDatabase) {
            lista.add(modelToView(aviao));
        }
        return lista;
    }

    private Aviao modelToView(model.Aviao aviao) {
        return new Aviao(
            aviao.getId(),
            aviao.getNome(),
            aviao.getCapacidade(),
            aviao.getTempoMaximoVoo()
        );
    }

    @FXML
    private void onAdicionarButtonAction() {
        tabela.getSelectionModel().select(-1);
        desabilitarCampos(false);
        desabilitarBotoes(true, true, true, false, false);
        limparCampos();
    }
    
    @FXML
    private void onSalvarButtonAction() {
        try {
            model.Aviao aviao = new model.Aviao();
            aviao.setNome(nomeField.getText());
            aviao.setCapacidade(Integer.parseInt(capacidadeField.getText()));
            aviao.setTempoMaximoVoo(Integer.parseInt(tempoMaximoVooField.getText()));
            model.Aviao aviaoSalvo = aviaoRepo.create(aviao); 
            Aviao aviaoView = modelToView(aviaoSalvo);
            tabela.getItems().add(aviaoView);
            tabela.getSelectionModel().select(aviaoView);
            desabilitarCampos(true);
            desabilitarBotoes(false, true, true, true, true);            
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Erro ao salvar: " + e.getMessage()).show();
        }
    }
    
    @FXML
    private void onAtualizarButtonAction() {
        // Implementar a lógica para atualizar o avião selecionado
    }
    
    @FXML
    private void onDeletarButtonAction() {
        // Implementar a lógica para deletar o avião selecionado
    }
    
    @FXML
    private void onCancelarButtonAction() {
        desabilitarCampos(true);
        desabilitarBotoes(false, true, true, true, true);
        limparCampos();
        tabela.getSelectionModel().select(-1);
    }

    private void handleAviaoSelected(Aviao newSelection) {
        if (newSelection != null) {
            idField.setText(Integer.toString(newSelection.getId()));
            nomeField.setText(newSelection.getNome());
            capacidadeField.setText(Integer.toString(newSelection.getCapacidade()));
            tempoMaximoVooField.setText(Integer.toString(newSelection.getTempoMaximoVoo()));
            desabilitarBotoes(false, false, false, true, true);
        }
    }

    private void desabilitarCampos(boolean desabilitado) {
        nomeField.setDisable(desabilitado);
        capacidadeField.setDisable(desabilitado);
        tempoMaximoVooField.setDisable(desabilitado);
    }

    private void desabilitarBotoes(boolean adicionar, boolean atualizar, boolean deletar, boolean cancelar, boolean salvar) {
        adicionarButton.setDisable(adicionar);
        atualizarButton.setDisable(atualizar);
        deletarButton.setDisable(deletar);
        cancelarButton.setDisable(cancelar);
        salvarButton.setDisable(salvar);
    }

    private void limparCampos() {
        idField.setText("");
        nomeField.setText("");
        capacidadeField.setText("");
        tempoMaximoVooField.setText("");
    }
}
