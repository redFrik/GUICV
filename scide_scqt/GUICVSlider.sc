GUICVSlider : AbstractGUICV {

	prCreateView {|parent, bounds, args|
		var view= GUICV.slider(parent, bounds);
		var tmb= args[\thumbSize];
		if(tmb.notNil, {view.thumbSize_(tmb)});
		^view
	}
}
