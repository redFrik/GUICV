GUICVButton : AbstractGUICVView {

	string_ {|str|
		str= str.asString;
		this.states_(this.states.flop.put(0, str!this.states.size).flop);
	}

	prCreateView {|parent, bounds|
		var skin= GUI.skins.guiCV;
		^GUICV.button(parent, bounds)
		.states= [
			["", skin.fontColor, skin.foreground],
			["", skin.background, skin.highlight]
		]
	}
}
