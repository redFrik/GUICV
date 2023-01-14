GUICVSliderLabel : GUICVSlider {

	var <>fillColor;
	var <>fillGap= 4;
	var <>font;
	var <>string= "";
	var <>stringColor;
	var <>stringRotation= 0;

	var container;

	asView {^container}
	refresh {container.refresh}

	prCreateView {|parent, bounds|
		var skin= GUI.skins.guiCV;
		var lastVal, refresher;

		var sl= StackLayout().mode_(\stackAll);

		var slider= super.prCreateView(parent, bounds).knobColor_(Color.clear);

		var userView= UserView(parent, bounds)
		.acceptsMouse_(false)
		.canFocus_(false)
		.drawFunc_({|usr|
			var w= usr.bounds.width;
			var h= usr.bounds.height;
			var val;
			Pen.translate(w*0.5, h*0.5);
			if(slider.orientation==\vertical, {
				Pen.rotate(1.5pi);
				w= usr.bounds.height;
				h= usr.bounds.width;
			});
			if(slider.value>0, {
				val= slider.value*(w-(fillGap*2));
				Pen.fillColor= fillColor;
				Pen.fillRect(Rect(-0.5*w+fillGap, -0.5*h+fillGap, val, h-(fillGap*2)));
			});
			if(stringRotation!=0, {
				Pen.rotate(stringRotation);
			});
			Pen.stringCenteredIn(string, Rect(-0.5*w, -0.5*h, w, h), font, stringColor);
		});

		sl.add(userView);
		sl.add(slider);
		container= View(parent, bounds).layout_(sl)
		.background_(skin.background)
		.resizeToBounds(bounds ?? {Size(skin.sliderWidth, skin.sliderHeight)});

		fillColor= skin.highlight;
		font= Font(*skin.fontSpecs);
		stringColor= skin.fontColor;

		refresher= {|cv, val|
			if(val!=lastVal, {
				userView.refresh;
				lastVal= val;
			});
		};
		cv.addAction(refresher);
		userView.onClose_({cv.removeAction(refresher)});

		^slider
	}
}
