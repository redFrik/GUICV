//redFrik

//related: AbstractGUICV

GUICV {
	classvar skin;

	*initClass {

		skin= (
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
		);

		skin.palette= QPalette.auto(Color.clear, skin.foreground)
		.setColor(skin.foreground, \base)
		.setColor(skin.fontColor, \baseText)
		.setColor(skin.foreground, \button)
		.setColor(skin.fontColor, \buttonText)
		.setColor(skin.highlight, \highlight)
		.setColor(skin.background, \highlightText)
		.setColor(skin.foreground, \window)
		.setColor(skin.fontColor, \windowText);

		GUI.skins.put(\guiCV, skin);
	}

	*button {|parent, bounds|
		^Button(parent, bounds)
		.palette_(skin.palette)
		.focusColor_(skin.highlight)
		.font_(Font(*skin.fontSpecs))
	}

	*knob {|parent, bounds|
		^Knob(parent, bounds)
		.palette_(skin.palette)
		.focusColor_(skin.highlight)
		.color_([skin.foreground, skin.highlight, skin.foreground, skin.highlight])
	}

	*numberBox {|parent, bounds|
		^NumberBox(parent, bounds)
		.palette_(skin.palette)
		.font_(Font(*skin.fontSpecs))
		.normalColor_(skin.fontColor)
		.typingColor_(skin.highlight)
	}

	*popUpMenu {|parent, bounds|
		^PopUpMenu(parent, bounds)
		.palette_(skin.palette)
		.font_(Font(*skin.fontSpecs))
		.minWidth_(skin.knobWidth)
	}

	*slider {|parent, bounds|
		^Slider(parent, bounds)
		.palette_(skin.palette)
		.background_(skin.foreground)
		.focusColor_(skin.highlight)
		.knobColor_(skin.highlight)
		.orientation_(\vertical)
		.thumbSize_(4)
	}

	*staticText {|parent, bounds|
		^StaticText(parent, bounds)
		.palette_(skin.palette)
		.font_(Font(*skin.fontSpecs))
	}
}
