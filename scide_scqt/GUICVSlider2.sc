GUICVSlider2 : GUICVSlider {

	var container;
	var userView, slider;

	asView {^container}

	prCreateView {|args|
		var fnt= Font(
			args.atFail(\fontName, {skin.fontSpecs.first}),
			args.atFail(\fontSize, {skin.fontSpecs[1]})
		);
		var gap= args.atFail(\fillGap, {2});
		var rot= args.atFail(\stringRotation, {1.5pi});
		var str= args.atFail(\string, {this.class.name});

		var lastVal;
		var controller;

		var sl= StackLayout().mode_(\stackAll);
		container= View().layout_(sl);

		slider= super.prCreateView(args);

		userView= UserView()
		.acceptsMouse_(false)
		.canFocus_(false)
		.drawFunc_({|usr|
			var v, valRect;
			var w= usr.bounds.width;
			var h= usr.bounds.height;
			var w2, h2;
			Pen.translate(w*0.5, h*0.5);
			if(slider.orientation==\vertical, {
				Pen.rotate(rot);
				w= usr.bounds.height;
				h= usr.bounds.width;
			});
			v= w*slider.value;
			w2= -0.5*w;
			h2= -0.5*h;
			valRect= Rect(w2+gap, h2+gap, v.linlin(0, w, 0, w-(gap*2)), h-(gap*2));
			if(v>0, {
				Pen.fillColor= skin.hiliteColor;
				Pen.fillRect(valRect);
			});
			Pen.stringCenteredIn(str, Rect(w2, h2, w, h), fnt, skin.fontColor);
		});

		sl.add(userView);
		sl.add(slider);

		controller= SimpleController(ref).put(\value, {|r|
			if(r.value!=lastVal, {
				userView.refresh;
				lastVal= r.value;
			});
		});
		userView.onClose_({controller.remove});

		^slider
	}
}
