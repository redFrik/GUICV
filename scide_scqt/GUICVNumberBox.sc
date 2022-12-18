GUICVNumberBox : AbstractGUICV {

	prCreateView {|args|
		normalized= false;
		^NumberBox()
		.palette_(QPalette.auto(Color.clear, skin.foreground).setColor(skin.hiliteColor, \highlight))
		.background_(skin.foreground)
		.font_(Font(*skin.fontSpecs))
		.minSize_(Size(skin.minWidth, skin.fontSpecs.last*1.2))
		.normalColor_(skin.fontColor)
		.typingColor_(skin.hiliteColor)
	}
}
