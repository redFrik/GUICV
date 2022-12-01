GUICVSlider2 : GUICVSlider {

	var container;
	var userView, slider;

	orientation_ {|aSymbol| slider.orientation_(aSymbol)}

	asView {^container}

	prCreateView {|args|
		var lastVal;
		var controller;

		var fontSize= args.atFail(\fontSize, {skin.fontSpecs[1]*skin.fontFactor});
		var fnt= Font(skin.fontSpecs.first, fontSize);
		var str= args.atFail(\name, {this.class.name});
		var gap= args.atFail(\gap, {2});

		var sl= StackLayout().mode_(\stackAll);

		container= View().layout_(sl);

		userView= UserView()
		.acceptsMouse_(false)
		.drawFunc_({|usr|
			var v, valRect;
			var w= usr.bounds.width;
			var h= usr.bounds.height;
			var w2, h2;
			Pen.translate(w*0.5, h*0.5);
			if(slider.orientation.isNil, {
				slider.orientation= if(w<h, {\vertical}, {\horizontal});
			});
			if(slider.orientation==\vertical, {
				Pen.rotate(1.5pi);
				w= usr.bounds.height;
				h= usr.bounds.width;
			});
			v= w-slider.thumbSize-1.5*slider.value;
			w2= -0.5*w;
			h2= -0.5*h;
			valRect= Rect(w2+gap, h2+gap, v-(gap*2), h-(gap*2));
			if(valRect.width>0, {
				Pen.fillColor= skin.hiliteColor;
				Pen.fillRect(valRect);
			});
			Pen.stringCenteredIn(str, Rect(w2, h2, w, h), fnt, skin.fontColor);
		});

		slider= Slider()
		.palette_(QPalette.auto(Color.clear, skin.foreground))
		.background_(skin.foreground)
		.focusColor_(skin.hiliteColor)
		.knobColor_(skin.hiliteColor)
		.thumbSize_(0);

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
