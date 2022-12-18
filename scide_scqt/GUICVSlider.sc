GUICVSlider : AbstractGUICV {

	prCreateView {|args|
		var tmb= args.atFail(\thumbSize, {4});

		^Slider()
		.palette_(QPalette.auto(Color.clear, skin.foreground))
		.background_(skin.foreground)
		.focusColor_(skin.hiliteColor)
		.knobColor_(skin.hiliteColor)
		.minSize_(Size(skin.minWidth, skin.minHeight))
		.orientation_(\vertical)
		.thumbSize_(tmb)
	}
}
