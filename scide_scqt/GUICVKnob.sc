GUICVKnob : AbstractGUICV {

	prCreateView {|args, skin|
		^Knob()
		.focusColor_(skin.highlight)
		.palette_(skin.palette)  //must set palette before color
		.color_([skin.foreground, skin.highlight, skin.foreground, skin.highlight])
		.minHeight_(skin.knobWidth)
		.minWidth_(skin.knobWidth)
	}
}
