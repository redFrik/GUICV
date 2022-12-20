GUICVSlider : AbstractGUICV {

	prCreateView {|args, skin|
		var view= GUICV.slider(skin);
		var tmb= args[\thumbSize];
		if(tmb.notNil, {view.thumbSize_(tmb)});
		^view
	}
}
