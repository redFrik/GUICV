GUICVSlider : AbstractGUICV {

	prCreateView {|args|
		^Slider()
		.palette_(QPalette.auto(skin.hiliteColor, skin.foreground))
		.background_(skin.foreground)
		.focusColor_(skin.hiliteColor)
		.knobColor_(skin.hiliteColor)
		//.thumbSize_(0)
	}
}
