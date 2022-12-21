//redFrik

//related: GUICV

AbstractGUICV : SCViewHolder {

	var <ref, <spec;
	var normalized= true;  //unmap or constrain spec. e.g. GUICVNumberBox

	*new {|parent, bounds, ref, spec, args, update= true|
		^super.new.initAbstractGUICV(parent, bounds, ref, spec, args, update)
	}

	initAbstractGUICV {|parent, bounds, argRef, argSpec, args, update|
		ref= argRef ? Ref(0);
		spec= argSpec.asSpec;
		args= (args ? ()).asDict;
		view= this.prCreateView(parent, bounds, args);
		this.prDkey;
		this.prConnect;
		this.step= spec.step;
		if(update, {ref.changed(\value)});
	}

	close {this.asView.close}
	front {this.asView.front}

	get {^spec.unmap(this.value)}
	set {|val| ^this.value_(spec.map(val))}
	softSet {|val, within= 0.05|
		if((val-this.get).abs<=within, {
			this.set(val);
			^true
		});
		^false
	}

	value_ {|val| ref.value_(spec.constrain(val)).changed(\value)}
	value {^ref.value}

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

	prCreateView {|parent, bounds, args|
		^this.subclassResponsibility(thisMethod)
	}

	prDkey {  //key D for spec default
		view.keyDownAction_({|v ...args|
			v.defaultKeyDownAction(*args);
			if(args[0]==$d, {this.value_(spec.default)});
		});
	}
}
