GUICVNumberBox : AbstractGUICV {

	prCreateView {|parent, bounds, args|
		normalized= false;
		^GUICV.numberBox(parent, bounds)
	}
}
