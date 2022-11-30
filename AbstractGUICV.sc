//redFrik

AbstractGUICV : SCViewHolder {
	classvar <>skin;

	var <ref, <spec;

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
		ref.changed(\value);
	}

	value_ {|val| ref.value_(val).changed(\value)}
	value {^this.map(view.value)}
	map {|val| ^spec.map(val)}
	unmap {|val| ^spec.unmap(val)}

	//--private

	prCreateView {|args| ^this.subclassResponsibility(thisMethod)}

	prConnect {
		var controller= SimpleController(ref).put(\value, {|ref|
			view.value= this.unmap(ref.value);
		});
		view.action_({|v| ref.value_(this.map(v.value)).changed(\value)});
		view.onClose_({controller.remove});
	}
}
