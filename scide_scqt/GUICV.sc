//redFrik

//related: AbstractGUICV

GUICV {

	*initClass {
		var skin;
		Class.initClassTree(Font);
		GUI.skins.put(\guiCV, (
			background: Color.black,
			buttonHeight: 15,
			fontColor: Color.new255(94, 181, 94),
			fontSpecs: [Font.defaultMonoFace, 10],
			foreground: Color.new255(5, 62, 6),
			highlight: Color.new255(94, 249, 94),
			knobWidth: 40,
			margin: Point(4, 4),
			sliderHeight: 75,
			sliderWidth: 20,
			spacing: 4
		));
		skin= GUI.skins.guiCV;
		skin.palette= QPalette.auto(Color.clear, skin.foreground)
		.setColor(skin.foreground, \base)
		.setColor(skin.foreground, \button)
		.setColor(skin.fontColor, \buttonText)
		.setColor(skin.highlight, \highlight)
		.setColor(skin.background, \highlightText)
		.setColor(skin.foreground, \window)
		.setColor(skin.fontColor, \windowText);
	}
}
