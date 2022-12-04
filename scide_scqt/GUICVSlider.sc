GUICVSlider : AbstractGUICV {

	prCreateView {|args|
		var tmb= args.atFail(\thumbSize, {4});

		^Slider()
		.palette_(QPalette.auto(Color.clear, skin.foreground))
		.background_(skin.foreground)
		.focusColor_(skin.hiliteColor)
		.knobColor_(skin.hiliteColor)
		.thumbSize_(tmb)
		.orientation_(\vertical)
	}
}
