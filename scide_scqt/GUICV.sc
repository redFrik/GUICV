//redFrik

//related: AbstractGUICVView

GUICV {

	*initClass {

		var skin= (
			background: Color.black,
			buttonHeight: 15,
			fontColor: Color.new255(94, 181, 94),
			fontSpecs: [Font.defaultMonoFace, 10],
			fontSmallSpecs: [Font.defaultMonoFace, 8],
			foreground: Color.new255(5, 62, 6),
			highlight: Color.new255(94, 249, 94),
			knobWidth: 40,
			margin: Point(4, 4),
			sliderHeight: 75,
			sliderWidth: 20,
			spacing: 4
		);

		skin.palette= QPalette.auto(Color.clear, skin.foreground)
		.setColor(skin.foreground, \base)
		.setColor(skin.fontColor, \baseText)
		.setColor(skin.foreground, \button)
		.setColor(skin.fontColor, \buttonText)
		.setColor(skin.highlight, \highlight)
		.setColor(skin.background, \highlightText)
		.setColor(skin.foreground, \window)
		.setColor(skin.fontColor, \windowText);

		GUI.skins.put(\guiCV, skin);
	}

	*button {|parent, bounds| ^this.adapt(Button(parent, bounds))}
	*knob {|parent, bounds| ^this.adapt(Knob(parent, bounds))}
	*levelIndicator {|parent, bounds| ^this.adapt(LevelIndicator(parent, bounds))}
	*numberBox {|parent, bounds| ^this.adapt(NumberBox(parent, bounds))}
	*popUpMenu {|parent, bounds| ^this.adapt(PopUpMenu(parent, bounds))}
	*slider {|parent, bounds| ^this.adapt(Slider(parent, bounds))}
	*staticText {|parent, bounds| ^this.adapt(StaticText(parent, bounds))}
	*textField {|parent, bounds| ^this.adapt(TextField(parent, bounds))}

	*adapt {|view|
		var skin= GUI.skins.guiCV;
		view.palette_(skin.palette);
		switch(view.class,
			Button, {
				view.focusColor_(skin.highlight)
				.font_(Font(*skin.fontSpecs))
			},
			Knob, {
				view.focusColor_(skin.highlight)
				.color_([skin.foreground, skin.highlight, skin.foreground, skin.highlight])
			},
			LevelIndicator, {
				view.background_(skin.background)
				.critical_(-1.dbamp)
				.criticalColor_(Color.white)
				.drawsPeak_(true)
				.meterColor_(skin.highlight)
				.stepWidth_(1)
				.style_(\led)
				.warning_(-3.dbamp)
				.warningColor_(skin.highlight.blend(Color.white))
			},
			NumberBox, {
				view.font_(Font(*skin.fontSpecs))
				.normalColor_(skin.fontColor)
				.typingColor_(skin.highlight)
			},
			PopUpMenu, {
				view.font_(Font(*skin.fontSpecs))
				.minWidth_(skin.knobWidth)
			},
			Slider, {
				view.orientation_(\vertical);
				if(view.bounds.notNil and:{view.bounds.width>view.bounds.height}, {
					view.orientation_(\horizontal);
				});
				view.background_(skin.foreground)
				.focusColor_(skin.highlight)
				.knobColor_(skin.highlight)
				.thumbSize_(4)
			},
			StaticText, {
				view.font_(Font(*skin.fontSpecs))
			},
			TextField, {
				view.font_(Font(*skin.fontSpecs))
			}
		);
		^view
	}

	*fixDec {|val, numDecimals= 2|  //float to string with fixed number of decimals
		var str= val.round(0.1**numDecimals).asString;
		var num= str.size-str.indexOf($.)-1;
		^str.extend(str.size+numDecimals-num, $0)
	}
}
