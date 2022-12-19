GUICVStaticText : AbstractGUICV {

	initAbstractGUICV {|argRef, argSpec, args, update|
		args.atFail(\string, {args.put(\string, argRef.value)});
		this.view_(this.prCreateView(args.asDict).resizeToHint);
	}

	value {^view.string}
	value_ {|val| view.string_(val)}

	prCreateView {|args|
		var fnt= Font(
			args.atFail(\fontName, {skin.fontSpecs.first}),
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
