package com.dc_vb.sgc.controller;

//import com.dc_vb.sgc.dao.ChaveDAO;


/*public class ChaveController {

    @FXML private TableView<Chaves> tableChave;
    @FXML private TableColumn<Chaves, Integer> colId;
    @FXML private TableColumn<Chaves, String> colIdentificador;
    @FXML private TableColumn<Chaves, Integer> colSalaId;

    private ChaveDAO chaveDAO;
    private ObservableList<Chaves> chaves;

    public void initialize() {
        try {
            Connection conn = ConnectionFactory.getConnection();
            chaveDAO = new ChaveDAO(conn);
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadData() throws SQLException {
        List<Chaves> lista = chaveDAO.findAll();
        chaves = FXCollections.observableArrayList(lista);
        tableChave.setItems(chaves);

        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getId_chave()).asObject());
        colIdentificador.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCodigo()));
        colSalaId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getId_sala()).asObject());
    }

    @FXML
    private void handleAdd() {
        // TODO: abrir diálogo de cadastro
    }

    @FXML
    private void handleEdit() {
        // TODO: abrir diálogo de edição
    }

    @FXML
    private void handleDelete() {
        Chaves selected = tableChave.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                chaveDAO.delete(selected.getId_chave());
                chaves.remove(selected);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}*/
