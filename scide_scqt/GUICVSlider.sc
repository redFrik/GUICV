GUICVSlider : AbstractGUICV {

	prCreateView {|args|
		var view= GUICV.slider;
		var tmb= args[\thumbSize];
		if(tmb.notNil, {view.thumbSize_(tmb)});
		^view
	}
}
