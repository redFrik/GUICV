GUICVNumberBox : AbstractGUICV {

	prCreateView {|parent, bounds|
		normalized= false;
		^GUICV.numberBox(parent, bounds)
	}
}
