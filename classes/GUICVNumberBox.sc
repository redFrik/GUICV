GUICVNumberBox : AbstractGUICV {

	map {|val| ^spec.constrain(val)}
	unmap {|val| ^spec.constrain(val)}

	prCreateView {|args|
		^NumberBox()
		.palette_(QPalette.auto(Color.clear, skin.foreground).setColor(skin.hiliteColor, \highlight))
		.background_(skin.foreground)
		.font_(Font(*skin.fontSpecs))
		.normalColor_(skin.fontColor)
		.typingColor_(skin.hiliteColor)
	}
}
