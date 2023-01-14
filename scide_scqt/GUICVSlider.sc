GUICVSlider : AbstractGUICVView {

	thumbColor {^this.palette.button}
	thumbColor_ {|col| this.palette_(this.palette.button_(col))}

	prCreateView {|parent, bounds|
		^GUICV.slider(parent, bounds)
	}
}
