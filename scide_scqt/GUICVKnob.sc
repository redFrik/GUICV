GUICVKnob : AbstractGUICV {

	prCreateView {|args|
		^Knob()
		.palette_(QPalette.auto(Color.clear, skin.foreground))
		.color_([skin.foreground, skin.hiliteColor, skin.foreground, skin.hiliteColor])
		.focusColor_(skin.hiliteColor)
		.minSize_(Size(skin.minHeight, skin.minHeight))
	}
}
