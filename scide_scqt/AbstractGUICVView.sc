//redFrik

//related: CV, GUICV

AbstractGUICVView : SCViewHolder {

	var <cv;
	var normalized= true;  //unmap or constrain spec. e.g. GUICVNumberBox
	var <>action;

	*new {|parent, bounds, ref, spec, update= true|
		^super.new.initAbstractGUICVView(parent, bounds, ref, spec, update)
	}

	ref {^cv.ref}
	spec {^cv.spec}

	initAbstractGUICVView {|parent, bounds, argRef, argSpec, update|
		var updateFunc;

		cv= CV(argRef, argSpec);
		view= this.prCreateView(parent, bounds);
		this.prDkey;
		this.prConnect;
		this.step= cv.spec.step;

		if(update, {
			updateFunc= {|view|  //run once
				cv.update;
				view.toFrontAction_(view.toFrontAction.removeFunc(updateFunc));
			};
			view.toFrontAction_(view.toFrontAction.addFunc(updateFunc));
		}, {
			view.value= if(normalized, {cv.get}, {cv.value});
		});
	}

	close {this.asView.close}
	front {this.asView.front}

	default {^cv.default}

	get {^cv.get}
	set {|val| cv.set(val)}
	softSet {|val, within= 0.05| ^cv.softSet(val, within)}

	value_ {|val| cv.value_(val)}
	value {^cv.value}

	update {cv.update}

	link {|widget| cv.link(widget.cv)}
	unlink {|widget| cv.unlink(widget.cv)}

	//--private

	prConnect {
		var lastVal;
		if(normalized, {
			cv.addAction({|cv, val|
				if(val!=lastVal, {view.value_(val); lastVal= val});
				this.action.value(this);
			});
			view.action_({|v| this.set(v.value)});
		}, {
			cv.addAction({|cv, val|
				if(val!=lastVal, {view.value_(val); lastVal= val});
				this.action.value(this);
			});
			view.action_({|v| this.value_(v.value)});
		});

		view.onClose_({cv.remove});
	}

	prCreateView {|parent, bounds|
		^this.subclassResponsibility(thisMethod)
	}

	prDkey {  //key D for spec default
		view.keyDownAction_({|v ...args|
			v.defaultKeyDownAction(*args);
			if(args[0]==$d, {this.value_(cv.default)});
		});
	}
}
