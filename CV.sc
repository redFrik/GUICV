//redFrik

//related: AbstractGUICVView, SimpleController

CV {

	var <ref, <spec;
	var actions;

	*new {|ref, spec|
		var r= ref !? {ref.asRef};
		var cv= super.new.initCV(r, spec);
		if(r.value.isCollection, {^cv.as(CVArray)});
		if(r.value.isKindOf(Boolean), {^cv.as(CVBoolean)});
		//array of booleans not taken care of - maybe future improvement
		^cv
	}

	*newFrom {|cv|
		^super.new.initCV(cv.ref, cv.spec)
	}

	initCV {|argRef, argSpec|
		spec= argSpec.asSpec;
		ref= argRef ?? {Ref(spec.default)};
		ref.addDependant(this);
		actions= List.new;
	}

	default {^spec.default}

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
	value {^spec.constrain(ref.value)}

	action_ {|action|
		actions= List.new;
		this.addAction(action);
	}
	addAction {|action| actions.add(action)}
	removeAction {|action| actions.remove(action)}

	remove {ref.removeDependant(this)}

	update {
		actions.do{|action|
			action.value(this, ref.value);
		}
	}

	link {|other|
		ref.removeDependant(this);
		ref= other.ref;
		ref.addDependant(this);
	}
	unlink {
		ref.removeDependant(this);
		ref= Ref(ref.value);
		ref.addDependant(this);
	}

	asControlInput {^this.value.asControlInput}

	printOn {|stream|
		stream << this.class.name << "(" << ref.value << ")";
	}
	storeOn {|stream|
		stream << this.class.name << "(" << ref.value << ", " << spec << ")";
	}
}

CVArray : CV {

	default {^spec.default.dup(ref.value.size.max(1))}

	softSet {|val, within= 0.05|
		if((val-this.get).abs.every{|x| x<=within}, {
			this.set(val);
			^true
		});
		^false
	}
}

CVBoolean : CV {

	var <>thresh= 0.5;

	initCV {|argRef, argSpec|
		spec= ControlSpec(0, 1, 'lin', 1, (argRef.value ? false).asInteger);
		ref= argRef ?? {Ref(spec.default)};
		ref.addDependant(this);
		actions= List.new;
	}

	default {^spec.default.asBoolean}

	get {^this.value.asInteger}
	set {|val| ^this.value_(val>=thresh)}
	softSet {|val, within= 0.05|
		if(val<=within or:{val>=(1-within)}, {
			this.set(val);
			^true
		});
		^false
	}

	value_ {|bool| ref.value_(bool).changed(\value)}
	value {^ref.value}
}
