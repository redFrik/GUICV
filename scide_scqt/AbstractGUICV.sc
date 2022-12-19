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
			buttonHeight: 15,
			fontColor: Color.new255(94, 181, 94),
			fontSpecs: [Font.defaultMonoFace, 10],
			foreground: Color.new255(5, 62, 6),
			highlight: Color.new255(94, 249, 94),
			knobWidth: 45,
			margin: Point(4, 4),
			sliderHeight: 75,
			sliderWidth: 20,
			spacing: 4
		));
		skin= GUI.skins.guiCV;
		skin.palette= QPalette.auto(Color.clear, skin.foreground)
		.setColor(skin.foreground, \base)
		.setColor(skin.foreground, \button)
		.setColor(skin.fontColor, \buttonText)
		.setColor(skin.highlight, \highlight)
		.setColor(skin.background, \highlightText)
		.setColor(skin.foreground, \window)
		.setColor(skin.fontColor, \windowText);
	}

	initAbstractGUICV {|argRef, argSpec, args, update|
		ref= argRef ? Ref(0);
		spec= argSpec.asSpec;
		this.view_(this.prCreateView(args.asDict));
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
