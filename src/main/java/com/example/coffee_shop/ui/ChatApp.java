package com.example.coffee_shop.ui;

import com.example.coffee_shop.db.DatabaseUtil;
import com.example.coffee_shop.singleton.CoffeeShop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ChatApp extends Application {

	private TextArea chatArea;
	private TextField input;
	private ListView<String> imageList;

	@Override
	public void start(Stage stage) {

		chatArea = new TextArea();
		chatArea.setEditable(false);

		input = new TextField();
		input.setPromptText("Write message or order latte...");

		imageList = new ListView<>();

		// IMAGE PREVIEW
		imageList.setCellFactory(param -> new ListCell<>() {

			private final ImageView imageView = new ImageView();

			{
				imageView.setFitWidth(120);
				imageView.setFitHeight(120);
				imageView.setPreserveRatio(true);
			}

			@Override
			protected void updateItem(String path, boolean empty) {
				super.updateItem(path, empty);

				if (empty || path == null) {
					setGraphic(null);
					setText(null);
				} else {
					Image image = new Image("file:" + path);
					imageView.setImage(image);

					setGraphic(imageView);
					setText(null);
				}
			}
		});

		Button send = new Button("Send");
		send.setOnAction(e -> sendMessage());

		Button uploadBtn = new Button("Upload Photo");
		uploadBtn.setOnAction(e -> uploadImage());

		VBox root = new VBox(10, chatArea, imageList, input, send, uploadBtn);

		Scene scene = new Scene(root, 500, 700);

		stage.setTitle("Coffee Chat");
		stage.setScene(scene);
		stage.show();

		loadHistory();
		loadImages();
	}

	private void sendMessage() {
		String msg = input.getText();

		if (msg == null || msg.isBlank())
			return;

		String reply = CoffeeShop.getInstance().sendChatMessage("User", msg);

		chatArea.appendText("User: " + msg + "\n");
		chatArea.appendText("Barista: " + reply + "\n");

		input.clear();
	}

	private void uploadImage() {
		FileChooser chooser = new FileChooser();

		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));

		File file = chooser.showOpenDialog(null);

		if (file != null) {
			imageList.getItems().add(file.getAbsolutePath());

			DatabaseUtil.saveImage(file.getAbsolutePath());

			chatArea.appendText("User uploaded image: " + file.getName() + "\n");
		}
	}

	private void loadHistory() {
		chatArea.appendText("---- Chat History ----\n");
		chatArea.appendText(DatabaseUtil.getChatHistory());
	}

	private void loadImages() {
		String data = DatabaseUtil.getImages();

		if (data == null || data.isBlank())
			return;

		String[] paths = data.split("\n");

		for (String path : paths) {
			imageList.getItems().add(path);
		}
	}

	public static void main(String[] args) {
		launch();
	}
}
