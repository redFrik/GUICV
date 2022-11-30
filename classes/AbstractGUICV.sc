//redFrik

AbstractGUICV : SCViewHolder {
	classvar <>skin;

	var <ref, <spec, normalized= true;

	*new {|ref, spec, args|
		^super.new.initAbstractGUICV(ref, spec, args?())
	}

	*initClass {
		if(\GUI.asClass.notNil, {
			Class.initClassTree(Font);
			GUI.skins.put(\guiCV, (
				background: Color.black,
				foreground: Color.new255(5, 62, 6),
				fontColor: Color.new255(94, 181, 94),//Color.new255(34, 121, 34),
				hiliteColor: Color.new255(94, 249, 94),//Color.new255(42, 249, 47),
				fontFactor: 1.5,
				fontSpecs: [Font.defaultMonoFace, 10],
				height: 74,
				width: 48,
				margins: 4,
				spacing: 4
			));
			skin= GUI.skins.guiCV;
		});
	}

	initAbstractGUICV {|argRef, argSpec, args|
		ref= argRef ? Ref(0);
		spec= argSpec.asSpec;
		this.view_(this.prCreateView(args.asDict));
		this.prConnect;
		view.step= spec.step;
		ref.changed(\value);
	}

	value_ {|val|
		if(normalized, {val= spec.unmap(val)});
		ref.value_(val).changed(\value);
	}
	value {
		^if(normalized, {spec.map(view.value)}, {view.value})
	}

	//--private

	prConnect {
		var lastVal;

		var controller= SimpleController(ref).put(\value, {|r|
			var val= spec.map(r.value);
			if(normalized, {val= spec.unmap(val)});
			if(val!=lastVal, {view.value_(lastVal= val)});
		});

		view.action_({|v|
			var val= v.value;
			if(normalized, {val= spec.map(val)});
			val= spec.unmap(val);
			v.value= if(normalized, {val}, {spec.constrain(v.value)});
			ref.value_(val).changed(\value);
		});

		view.onClose_({controller.remove});
	}

	prCreateView {|args| ^this.subclassResponsibility(thisMethod)}
}
