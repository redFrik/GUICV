GUICVNumberBox : AbstractGUICV {

	prCreateView {|args|
		normalized= false;
		^NumberBox()
		.palette_(QPalette.auto(Color.clear, skin.foreground).setColor(skin.hiliteColor, \highlight))
		.background_(skin.foreground)
		.font_(Font(*skin.fontSpecs))
		.normalColor_(skin.fontColor)
		.typingColor_(skin.hiliteColor)
	}
}
