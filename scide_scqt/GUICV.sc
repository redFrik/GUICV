//redFrik

//related: AbstractGUICV

GUICV {

	*initClass {

		var skin= (
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
		.setColor(skin.foreground, \button)
		.setColor(skin.fontColor, \buttonText)
		.setColor(skin.highlight, \highlight)
		.setColor(skin.background, \highlightText)
		.setColor(skin.foreground, \window)
		.setColor(skin.fontColor, \windowText);

		GUI.skins.put(\guiCV, skin);
	}

	*button {|skin|
		^Button()
		.palette_(skin.palette)
		.fixedHeight_(skin.buttonHeight)
		.font_(Font(*skin.fontSpecs))
		.maxWidth_(skin.knobWidth)
	}

	*knob {|skin|
		^Knob()
		.palette_(skin.palette)
		.focusColor_(skin.highlight)
		.color_([skin.foreground, skin.highlight, skin.foreground, skin.highlight])
		.minHeight_(skin.knobWidth)
		.minWidth_(skin.knobWidth)
	}

	*numberBox {|skin|
		^NumberBox()
		.palette_(skin.palette)
		.fixedHeight_(skin.buttonHeight)
		.font_(Font(*skin.fontSpecs))
		.minWidth_(skin.knobWidth)
		.normalColor_(skin.fontColor)
		.typingColor_(skin.highlight)
	}

	*popUpMenu {|skin|
		^PopUpMenu()
		.palette_(skin.palette)
		.fixedHeight_(skin.buttonHeight)
		.font_(Font(*skin.fontSpecs))
		.minWidth_(skin.knobWidth)
	}

	*slider {|skin|
		^Slider()
		.palette_(skin.palette)
		.background_(skin.foreground)
		.focusColor_(skin.highlight)
		.knobColor_(skin.highlight)
		.minSize_(Size(skin.sliderWidth, skin.sliderHeight))
		.orientation_(\vertical)
		.thumbSize_(4)
	}

	*staticText {|skin|
		^StaticText()
		.palette_(skin.palette)
		.font_(Font(*skin.fontSpecs))
	}
}
