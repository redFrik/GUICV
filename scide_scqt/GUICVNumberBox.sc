GUICVNumberBox : AbstractGUICV {

	prCreateView {|args, skin|
		normalized= false;
		^GUICV.numberBox(skin)
	}
}
