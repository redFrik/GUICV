//redFrik

AbstractGUICV : SCViewHolder {
	classvar <>skin;

	var <ref, <spec;
	var normalized= true;  //unmap or constrain spec. e.g. GUICVNumberBox

	*new {|ref, spec, args, update= true|
		^super.new.initAbstractGUICV(ref, spec, args ? (), update)
	}

	*initClass {
		Class.initClassTree(Font);
		GUI.skins.put(\guiCV, (
			background: Color.black,
			foreground: Color.new255(5, 62, 6),
			fontColor: Color.new255(94, 181, 94),
			hiliteColor: Color.new255(94, 249, 94),
			fontFactor: 1.5,
			fontSpecs: [Font.defaultMonoFace, 10],
			height: 74,
			minHeight: 30,
			minWidth: 20,
			width: 48,
			margins: 4,
			spacing: 4
		));
		skin= GUI.skins.guiCV;
	}

	initAbstractGUICV {|argRef, argSpec, args, update|
		ref= argRef ? Ref(0);
		spec= argSpec.asSpec;
		this.view_(this.prCreateView(args.asDict));
		this.prConnect;
		view.step= spec.step;
		if(update, {ref.changed(\value)});
	}

	get {
		^spec.unmap(this.value)
	}
	set {|val|
		^this.value_(spec.map(val))
	}
	softSet {|val, within= 0.05|
		if((val-this.get).abs<=within, {
			this.set(val);
			^true
		});
		^false
	}

	value_ {|val|
		ref.value_(spec.constrain(val)).changed(\value);
	}
	value {
		^ref.value
	}

	//--private

	prConnect {
		var lastVal;
		var controller= SimpleController(ref);
		if(normalized, {
			controller.put(\value, {|r|
				var val= spec.unmap(r.value);
				if(val!=lastVal, {view.value_(val); lastVal= val});
			});
			view.action_({|v| this.set(v.value); v.value= this.get});
		}, {
			controller.put(\value, {|r|
				var val= spec.constrain(r.value);
				if(val!=lastVal, {view.value_(val); lastVal= val});
			});
			view.action_({|v| this.value_(v.value); v.value= this.value});
		});

		view.onClose_({controller.remove});
	}

	prCreateView {|args| ^this.subclassResponsibility(thisMethod)}
}
