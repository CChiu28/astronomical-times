package com.astronomicaltimes;

import com.jfoenix.controls.JFXTextArea;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Definitions {
	public HBox setDef() {
		HBox box = new HBox(5);
		box.setAlignment(Pos.CENTER);

		ImageView imgView;
		Image img = new Image(Definitions.class.getResourceAsStream("resources/twiligh-phases.png"));
		imgView = new ImageView(img);
		
		JFXTextArea def = new JFXTextArea();
		
		def.setText("Sunrise: Defined in astronomy, sunrise is when the upper limb of the sun appears on the horizon\n\n"
				+ "Sunset: Defined in astronomy, sunset is when the upper limb of the sun disappears below the horizon\n\n"
				+ "Day Length: The day length marks the period of time from sunrise to sunset. The brief moment of light " 
				+ "when the sun is below the horizon is called twilight.\n\n"
				+ "Astronomical Twilight: This is the period of dawn and dusk when light is dimmest. During this time, "
				+ "the sun's geometric center is 12-18 degrees below the horizon. Astronomical Dawn and Dusk marks the "
				+ "end and beginning of nighttime respectively. To the naked eye, it may be difficult to distinguish the "
				+ "difference between astronomical twilight and night time. Most stars can be seen during this phase.\n\n"
				+ "Nautical Twilight: This is the period of twilight when the sun's geometric center is 6-12 degrees below "
				+ "the horizon. The horizon is faintly visible during this time and brighter stars are still visible, making "
				+ "it possible to navigate at sea, hence the name nautical twilight. Nautical dusk marks the beginning of astronomical twilight "
				+ "and nautical dawn is preceded by astronomical twilight.\n\n"
				+ "Civil Twilight: This period of twilight marks the sun's geometric center at most 6 degrees below the horizon. "
				+ "It marks the period of time just before the sunrise and immediately after sunset. "
				+ "The sky is colored yellow and orange at this point, and only the brightest stars and planets such as Venus and Jupiter are visible. "
				+ "Civil dawn is preceded by nautical twilight while civil dusk marks the beginning of nautical twilight.\n\n"
				+ "Solar Noon: Also known as high noon, this is the instance when the sun is at the highest point in the sky "
				+ "given the location's local meridian. It usually doesn't occur at 12:00. The poles also do not experience "
				+ "solar noon because they do not have a meridian the sun crosses.\n\n");
		def.setStyle("-fx-text-fill: #0061ff");
		box.getChildren().addAll(def, imgView);
		box.setMargin(def, new Insets(30,10,25,10));
		
		return box;
	}
}
