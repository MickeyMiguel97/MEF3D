package pagination;

import java.io.File;
import java.net.URI;
import java.util.stream.Stream;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.testng.annotations.Test;
import sun.util.resources.cldr.lu.CurrencyNames_lu;

// DisplayShelf
public class DisplayShelf extends Application {

	private Image Image;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		//String path = Test.class.getResource("/pagination/musi.mp3").toString();
		//Media media = new Media(path);
		Media media = new Media(getClass().getResource("/pagination/musi.mp3").toExternalForm());
		MediaPlayer mp = new MediaPlayer(media);
		mp.play();

		System.out.println("Playing...");

		HostServices hs = getHostServices();
		String folder = hs.resolveURI(hs.getDocumentBase(), "imgs/animals/");

		int[] index = {0};

		//Image imageInicio = new Image(getClass().getResourceAsStream("/imgs/animals/animal14"));
		//ImageView imageViewInicio = new ImageView(imageInicio);

		//Unit[] images = {imageInicio};

		Unit[] images =
				Stream.of(new File(new URI(folder).getPath()).list())
						.map(name -> hs.resolveURI(folder, name))
						.map(url -> new Unit(url, index[0]++))
						.toArray(Unit[]::new);

		Group container = new Group();
		container.setStyle("-fx-background-color:derive(black, 20%)");
		//container.setStyle("\"-fx-background-color: linear-gradient(#E4EAA2, #9CD672);\"");
		//container.setEffect();
		container.getChildren().addAll(images);

		Slider slider = new Slider(0, images.length -1, 0);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(0);
		slider.setBlockIncrement(1);
		//slider.setBackground();
		slider.setSnapToTicks(true);
		
		container.getChildren().add(slider);

		Scene scene = new Scene(container, 1200, 500, true);//tamaño de la scena
		scene.setFill(Color.rgb(33,33,33));


		stage.setScene(scene);
		stage.getScene().setCamera(new PerspectiveCamera());
		stage.setResizable(false);
		stage.setMaximized(true);
		stage.setX(70);
		stage.setY(85);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();

		slider.translateXProperty().bind(stage.widthProperty().divide(2).subtract(slider.widthProperty().divide(2)));
		//slider.translateYProperty().bind(stage.widthProperty().divide(2).subtract(slider.widthProperty().divide(2)));
		slider.setTranslateY(10);
		slider.setLayoutY(15);
		slider.setPrefWidth(200);
		slider.setPrefHeight(50);
		slider.setPrefSize(200, 50);

		// FxTransformer.sliders(new DoubleProperty[] {x, z, rotation}, new String[] {"x", "z", "rotation"}, stage, -360, 360).show();
		// new FxTransformer(images, IntStream.range(0, images.length).mapToObj(i -> "images["+i+"]").toArray(String[]::new), stage, -500, 1000).show();

		slider.valueProperty().addListener((p, o, n) -> {
			if(n.doubleValue() == n.intValue())
				Stream.of(images).forEach(u -> u.update(n.intValue(), stage.getWidth(), stage.getHeight()));
		});

		container.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if(e.getTarget() instanceof Unit)
				slider.setValue(((Unit)e.getTarget()).index);
		});
		
		Button close = new Button("X");
		close.setOnAction(e -> System.exit(0));
		//close.setOnAction(event -> slider.setValue(4));
		close.getStyleClass().clear();
		//close.setTextFill(Color.rgb(255,0,0));
		close.setLayoutX(-15);
		close.setLayoutY(5);
		close.setStyle("-fx-text-fill:red;-fx-font-size:20;-fx-font-weight:bold;-fx-font-family:'Comic Sans MS';");
		container.getChildren().add(close);
		close.translateXProperty().bind(stage.widthProperty().subtract(15));

		Button Modelo = new Button("Modelo");
		Modelo.setOnAction( event -> slider.setValue(3));
		Modelo.setLayoutX(-1050);
		Modelo.setLayoutY(120);
		Modelo.setTextFill(Color.rgb(178,19,31));
		Modelo.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-font-family:'Comic Sans MS';");
		container.getChildren().add(Modelo);
		Modelo.translateXProperty().bind(stage.widthProperty().subtract(15));

		Button Dominio = new Button("Dominio");
		Dominio.setOnAction( event -> slider.setValue(4));
		Dominio.setLayoutX(-950);
		Dominio.setLayoutY(120);
		Dominio.setTextFill(Color.rgb(232,34,47));
		Dominio.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-font-family:'Comic Sans MS';");
		container.getChildren().add(Dominio);
		Dominio.translateXProperty().bind(stage.widthProperty().subtract(15));

		Button P1 = new Button("Paso 1");
		P1.setOnAction( event -> slider.setValue(5));
		P1.setLayoutX(-800);
		P1.setLayoutY(120);
		P1.setTextFill(Color.rgb(255,165,59));
		P1.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-font-family:'Comic Sans MS';");
		container.getChildren().add(P1);
		P1.translateXProperty().bind(stage.widthProperty().subtract(15));

		Button P2 = new Button("Paso 2");
		P2.setOnAction( event -> slider.setValue(6));
		P2.setLayoutX(-700);
		P2.setLayoutY(120);
		P2.setTextFill(Color.rgb(227,202,63));
		P2.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-font-family:'Comic Sans MS';");
		container.getChildren().add(P2);
		P2.translateXProperty().bind(stage.widthProperty().subtract(15));

		Button P3 = new Button("Paso 3");
		P3.setOnAction( event -> slider.setValue(7));
		P3.setLayoutX(-600);
		P3.setLayoutY(120);
		P3.setTextFill(Color.rgb(159,223,68));
		P3.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-font-family:'Comic Sans MS';");
		container.getChildren().add(P3);
		P3.translateXProperty().bind(stage.widthProperty().subtract(15));

		Button P4 = new Button("Paso 4");
		P4.setOnAction( event -> slider.setValue(8));
		P4.setLayoutX(-500);
		P4.setLayoutY(120);
		P4.setTextFill(Color.rgb(31,154,54));
		P4.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-font-family:'Comic Sans MS';");
		container.getChildren().add(P4);
		P4.translateXProperty().bind(stage.widthProperty().subtract(15));

		Button P5 = new Button("Paso 5");
		P5.setOnAction( event -> slider.setValue(9));
		P5.setLayoutX(-400);
		P5.setLayoutY(120);
		P5.setTextFill(Color.rgb(2,93,190));
		P5.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-font-family:'Comic Sans MS';");
		container.getChildren().add(P5);
		P5.translateXProperty().bind(stage.widthProperty().subtract(15));

		Button P6 = new Button("Paso 6");
		P6.setOnAction( event -> slider.setValue(9));
		P6.setLayoutX(-400);
		P6.setLayoutY(120);
		P6.setTextFill(Color.rgb(125,40,186));
		P6.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-font-family:'Comic Sans MS';");
		container.getChildren().add(P6);
		P6.translateXProperty().bind(stage.widthProperty().subtract(15));

		Button P7 = new Button("Paso 7");
		P7.setOnAction( event -> {
		    AnchorPane pocta = new AnchorPane();
            Media die = new Media(getClass().getResource("/pagination/pb.mp4").toExternalForm());
            MediaPlayer yanowe = new MediaPlayer(die);
            yanowe.setAutoPlay(true); //POCTA
			MediaView aiuda = new MediaView(yanowe);
            pocta.getChildren().add(aiuda);
            pocta.setMaxHeight(500);
            pocta.setMaxWidth(500);
			Scene video = new Scene(pocta, 500, 500, true);//tamaño de la scena
            Stage enma = new Stage();
            enma.setMaximized(true);
            enma.setScene(video);
            enma.show();
		});
        P7.setLayoutX(-300);
        P7.setLayoutY(120);
        P7.setTextFill(Color.rgb(176,32,170));
        P7.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-font-family:'Comic Sans MS';");
        container.getChildren().add(P7);
        P7.translateXProperty().bind(stage.widthProperty().subtract(15));

		Button P8 = new Button("Paso 8");
		P8.setOnAction( event -> {
			AnchorPane pocta = new AnchorPane();
			Media die = new Media(getClass().getResource("/pagination/pb.mp4").toExternalForm());
			MediaPlayer yanowe = new MediaPlayer(die);
			MediaView aiuda = new MediaView(yanowe);
			pocta.getChildren().add(aiuda);

			Button Play = new Button("Play");
			Play.setOnAction( t -> { yanowe.play();});
			Play.setLayoutX(-200);
			Play.setLayoutY(120);
			Play.setTextFill(Color.rgb(125,40,186));
			Play.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-font-family:'Comic Sans MS';");
			pocta.getChildren().add(Play);

			Button Reset = new Button("Reset");
			Reset.setOnAction( t -> { yanowe.stop();});
			Reset.setLayoutX(-300);
			Reset.setLayoutY(120);
			Reset.setTextFill(Color.rgb(125,40,186));
			Reset.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-font-family:'Comic Sans MS';");
			pocta.getChildren().add(Reset);

			pocta.getChildren().get(1).setLayoutX(100);
			pocta.getChildren().get(1).setLayoutY(120);

			pocta.getChildren().get(2).setLayoutX(300);
			pocta.getChildren().get(2).setLayoutY(500);

			//yanowe.setAutoPlay(true); //POCTA

			pocta.setMaxHeight(500);
			pocta.setMaxWidth(500);
			Scene video = new Scene(pocta, 500, 500, true);//tamaño de la scena
			Stage enma = new Stage();

			enma.setMaximized(true);
			enma.setScene(video);
			enma.show();
		});
		P8.setLayoutX(-300);
		P8.setLayoutY(120);
		P8.setTextFill(Color.rgb(127,82,69));
		P8.setStyle("-fx-font-size:20;-fx-font-weight:bold;-fx-font-family:'Comic Sans MS';");
		container.getChildren().add(P8);
		P8.translateXProperty().bind(stage.widthProperty().subtract(15));

		slider.setValue(2); //iniciar en la imagen 0
	}

	private static class Unit extends ImageView {
		final static Reflection reflection = new Reflection();
		final static Point3D  rotationAxis = new Point3D(5, 90, 3);

		static {
			reflection.setFraction(0.5);
		}

		final int index;
		final Rotate rotate = new Rotate(0, rotationAxis);
		final TranslateTransition transition = new TranslateTransition(Duration.millis(300), this);

		public Unit(String imageUrl, int index) {
			super(imageUrl);
			setLayoutY(350);//mover pa abajo
			setFitWidth(510);
			setFitHeight(350);
			setEffect(reflection);
			setUserData(index);

			this.index = index;
			getTransforms().add(rotate);
		}
		public void update(int currentIndex, double width, double height) {
			int ef = index - currentIndex;
			double middle = width / 2 - 200; //ubicacion de la imagen
			boolean b = ef < 2; //ocula la siguiente imagen

			setTranslateY(height/2 - getImage().getHeight()/1.5); //ubicacion de la imagen
			double x,z, theta, pivot;

			if(ef == 0) {
				z = -300;
				x = middle;
				theta = 0;
				pivot = b ? 200 : 0;
			}
			else {
				x = middle + ef * 82 + (b ? -147 : 147);
				z = -78.588;
				pivot = b ? 200 : 0 ;
				theta = b ? 46 : -46;
			}
			rotate.setPivotX(pivot);
			rotate.setAngle(theta);

			transition.pause();
			transition.setToX(x);
			transition.setToZ(z);
			transition.play();
		}

	}

}