//redFrik

//related: GUICV

AbstractGUICV : SCViewHolder {

	var <ref, <spec;
	var normalized= true;  //unmap or constrain spec. e.g. GUICVNumberBox

	*new {|ref, spec, args, update= true, skin|
		^super.new.initAbstractGUICV(ref, spec, args ? (), update, skin ? GUI.skins.guiCV)
	}

	initAbstractGUICV {|argRef, argSpec, args, update, skin|
		ref= argRef ? Ref(0);
		spec= argSpec.asSpec;
		this.view_(this.prCreateView(args.asDict, skin));
		this.prConnect;
		this.step= spec.step;
		if(update, {ref.changed(\value)});
	}

	close {this.asView.close}
	front {this.asView.front}

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
			view.action_({|v| this.set(v.value)});
		}, {
			controller.put(\value, {|r|
				var val= spec.constrain(r.value);
				if(val!=lastVal, {view.value_(val); lastVal= val});
			});
			view.action_({|v| this.value_(v.value)});
		});

		view.onClose_({controller.remove});
	}

	prCreateView {|args| ^this.subclassResponsibility(thisMethod)}
}
