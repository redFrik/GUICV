GUICVNumberBox : AbstractGUICV {

	prCreateView {|args|
		normalized= false;

		^NumberBox()
		.fixedHeight_(skin.buttonHeight)
		.font_(Font(*skin.fontSpecs))
		.maxWidth_(skin.knobWidth)
		.palette_(skin.palette)
		.normalColor_(skin.fontColor)
		.typingColor_(skin.highlight)
	}
}
