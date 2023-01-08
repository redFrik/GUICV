GUICVSlider : AbstractGUICV {

	prCreateView {|parent, bounds, args|
		var view= GUICV.slider(parent, bounds);
		var tmb= args[\thumbSize];
		var but= args[\button];
		if(tmb.notNil, {view.thumbSize_(tmb)});
		if(but.notNil, {view.palette_(view.palette.button_(but))});
		^view
	}
}
