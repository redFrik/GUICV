GUICVKnob : AbstractGUICV {

	valueColor {^this.color[1]}
	valueColor_ {|col| this.color_(this.color.put(1, col))}

	prCreateView {|parent, bounds|
		^GUICV.knob(parent, bounds)
	}
}
