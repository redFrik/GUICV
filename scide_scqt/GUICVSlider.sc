GUICVSlider : AbstractGUICV {

	prCreateView {|args|
		var tmb= args.atFail(\thumbSize, {4});

		^Slider()
		.background_(skin.foreground)
		.focusColor_(skin.highlight)
		.knobColor_(skin.highlight)
		.minSize_(Size(skin.sliderWidth, skin.sliderHeight))
		.orientation_(\vertical)
		.palette_(skin.palette)
		.thumbSize_(tmb)
	}
}
