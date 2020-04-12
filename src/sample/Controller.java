package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.io.IOException;

public class Controller {

    @FXML public Button b1;
    @FXML public Button b2;
    @FXML public Button b3;
    @FXML public Button b4;
    @FXML public Button b5;
    @FXML public Button b6;
    @FXML public Button b7;
    @FXML public Button b8;
    @FXML public Button b9;
    @FXML public Label winText;
    @FXML public Button playButt;
    @FXML public Button exitButt;
    @FXML public Button inButton;
    @FXML public Button reset;
    @FXML public Button mainMenu;
    @FXML public GridPane gridBtn;

    Button[] btn = new Button[9];
    boolean turn = true;
    boolean textTurn = false;
    String player = turn ? "UNO":"DOS";
    String textPlayer = textTurn? "UNO":"DOS";
    int uno = 0, dos =0;
//    Label winText = new Label(" ");

    @FXML
    public void setPlayStage() throws IOException{
        Parent gameView = FXMLLoader.load(getClass().getResource("play.fxml"));
        Scene play = new Scene(gameView);
        Stage stage = (Stage) playButt.getScene().getWindow();
        stage.setScene(play);
        stage.show();


    }

    @FXML
    public void setInstructions() throws IOException{
        Parent gameView = FXMLLoader.load(getClass().getResource("instructions.fxml"));
        Scene play = new Scene(gameView);
        Stage stage = (Stage) inButton.getScene().getWindow();
        stage.setScene(play);
        stage.show();


    }

    @FXML
    public void resetBoard(){
        gridBtn.getChildren().toArray(btn);

        for(Button b : btn){
            b.setText("FREE");
            b.setStyle(" -fx-text-fill: black ");
        }
        uno = 0;
        dos = 0;
        turn = true;
        textTurn = !turn;
        textPlayer = !textTurn? "UNO": "DOS";
        winText.setStyle(" -fx-text-fill: red ");
        winText.setText(textPlayer + "'s Turn");

    }

    @FXML
    public void backToMenu() throws IOException{
        resetBoard();
        Parent mainView = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene mainMen = new Scene(mainView);
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.setScene(mainMen);
        stage.show();

    }

    @FXML
    public void boardClicked(ActionEvent event){
        Button clicked = (Button) event.getTarget();

        player = turn ? "UNO":"DOS";
        textPlayer = textTurn? "UNO":"DOS";

        if(uno < 3 || dos <3){
           if(clicked.getText().equals("FREE")){
               gameSelect(clicked);
           }
        }
        else{
            if(clicked.getText().equals(player)){
                gameSelect(clicked);
            }
        }
    }

    @FXML
    public void closeStage() {
        Stage stage = (Stage) exitButt.getScene().getWindow();
        stage.close();
    }

    public void gameSelect(Button board){

        if(uno < 3 || dos < 3){
            if(turn){
                board.setStyle(" -fx-text-fill: red ");
                winText.setStyle(" -fx-text-fill: green ");
                uno++;
            }
            else {
                board.setStyle(" -fx-text-fill: green ");
                winText.setStyle(" -fx-text-fill: red ");
                dos++;
            }

            board.setText(player);
            turn = !turn;
            textTurn = !textTurn;
            winText.setText(textPlayer + "'s Turn");
        }

        else{
            if(turn){
                uno--;
            }
            else {
                dos--;
            }
            board.setText("FREE");
            board.setStyle(" -fx-text-fill: black ");
        }
        if(checkWin()){
            if(player == "DOS"){
                winText.setStyle(" -fx-text-fill: green ");
            }
            else{
                winText.setStyle(" -fx-text-fill: red ");
            }
            winText.setText(player + " WINS!");
        }
    }

    public boolean checkWin(){
        boolean win = false;

        if(b5.getText().equals(player)){
            if(b1.getText().equals(player) && b9.getText().equals(player)){
                win = true;
            }
            if(b3.getText().equals(player) && b7.getText().equals(player)){
                win = true;
            }
            if(b2.getText().equals(player) && b8.getText().equals(player)){
                win = true;
            }
            if(b4.getText().equals(player) && b6.getText().equals(player)){
                win = true;
            }

        }
        return win;
    }




}
