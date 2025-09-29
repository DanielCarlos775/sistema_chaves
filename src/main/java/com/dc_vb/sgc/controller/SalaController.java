package com.dc_vb.sgc.controller;

//import com.dc_vb.sgc.dao.SalaDAO;

/*public class SalaController {

    @FXML private TableView<Salas> tableSalas;
    @FXML private TableColumn<Salas, Integer> colId_sala;
    @FXML private TableColumn<Salas, String> colNome;
    @FXML private TableColumn<Salas, Integer> colId_Predio;

    private SalaDAO salaDAO;
    private ObservableList<Salas> salas;

    public void initialize() {
        try {
            Connection conn = ConnectionFactory.getConnection();
            salaDAO = new SalaDAO(conn);
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadData() throws SQLException {
        List<Salas> lista = salaDAO.findAll();
        salas = FXCollections.observableArrayList(lista);
        tableSalas.setItems(salas);

        colId_sala.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getId_sala()).asObject());
        colNome.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNome()));
        colId_Predio.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getId_Predio()).asObject());
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
        Salas selected = tableSalas.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                salaDAO.delete(selected.getId_sala());
                salas.remove(selected);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}*/
