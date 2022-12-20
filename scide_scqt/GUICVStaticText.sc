GUICVStaticText : AbstractGUICV {

	initAbstractGUICV {|argRef, argSpec, args, update, skin|
		args.atFail(\string, {args.put(\string, argRef.value)});
		this.view_(this.prCreateView(args.asDict, skin).resizeToHint);
	}

	value {^view.string}
	value_ {|val| view.string_(val)}

	prCreateView {|args, skin|
		var temp= [\static, skin].postln;
		var fnt= Font(
			args.atFail(\fontName, {skin.fontSpecs[0]}),
			args.atFail(\fontSize, {skin.fontSpecs[1]})
		);
		var str= args.atFail(\string, {this.class.name});

		^StaticText()
		.fixedHeight_(skin.buttonHeight)
		.font_(fnt)
		.palette_(skin.palette)
		.string_(str)
	}
}
