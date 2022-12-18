GUICVSliderLabel : GUICVSlider {

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
			var w= usr.bounds.width;
			var h= usr.bounds.height;
			Pen.translate(w*0.5, h*0.5);
			if(slider.orientation==\vertical, {
				Pen.rotate(rot);
				w= usr.bounds.height;
				h= usr.bounds.width;
			});
			if(slider.value>0, {
				Pen.fillColor= skin.hiliteColor;
				Pen.fillRect(Rect(-0.5*w+gap, -0.5*h+gap, slider.value*(w-(gap*2)), h-(gap*2)));
			});
			Pen.stringCenteredIn(str, Rect(-0.5*w, -0.5*h, w, h), fnt, skin.fontColor);
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
