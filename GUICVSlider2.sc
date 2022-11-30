GUICVSlider2 : GUICVSlider {

	var userView, slider;

	orientation_ {|aSymbol| slider.orientation_(aSymbol)}

	value {^this.map(slider.value)}

	prCreateView {|args|
		var sl= StackLayout().mode_(\stackAll);
		var view= View().layout_(sl);

		var fontSize= args.atFail(\fontSize, {skin.fontSpecs[1]*skin.fontFactor});
		var fnt= Font(skin.fontSpecs.first, fontSize);
		var str= args.atFail(\name, {this.class.name});
		var gap= args.atFail(\gap, {2});

		userView= UserView()
		.acceptsMouse_(false)
		.drawFunc_({|usr|
			var v, valRect;
			var w= usr.bounds.width;
			var h= usr.bounds.height;
			Pen.translate(w*0.5, h*0.5);
			if(slider.orientation==\vertical, {
				Pen.rotate(1.5pi);
				w= usr.bounds.height;
				h= usr.bounds.width;
			});
			v= w-slider.thumbSize-1.5*slider.value;
			valRect= Rect(-0.5*w+gap, -0.5*h+gap, v-(gap*2), h-(gap*2));
			if(valRect.width>0, {
				Pen.fillColor= skin.hiliteColor;
				Pen.fillRect(valRect);
			});
			Pen.stringCenteredIn(str, Rect.aboutPoint(Point(0, 0), w, h), fnt, skin.fontColor);
		});

		slider= Slider()
		.palette_(QPalette.auto(Color.clear, skin.foreground))
		.background_(skin.foreground)
		.focusColor_(skin.hiliteColor)
		.knobColor_(skin.hiliteColor)
		.thumbSize_(0);

		sl.add(userView);
		sl.add(slider);

		^view
	}

	prConnect {
		var lastValue;
		var controller= SimpleController(ref).put(\value, {|ref|
			slider.value= this.unmap(ref.value);
			if(slider.value!=lastValue, {
				userView.refresh;
				lastValue= slider.value;
			});
		});
		slider.action_({|v| ref.value_(this.map(v.value)).changed(\value)});
		view.onClose_({controller.remove});
	}
}
